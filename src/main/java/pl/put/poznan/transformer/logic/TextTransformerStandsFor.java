package pl.put.poznan.transformer.logic;

import org.w3c.dom.Text;

public class TextTransformerStandsFor extends TextTransformerDecorator {
    public TextTransformerStandsFor(TextTransformerInterface decoratedTransformer) {
        super(decoratedTransformer);
    }

    @Override
    public String transform(String text) {
        // If the object wraps another transformer, perform its transform
        if (decoratedTransformer != null) {
            text = decoratedTransformer.transform(text);
        }
        String[] tempArray = text.split(" ");

        // Iterate over every word in the array and replace the abbreviations with full text
        for(int i = 0 ; i < tempArray.length ; i++){
            if(tempArray[i].equalsIgnoreCase("prof.")){
                if(tempArray[i].equals("PROF.")) tempArray[i]="PROFESOR";
                else tempArray[i]=tempArray[i].charAt(0)+"rofesor";
            }
            else if(tempArray[i].equalsIgnoreCase("dr.")){
                if(tempArray[i].equals("DR.")) tempArray[i]="DOKTOR";
                else tempArray[i]=tempArray[i].charAt(0)+"oktor";
            }
            else if(tempArray[i].equalsIgnoreCase("np.")){
                if(tempArray[i].equals("NP.")) tempArray[i]="NA PRZYKLAD";
                else tempArray[i]=tempArray[i].charAt(0)+"a przykład";
            }
            else if(tempArray[i].equalsIgnoreCase("itd.")){
                if(tempArray[i].equals("ITD.")) tempArray[i]="I TAK DALEJ";
                else tempArray[i]=tempArray[i].charAt(0)+" tak dalej";
            }
        }

        // Create string buffer to convert array to string
        StringBuffer sb = new StringBuffer();
        for(int i = 0; i < tempArray.length-1; i++) {
            sb.append(tempArray[i]+" ");
        }
        sb.append(tempArray[tempArray.length-1]);

        return sb.toString();
    }
}
