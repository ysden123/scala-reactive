/*
 * Copyright (c) 2018. Yuriy Stul
 */

package com.stulsoft.scala.reactive.stream

import akka.NotUsed
import akka.actor.ActorSystem
import akka.stream.scaladsl.{Flow, GraphDSL, RunnableGraph, Sink, Source}
import akka.stream.{ActorMaterializer, ClosedShape}
import com.typesafe.scalalogging.LazyLogging

/** Example of use the edge operator (~>)
  *
  * @author Yuriy Stul
  */
object NumberSystemWithEdgeOperator extends App with LazyLogging {
  implicit val actorSystem: ActorSystem = ActorSystem("NumberSystemWithEdgeOperator")
  implicit val materializer: ActorMaterializer = ActorMaterializer()

  val numbersSource = Source(1 to 10)

  val sampleSink = Sink.foreach((n: Int) => logger.info(n.toString))

  val numberFlow = Flow[Int].map(num => num + 1)

  val numberRunnableGraph = RunnableGraph.fromGraph(GraphDSL.create() {
    implicit builder: GraphDSL.Builder[NotUsed] =>
      import akka.stream.scaladsl.GraphDSL.Implicits._
      numbersSource ~> numberFlow ~> sampleSink
      ClosedShape
  })

  numberRunnableGraph.run

  actorSystem.terminate
}
