package pl.put.poznan.transformer.logic;

import java.util.ArrayList;

public class TextTransformerEquivalent extends TextTransformerDecorator {
    /**
     * Calls the superclass constructor with a passed TextTransformerInterface.
     * @param decoratedTransformer The TextTransformerInterface to be decorated.
     */
    public TextTransformerEquivalent(TextTransformerInterface decoratedTransformer) {
        super(decoratedTransformer);
    }

    /**
     * This method implements the "capitalize" transform, which sets the letter case of the first alphabetic character
     * of each space-separated substring.
     * @param text The input to be transformed.
     * @return The output of the transformation.
     */
    @Override
    public String transform(String text) {
        // If the object wraps another transformer, perform its transform
        if (decoratedTransformer != null) {
            text = decoratedTransformer.transform(text);
        }

        text = text.replaceAll("ą","a");
        text = text.replaceAll("Ą","A");
        text = text.replaceAll("ę","e");
        text = text.replaceAll("Ę","E");
        text = text.replaceAll("ć","c");
        text = text.replaceAll("Ć","C");
        text = text.replaceAll("Ł","L");
        text = text.replaceAll("ł","l");
        text = text.replaceAll("ó","o");
        text = text.replaceAll("Ó","O");
        text = text.replaceAll("ń","n");
        text = text.replaceAll("Ń","N");
        text = text.replaceAll("ź","z");
        text = text.replaceAll("Ź","Z");
        text = text.replaceAll("ż","z");
        text = text.replaceAll("Ż","Z");
        text = text.replaceAll("Ś","S");
        text = text.replaceAll("ś","s");



      return text;
    }
}
