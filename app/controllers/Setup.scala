package controllers

import play.api.libs.json.Json
import play.api.mvc.{Action, Controller}
import respository.HashSiteRepository
import scala.concurrent.ExecutionContext.Implicits.global

/**
 * Created by hashcode on 2015/04/20.
 */
object Setup extends Controller{

  def dbsetup = Action.async {
    val results = for {
      feed <- HashSiteRepository.createTable()
    } yield (feed)
    results map (result => {
      Ok(Json.toJson("Done"))
    })
  }

}
