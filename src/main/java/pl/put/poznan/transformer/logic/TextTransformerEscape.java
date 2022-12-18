package pl.put.poznan.transformer.logic;

public class TextTransformerEscape extends TextTransformerDecorator {
    public TextTransformerEscape(TextTransformerInterface decoratedTransformer) {
        super(decoratedTransformer);
    }

    @Override
    public String transform(String text) {
        // If the object wraps another transformer, perform its transform
        if (decoratedTransformer != null) {
            text = decoratedTransformer.transform(text);
        }
        // Make sure to escape twice in case of backslash
        text = text.replaceAll("\\$", "\\\\\\$");
        text = text.replaceAll("&", "\\\\&");
        return text;
    }
}
