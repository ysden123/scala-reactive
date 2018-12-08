/*
 * Copyright (c) 2018. Yuriy Stul
 */

package com.stulsoft.scala.reactive.stream

import akka.actor.ActorSystem
import akka.stream.ActorMaterializer
import akka.stream.scaladsl.{Flow, Sink, Source}
import com.typesafe.scalalogging.LazyLogging

/** Use of fold
  *
  * @author Yuriy Stul
  */
object AggregatorEx01 extends App with LazyLogging {
  implicit val actorSystem: ActorSystem = ActorSystem("NumberSystem")
  implicit val materializer: ActorMaterializer = ActorMaterializer()

  val numbersSource = Source(1 to 10)

  val sampleSink = Sink.foreach((n: Int) => logger.info(n.toString))

  val numberFlow = Flow[Int].fold(0)((a,n)=> a + n)

  val numberRunnableGraph =
    numbersSource.via(numberFlow).to(sampleSink)

  numberRunnableGraph.run

  actorSystem.terminate
}
