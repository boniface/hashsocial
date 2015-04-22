package services.api

import java.net.URL

import conf.connections.SocialConnect
import conf.util.SocialMedia
import domain.HashSite
import facebook4j.Facebook
import model.{Credentials, Media}
import org.apache.commons.logging.Log
import respository.HashSiteRepository
import twitter4j.{TwitterException, StatusUpdate, Twitter}
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future


/**
 * Created by hashcode on 2015/03/26.
 */
trait MediaAPI {
  def postToTwitter(media: Media,twitter:Twitter): String
  def postToFacebook(media: Media,facebook:Facebook): String
  def getTwitterCredentials(zone:String):Future[Option[HashSite]]
  def getTwitterConnection(creds:Credentials):Twitter
  def getFacebookConnection(creds:Credentials):Facebook
}

object MediaAPI {
  def apply(): MediaAPI = new MediaAPIImpl
  private class MediaAPIImpl extends MediaAPI {


    override def getTwitterConnection(creds: Credentials): Twitter = {

      SocialConnect.apply().twitter(creds)
    }

    override def getFacebookConnection(creds: Credentials): Facebook = {
      SocialConnect.apply().facebook(creds)
    }

    override def getTwitterCredentials(zone: String) = {
      val twitter = SocialMedia.TWITTER.toString
      HashSiteRepository.getSocialSiteById(zone,twitter)

    }

    override def postToTwitter(media: Media, twitter: Twitter): String = {
      println("The Media is ",media.url, " The Twitter Object", twitter)
//        val status = new StatusUpdate(media.message)
//        val input = new URL(media.image).openStream()
//        status.setMedia(media.url,input)
//        val s = twitter.updateStatus(status)
//        s.getText
      " Done"
    }

    override def postToFacebook(media: Media, facebook: Facebook): String = ???
  }
}


