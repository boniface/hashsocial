package services.actors

import akka.actor.Actor.Receive
import akka.actor.{ActorLogging, Actor}
import conf.util.SocialMedia
import domain.PublishedLinks
import respository.PublishedLinksRepository
import services.actors.messages.Messages.PublishPost

/**
 * Created by hashcode on 2015/05/06.
 */
class PublishTweetActor extends Actor with ActorLogging{
  override def receive: Receive = {
    case PublishPost(post)=>  {
      val pblinks = PublishedLinks(SocialMedia.TWITTER.toString,post.linkhash)
      PublishedLinksRepository.save(pblinks)
    }
    case _=> log.info(" Wrong Message Recieved")
  }
}
