package respository

import com.datastax.driver.core.{ResultSet, Row}
import com.websudos.phantom.CassandraTable
import com.websudos.phantom.Implicits._
import com.websudos.phantom.iteratee.Iteratee
import com.websudos.phantom.keys.{PartitionKey, PrimaryKey}
import conf.connections.DataConnection
import domain.HashSite

import scala.concurrent.Future

/**
 * Created by hashcode on 2015/04/04.
 */
class HashSiteRepository extends CassandraTable[HashSiteRepository, HashSite] {


  object zone extends StringColumn(this) with PartitionKey[String]

  object social extends StringColumn(this) with PrimaryKey[String]

  object appKey extends StringColumn(this)

  object appSecret extends StringColumn(this)

  object appToken extends StringColumn(this)

  object appOther extends StringColumn(this)

  override def fromRow(row: Row): HashSite = {
    HashSite(zone(row),
              social(row),
              appKey(row),
              appSecret(row),
              appToken(row),
              appOther(row))
  }
}
object HashSiteRepository extends HashSiteRepository with  DataConnection {
  override lazy val tableName = "hashsites"

  def save(site: HashSite): Future[ResultSet] = {
    insert
      .value(_.zone, site.zone)
      .value(_.social, site.social)
      .value(_.appKey, site.appKey)
      .value(_.appSecret, site.appSecret)
      .value(_.appToken, site.appToken)
      .value(_.appOther, site.appOther)
      .future()
  }

  def getSocialSiteById(zone: String, social: String): Future[Option[HashSite]] = {
    select.where(_.zone eqs zone).and(_.social eqs social).one()
  }

  def deleteSocialSiteById(zone:String,social: String): Future[ResultSet] = {
    delete.where(_.zone eqs zone).and(_.social eqs social).future()
  }

  def getSocialSites(): Future[Seq[HashSite]] = {
    select.fetchEnumerator() run Iteratee.collect()
  }

  def getSocialByZone(zone: String): Future[Seq[HashSite]] = {
    select.where(_.zone eqs zone).fetchEnumerator() run Iteratee.collect()
  }
}


