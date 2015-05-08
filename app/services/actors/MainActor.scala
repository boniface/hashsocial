package services.actors

import akka.actor.{Props, Actor}
import akka.event.Logging
import akka.routing.RoundRobinPool
import services.actors.messages.Messages.{ZoneMessage, StartMessage}
import services.api.MediaAPI
import scala.concurrent.ExecutionContext.Implicits.global



/**
 * Created by hashcode on 2015/05/01.
 */
class MainActor extends Actor{


  val log = Logging(context.system, this)

  override def receive: Receive = {
  // Send Message to get Credentials
    case StartMessage(start) =>{
      val credentialsActor = context.actorOf(Props[GetCredentialsActor].withRouter(RoundRobinPool(nrOfInstances = 5)))
      MediaAPI().getZones map (zones => zones foreach {
        zone => credentialsActor ! ZoneMessage(zone.code)
      })
    }
    case _=> log.info("received unknown message")

  }
}
