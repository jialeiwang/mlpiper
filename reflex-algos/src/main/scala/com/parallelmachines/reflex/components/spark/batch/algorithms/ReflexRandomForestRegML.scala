package com.parallelmachines.reflex.components.spark.batch.algorithms

import com.parallelmachines.reflex.components.spark.batch.algorithms.MlMethod.MlMethodType
import com.parallelmachines.reflex.components.{ComponentAttribute, FeaturesColComponentAttribute, LabelColComponentAttribute, PredictionColComponentAttribute}
import org.apache.spark.ml.PipelineStage
import org.apache.spark.ml.regression.RandomForestRegressor
import com.parallelmachines.reflex.common.constants.McenterTags


/** This component receives as an input mllib RDD. Then it is convered to a dataframe with
  * a label and a feature vector of spark ML. Then the vector slicer translates it into feature
  * columns.
  * This component should be split in the future to a spark ML component
  * that receives directly a dataframe and a connector component that converts
  * the RDD into the correct dataframe. Thsi component will be generally used for
  * spark ML components.
  * The output of the model is sparkml format
  */

class ReflexRandomForestRegML extends ReflexSparkMLAlgoBase {
  override val label: String = "Random Forest Regression Training"
  override val description: String = "Random Forest Regression Training"
  override val version: String = "1.0.0"
  addTags(McenterTags.explainable)

  val rfr = new RandomForestRegressor()

  val tempSharedPath = ComponentAttribute("tempSharedPath", "",
    "temp Shared Path", "Temporary shared path for model transfer, " +
      "paths with prefix file:// or hdfs://", optional = true)
  val significantFeaturesNumber = ComponentAttribute("significantFeaturesNumber", 0,
    "significant Features Number", "Number of significant features in Feature Importance vector. " +
      "0 indicates not presenting feature importance.. (>= 0) (Default: 0)",
    optional = true).setValidator(x => x >= 0)
  val maxDepth = ComponentAttribute("maxDepth", 5, "Maximum Tree Depth", "Maximum depth of the tree. (>= 0)" +
    " E.g., depth 0 means 1 leaf node; depth 1 means 1 internal node + 2 leaf nodes." +
    "(Default: 5)", optional = true).setValidator(x => x >= 0)
  val minInstancesPerNode = ComponentAttribute("minInstancesPerNode", 1, "Minimum Instances Per Node",
    "Minimum number of instances each child must have after split. If a split causes the left or" +
      " right child to have fewer than minInstancesPerNode, the split will be discarded as invalid." +
      " Should be >= 1. (default: 1)", optional = true).setValidator(x => x >= 1)
  val minInfoGain = ComponentAttribute("minInfoGain", 0.0, "Minimum Information Gain", "Minimum information gain" +
    " for a split to be considered at a tree node.  Should be >= 0.0. (Default: 0.0)", optional = true)
    .setValidator(x => x >= 0.0)
  val maxMemoryInMB = ComponentAttribute("maxMemoryInMB", 256, "Maximum Memory In MB", "" +
    "Maximum memory in MB allocated to histogram aggregation.  Should be >= 256MB." +
    "(Default: 256)", optional = true).setValidator(x => x >= 256)
  val cacheNodeIds = ComponentAttribute("cacheNodeIds", false, "Cache Node Ids", "If false, the " +
    "algorithm will pass trees to executors to match instances with nodes. If true, " +
    "the algorithm will cache node IDs for each instance. Caching can speed up training of " +
    "deeper trees. (Default: false)", optional = true)
  val checkpointInterval = ComponentAttribute("checkpointInterval", 10, "Checkpoint Interval",
    "Specifies how often to checkpoint the cached node IDs. E.g. 10 means that the cache will" +
      " get checkpointed every 10 iterations. This is only used if cacheNodeIds is true and " +
      "if the checkpoint directory is set in [[org.apache.spark.SparkContext]]. Must be at" +
      " least 1. (default: 10)", optional = true).setValidator(x => x >= 1)
  val seed = ComponentAttribute("seed", "Random", "Seed", "random seed (A number). (Default: Random) ", optional = true)
  val maxBins = ComponentAttribute("maxBins", 32, "Maximum number of Bins", "Maximum number of bins used for" +
    " splitting features (Default: 32). Must be >=2", optional = true).setValidator(x => x >= 2)
  val subsamplingRate = ComponentAttribute("subsamplingRate", 1.0, "Subsampling Rate", "Fraction of" +
    " the training data used for learning each decision tree, in range (0.0, 1.0]." +
    " (Default: 1.0)", optional = true).setValidator(x => (x > 0.0) & (x <= 1.0))
  val numTrees = ComponentAttribute("num-trees", 20, "Number of Trees", "Number of Trees." +
    "(>= 1) (Default: 20)", optional = true).setValidator(x => x >= 1)
  val featureSubsetStrategy = ComponentAttribute("featureSubsetStrategy", "auto", "feature Subset Strategy",
    "Number of features to consider for splits at each node." +
      "  Supported values: :auto (Default):, :all:, :sqrt:, :log2:, :onethird:.  If :auto: is" +
      " set, this parameter is set based on numTrees: if numTrees == 1, set to :all:; " +
      " if numTrees is greater than 1 (forest) set to :sqrt:.", optional = true)
  featureSubsetStrategy.setOptions(List[(String, String)](("auto", "auto"), ("all", "all"),
    ("sqrt", "sqrt"), ("log2", "log2"), ("onethird", "onethird")))

  // Currently excluded from parameters
  /*
  val categorialFeatureInfo = ComponentAttribute("categorialFeatureInfo", Map.empty[Int, Int],
    "categorialFeatureInfo", "Map storing varity of categorical features. An entry (n to k) " +
      " indicates that feature n is categorical with k categories indexed" +
      " from 0: {0, 1, ..., k-1}.")
      */

  val labelCol = LabelColComponentAttribute()
  val featuresCol = FeaturesColComponentAttribute()
  val predictionCol = PredictionColComponentAttribute()


  attrPack.add(tempSharedPath, significantFeaturesNumber, numTrees, maxDepth, maxBins, cacheNodeIds, checkpointInterval,
    featureSubsetStrategy, labelCol, featuresCol, maxMemoryInMB, minInfoGain,
    minInstancesPerNode, seed, subsamplingRate, predictionCol)

  override val mlType: MlMethodType = MlMethod.Regression

  override def getLabelColumnName: Option[String] = Some(labelCol.value)

  override def configure(paramMap: Map[String, Any]): Unit = {
    super.configure(paramMap)

    if (paramMap.contains(tempSharedPath.key)) {
      this.tempSharedPathStr = tempSharedPath.value
    }
    if (paramMap.contains(significantFeaturesNumber.key)) {
      this.significantFeatures = significantFeaturesNumber.value
    }
    if (paramMap.contains(maxDepth.key)) {
      rfr.setMaxDepth(maxDepth.value)
    }
    if (paramMap.contains(maxBins.key)) {
      rfr.setMaxBins(maxBins.value)
    }
    if (paramMap.contains(minInstancesPerNode.key)) {
      rfr.setMinInstancesPerNode(minInstancesPerNode.value)
    }
    if (paramMap.contains(minInfoGain.key)) {
      rfr.setMinInfoGain(minInfoGain.value)
    }
    if (paramMap.contains(maxMemoryInMB.key)) {
      rfr.setMaxMemoryInMB(maxMemoryInMB.value)
    }
    if (paramMap.contains(cacheNodeIds.key)) {
      rfr.setCacheNodeIds(cacheNodeIds.value)
    }
    if (paramMap.contains(checkpointInterval.key)) {
      rfr.setCheckpointInterval(checkpointInterval.value)
    }
    if (paramMap.contains(seed.key)) {
      if (seed.value != "Random"){
        rfr.setSeed(seed.value.toLong)
      }
    }
    if (paramMap.contains(subsamplingRate.key)) {
      rfr.setSubsamplingRate(subsamplingRate.value)
    }
    if (paramMap.contains(numTrees.key)) {
      rfr.setNumTrees(numTrees.value)
    }
    if (paramMap.contains(featureSubsetStrategy.key)) {
      rfr.setFeatureSubsetStrategy(featureSubsetStrategy.value)
    }
    if (paramMap.contains(featuresCol.key)) {
      rfr.setFeaturesCol(featuresCol.value)
    }
    if (paramMap.contains(labelCol.key)) {
      rfr.setLabelCol(labelCol.value)
    }
    if (paramMap.contains(predictionCol.key)) {
      rfr.setPredictionCol(predictionCol.value)
    }

  }



  override def getAlgoStage(): PipelineStage = {
    this.featuresColName = featuresCol.value
    this.supportFeatureImportance = true

    rfr
  }

}