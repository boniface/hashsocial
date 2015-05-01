package conf.connections

import com.datastax.driver.core.{Cluster, Session}
import com.websudos.phantom.Implicits._

import scala.concurrent.{Future, blocking}

/**
 * Created by hashcode on 2015/04/05.
 */
object DataConnection {
  val keySpace = "hashmedia"

  lazy val cluster = Cluster.builder()
    .addContactPoints("localhost")
    .withPort(9042)
    .withoutJMXReporting()
    .withoutMetrics()
    .build()

  lazy val session = blocking {
    cluster.connect(keySpace)
  }
}

trait DataConnection {
  self: CassandraTable[_, _] =>

  def createTable(): Future[Unit] = {
    create.future() map (_ => ())
  }

  implicit lazy val datastax: Session = DataConnection.session
}