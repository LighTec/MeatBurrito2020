/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import View.Gui;
import twitter4j.TwitterException;

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

        Gui gui = new Gui();
        gui.init(args);

        /*
        try {
            TwitterResources.getTweets("", "");
        } catch (TwitterException e) {
            e.printStackTrace();
        }
        */

        Processor proc = new Processor();
        proc.test();

        String[] inputFiles = {"/home/kell/IdeaProjects/MeatBurrito2020/src/main/java/Data/newTweets.txt"};
        double[][] vals = proc.relatedWords(inputFiles, 5, 1);
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
