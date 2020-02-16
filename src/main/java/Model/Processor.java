package Model;

import org.deeplearning4j.models.word2vec.VocabWord;
import org.deeplearning4j.models.word2vec.wordstore.VocabCache;

import java.util.Collection;
import java.util.Iterator;

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
        for(int i = 0; i < wordCount; i++){
            String word = words.wordAtIndex(i);
            Collection related = this.vect.getWordsNearest(word,relatedCount);
            Iterator iter = related.iterator();
            while(iter.hasNext()){
                j = words.indexOf((String)iter.next());
                indices[i][j] = val;
                //System.out.println(i + " " + j);
            }
        }
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
}
