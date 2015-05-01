package services.actors

import akka.actor.{ActorLogging, Actor}
import model.Credentials
import services.actors.messages.Messages.ZoneMessage
import services.api.MediaAPI

/**
 * Created by hashcode on 2015/05/01.
 */
class GetCredentials extends Actor with ActorLogging{
  override def receive: Receive = {
    case ZoneMessage(zone)=>{
      MediaAPI().getTwitterCredentials(zone) map(creds => creds match {

        case Some(value) => {
          val creds = Credentials(true,value.appKey,value.appSecret,value.appToken,value.appOther)
          val twitter = MediaAPI().getTwitterConnection(creds)
        }

        case None => log.info("Twitter Site Not Set up for Zone ", zone)
      } )
    }

  }
}
