package pl.put.poznan.transformer.logic;

import java.util.ArrayList;
import java.util.Arrays;

import static java.lang.Character.*;

/**
 * This is just an example to show that the logic should be outside the REST service.
 */
public class TextTransformer {

    private final String[] transforms;

    public TextTransformer(String[] transforms){
        this.transforms = transforms;
    }

    public String transform(String text){
        // Perform transformations until the entirety of transforms has been traversed
        for (String transform: this.transforms) {
            switch (transform) {
                case "upper":
                    text = text.toUpperCase();
                    break;
                case "lower":
                    text = text.toLowerCase();
                    break;
                case "capitalize":
                    String[] separatedText = text.split(" ");
                    ArrayList<String> capitalizedWords = new ArrayList<>();
                    // Iterate over the space-separated substrings of the input
                    for (String word : separatedText) {
                        // If the substring is 1 character long, call toUpperCase() on it and add it to the output
                        if (word.length() == 1) {
                            capitalizedWords.add(word.toUpperCase());
                        }
                        // Otherwise, capitalize the first character and concatenate the rest of the substring unchanged
                        else {
                            capitalizedWords.add(word.substring(0, 1).toUpperCase() + word.substring(1));
                        }
                    }
                    // The final output is made up of the contents of capitalizedWords joined together with a space
                    text = String.join(" ", capitalizedWords);
                    break;
                case "inverse":
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
                    text = String.valueOf(transformedArray);
                    break;
                case "numerals":
                    // TODO: Implement conversion of numbers to numerals
                    break;
                case "abbreviate":
                    // TODO: Implement abbreviations to na przykład; między innymi; i tym podobne
                    break;
                case "stands-for":
                    String[] temp = text.split(" ");

                    for(int i=0;i<temp.length;i++){
                        if(temp[i].equalsIgnoreCase("prof.")){
                            temp[i]=temp[i].charAt(0)+"rofesor";
                        }
                        else if(temp[i].equalsIgnoreCase("Dr.")){
                            temp[i]=temp[i].charAt(0)+"oktor";
                        }
                        else if(temp[i].equalsIgnoreCase("Np.")){
                            temp[i]=temp[i].charAt(0)+"a przykład";
                        }
                        else if(temp[i].equalsIgnoreCase("Itd.")){
                            temp[i]=temp[i].charAt(0)+" tak dalej";
                        }
                    }

                    StringBuffer sb = new StringBuffer();
                    for(int i = 0; i < temp.length-1; i++) {
                        sb.append(temp[i]+" ");
                    }
                    sb.append(temp[temp.length-1]);

                    text = sb.toString();
                    break;
                case "escape":
                    // TODO: Implement escaping LaTeX's special characters ($, &)
                    break;
                case "trim":
                    // TODO: Implement removal of subsequent repetitions
                    break;
            }

        }
        return text;
    }
}
