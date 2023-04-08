package swim.tutorial;

import swim.api.ref.WarpRef;
import swim.structure.Record;
import com.twitter.clientlib.TwitterCredentialsOAuth2;
import com.twitter.clientlib.ApiException;
import com.twitter.clientlib.api.TwitterApi;
import com.twitter.clientlib.model.*;



/**
 * Simple wrapper around some {@code SwimRef}, e.g. a {@code SwimClient} handle,
 * that pushes data to the Swim server running at {@code hostUri}.
 */
public class DataSource {


  private final WarpRef ref;
  private final String hostUri;

  DataSource(WarpRef ref, String hostUri) {
    this.ref = ref;
    this.hostUri = hostUri;
  }

  void sendCommands() throws InterruptedException {
    int indicator = 0;
    while (true) {
      int foo = (int) (Math.random() * 10 - 5) + 30;
      int bar = (int) (Math.random() * 20 - 10) + 60;
      int baz = (int) (Math.random() * 30 - 15) + 90;
      if ((indicator / 25) % 2 == 0) {
        foo *= 2;
        bar *= 2;
        baz *= 2;
      }
      // msg's Recon serialization will take the following form:
      //   "{foo:$foo,bar:$bar,baz:$baz}"
      final Record msg = Record.create(3)
          .slot("foo", foo)
          .slot("bar", bar)
          .slot("baz", baz);

      // Push msg to the
      //   *CommandLane* addressable by "publish" OF the
      //   *Web Agent* addressable by "/unit/master" RUNNING ON the
      //   *(Swim) server* addressable by hostUri
      this.ref.command(this.hostUri, "/unit/master", "publish", msg);
      indicator = (indicator + 1) % 1000;

      // Throttle events to four every three seconds
      Thread.sleep(750);
    }
  }

   public static void main(String[] args) {
    //  String accessToken = "1585455903544786944-59p5Rnbt1D0GPpIE3bHKpgBN72G3ov";
    //  String accessTokenSecret = "oeijmzdxKUxY2fKBkE7jaxGjphqFtZnlb6QcVrzy1FiI0";
    //  String bearerToken = "AAAAAAAAAAAAAAAAAAAAAD26mQEAAAAAw%2FkNWI%2FMMBioPIapG1MRmCtXL7g%3DPc29nsmIldwPZmJkj7YwoqQYvfwEPYo4RSASOGOjv6yD4srCb1";
    // TwitterCredentials credentials = TwitterCredentials.builder()
    //         .accessToken(accessToken)
    //         .accessTokenSecret(accessTokenSecret)
    //         .bearerToken(bearerToken)
    //         .build();

    //  TwitterClient twitterClient = new TwitterClient(credentials);

//     //even after debugging, keep this block commented out
//     // try {
//     //     Tweet[] tweets = client.getUserTimeline("elonmusk");
//     //     for (Tweet tweet : tweets) {
//     //         System.out.println("Tweet: " + tweet.getText());
//     //     }
//     // } catch (Exception e) {
//     //     e.printStackTrace();
//     // }
//     //end here

//     Tweet tweet = twitterClient.getTweet("1224041905333379073");


 }

}
