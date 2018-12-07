/*
 * Copyright (c) 2018. Yuriy Stul
 */

package com.stulsoft.scala.reactive.stream

import akka.actor.ActorSystem
import akka.stream.ActorMaterializer
import akka.stream.scaladsl.{Flow, Sink, Source}

/** Akka Streams HelloWorld example with the Flow component
  *
  * @author Yuriy Stul
  */
object HelloWorldApp extends App {
  implicit val actorSystem: ActorSystem = ActorSystem("HelloWorldSystem")
  implicit val materializer: ActorMaterializer = ActorMaterializer()

  val helloWorldSource = Source.single("Akka Streams Hello World")

  val helloWorldSink = Sink.foreach(print)

  val helloWorldFlow = Flow[String].map(str => str.toUpperCase)

  // Source --> Flow --> Sink
  val helloWorldGraph = helloWorldSource.via(helloWorldFlow).to(helloWorldSink)

  helloWorldGraph.run

  actorSystem.terminate
}
