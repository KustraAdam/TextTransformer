package pl.put.poznan.transformer.logic;

/**
 * This is the abstract decorator class, which defines the decoratedTransformer member to provide a nested structure
 * for TextTransformerInterfaces.
 */
public abstract class TextTransformerDecorator implements TextTransformerInterface{
    /**
     * The decorated Transformer, the transformation of which will be applied before the parent object's.
     */
    protected TextTransformerInterface decoratedTransformer;

    /**
     * The superclass constructor for all decorators. Allows the passing of another TextTransformerInterface to be
     * decorated. The passed TextTransformerInterface *can* be null.
     * @param decoratedTransformer The TextTransformerInterface to be decorated.
     */
    public TextTransformerDecorator(TextTransformerInterface decoratedTransformer) {
        this.decoratedTransformer = decoratedTransformer;

    }

}
