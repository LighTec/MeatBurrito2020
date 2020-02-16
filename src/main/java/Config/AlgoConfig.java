package Config;

import org.deeplearning4j.nn.api.OptimizationAlgorithm;
import org.deeplearning4j.nn.conf.BackpropType;
import org.deeplearning4j.nn.conf.MultiLayerConfiguration;
import org.deeplearning4j.nn.conf.NeuralNetConfiguration;
import org.deeplearning4j.nn.conf.Updater;
import org.deeplearning4j.nn.conf.layers.LSTM;
import org.deeplearning4j.nn.conf.layers.RnnOutputLayer;
import org.deeplearning4j.nn.weights.WeightInit;
import org.nd4j.linalg.activations.Activation;
import org.nd4j.linalg.lossfunctions.LossFunctions;

public class AlgoConfig {
    public static MultiLayerConfiguration get(String[] validKeyChars,int tBPTTLength){


         MultiLayerConfiguration conf = new NeuralNetConfiguration.Builder()//the network configuration
                .optimizationAlgo(OptimizationAlgorithm.STOCHASTIC_GRADIENT_DESCENT)
                //.iterations(1)
                //.learningRate(0.001)
                .seed(12345)
               //.regularization(true)
                .l2(0.001)
                .weightInit(WeightInit.XAVIER)
                .updater(Updater.NESTEROVS)
                //.momentum(0.9)
                .list()//For configuring MultiLayerNetwork we call the list method
                .layer(0, new LSTM.Builder().nIn(validKeyChars.length).nOut(validKeyChars.length).activation(Activation.TANH).build())
                .layer(1, new RnnOutputLayer.Builder(LossFunctions.LossFunction.MCXENT).nIn(validKeyChars.length).nOut(validKeyChars.length).activation(Activation.SOFTMAX).build())
                .backpropType(BackpropType.TruncatedBPTT).tBPTTForwardLength(tBPTTLength).tBPTTBackwardLength(tBPTTLength)
                //.pretrain(false)
                //.backprop(true)
                .build();
         //System.out.println(conf.toJson());
        return conf;
    }


}
