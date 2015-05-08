package services.actors

import akka.actor.Actor.Receive
import akka.actor.{Props, ActorLogging, Actor}
import akka.routing.RoundRobinPool
import model.Media
import services.actors.messages.Messages.{PublishPost, PostsMessage}
import services.api.MediaAPI
import scala.concurrent.ExecutionContext.Implicits.global

/**
 * Created by hashcode on 2015/05/01.
 */
class TweetStoriesActor extends Actor with ActorLogging {
  val publishTweetActor = context.actorOf(Props[PublishTweetActor].withRouter(RoundRobinPool(nrOfInstances = 5)))
  override def receive: Receive = {
    case PostsMessage(twitter,zone,posts)=>{
      MediaAPI().getZoneHashTags(zone) map ( tag =>  tag match {
        case Some(value) =>{
          posts foreach( post => {
            val media = Media(post.title,post.link,post.imageUrl,value.hashtag)
//             val reply = MediaAPI().postToTwitter(media,twitter)
            println("The Tweeted Value is ", media)
            publishTweetActor ! PublishPost(post)
          })
        }
        case None => None
        })




    }
  }
}
