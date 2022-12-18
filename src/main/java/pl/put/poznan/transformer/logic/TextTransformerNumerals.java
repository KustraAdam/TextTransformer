package pl.put.poznan.transformer.logic;

public class TextTransformerNumerals extends TextTransformerDecorator{
    public TextTransformerNumerals(TextTransformerInterface decoratedTransformer) {
        super(decoratedTransformer);
    }

    private boolean isNumeric(String str) {
        try {
            str = str.replace(",","");
            Double.parseDouble(str);
            return true;
        } catch(NumberFormatException e){
            return false;
        }
    }

    private static final String[] unitsNames = {
            "",
            " jeden",
            " dwa",
            " trzy",
            " cztery",
            " pięć",
            " sześć",
            " siedem",
            " osiem",
            " dziewięć",
            " dziesięć",    // this instance never used in actual program
            " jedynaście",  // used only for keeping chronology of numbers
            " dwanaście",
            " trzynaście",
            " czternaście",
            " pietnaście",
            " szesnaście",
            " siedemnaście",
            " osiemnaście",
            " dziewietnaście"
    };

    private static final String[] tensNames = {
            "",
            " dziesięć",
            " dwadzieścia",
            " trzydzieści",
            " czterdzieści",
            " pięćdziesiąt",
            " sześćdziesiąt",
            " siedemdziesiąt",
            " osiemdziesiąt",
            " dziewięćdziesiąt"
    };

    private static final String[] hundredthsNames = {
            "",
            " sto",
            " dwieście",
            " trzysta",
            " czterysta",
            " pięćset",
            " sześćset",
            " siedemset",
            " osiemset",
            " dziewięćset"
    };

    private static final String[] unitFractionalNames = {
            "",
            " jedna dziesiąta",
            " dwie dziesiąte",
            " trzy dziesiąte",
            " cztery dziesiąte",
            " pięć dziesiątych",
            " sześć dziesiątych",
            " siedem dziesiątych",
            " osiem dziesiątych",
            " dziewięć dziesiątych"
    };

    private static String Num2word(String number){
        // preparing number for conversion, countering programme errors
        if(number.indexOf(',') != -1){
            number = number.replace(',', '.');}
        if(number.substring(number.length() - 1).equals(".")){
            number = number.substring(0, number.length() - 1);
        }

        String convNumber = "";  // converted number
        double numberDouble = Double.parseDouble(number); // changing from String to double
        int numberInteger = 0;
        int numberFractional = 0;

        // converting all numbers to int in order to use them conveniently later
        if(number.indexOf('.') != -1){
            numberFractional = (int)(numberDouble * 100);
            numberInteger = numberFractional/100;
            numberFractional = numberFractional % 100;
        }
        else{
            numberInteger = Integer.parseInt(number);}

        // converting integer numbers
        if (numberInteger >= 100) {
            convNumber = convNumber + hundredthsNames[numberInteger / 100];
            numberInteger = numberInteger % 100; // discarding unnecessary numbers
        }
        if (numberInteger >= 20 || numberInteger%10 == 0) {
            convNumber = convNumber + tensNames[numberInteger / 10];
            numberInteger = numberInteger % 10;
        }
        if (numberInteger >= 0) {
            convNumber = convNumber + unitsNames[numberInteger];}

        // handling fractional part of number
        if (numberFractional > 0){
            if (numberFractional % 10 == 0){
                convNumber = convNumber + " i" + unitFractionalNames[numberFractional/10];
            }
            else{
                if(numberFractional < 20 && numberFractional > 1){
                    convNumber = convNumber + " i" + unitsNames[numberFractional] + " setnych";}
                else if (numberFractional == 1) { convNumber = convNumber + " i" + " jedna setna";}
                else if (numberFractional >= 20) {
                    convNumber = convNumber + " i" + tensNames[numberFractional / 10];
                    numberFractional = numberFractional % 10;

                    if (numberFractional == 2) {
                        convNumber = convNumber + " dwie";
                    } else {
                        convNumber = convNumber + unitsNames[numberFractional];
                    }

                    if (numberFractional == 2 || numberFractional == 3 || numberFractional == 4) {
                        convNumber = convNumber + " setne";
                    } else {
                        convNumber = convNumber + " setnych";
                    }
                }}}

        return convNumber;
    }
    @Override
    public String transform(String text) {
        // If the object wraps another transformer, perform its transform
        if (decoratedTransformer != null) {
            text = decoratedTransformer.transform(text);
        }
        String[] splitText;
        String convertedText = "";

        splitText = text.split(" ");
        for(int i = 0; i < splitText.length; i++)
        {
            if(isNumeric(splitText[i]) == false){
                convertedText += splitText[i] += " ";
            }

            else{
                convertedText = convertedText + Num2word(splitText[i]) + " ";
            }
        }

        text = convertedText;
        return text;
    }
}
