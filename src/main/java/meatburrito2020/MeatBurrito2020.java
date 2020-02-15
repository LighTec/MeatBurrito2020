/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package meatburrito2020;

import org.deeplearning4j.nn.conf.MultiLayerConfiguration;
import org.deeplearning4j.nn.conf.NeuralNetConfiguration;
import org.deeplearning4j.nn.conf.layers.DenseLayer;
import org.deeplearning4j.nn.conf.layers.OutputLayer;
import org.deeplearning4j.nn.weights.WeightInit;
import org.nd4j.linalg.activations.Activation;
import org.nd4j.linalg.learning.config.AdaGrad;
import org.nd4j.linalg.lossfunctions.LossFunctions;

/**
 *
 * @author kell
 */
public class MeatBurrito2020 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        MultiLayerConfiguration conf = new NeuralNetConfiguration.Builder()
                .seed(12345)
                .weightInit(WeightInit.XAVIER)
                .updater(new AdaGrad(0.05))
                .activation(Activation.RELU)
                .l2(0.0001)
                .list()
                .layer(new DenseLayer.Builder().nIn(784).nOut(250)
                        .build())
                .layer(new DenseLayer.Builder().nIn(250).nOut(10)
                        .build())
                .layer(new DenseLayer.Builder().nIn(10).nOut(250)
                        .build())
                .layer(new OutputLayer.Builder().nIn(250).nOut(784)
                        .lossFunction(LossFunctions.LossFunction.MSE)
                        .build())
                .build();

    }

}
