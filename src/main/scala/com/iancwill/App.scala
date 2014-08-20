package com.iancwill

import edu.cmu.sphinx.api.{StreamSpeechRecognizer, Configuration}
import edu.cmu.sphinx.result.WordResult
import scala.collection.JavaConversions._
import java.io.FileInputStream

/**
 * @author ${user.name}
 */
object App {

  def config() = {
    val conf = new Configuration()
    conf.setAcousticModelPath("resource:/edu/cmu/sphinx/models/acoustic/wsj")
    // You can also load model from folder
    // configuration.setAcousticModelPath("file:en-us");

    conf.setDictionaryPath("resource:/edu/cmu/sphinx/models/acoustic/wsj/dict/cmudict.0.6d")
    conf.setLanguageModelPath("resource:/edu/cmu/sphinx/models/language/en-us.lm.dmp")
    conf
  }

  def main(args : Array[String]) {
    if( args.length != 1 ){
      println("Usage: " + this.getClass().getSimpleName)
      return
    }

    println("Trying to transcribe " +args(0))
    val rec = new StreamSpeechRecognizer(config())
    val stream = new FileInputStream(args(0))
    rec.startRecognition(stream)
    var result = rec.getResult()
    while (result != null) {
      println("Hypothesis: " + result.getHypothesis())
      println("List of recognized words and their times:")
      result.getWords().toList.foreach{ word => println(word) }
      result = rec.getResult()
    }
    rec.stopRecognition()
  }

}
