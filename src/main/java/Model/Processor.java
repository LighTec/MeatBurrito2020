package Model;

import org.deeplearning4j.models.word2vec.VocabWord;
import org.deeplearning4j.models.word2vec.Word2Vec;
import org.deeplearning4j.models.word2vec.wordstore.VocabCache;
import org.deeplearning4j.text.sentenceiterator.LineSentenceIterator;
import org.deeplearning4j.text.sentenceiterator.SentenceIterator;
import org.deeplearning4j.text.sentenceiterator.SentencePreProcessor;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collection;
import java.util.Iterator;
import java.util.Scanner;

public class Processor {

    private Word2Vec_Thing vect;

    public Processor(){

    }

    public String[] bagOfWords(String tweet){
        String[] temp = tweet.replaceAll("[^a-zA-Z ]", "").toLowerCase().split("\\s+");
        String[] out = new String[temp.length+1];
        for(int i = 0; i < temp.length; i++){
            out[i] = temp[i];
        }
        out[out.length-1] = "0";
        return out;
    }

    public void test(){
        double val = 1 / (double)5;
        this.vect = new Word2Vec_Thing();
        this.vect.BuildModel("/home/kell/IdeaProjects/MeatBurrito2020/src/main/java/Data/newTweets.txt");
        this.vect.Train(1);
        int wordCount = (int)this.vect.vocabSize();
        VocabCache<VocabWord> words = this.vect.vocab();
        double[][] indices = new double[wordCount][wordCount];
        int j;
        Word2Vec temp = this.vect.returnModel();
        for(int i = 0; i < wordCount; i++){
            String word = words.wordAtIndex(i);
            double[] vals = temp.getWordVector(word);
            double sum = 0;
            for(int k = 0; k < vals.length; k++){
                if(vals[k] < 0){
                    vals[k] = 0;
                }
                sum += vals[k];
            }
            double newsum = 0;
            for(int k = 0; k < vals.length; k++){
                vals[k] = vals[k] / sum;
                newsum += vals[k];
            }
            indices[i] = vals;
        }
    }

    public double[][] relatedWords(String[] inputFiles, int relatedCount, int trainCount){
        double val = 1 / (double)relatedCount;
        this.vect = new Word2Vec_Thing();
        this.vect.BuildModel(inputFiles[0]); // TODO allow multiple inputs
        this.vect.Train(trainCount);
        int wordCount = (int)this.vect.vocabSize();
        VocabCache<VocabWord> words = this.vect.vocab();
        double[][] indices = new double[wordCount][wordCount];
        int j;
        Word2Vec temp = this.vect.returnModel();
        System.out.println("Normalizing vectors...");
        for(int i = 0; i < wordCount; i++){
            String word = words.wordAtIndex(i);
            double[] vals = temp.getWordVector(word);
            double sum = 0;
            for(int k = 0; k < vals.length; k++){
                if(vals[k] < 0){
                    vals[k] = 0;
                }
                sum += vals[k];
            }
            double newsum = 0;
            for(int k = 0; k < vals.length; k++){
                vals[k] = vals[k] / sum;
                newsum += vals[k];
            }
            indices[i] = vals;
        }
        System.out.println("vectors normalized.");
        return indices;
    }

    public void printBestWords(){
        long wordCount = this.vect.vocabSize();
        VocabCache<VocabWord> words = this.vect.vocab();
        for(long i = 0; i < wordCount; i++){
            String word = words.wordAtIndex((int)i);
            System.out.print(word + ": ");
            Collection related = this.vect.getWordsNearest(word,5);
            System.out.println(related);
        }
    }

    public String[] cipher(){
        System.out.println("Creating cipher...");
        Collection<String> collec = this.vect.vocab().words();
        String[] out = new String[collec.size()];
        Iterator<String> iter = collec.iterator();
        for(int i = 0; i < out.length; i++){
            out[i] = iter.next();
        }
        System.out.println("Cipher created.");
        return out;
    }

    public int[] getmapping(String[] inputfilepaths){
        System.out.println("Generating mappings...");
        int[][] rawmappings = new int[inputfilepaths.length][];
        for(int i = 0; i < inputfilepaths.length; i++){
            File fl = new File(inputfilepaths[i]);
            try {
                String data = new String(Files.readAllBytes(Paths.get(inputfilepaths[i])));
                String[] words = data.replaceAll("[^a-zA-Z.@ ]", "").toLowerCase().split("\\s+");
                int[] mappings = new int[words.length];
                VocabCache<VocabWord> vocab = this.vect.vocab();
                for(int k = 0; k < mappings.length; k++){
                    mappings[k] = vocab.indexOf(words[k]);
                }
                rawmappings[i] = mappings;
            } catch (IOException e) {
                e.printStackTrace();
                break;
            }
            System.out.println("File " + (i+1) + " out of " + inputfilepaths.length + " mapped.");
        }
        return jaggedMatrixToArray(rawmappings);
    }

    public int[] jaggedMatrixToArray(int[][] matrix){
        System.out.println("Flattening mappings...");
        int tot = 0;
        for(int i = 0; i < matrix.length; i++){
            tot += matrix[i].length;
        }
        int[] out = new int[tot];
        for(int i = 0; i < matrix.length; i++){
            int index = 0;
            for(int j = 0; j < i; j++){
                index += matrix[j].length;
            }
            for(int j = 0; j < matrix[i].length; j++){
                out[index+j] = matrix[i][j];
            }
        }
        System.out.println("Mappings generated.");
        return out;
    }
}
