package services

import model.{Credentials, Media}
import org.scalatest.{FeatureSpec, GivenWhenThen}
import services.api.MediaAPI

import scala.concurrent.Await
import scala.concurrent.duration._


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

      val media = Media("I have nothing to fear in delivering constitution – Lungu",
        "http://www.qfmzambia.com/2015/04/26/i-have-nothing-to-fear-in-delivering-constitution-lungu/",
        " http://www.qfmzambia.com/wp-content/uploads/2015/04/EL-and-LAZ.jpg")

     val res = Await.result(hashsite, 10 seconds)
      res  match{
        case Some(t) => { t
          val creds = Credentials(true,t.appKey,t.appSecret,t.appToken,t.appOther)
          val twitter = MediaAPI().getTwitterConnection(creds)

          val result = MediaAPI().postToTwitter(media,twitter)

          println(" the Results Obtained is ", result)

        }

        case None => println(" There was an erroe that Happened")
      }

    }

  }

}
