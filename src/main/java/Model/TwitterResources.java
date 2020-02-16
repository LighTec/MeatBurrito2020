package Model;
import twitter4j.Twitter;
import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.TwitterFactory;

public class TwitterResources {
    public TwitterResources() {

    }

    public static void getTweets(String source, String outputFile) {
        Twitter twitter = TwitterFactory.getSingleton();
        QueryResult tweets = twitter.search(new Query("source:twitter4j yusukey"));
        for(int i = 0; i < tweets.getTweets().size(); i++) {
            System.out.println(tweets.getTweets().get(i).getText());
        }
    }
}
