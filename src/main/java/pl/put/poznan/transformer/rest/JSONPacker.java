package pl.put.poznan.transformer.rest;

import org.json.simple.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JSONPacker {
    public static JSONObject packJSON(String inputText, String outputText, String[] transforms) {
        JSONObject obj = new JSONObject();
        obj.put("input", inputText);
        obj.put("output", outputText);
        obj.put("transforms", List.of(transforms));
        return obj;
    }
}
