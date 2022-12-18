package pl.put.poznan.transformer.logic;

/**
 * This is the interface, which declares the transform method for TextTransformer objects.
 */
public interface TextTransformerInterface {
    /** This is the method, where the transform logic is implemented.
     *
     * @param text The input to be transformed.
     * @return The output of the transformation.
     */
    public String transform(String text);

}
