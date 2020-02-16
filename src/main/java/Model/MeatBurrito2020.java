/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import View.Gui;
import org.deeplearning4j.nn.multilayer.MultiLayerNetwork;

import java.io.File;
import java.io.IOException;

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

        //proc.test();

        File in1 = new File("src/main/java/Data/newTweets.txt");

        String[] inputFiles = {in1.getAbsolutePath()};
        double[][] vals = proc.relatedWords(inputFiles, 5, 1);

        MultiLayerNetwork net = new MultiLayerNetwork(Config.AlgoConfig.get(proc.cipher(), 50));
        net.init();//actually creates network
        File networkSave = new File("src/main/java/Data/newTweets.txt");
        try {
            networkSave = Train.train(net,networkSave,50, proc.getmapping(inputFiles), proc.cipher(),vals);
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
