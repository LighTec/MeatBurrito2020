/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package meatburrito2020;

import config.AlgoConfig;
import org.deeplearning4j.nn.multilayer.MultiLayerNetwork;

/**
 *
 * @author kell
 */
public class MeatBurrito2020 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {


        MultiLayerNetwork net = new MultiLayerNetwork(AlgoConfig.get(new char[]{'a','b','c'}, 50));
        net.init();//acctualy creats network

        System.out.println("end");

    }

}
