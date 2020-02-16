package Model;

import org.deeplearning4j.nn.multilayer.MultiLayerNetwork;
import org.nd4j.linalg.api.ndarray.INDArray;
import org.nd4j.linalg.dataset.DataSet;
import org.nd4j.linalg.factory.Nd4j;

import java.io.File;
import java.io.IOException;

public class  Train {
    public static File train(MultiLayerNetwork networkConfig,File networkFile, int numOfEpochs, int[] sizedTFile) throws IOException {
        int perEpoch = (int)(sizedTFile.length / numOfEpochs);

        for (int epoch = 0; epoch<numOfEpochs;epoch++) {
            INDArray inputArray = Nd4j.zeros(1, validKeyChars.length, perEpoch);//oneD arr that stores input data
            INDArray inputLabels = Nd4j.zeros(1, validKeyChars.length, perEpoch);//another oneD array that holds an offset version of the last to be used as output while training


            DataSet dataSet = new DataSet(inputArray, inputLabels);
            networkConfig.fit(dataSet);


        }

        networkConfig.save(networkFile);
        return null;
    }
}
