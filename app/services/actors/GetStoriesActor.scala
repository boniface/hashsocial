package services.actors

import akka.actor.Actor.Receive
import akka.actor.{Props, ActorLogging, Actor}
import akka.routing.RoundRobinPool
import services.actors.messages.Messages.{PostsMessage, CredentialMessage}
import services.api.MediaAPI
import scala.concurrent.ExecutionContext.Implicits.global
/**
 * Created by hashcode on 2015/05/01.
 */
class GetStoriesActor extends Actor with ActorLogging{
  override def receive: Receive = {
    case CredentialMessage(twitter,zone)=>{
      val tweetStoriesActor = context.actorOf(Props[TweetStoriesActor].withRouter(RoundRobinPool(nrOfInstances = 5)))
      val stories = MediaAPI().getZoneStories(zone)
      stories map (posts => {
        tweetStoriesActor ! PostsMessage(twitter,zone,posts)
      })
    }
  }
}
