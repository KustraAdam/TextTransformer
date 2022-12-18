package pl.put.poznan.transformer.logic;

import java.util.ArrayList;

/**
 * This class decorates a TextTransformerInterface with an added implementation of the "capitalize" transform.
 * @author Jędrzej Górski
 */
public class TextTransformerCapitalize extends TextTransformerDecorator {
    /**
     * Calls the superclass constructor with a passed TextTransformerInterface.
     * @param decoratedTransformer The TextTransformerInterface to be decorated.
     */
    public TextTransformerCapitalize(TextTransformerInterface decoratedTransformer) {
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
        String[] separatedText = text.split(" ");
        ArrayList<String> capitalizedWords = new ArrayList<>();
        // Iterate over the space-separated substrings of the input
        for (String word : separatedText) {
            // If the substring is 1 character long, call toUpperCase() on it and add it to the output
            if (word.length() == 1) {
                capitalizedWords.add(word.toUpperCase());
            }
            // If the string ends or starts with a whitespace, the split array will start with an empty string. In order
            // to circumvent this, skip the "word" if its length is 0.
            else if (word.length() == 0) {
                continue;
            }
            // Otherwise, capitalize the first character and concatenate the rest of the substring unchanged
            else {
                capitalizedWords.add(word.substring(0, 1).toUpperCase() + word.substring(1));
            }
        }
        // The final output is made up of the contents of capitalizedWords joined together with a space
        return(String.join(" ", capitalizedWords));
    }
}
