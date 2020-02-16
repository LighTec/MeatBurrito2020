package Model;
import javafx.scene.control.TextArea;
import twitter4j.*;
import twitter4j.conf.ConfigurationBuilder;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public abstract class TwitterResources {

    public static void postTweet(String tweet) throws TwitterException {
        ConfigurationBuilder config = new ConfigurationBuilder();
        config.setOAuthConsumerKey("f90kEE6UrOarG4aUR8yynbLff");
        config.setOAuthConsumerSecret("Cd98LwoyKO1ZS3vHw7cH2SQCMUW3bsSkpTgpST1ZS0i0iu3PBU");
        config.setOAuthAccessToken("897951635745325056-YNa3BM9zV1Jxsk8Vyo8b9dRyIjBDN6S");
        config.setOAuthAccessTokenSecret("3ZKVJhmJapU8X2zWOkytL8nPLP4iP2fw5kZPV6uNoee6D");

        TwitterFactor factory = new TwitterFactory(config.build());
        Twitter twitter = factory.getInstance();
        twitter.updateStatus(tweet);
    }

    public static void getAnalysisTweets(String source, String outputFile) throws TwitterException, IOException {
        ConfigurationBuilder config = new ConfigurationBuilder();
        config.setOAuthConsumerKey("MNxOKZ379XgXBOEZxaiL40mlU");
        config.setOAuthConsumerSecret("6Y4TJ6i7Z7RD157Sgfxw2UTVPdljAigA5rSYfqo5cVO33MxF21");
        config.setOAuthAccessToken("1228793132285739008-FSvtjI42wgqwwscqz6KCJdmMuc6i6w");
        config.setOAuthAccessTokenSecret("VRzHgrlx9EUg07PFt8V6CS87iKSuiJWtyGh6baTubsZhk");

        TwitterFactory factory = new TwitterFactory(config.build());
        Twitter twitter = factory.getInstance();

        FileWriter output = new FileWriter(outputFile);
        Query search = new Query("from:" + source);
        search.count(100);
        QueryResult tweets = twitter.search(search);
        for(int i = 0; i < tweets.getTweets().size(); i++) {
            output.append(tweets.getTweets().get(i).getText().replace('\n', ' '));
            if(i != tweets.getTweets().size() - 1)
                output.append('\n');
        }
        output.close();
    }

    public static void getAnalysisTweets(String source, String outputFile, TextArea testOutput) throws TwitterException, IOException {
        TwitterResources.getAnalysisTweets(source, outputFile);
        Scanner file = new Scanner(new File(outputFile));
        while(file.hasNextLine())
            testOutput.setText(testOutput.getText() + '\n' + file.nextLine());
        file.close();
    }
}
