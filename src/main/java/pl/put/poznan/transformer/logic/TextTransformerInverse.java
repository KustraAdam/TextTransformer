package pl.put.poznan.transformer.logic;

import static java.lang.Character.*;

/**
 * This class decorates a TextTransformerInterface with an added implementation of the "inverse" transform.
 * @author Jędrzej Górski
 */
public class TextTransformerInverse extends TextTransformerDecorator{
    /**
     * Calls the superclass constructor with a passed TextTransformerInterface.
     * @param decoratedTransformer The TextTransformerInterface to be decorated.
     */
    public TextTransformerInverse(TextTransformerInterface decoratedTransformer) {
        super(decoratedTransformer);
    }

    /**
     * This method implements the "inverse" transform, which mirrors the provided input, while maintaining
     * the original letterCase for each alphabetical character.
     * @param text The input to be transformed.
     * @return The output of the transformation.
     */
    @Override
    public String transform(String text) {
        // If the object wraps another transformer, perform its transform
        if (decoratedTransformer != null) {
            text = decoratedTransformer.transform(text);
        }
        Boolean[] letterCase = new Boolean[text.length()];
        char[] textArray = text.toCharArray();
        char[] transformedArray = new char[text.length()];
        // Iterate over every character in the input and set the corresponding index in letterCase
        // depending on whether the character is an upper case letter or not
        for (int i = 0; i < textArray.length; i++) {
            letterCase[i] = isUpperCase(textArray[i]);
        }
        // Iterate over every character in the input *backwards* and add it to the output, while changing
        // the case according to the value at the analogous index of letterCase (j)
        for (int i = textArray.length - 1; i >= 0; i--) {
            int j = textArray.length - 1 - i;
            if (letterCase[j]) {
                transformedArray[j] = toUpperCase(textArray[i]);
            }
            else {
                transformedArray[j] = toLowerCase(textArray[i]);
            }
        }
        return(String.valueOf(transformedArray));
    }
}
