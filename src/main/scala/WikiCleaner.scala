package main

import org.apache.spark.rdd.RDD
import scala.xml.XML
import org.apache.hadoop.io.Text

// https://github.com/joaomsa/txtwiki.js
case class TempParsed(text: Option[String], pos: Int)

object WikiCleaner {
	
	def parse(contentVal: String): String = {
		var content = contentVal;
		var parsed = ""
		
		content = stripWhitespace(content)
		
		parsed
	}
	
	def stripWhitespace(contentVal: String): String = {
		var parsed = ""
		val content = contentVal
		content.replaceAll(" +", " ").split("\n").foreach(block =>
			if (block.matches("^\\s*$")) parsed += "\n\n"
			else if (block.matches("^==+.+==+$")) parsed += block + "\n"
			else parsed += block
		)
		parsed = parsed.replaceAll("\\n\\n", "\n\n").replaceAll("(^\\n*|\\n*$)", "")
		parsed.trim
	}
	
	def firstPass(content: String): String = {
		var parsed = ""
		var pos = 0
		while (pos < content.size) {
			var out: Option[TempParsed] = None
			
		}
		parsed
	}
	
	def parseSimpleTag(content: String, posVal: Int, start: String, end: String): Option[TempParsed] = {
		var pos = posVal
		if(content.slice(pos, pos + start.length) == start) {
			pos = pos + start.length
			var posEnd = content.indexOf(end, pos)
			if(posEnd == -1) posEnd = content.length
			Some(TempParsed(Option(content.slice(pos, posEnd)), posEnd + end.length))
		}
		else None
	}
	
	def secondPass(content: String) = {
	
	}
	
}
