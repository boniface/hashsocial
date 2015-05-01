package services.actors.messages

import domain.Post
import twitter4j.Twitter

/**
 * Created by hashcode on 2015/05/01.
 */
object Messages {
    case class StartMessage(message:String)
    case class ZoneMessage(zone:String)
    case class CredentialMessage(twitter:Twitter,zone:String)
    case class PostsMessage(twitter:Twitter,zone:String,posts:Iterator[Post])

}
