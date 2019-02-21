/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.flink.streaming.scala.examples.common.parameters.performance

import org.apache.flink.streaming.scala.examples.common.parameters.common.PositiveLongParameter
import org.apache.flink.streaming.scala.examples.common.parameters.tools.WithArgumentParameters


case object PrintInterval extends PositiveLongParameter {
  override val key = "printInterval"
  override val label: String = "Print Interval"
  override val defaultValue = Some(10000L)
  override val required = false
  override val description = "Number of samples to process per task slot before a metric/statistic is output from the pipeline. (Default: 10000)"
}

trait PrintInterval[Self] extends WithArgumentParameters {

  that: Self =>

  def setPrintInterval(interval: Long): Self = {
    this.parameters.add(PrintInterval, interval)
    that
  }
}
