package com.example

import java.net.InetSocketAddress

import akka.actor.{Actor, ActorLogging, Props}
import akka.io.{IO, Tcp}

object Server {
  def props: Props = Props(new Server)
}

class Server extends Actor with ActorLogging {

  import akka.io.Tcp._
  import context.system

  IO(Tcp) ! Bind(self, new InetSocketAddress("localhost", 9000))

  def receive = {
    case b@Bound(address) =>
      // do some logging or setup ...
      log.info(s"Listening for TCP on /${address.getHostName}:${address.getPort}")

    case CommandFailed(_: Bind) => context stop self

    case c@Connected(remote, local) =>
      val handler = context.actorOf(ServerHandler.props)
      val connection = sender()
      connection ! Register(handler)
  }

}

