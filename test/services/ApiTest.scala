package services

import model.{Credentials, Media}
import org.scalatest.{FeatureSpec, GivenWhenThen}
import services.api.MediaAPI
import scala.concurrent.ExecutionContext.Implicits.global

/**
 * Created by hashcode on 2015/03/19.
 */
class ApiTest extends FeatureSpec with GivenWhenThen {


  feature("Feature") {

    info("")
    info("")
    info("")

    scenario("") {
      val hashsite = MediaAPI().getTwitterCredentials("ZM")

      Given("")

      When("")

      Then("")
      val mediaTypes = List("FACTBOOK", "TWITTER")
      val media = Media("Message", "www.lsk.com", "Picture URL", mediaTypes)

      hashsite map(twit => twit match{
        case Some(t) => {t
        println(" The Value for X",t.appKey)
          val creds = Credentials(true,t.appKey,t.appSecret,t.appToken,t.appOther)
          val twitter = MediaAPI().getTwitterConnection(creds)
//          MediaAPI().postToTwitter(media,twitter)

        }

        case None =>
      })



    }

  }

}
