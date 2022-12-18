package pl.put.poznan.transformer.logic;

/**
 * This class decorates a TextTransformerInterface with an added implementation of the "lower" transform.
 * @author Jędrzej Górski
 */
public class TextTransformerLower extends TextTransformerDecorator{

    /**
     * Calls the superclass constructor with a passed TextTransformerInterface.
     * @param decoratedTransformer The TextTransformerInterface to be decorated.
     */
    public TextTransformerLower(TextTransformerInterface decoratedTransformer) {
        super(decoratedTransformer);
    }

    /**
     * This method implements the "lower" transform.
     * @param text The input to be transformed.
     * @return The output of the transformation.
     */
    @Override
    public String transform(String text) {
        // If the object wraps another transformer, perform its transform
        if (decoratedTransformer != null) {
            text = decoratedTransformer.transform(text);
        }
        return text.toLowerCase();
    }
}
