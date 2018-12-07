/*
 * Copyright (c) 2018. Yuriy Stul
 */

package com.stulsoft.scala.reactive

import com.typesafe.scalalogging.LazyLogging
import rx.lang.scala.Observable

/**
  * @author Yuriy Stul
  */
object RxScalaHelloWorld extends App with LazyLogging {
  val helloWorld: String = "Hello RxScala World!"

  test1()
  test2()
  test3()

  def test1(): Unit = {
    println("==>test1")
    val observable = Observable.from(helloWorld)
    observable.subscribe { msg =>
      println(s"$msg")
    }
    println("<==test1")
  }

  def test2(): Unit = {
    println("==>test2")
    val observable = Observable.from(helloWorld)
    observable.subscribe { msg =>
      print(s"$msg")
    }
    println("")
    println("<==test2")
  }

  def test3(): Unit = {
    println("==>test3")
    val observable = Observable.from(helloWorld)
    observable
      .toList
      .flatMap(listOfChars => Observable.just(listOfChars.mkString))
      .subscribe { msg =>
        println(s"$msg")
      }
    println("<==test3")
  }
}
