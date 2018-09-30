package com.satyapal.dispatcherExmple

import akka.actor.{Actor, ActorLogging, ActorSystem, Props}
import akka.pattern._
import akka.util.Timeout

import scala.concurrent.duration.DurationInt
import scala.concurrent.ExecutionContext.Implicits.global

case class Count(line: String)
case class Print(line: String)

class WordCountActor extends Actor with ActorLogging {

  def receive = {
    case Count(line: String) => {
      log.debug("WordCountActor called")
      log.debug("Thread name for counter actor::::::::::::::: " + Thread.currentThread().getName)
      sender ! line.length()
      val printer = context.actorOf(Props[PrinterActor].withDispatcher("my-thread-pool-dispatcher"), "printerActor")
      val actorpath = printer.path
      log.debug("PrinterActor path ::::: " + actorpath)
      printer ! Print(line)
    }
    case _ => log.debug("Oops..!! I did'nt understand the message..!!")
  }

}

class PrinterActor extends Actor with ActorLogging {

  def receive ={
    case Print(file: String) => {
      log.debug("PrinterActor called")
      log.debug("Thread name for printer actor::::::::::::::: " + Thread.currentThread().getName)
      val linesToPrint = file.split(" ").take(300).mkString(" ")
      log.debug("First 300 words from the file you entered is :::: " + linesToPrint)
    }
    case _ => log.debug("Oops..!! I did'nt understand the message..!!")
  }

}

object DispatcherExampleMain extends App {

  System.out.println("Hello, You are in Dispatcher example")
  System.out.println(2000)
  System.out.println("You are going to see how to use dispatcher here")
  Thread.sleep(2000)

  implicit val timeOut = Timeout(10 seconds)

  //Defining actor system here
  val system = ActorSystem("wordprocessor")

  //getting data from text file, Change th name of the file according to your system
  val file = scala.io.Source.fromFile("/home/satyapal/Desktop/play-java8/learning-tutorial/modules/learning-service/SampleFile/fileExample").getLines.mkString

  /**
    * First way to use dispatchers with conf file
    */
  val wordCounterActor = system.actorOf(Props[WordCountActor], "wordCounter")

  val actorpath = wordCounterActor.path

  System.out.println("WordCountActor path :::::::::::: " + actorpath)

  val res = wordCounterActor ? Count(file)

  res.map { count =>
    System.out.println("TOTAL CHARACTERS COUNT IN FILE ::::: " + count.asInstanceOf[Int])
    Thread.sleep(2000)
  }
}
