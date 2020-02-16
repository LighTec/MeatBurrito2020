/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Config.AlgoConfig;
import javafx.application.Application;
import javafx.stage.Stage;
import org.deeplearning4j.models.word2vec.Word2Vec;
import org.deeplearning4j.nn.multilayer.MultiLayerNetwork;

import java.util.Scanner;
import View.Gui;
import org.nd4j.linalg.api.ndarray.INDArray;
import twitter4j.TwitterException;

import java.util.Scanner;

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

        try {
            TwitterResources.getTweets("", "");
        } catch (TwitterException e) {
            e.printStackTrace();
        }

        Word2Vec_Thing test = new Word2Vec_Thing();
        test.BuildModel("/home/kell/IdeaProjects/MeatBurrito2020/src/main/java/Data/newTweets.txt");
        test.Train(3);

        while(true){
            Scanner keyboard = new Scanner(System.in);
            System.out.println(test.getWordsNearest(keyboard.nextLine(),5));
        Processor proc = new Processor();
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
