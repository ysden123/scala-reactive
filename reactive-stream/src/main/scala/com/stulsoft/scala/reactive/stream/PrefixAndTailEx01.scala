/*
 * Copyright (c) 2018. Yuriy Stul
 */

package com.stulsoft.scala.reactive.stream

import akka.actor.ActorSystem
import akka.stream.ActorMaterializer
import akka.stream.scaladsl.{Flow, Sink, Source}
import com.typesafe.scalalogging.LazyLogging

/** Use of prefixAndTail
  *
  * @author Yuriy Stul
  */
object PrefixAndTailEx01 extends App with LazyLogging {
  implicit val actorSystem: ActorSystem = ActorSystem("PrefixAndTailEx01")
  implicit val materializer: ActorMaterializer = ActorMaterializer()

  test(0)
  Thread.sleep(1000)
  test(1)


  actorSystem.terminate

  def test(skipN: Int): Unit = {
    logger.info(s"==>test. skipN = $skipN")
    val numbersSource = Source(1 to 10)
      .prefixAndTail(skipN).flatMapConcat { case (_, tail) => tail }

    val sampleSink = Sink.foreach((n: Int) => logger.info(n.toString))

    val numberFlow = Flow[Int].map(num => num + 1)

    val numberRunnableGraph =
      numbersSource
        .via(numberFlow)
        .to(sampleSink)

    numberRunnableGraph.run
    logger.info("<==test")
  }
}
