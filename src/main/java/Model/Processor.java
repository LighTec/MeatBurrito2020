package Model;

public class Processor {

    public Processor(){

    }

    public String[] BagOfWords(String tweet){
        String[] temp = tweet.replaceAll("[^a-zA-Z ]", "").toLowerCase().split("\\s+");
        String[] out = new String[temp.length+1];
        for(int i = 0; i < temp.length; i++){
            out[i] = temp[i];
        }
        out[out.length-1] = "0";
        return out;
    }

    public double[][] relatedWords(String[] inputFiles){


        return null;
    }
}
