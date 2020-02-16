/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import org.deeplearning4j.nn.multilayer.MultiLayerNetwork;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Iterator;

/**
 *
 * @author kell
 */
public class MeatBurrito2020{

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {


        //MultiLayerNetwork net = new MultiLayerNetwork(AlgoConfig.get(new char[]{'a','b','c'}, 50));
        //net.init();//actually creates network

       // Gui gui = new Gui();
       // gui.init(args);

        /*
        try {
            TwitterResources.getTweets("", "");
        } catch (TwitterException e) {
            e.printStackTrace();
        }
        */
        Processor proc = new Processor();

        //proc.test();

        File in1 = new File("src/main/java/Data/newTweets.txt");

        String[] inputFiles = {in1.getAbsolutePath()};
        double[][] vals = proc.relatedWords(inputFiles, 1);
/*
        String[] cip = proc.cipher();
        try {
            String data = new String(Files.readAllBytes(Paths.get("src/main/java/Data/newTweets.txt")));
            String[] matcher = data.replaceAll("[^a-zA-Z.@ ]", "").toLowerCase().split("\\s+");
            for(int i = 0; i < cip.length; i++){
                int cnt = 0;
                for(int k = 0; k < matcher.length; k++){
                    if(matcher[k].equals(cip[i])){
                        cnt++;
                    }
                }
                System.out.println(cip[i] + " found " + cnt + " times.");
                if(cnt < 10){
                    System.out.println("^^^^^^^Found less than 10 times!!!");
                }
            }
            System.exit(0);
        } catch (IOException e) {
            e.printStackTrace();
        }
*/
        MultiLayerNetwork net = new MultiLayerNetwork(Config.AlgoConfig.get(proc.cipher(), 25));
        net.init();//actually creates network
        File networkSave = new File("src/main/java/Data/newTweets.txt");
        try {
            networkSave = Train.train(net,networkSave,50000, proc.getmapping(inputFiles), proc.cipher(),vals);
        } catch (IOException e) {
            e.printStackTrace();
        }

        /*
        for(int i = 0; i < vals.length; i++){
            for(int j = 0; j < vals.length; j++){
                if(vals[i][j] > 0.1){
                    System.out.println("1/5 for line " + i + " is at column " + j);
                }
            }
        }
        */
        //proc.printBestWords();

      System.out.println("end");

    }
}
