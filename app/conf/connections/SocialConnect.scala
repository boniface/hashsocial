package conf.connections

import facebook4j.{Facebook, FacebookFactory}
import model.Credentials
import twitter4j.{Twitter, TwitterFactory}


/**
 * Created by hashcode on 2015/04/04.
 */
trait SocialConnect {
  def twitter(creds: Credentials): Twitter

  def facebook(creds: Credentials): Facebook
}

object SocialConnect {
  def apply(): SocialConnect = new SocialConnectImpl()

  private class SocialConnectImpl extends SocialConnect {
    override def twitter(creds: Credentials): Twitter = {
      val cb = new twitter4j.conf.ConfigurationBuilder()
      cb.setDebugEnabled(creds.debug)
        .setOAuthConsumerKey(creds.appKey)
        .setOAuthConsumerSecret(creds.appSecret)
        .setOAuthAccessToken(creds.appToken)
        .setOAuthAccessTokenSecret(creds.appOther)
      val tf = new TwitterFactory(cb.build())
      tf.getInstance()
    }

    override def facebook(creds: Credentials): Facebook = {
      val cb = new facebook4j.conf.ConfigurationBuilder()
      cb.setDebugEnabled(creds.debug)
        .setOAuthAppId(creds.appKey)
        .setOAuthAppSecret(creds.appSecret)
        .setOAuthAccessToken(creds.appToken)
        .setOAuthPermissions(creds.appOther)
      val ff = new FacebookFactory(cb.build())
      ff.getInstance()
    }
  }
}



