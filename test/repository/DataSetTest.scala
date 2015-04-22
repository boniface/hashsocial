package repository

import conf.util.SocialMedia
import domain.HashSite
import org.scalatest.{GivenWhenThen, FeatureSpec}
import respository.HashSiteRepository
/**
 * Created by hashcode on 2015/04/06.
 */
class DataSetTest extends FeatureSpec with GivenWhenThen{
  feature("Populate Table with Data ") {
    info("So that we Can use it to Persist Data")
    info("")
    scenario("Save Data") {
      Given("Data Object  ")
      val twitter = SocialMedia.withName("TWITTER").toString
      val repo = HashSiteRepository
      val zambia = HashSite(
        "ZM",
        twitter,
      "GmIKKGsaecNNOeAiJh8xf8cup",
      "RTb3xQ5kwRqpn1Yij04jlWnUtmrAe69YOHYq0x8aUWanO7kQ2Y",
      "2994411497-cExGeGVKvCHxSKqUGwB3q2pqTmkB2aZVQqVMPWW",
      "D6LJwftl1znc9i3DakYyetnqzL7M92WN3l8XxdnVN1dSa")
      When("Created  When Persisited ")
      val nigeria = HashSite(
        "NG",
        twitter,
        "ytI4U2t1699oxFniqJ2UJOVDI",
        "FD8Eiqj5yQ3xQVUWwsOaYDsR7N68QJn2LnRISMbBCYY67clIYg",
        "3049216941-Qux784gyTNDEGndsdlaGIjzSIgfKE5t2QqfWlKf",
        "m17VtTCcEr0wqc5N9y3K3xjGNe4dyiXc0jEFErNdzRxHa")
      When("Created  When Persisited ")

      repo.save(zambia)
      repo.save(nigeria)
      Then("The Values must validate ")
      And("")
    }
  }

}
