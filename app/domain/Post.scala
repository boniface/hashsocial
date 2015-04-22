package domain

import java.util.Date

import play.api.libs.json.Json

/**
 * Created by hashcode on 2015/04/06.
 */
case class Post(
                 zone: String,
                 yeardate: Date,
                 linkhash: String,
                 domain: String,
                 date: Date,
                 title: String,
                 article: String,
                 metakeywords: String,
                 metaDescription: String,
                 link: String,
                 imageUrl: String,
                 seo: String,
                 imagePath: String,
                 caption: String,
                 siteCode: String
                 )

object Post {
  implicit val postFmt = Json.format[Post]
}