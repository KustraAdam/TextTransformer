package pl.put.poznan.transformer.logic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
                    List<String> words = new ArrayList<String>(Arrays.asList(text.split(" ")));

                    // Iterate over every element in the list and abbreviate words
                    for(int i = 0 ; i < words.size(); i++) {
                        if(words.get(i).equalsIgnoreCase("profesor")){
                            words.set(i,words.get(i).substring(0, 4)+".");
                        }
                        else if(words.get(i).equalsIgnoreCase("doktor")){
                            words.set(i,words.get(i).replace(words.get(i).substring(1,5),"")+".");
                        }
                        else if(i < words.size()-1 && words.get(i).equalsIgnoreCase("na") && words.get(i+1).equalsIgnoreCase("przykład")){
                            words.set(i,words.get(i).substring(0,1)+words.get(i+1).charAt(0)+".");
                            words.remove(i+1);
                        }
                        else if(i < words.size()-2 && words.get(i).equalsIgnoreCase("i") && words.get(i+1).equalsIgnoreCase("tak") && words.get(i+2).equalsIgnoreCase("dalej")){
                            words.set(i,words.get(i).substring(0,1)+words.get(i+1).charAt(0)+words.get(i+2).charAt(0)+".");
                            //Remove next element twice (as we want to remove two elements)
                            words.remove(i+1);
                            words.remove(i+1);
                        }
                    }

                    //Creating string buffer to convert list to string
                    StringBuffer sb1 = new StringBuffer();
                    for(int i = 0; i < words.size()-1; i++) {
                        sb1.append(words.get(i)+" ");
                    }
                    sb1.append(words.get(words.size()-1));

                    text = sb1.toString();
                    break;
                case "stands-for":
                    String[] temparray = text.split(" ");

                    // Iterate over every word in the array and replace the abbreviations with full text
                    for(int i = 0 ; i < temparray.length ; i++){
                        if(temparray[i].equalsIgnoreCase("prof.")){
                            if(temparray[i].equals("PROF.")) temparray[i]="PROFESOR";
                            else temparray[i]=temparray[i].charAt(0)+"rofesor";
                        }
                        else if(temparray[i].equalsIgnoreCase("dr.")){
                            if(temparray[i].equals("DR.")) temparray[i]="DOKTOR";
                            else temparray[i]=temparray[i].charAt(0)+"oktor";
                        }
                        else if(temparray[i].equalsIgnoreCase("np.")){
                            if(temparray[i].equals("NP.")) temparray[i]="NA PRZYKLAD";
                            else temparray[i]=temparray[i].charAt(0)+"a przykład";
                        }
                        else if(temparray[i].equalsIgnoreCase("itd.")){
                            if(temparray[i].equals("ITD.")) temparray[i]="I TAK DALEJ";
                            else temparray[i]=temparray[i].charAt(0)+" tak dalej";
                        }
                    }

                    //Creating string buffer to convert array to string
                    StringBuffer sb2 = new StringBuffer();
                    for(int i = 0; i < temparray.length-1; i++) {
                        sb2.append(temparray[i]+" ");
                    }
                    sb2.append(temparray[temparray.length-1]);

                    text = sb2.toString();
                    break;
                case "escape":
                    //Creating string buffer to easily insert a new character
                    StringBuffer tempsb = new StringBuffer(text);
                    for(int i = 0; i<text.length();i++){
                        if(tempsb.charAt(i)=='$' || tempsb.charAt(i)=='&'){
                            tempsb.insert(i, '\\');
                            i++;
                        }
                    }
                    //Convert string buffer to string
                    text=tempsb.toString();
                    break;
                case "trim":
                    // TODO: Implement removal of subsequent repetitions
                    break;
            }

        }
        return text;
    }
}
