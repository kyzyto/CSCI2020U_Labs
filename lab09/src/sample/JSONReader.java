package sample;

import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonParser;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.IOException;
import java.io.Reader;
import java.net.URL;

public class JSONReader {
    // downloads JSON file from web
    public static JsonObject download(String url) {
        return getJson(url);
    }

    // reads JSON file
    public static String readAll(Reader reader) throws IOException {
        StringBuilder builder = new StringBuilder();

        int ch;
        while ((ch = reader.read()) != -1){
            builder.append((char)ch);
        }

        return builder.toString();
    }

    // retrieves and parses the JSON file
    private static JsonObject getJson(String url) {
        InputStream inStream = null;
        JsonObject json = null;

        try {
            inStream = new URL(url).openStream();
            String jsonString = readAll(new InputStreamReader(inStream));
            json = new JsonParser().parse(jsonString).getAsJsonObject();
        } catch (IOException e) {
            System.err.println("unable to retrieve json data");
            e.printStackTrace();
        } catch (JsonParseException e) {
            System.err.println("unable to parse json file");
            e.printStackTrace();
        } finally {
            try {
                inStream.close();
            } catch (IOException e) {
                System.err.println("unable to close connection");
                e.printStackTrace();
            }
        }
        return json;
    }
}
