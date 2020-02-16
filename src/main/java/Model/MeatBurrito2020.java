/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import org.deeplearning4j.nn.multilayer.MultiLayerNetwork;

import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.GregorianCalendar;

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
        double[][] vals = proc.relatedWords(inputFiles,  1);

        MultiLayerNetwork net = new MultiLayerNetwork(Config.AlgoConfig.get(proc.cipher(), 3));
        net.init();//actually creates network
        File networkSave = new File("src/main/java/Data/newTweets.txt");
        try {
            networkSave = Train.train(net,networkSave,1000, proc.getmapping(inputFiles), proc.cipher(),vals);
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

        Calendar time = new GregorianCalendar();
        boolean checker = false;

        while(true) {
            if(time.MINUTE % 15 == 0 && !checker) {
                checker = true;
                //Insert Tweet Code Here!!!
                System.out.println(time.MINUTE + '\n');
            } else if(time.MINUTE % 15 != 0 && checker) {
                checker = false;
            }
        }


     // System.out.println("end");

    }
}
