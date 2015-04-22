package repository

import org.scalatest.{FeatureSpec, GivenWhenThen}
import respository.HashSiteRepository

import scala.concurrent.Await

import scala.concurrent.duration._

/**
 * Created by hashcode on 2015/04/05.
 */
class SetupTableTest extends FeatureSpec with GivenWhenThen {

  feature("Set Up Table") {
    info("So that Weh can Save Data")
    scenario("Create Object for the Table") {

      Given("An Object to the Repository")
      val repo = HashSiteRepository
      When("Created  ")
      val res= Await.result(repo.createTable(), 2 minutes)
      Then("Table Should be Created ")
      And("")
    }
  }
}
