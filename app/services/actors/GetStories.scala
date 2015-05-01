package services.actors

import akka.actor.Actor.Receive
import akka.actor.{ActorLogging, Actor}
import services.actors.messages.Messages.CredentialMessage
import services.api.MediaAPI

/**
 * Created by hashcode on 2015/05/01.
 */
class GetStories extends Actor with ActorLogging{
  override def receive: Receive = {
    case CredentialMessage(twitter,zone)=>{
      val stories = MediaAPI().getZoneStories(zone)

      stories map (story => {

        story

      })

    }
  }
}
