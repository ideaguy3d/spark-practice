object PracticeOne {
	
	def spark_practice_1(): Unit = {
		/*
		  ran from the CLI:
		  $ spark-shell
		  
		  20/12/05 08:33:16 WARN NativeCodeLoader: Unable to load native-hadoop library for your platform... using builtin-java classes where applicable
		  Using Spark's default log4j profile: org/apache/spark/log4j-defaults.properties
		  Setting default log level to "WARN".
		  To adjust logging level use sc.setLogLevel(newLevel). For SparkR, use setLogLevel(newLevel).
		  Spark context Web UI available at http://juliusx.mshome.net:4040
		  Spark context available as 'sc' (master = local[*], app id = local-1607186000874).
		  Spark session available as 'spark'.
		  Welcome to
				____              __
			   / __/__  ___ _____/ /__
			  _\ \/ _ \/ _ `/ __/  '_/
			 /___/ .__/\_,_/_/ /_/\_\   version 2.4.7
				/_/
	
		  Using Scala version 2.11.12 (Java HotSpot(TM) 64-Bit Server VM, Java 1.8.0_251)
		  Type in expressions to have them evaluated.
		  Type :help for more information.
		*/
		val textFile = sc.textFile("file:///spark/readme.md")
		println(textFile.first) // res5: String = # Apache Spark
		val tokenizedFileData = textFile.flatMap(_.split(" "))
		
		//  MAP  \\
		val countPrep = tokenizedFileData.map((_, 1))
		// REDUCE \\
		val counts = countPrep.reduceByKey((accumVal, newVal) => accumVal + newVal)
		//  SORT  \\
		val sortedCounts = counts.sortBy(_._2, false)
		// save the hadoop distributed results to disk
		sortedCounts.saveAsTextFile("file:///jdata/SparkReadmeWordCount")
		
		// the MapReduce can be combined with
		tokenizedFileData.countByValue /* CLI OUTPUT:
      res4: scala.collection.Map[String,Long] = Map(site, -> 1, Please -> 4, Contributing -> 1, GraphX -> 1, project. -> 1,
      "" -> 72, for -> 12, find -> 1, Apache -> 1, package -> 1, Hadoop, -> 2, review -> 1, Once -> 1, For -> 3, name -> 1,
      this -> 1, protocols -> 1, Hive -> 2, in -> 5, "local[N]" -> 1, MASTER=spark://host:7077 -> 1, have -> 1, your -> 1,
      are -> 1, is -> 7, HDFS -> 1, Data. -> 1, built -> 1, thread, -> 1, examples -> 2, developing -> 1, using -> 3,
      system -> 1, Shell -> 2, mesos:// -> 1, easiest -> 1, This -> 2, [Apache -> 1, N -> 1, integration -> 1, <class> -> 1,
      different -> 1, "local" -> 1, README -> 1, YARN"](http://spark.apache.org/docs/latest/building-spark.html#specifying-the-hadoop-version-and-enabling-yarn) -> 1,
      online -> 1, spark:// -> 1, return -> 2, Note -> 1, ...
    */
	}
	
}
