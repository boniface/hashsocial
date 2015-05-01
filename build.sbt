name := "hashsocial"

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.11.6"

libraryDependencies ++= Seq(
  jdbc,
  anorm,
  cache,
  ws
)

libraryDependencies += "org.twitter4j" % "twitter4j-core" % "4.0.2"

libraryDependencies += "org.twitter4j" % "twitter4j-stream" % "4.0.2"

libraryDependencies += "org.twitter4j" % "twitter4j-async" % "4.0.2"

libraryDependencies += "org.twitter4j" % "twitter4j-media-support" % "4.0.2"

libraryDependencies += "org.facebook4j" % "facebook4j-core" % "2.2.2"

libraryDependencies += "com.github.willisju" % "facebook4j-core" % "2.3.0"

libraryDependencies += "org.scalatest" % "scalatest_2.11" % "2.2.4"

libraryDependencies += "com.websudos" % "phantom_2.11" % "1.5.0"

libraryDependencies += "com.websudos" % "phantom-dsl_2.11" % "1.5.0"

libraryDependencies += "com.datastax.cassandra" % "cassandra-driver-core" % "2.1.5"

libraryDependencies += "com.beachape" % "enumeratum_2.11" % "1.1.0"

libraryDependencies += "com.beachape" % "enumeratum-play_2.11" % "1.1.0"

libraryDependencies += "com.beachape" % "enumeratum-play-json_2.11" % "1.1.0"

libraryDependencies += "org.jsoup" % "jsoup" % "1.8.2"

libraryDependencies += "com.syncthemall" % "goose" % "2.1.25"

libraryDependencies += "org.scalaz" % "scalaz-core_2.11" % "7.1.1"

libraryDependencies += "org.scalaz" % "scalaz-effect_2.11" % "7.1.1"

libraryDependencies += "org.scalaz" % "scalaz-typelevel_2.11" % "7.1.1"

libraryDependencies += "org.scalaz" % "scalaz-scalacheck-binding_2.11" % "7.1.1" % "test"

libraryDependencies += "org.scalaz" % "scalaz-concurrent_2.11" % "7.1.1"

libraryDependencies += "org.scala-lang.modules" %% "scala-async" % "0.9.2"

