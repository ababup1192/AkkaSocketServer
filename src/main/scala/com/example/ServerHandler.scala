package com.example

import akka.actor.{Actor, ActorLogging, Props}
import akka.util.ByteString

object ServerHandler {
  def props = Props(new ServerHandler)
}

class ServerHandler extends Actor with ActorLogging {

  import akka.io.Tcp._

  def receive = {
    case Received(data) =>
      log.info("Received: " + data.decodeString("UTF-8"))
      // 大文字にして返す
      sender() ! Write(ByteString(data.decodeString("UTF-8").toUpperCase))
    case PeerClosed => context stop self
  }
}
