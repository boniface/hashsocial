package services.api

import java.net.URL

import com.websudos.phantom.Implicits.ResultSet
import conf.connections.SocialConnect
import conf.util.SocialMedia
import domain._
import facebook4j.Facebook
import model.{Credentials, Media}
import respository._
import twitter4j.{StatusUpdate, Twitter}

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.{ExecutionContext, Future}
import scala.util.{Failure, Success, Try}

import ExecutionContext.Implicits.global
import scala.async.Async.{async, await}

/**
 * Created by hashcode on 2015/03/26.
 */
trait MediaAPI {
  def postToTwitter(media: Media,twitter:Twitter): String
  def postToFacebook(media: Media,facebook:Facebook): String
  def getTwitterCredentials(zone:String):Future[Option[HashSite]]
  def getTwitterConnection(creds:Credentials):Twitter
  def getFacebookConnection(creds:Credentials):Facebook
  def getZoneStories(zone:String):Future[Iterator[Post]]
  def publishTwittedStory(item:String,linkhash:String):Future[ResultSet]
  def getZoneHashTags(zone:String):Future[Option[HashTag]]
  def getZones:Future[Seq[Zone]]
}

object MediaAPI {
  def apply(): MediaAPI = new MediaAPIImpl
  private class MediaAPIImpl extends MediaAPI {

    val twitterItem = SocialMedia.TWITTER.toString

    def hasTweeted(post: Post): Boolean = {
      val link = PublishedLinksRepository.getLinkById(twitterItem,post.linkhash)
      val results = link map (result => result match {
        case Some(p) => false
        case None => true
      })
      var result = true
      results onComplete{
        case Success(r) => result = r
        case Failure(_) => result = false
      }
      result
    }

    override  def getZoneStories(zone:String):Future[Iterator[Post]]={
      PostRespository.getLatestPosts(zone).map (posts => posts.filter(post=>hasTweeted(post: Post) ))
    }

    override def getTwitterConnection(creds: Credentials): Twitter = {

      SocialConnect.apply().twitter(creds)
    }

    override def getFacebookConnection(creds: Credentials): Facebook = {
      SocialConnect.apply().facebook(creds)
    }

    override def getTwitterCredentials(zone: String) = {
      HashSiteRepository.getSocialSiteById(zone,twitterItem)
    }

    override def postToTwitter(media: Media, twitter: Twitter): String = {
        val status = new StatusUpdate(media.message+" "+media.url)
        val result = Try(new URL(media.imageurl).openStream())
          result match {
            case Success(input) =>  None //status.setMedia(media.url,input)
            case Failure(input) =>
          }
        val s = twitter.updateStatus(status)
      s.getText
    }

    override def postToFacebook(media: Media, facebook: Facebook): String = ???

    override def publishTwittedStory(item: String, linkhash: String): Future[ResultSet]= {
      val publishedLink = PublishedLinks(twitterItem,linkhash)
      val res =  PublishedLinksRepository.save(publishedLink)
      res
    }
    override def getZoneHashTags(zone: String): Future[Option[HashTag]] = {
      HashTagRepository.getZoneHashTags(zone)
    }
    override def getZones: Future[Seq[Zone]] = {
      ZoneRespository.getAllZones
    }
  }
}


