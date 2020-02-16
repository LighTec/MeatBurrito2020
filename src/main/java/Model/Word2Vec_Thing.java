package Model;

import org.deeplearning4j.models.word2vec.VocabWord;
import org.deeplearning4j.models.word2vec.Word2Vec;
import org.deeplearning4j.models.word2vec.wordstore.VocabCache;
import org.deeplearning4j.text.sentenceiterator.LineSentenceIterator;
import org.deeplearning4j.text.sentenceiterator.SentenceIterator;
import org.deeplearning4j.text.sentenceiterator.SentencePreProcessor;
import org.deeplearning4j.text.tokenization.tokenizer.preprocessor.CommonPreprocessor;
import org.deeplearning4j.text.tokenization.tokenizerfactory.DefaultTokenizerFactory;
import org.deeplearning4j.text.tokenization.tokenizerfactory.TokenizerFactory;

import java.io.File;
import java.util.Collection;

public class Word2Vec_Thing {

    private Word2Vec thesaurus;

    public Word2Vec_Thing(){
    }

    public void BuildModel(String filepath){
        System.out.println("Beginning Word2Vec model creation");

        // Strip white space before and after for each line
        SentenceIterator iter = new LineSentenceIterator(new File(filepath));
        iter.setPreProcessor(new SentencePreProcessor() {
            public String preProcess(String sentence) {
                return sentence.replaceAll("[^a-zA-Z.@ ]", "").toLowerCase();
            }
        });

        // Split on white spaces in the line to get words
        TokenizerFactory t = new DefaultTokenizerFactory();
        t.setTokenPreProcessor(new CommonPreprocessor());

        this.thesaurus = new Word2Vec.Builder()
                .minWordFrequency(5)
                .layerSize(500)
                .seed(42)
                .windowSize(5)
                .iterate(iter)
                .tokenizerFactory(t)
                .build();

        System.out.println("Word2Vec created.");
    }

    public void Train(int count){
        for(int i = 0; i < count; i++){
            System.out.println("Training thesaurus");
            this.thesaurus.fit();
            System.out.println("Thesaurus trained.");
        }
    }

    public Collection<String> getWordsNearest(String in, int count){
        return this.thesaurus.wordsNearest(in,count);
    }

    public Word2Vec returnModel(){
    return this.thesaurus;
    }

    public long vocabSize(){
        return this.thesaurus.vocabSize();
    }

    public VocabCache<VocabWord> vocab(){
        return this.thesaurus.getVocab();
    }

    public double[] getVector(String word){
        return this.thesaurus.getWordVector(word);
    }
}
