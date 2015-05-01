package services.actors

import akka.actor.Actor
import akka.event.Logging
import services.actors.messages.Messages.StartMessage


/**
 * Created by hashcode on 2015/05/01.
 */
class MainActor extends Actor{

  val log = Logging(context.system, this)

  override def receive: Receive = {
    case StartMessage(start) =>{

    }

    case _=> log.info("received unknown message")

  }
}
