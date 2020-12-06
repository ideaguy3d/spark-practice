package main

import org.apache.spark.SparkContext
import org.apache.spark.SparkConf
import org.apache.hadoop.io._

object WordCounter {
  def main(args: Array[String]) {
    var saveToText = false

    val conf = new SparkConf().setAppName("Word Counter")
    val sc = new SparkContext(conf)
    val textFile = sc.textFile("file:///Spark/README.md")
    val tokenizedFileData = textFile.flatMap(line => line.split(" "))
    val countPrep = tokenizedFileData.map(word => (word, 1))
    val counts = countPrep.reduceByKey((accumValue, newValue) => accumValue + newValue)
    val sortedCounts = counts.sortBy(kvPair => kvPair._2, ascending = false)

    if (saveToText) {
      sortedCounts.saveAsTextFile("file:///jdata/ReadMeWordCountViaApp")
    }

    saveToText = true

    if (saveToText) {
      load_data_practice(sc)
    }
  }

  def load_data_practice(sc: SparkContext): Unit = {
    val parallel = sc.parallelize(1 to 100)
    val parallelCollect = parallel.collect
    val rdd = sc.makeRDD(List(1, 2, 3))
    val range = sc.range(1, 10).collect
    // not sure if if "file:///" is needed
    val wholeFile = sc.wholeTextFiles("file:///d:/data/accounting.csv")

    // make sure to "import org.apache.hadoop.io._"
    var sequenceFile = sc.sequenceFile(
      path = "file:///jdata/data/accounting.csv",
      classOf[Text], // key type, org.apache.hadoop.io.Writable
      classOf[IntWritable] // value type
    )
    val sequenceFileMap = sequenceFile.map(kv => (kv._1.toString, kv._2.get)).collect
    // or
    //TODO: figure out how to pass in the minPartitions arg correctly
    //sequenceFile = sc.sequenceFile[String, Int](path = "file:///jdata/data/accounting.csv", minPartitions = 5).collect

    println("\n", parallel, "\n")
  }
}