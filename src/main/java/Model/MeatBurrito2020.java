/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Config.AlgoConfig;
import org.deeplearning4j.nn.multilayer.MultiLayerNetwork;

import java.util.Scanner;
import View.Gui;

/**
 *
 * @author kell
 */
public class MeatBurrito2020 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {


        //MultiLayerNetwork net = new MultiLayerNetwork(AlgoConfig.get(new char[]{'a','b','c'}, 50));
        //net.init();//actually creates network

        Word2Vec_Thing test = new Word2Vec_Thing();
        test.BuildModel("/home/kell/IdeaProjects/MeatBurrito2020/src/main/java/Data/trumpsTweets.txt");
        test.Train(3);

        Gui gui = new Gui();
        gui.launch(new String[0]);

        while(true){
            Scanner keyboard = new Scanner(System.in);
            System.out.println(test.getWordsNearest(keyboard.nextLine(),5));
        }



      //  System.out.println("end");

    }

}
