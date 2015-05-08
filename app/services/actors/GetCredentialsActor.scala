package services.actors

import akka.actor.{Props, ActorLogging, Actor}
import akka.routing.RoundRobinPool
import model.Credentials
import services.actors.messages.Messages.{CredentialMessage, ZoneMessage}
import services.api.MediaAPI
import scala.concurrent.ExecutionContext.Implicits.global

/**
 * Created by hashcode on 2015/05/01.
 */
class GetCredentialsActor extends Actor with ActorLogging{
  override def receive: Receive = {
// Send to
    case ZoneMessage(zone)=>{
      val getStoriesActor = context.actorOf(Props[GetStoriesActor].withRouter(RoundRobinPool(nrOfInstances = 5)))

      MediaAPI().getTwitterCredentials(zone) map(creds => creds match {

        case Some(value) => {
          val creds = Credentials(true,value.appKey,value.appSecret,value.appToken,value.appOther)
          val twitter = MediaAPI().getTwitterConnection(creds)
          getStoriesActor ! CredentialMessage(twitter,zone)
        }
        case None => log.info("Twitter Site Not Set up for Zone ", zone)
      } )
    }

  }
}
