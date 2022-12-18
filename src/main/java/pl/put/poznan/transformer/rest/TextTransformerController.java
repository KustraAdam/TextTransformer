package pl.put.poznan.transformer.rest;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import pl.put.poznan.transformer.logic.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static java.lang.Character.*;


@RestController
@RequestMapping("/{text}")
public class TextTransformerController {

    private static final Logger logger = LoggerFactory.getLogger(TextTransformerController.class);

    private void logParameters(String text, String[] transforms) {
        logger.debug(text);
        logger.debug(Arrays.toString(transforms));
    }

    // TODO: Make this return a JSON instead
    private String doTransforms(String text, String[] transforms) {
        TextTransformerDecorator currentTransform = null, prevTransform = null;
        for (String transform: transforms) {
            // Create new TextTransformDecorator based on the type provided in transform
            // Pass prevTransform to the constructor to nest the decorators
            switch (transform) {
                case "upper":
                    currentTransform = new TextTransformerUpper(prevTransform);
                    break;
                case "lower":
                    currentTransform = new TextTransformerLower(prevTransform);
                    break;
                case "capitalize":
                    currentTransform = new TextTransformerCapitalize(prevTransform);
                    break;
                case "inverse":
                    currentTransform = new TextTransformerInverse(prevTransform);
                    break;
                case "numerals":
                    currentTransform = new TextTransformerNumerals(prevTransform);
                    break;
                case "abbreviate":
                    currentTransform = new TextTransformerAbbreviate(prevTransform);
                    break;
                case "stands-for":
                    currentTransform = new TextTransformerStandsFor(prevTransform);
                    break;
                case "escape":
                    currentTransform = new TextTransformerEscape(prevTransform);
                    break;
                case "trim":
                    currentTransform = new TextTransformerTrim(prevTransform);
                    break;
            }
            prevTransform = currentTransform;
        }
        return currentTransform.transform(text);
    }

    @RequestMapping(method = RequestMethod.GET, produces = "application/json")
    public JSONObject get(@PathVariable String text,
                              @RequestParam(value="transforms", defaultValue="upper,escape") String[] transforms) {

        logParameters(text, transforms);

        return JSONPacker.packJSON(text, doTransforms(text, transforms), transforms);
    }

    @RequestMapping(method = RequestMethod.POST, produces = "application/json")
    public JSONObject post(@PathVariable String text,
                      @RequestBody String[] transforms) {

        logParameters(text, transforms);

        return JSONPacker.packJSON(text, doTransforms(text, transforms), transforms);
    }



}


