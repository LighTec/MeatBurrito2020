package Model;
import twitter4j.Twitter;
public class TwitterResources {
    public TwitterResources() {

    }

    public static void getTweets(String source, String outputFile) {
        Twitter twitter = TwitterFactory.getSingleton();
    }
}
