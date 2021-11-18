import java.io.*;
import java.net.*;
import java.nio.charset.Charset;

import org.json.JSONObject;

public class Solution {

    public static final String givenUrl = "https://en.wikipedia.org/w/api.php?action=parse&section=0&prop=text&format=json&page=Cincinnati";

    public static int getOccurrences(JSONObject json) throws Exception {
        JSONObject parsedObject = json.getJSONObject("parse");
        JSONObject textObject = parsedObject.getJSONObject("text");
        String textField = textObject.getString("*");
        int count = 0;
        for(int idx = -1; (idx = textField.indexOf("Cincinnati", idx + 1)) != -1; count++);
        return count;
    }

    public static String getJsonString(BufferedReader bf) throws IOException {
        StringBuilder builder = new StringBuilder();
        int pointer;
        while((pointer = bf.read()) != -1) {
            builder.append((char) pointer);
        }
        return builder.toString();
    }

    public static JSONObject getJson(String url) throws IOException {
        InputStream stream = new URL(url).openStream();
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(stream, Charset.forName("UTF-8")));
            String jsonString = getJsonString(reader);
            JSONObject json = new JSONObject(jsonString);
            return json;
        } finally {
            stream.close();
        }
    }

    public static void main(String[] args) throws Exception {
        JSONObject json = getJson(givenUrl);
        int occurrences = getOccurrences(json);
        System.out.println(occurrences);
    }
}
