package conf.util

/**
 * Created by hashcode on 2015/04/06.
 */
import enumeratum.Enum

sealed trait SocialMedia

object SocialMedia extends Enum[SocialMedia] {

  val values = findValues

  case object TWITTER extends SocialMedia
  case object FACEBOOK extends SocialMedia
  case object LINKEDIN extends SocialMedia
  case object GOOGLEPLUS extends SocialMedia

}
