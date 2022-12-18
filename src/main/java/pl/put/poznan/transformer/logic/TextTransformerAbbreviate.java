package pl.put.poznan.transformer.logic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TextTransformerAbbreviate extends TextTransformerDecorator {
    public TextTransformerAbbreviate(TextTransformerInterface decoratedTransformer) {
        super(decoratedTransformer);
    }

    @Override
    public String transform(String text) {
        // If the object wraps another transformer, perform its transform
        if (decoratedTransformer != null) {
            text = decoratedTransformer.transform(text);
        }
        List<String> words = new ArrayList<String>(Arrays.asList(text.split(" ")));

        // Iterate over every element in the list and abbreviate words
        for(int i = 0 ; i < words.size(); i++) {
            if(words.get(i).equalsIgnoreCase("profesor")){
                words.set(i,words.get(i).substring(0, 4)+".");
            }
            else if(words.get(i).equalsIgnoreCase("doktor")){
                words.set(i,words.get(i).replace(words.get(i).substring(1,5),"")+".");
            }
            else if(i < words.size() - 1
                    && words.get(i).equalsIgnoreCase("na")
                    && words.get(i + 1).equalsIgnoreCase("przykład")){
                words.set(i,words.get(i).substring(0, 1)+words.get(i + 1).charAt(0)+".");
                words.remove(i + 1);
            }
            else if(i < words.size()-2
                    && words.get(i).equalsIgnoreCase("i")
                    && words.get(i + 1).equalsIgnoreCase("tak")
                    && words.get(i + 2).equalsIgnoreCase("dalej")){
                words.set(i,words.get(i).substring(0, 1)+words.get(i + 1).charAt(0)+words.get(i + 2).charAt(0) + ".");
                //Remove next element twice (as we want to remove two elements)
                words.remove(i + 1);
                words.remove(i + 1);
            }
        }

        // Create string buffer to convert list to string
        StringBuffer sb = new StringBuffer();
        for(int i = 0; i < words.size()-1; i++) {
            sb.append(words.get(i)+" ");
        }
        sb.append(words.get(words.size()-1));

        text = sb.toString();
        return text;
    }
}
