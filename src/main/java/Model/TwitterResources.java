package Model;
import twitter4j.*;

public class TwitterResources {
    public TwitterResources() {

    }

    public static void getTweets(String source, String outputFile) throws TwitterException {
        Twitter twitter = TwitterFactory.getSingleton();
        QueryResult tweets = twitter.search(new Query("source:twitter4j yusukey"));
        for(int i = 0; i < tweets.getTweets().size(); i++) {
            System.out.println(tweets.getTweets().get(i).getText());
        }
    }
}
