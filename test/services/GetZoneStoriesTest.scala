package services

import org.scalatest.{FeatureSpec, GivenWhenThen}
import services.api.MediaAPI

import scala.concurrent.Await
import scala.concurrent.duration._

/**
 * Created by hashcode on 2015/04/27.
 */
class GetZoneStoriesTest extends FeatureSpec with GivenWhenThen {

  feature("Stories to Tweet") {

    info(" AS a System")
    info(" I want to get the Stories from the Store ")
    info("So that I can Tweet Those that have not been Tweeted")

    scenario("Get the Stories to Tweet") {


      Given(" Give The Zone ZM")
      val results = MediaAPI().getZoneStories("ZM")


      val stories = Await.result(results, 10 seconds)

      When(" When Displayed ")

      Then("I should See Stories to Tweet")
      println(" The Stories Size  ", stories.size)

    }
  }
}
