package Model;
import javafx.scene.control.TextArea;
import twitter4j.*;
import twitter4j.conf.ConfigurationBuilder;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public abstract class TwitterResources {
    public static void getAnalysisTweets(String source, String outputFile) throws TwitterException, IOException {
        ConfigurationBuilder config = new ConfigurationBuilder();
        config.setOAuthConsumerKey("QuvBfIZwJsofhtXJHrVWSxRDm");
        config.setOAuthConsumerSecret("9Up6gzBDBupue3MYYBr0Ex0KZ6dqPQ28rQ2RDUJSg2LGchrJiU");
        config.setOAuthAccessToken("1228793132285739008-WpVQihekNNTut7FgWOzVWcaPMxWE2w");
        config.setOAuthAccessTokenSecret("AIUQLTGeSPmGBKvESWkrUU7bHH7Q84Bk1XjM4WPsv93m2");

        System.out.println(source + '\n' + outputFile + '\n');

        TwitterFactory factory = new TwitterFactory(config.build());
        Twitter twitter = factory.getInstance();

        FileWriter output = new FileWriter(outputFile);

        QueryResult tweets = twitter.search(new Query("from:" + source));
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
