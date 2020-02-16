package Model;

import org.deeplearning4j.nn.multilayer.MultiLayerNetwork;
import org.nd4j.linalg.api.ndarray.INDArray;
import org.nd4j.linalg.dataset.DataSet;
import org.nd4j.linalg.factory.Nd4j;

import java.io.File;
import java.io.IOException;

public class  Train {
    public static File train(MultiLayerNetwork networkConfig,File networkFile, int numOfEpochs, int[] sizedTFile, String[] validWord,double[][] simmilaratyMatrix) throws IOException {
        int perEpoch = (int)(sizedTFile.length / numOfEpochs);

        for (int epoch = 0; epoch<numOfEpochs;epoch++) {
            INDArray inputArray = Nd4j.zeros(1, validWord.length, perEpoch);//oneD arr that stores input data
            INDArray inputLabels = Nd4j.zeros(1, validWord.length, perEpoch);//another oneD array that holds an offset version of the last to be used as output while training
            for (int j = 0 +(epoch * perEpoch) ; j < ((epoch + 1) * perEpoch) -1 ; j++) {
                inputArray.putScalar(new int[]{0, sizedTFile[j], j}, 1);
                for(int i = 0; i < simmilaratyMatrix[j].length - 2 ; i++){
                   inputLabels.putScalar(new int[]{0,i,j},simmilaratyMatrix[j+1][i]);
                }
                //inputLabels.putRow(j,Nd4j.create(simmilaratyMatrix[j+1]) );
                //inputLabels.putScalar(new int[]{0, sizedTFile[j+1], j}, 1);
            }

            DataSet dataSet = new DataSet(inputArray, inputLabels);
            networkConfig.fit(dataSet);

            String output = "";

            networkConfig.rnnClearPreviousState();
            INDArray testInputArray = Nd4j.zeros(1, validWord.length, 1);
            int fistChar = (int) (validWord.length * Math.random());
            testInputArray.putScalar(fistChar, 1);//creats a random vector based on validKey characters

            INDArray outputArray  = networkConfig.rnnTimeStep(testInputArray);
/*
            // put the first character into the rrn as an initialisation
            INDArray testInit = Nd4j.zeros(1,validWord.length, 1);
            int fistChar = (int) (validWord.length * Math.random());
            testInit.putScalar(fistChar, 1);

            // clear current stance from the last example
            networkConfig.rnnClearPreviousState();
            // run one step -> IMPORTANT: rnnTimeStep() must be called, not
            // output()
            // the output shows what the net thinks what should come next
            INDArray outputArray = networkConfig.rnnTimeStep(testInit);
*/


            //outputArray = outputArray.tensorAlongDimension((int)outputArray.size(2)-1,1,0);
            System.out.println("reeched the yeet");
            for (int k = 0; k < 50; k++) {

                 //outputArray = networkConfig.rnnTimeStep(testInputArray);

                double maxPrediction = Double.MIN_VALUE;
                int maxPredictionIndex = -1;
                for (int h = 0; h < validWord.length; h++) {
                    if (maxPrediction < outputArray.getDouble(h)) {
                        maxPrediction = outputArray.getDouble(h);
                        maxPredictionIndex = h;
                    }
                }
                output = output + " " + validWord[maxPredictionIndex];
                testInputArray = Nd4j.zeros(1, validWord.length, 1);
                testInputArray.putScalar(maxPredictionIndex, 1);
                outputArray = networkConfig.rnnTimeStep(testInputArray);
            }
            System.out.println( validWord[fistChar] + " " + output);
            networkConfig.save(networkFile);

        }

        networkConfig.save(networkFile);
        return networkFile;
    }
}
