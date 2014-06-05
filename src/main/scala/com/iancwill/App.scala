package com.iancwill

import edu.cmu.sphinx.api.StreamSpeechRecognizer
import edu.cmu.sphinx.api.Configuration

/**
 * @author ${user.name}
 */
object App {
  
  def foo(x : Array[String]) = x.foldLeft("")((a,b) => a + b)

  def config() {
    Configuration config = new Configuration()
    return config
  }
  
  def main(args : Array[String]) {
    StreamSpeechRecognizer rec = new StreamSpeechRecognizer(config())
    println( "Hello World!" )
    println("concat arguments = " + foo(args))
  }

}
