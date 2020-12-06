package main

import org.apache.spark.SparkContext
import org.apache.spark.SparkConf

object WordCounter {
  def main(args: Array[String]) {
    var saveToText = false

    val conf = new SparkConf().setAppName("Word Counter")
    val sc = new SparkContext(conf)
    val textFile = sc.textFile("file:///Spark/README.md")
    val tokenizedFileData = textFile.flatMap(line=>line.split(" "))
    val countPrep = tokenizedFileData.map(word=>(word, 1))
    val counts = countPrep.reduceByKey((accumValue, newValue)=>accumValue + newValue)
    val sortedCounts = counts.sortBy(kvPair=>kvPair._2, ascending = false)

    if(saveToText) {
      sortedCounts.saveAsTextFile("file:///jdata/ReadMeWordCountViaApp")
    }

    load_data_practice(sc)
  }

  def load_data_practice(sc: SparkContext): Unit = {
    // .parallelize()
    val parallel = sc.parallelize(1 to 100)
    println(parallel)
  }
}