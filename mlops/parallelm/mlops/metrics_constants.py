from enum import Enum


class ClassificationMetrics(Enum):
    """
    Class will hold predefined naming of all classification ML Metrics supported by ParallelM.
    """
    ACCURACY_SCORE = "Accuracy Score"
    AUC = "AUC(Area Under the Curve)"
    AVERAGE_PRECISION_SCORE = "Average Precision Score"
    CONFUSION_MATRIX = "Confusion Matrix"