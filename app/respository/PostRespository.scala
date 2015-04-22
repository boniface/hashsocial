package respository

import java.util.Date

import com.datastax.driver.core.{ResultSet, Row}
import com.websudos.phantom.Implicits._
import com.websudos.phantom.iteratee.Iteratee
import conf.connections.DataConnection

import domain.Post

import scala.concurrent.Future

/**
 * Created by hashcode on 2015/04/06.
 */
class PostRespository extends CassandraTable[PostRespository, Post] {

  object zone extends StringColumn(this) with PartitionKey[String]

  object date extends DateColumn(this) with PrimaryKey[Date] with ClusteringOrder[Date] with Descending

  object linkhash extends StringColumn(this) with PrimaryKey[String] with ClusteringOrder[String] with Descending

  object yeardate extends DateColumn(this)

  object domain extends StringColumn(this)

  object title extends StringColumn(this)

  object article extends StringColumn(this)

  object metakeywords extends StringColumn(this)

  object metaDescription extends StringColumn(this)

  object link extends StringColumn(this)

  object imageUrl extends StringColumn(this)

  object seo extends StringColumn(this)

  object imagePath extends StringColumn(this)

  object caption extends StringColumn(this)

  object siteCode extends StringColumn(this)

  override def fromRow(row: Row): Post = {
    Post(
      zone(row),
      yeardate(row),
      linkhash(row),
      domain(row),
      date(row),
      title(row),
      article(row),
      metakeywords(row),
      metaDescription(row),
      link(row),
      imageUrl(row),
      seo(row),
      imagePath(row),
      caption(row),
      siteCode(row)
    )
  }
}

object PostRespository extends PostRespository with DataConnection {
  override lazy val tableName = "posts"
  def getLatestPosts(zone: String): Future[Iterator[Post]] = {
    select.where(_.zone eqs zone)
      .fetchEnumerator() run Iteratee.slice(0, 50)
  }

}