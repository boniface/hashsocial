package services.actors

import akka.actor.Actor.Receive
import akka.actor.{ActorLogging, Actor}
import model.Media
import services.actors.messages.Messages.PostsMessage
import services.api.MediaAPI

/**
 * Created by hashcode on 2015/05/01.
 */
class TweetStories extends Actor with ActorLogging {
  override def receive: Receive = {
    case PostsMessage(twitter,zone,posts)=>{
      MediaAPI().getZoneHashTags(zone) map ( tag =>  tag match {
        case Some(value) =>{
          posts foreach( post => {
            val media = Media(post.title,post.link,post.imageUrl,value.hashtag)
             val reply = MediaAPI().postToTwitter(media,twitter)

            // Send to PostPub Links
          })
        }
        case None =>

        })




    }
  }
}
