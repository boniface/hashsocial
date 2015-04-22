package model

import play.api.libs.json.Json

/**
 * Created by hashcode on 2015/03/27.
 */
case class Credentials (debug:Boolean,
                        appKey:String,
                        appSecret:String,
                        appToken:String,
                        appOther:String
                         )

object Credentials {
  implicit val credFmt = Json.format[Credentials]
}