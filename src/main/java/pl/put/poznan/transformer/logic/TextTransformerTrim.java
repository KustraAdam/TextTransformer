package pl.put.poznan.transformer.logic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * This class decorates a TextTransformerInterface with an added implementation of the "trim" transform.
 * @author Jędrzej Górski
 */
public class TextTransformerTrim extends TextTransformerDecorator{
    /**
     * Calls the superclass constructor with a passed TextTransformerInterface.
     * @param decoratedTransformer The TextTransformerInterface to be decorated.
     */
    public TextTransformerTrim(TextTransformerInterface decoratedTransformer) {
        super(decoratedTransformer);
    }

    /**
     * This method implements the "trim" transform, which removes subsequent repetitions of space-separated substrings
     * from the input.
     * @author Karolina Sztolcman
     * @param text The input to be transformed.
     * @return The output of the transformation.
     */
    @Override
    public String transform(String text) {
        // If the object wraps another transformer, perform its transform
        if (decoratedTransformer != null) {
            text = decoratedTransformer.transform(text);
        }
        List<String> list = new ArrayList<String>(Arrays.asList(text.split(" ")));

        // Iterate over every element in the list and remove duplicate words
        for(int i = 0 ; i < list.size(); i++) {
            while(i<list.size() - 1 && list.get(i).equals(list.get(i + 1))){
                list.remove(i + 1);
            }
        }

        // Create string buffer to convert list to string
        StringBuffer sb = new StringBuffer();
        for(int i = 0; i < list.size() - 1; i++) {
            sb.append(list.get(i) + " ");
        }
        sb.append(list.get(list.size() - 1));

        text = sb.toString();
        return text;
    }
}
