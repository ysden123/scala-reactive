/*
 * Copyright (c) 2018. Yuriy Stul
 */

package com.stulsoft.scala.reactive.stream

import akka.{Done, NotUsed}
import akka.actor.ActorSystem
import akka.stream.ActorMaterializer
import akka.stream.scaladsl.{Flow, RunnableGraph, Sink, Source}

import scala.concurrent.Future

/** Akka Streams HelloWorld example with the Flow component with type annotations
  *
  * @author Yuriy Stul
  */
object HelloWorldAppWithTypeAnnotation extends App {
  implicit val actorSystem: ActorSystem = ActorSystem("HelloWorldSystem")
  implicit val materializer: ActorMaterializer = ActorMaterializer()

  val helloWorldSource: Source[String, NotUsed] = Source.single("Akka Streams Hello World")

  val helloWorldSink: Sink[String, Future[Done]] = Sink.foreach(print)

  val helloWorldFlow: Flow[String, String, NotUsed] = Flow[String].map(str => str.toUpperCase)

  // Source --> Flow --> Sink
  val helloWorldGraph: RunnableGraph[NotUsed] = helloWorldSource.via(helloWorldFlow).to(helloWorldSink)

  helloWorldGraph.run

  actorSystem.terminate
}
