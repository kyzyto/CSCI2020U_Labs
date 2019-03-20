package sample;

import java.io.*;
import java.util.TreeMap;

public class WeatherWarningData {
    private static TreeMap<String, Integer> data;

    public WeatherWarningData(){
        this.data = new TreeMap<String, Integer>();
    }

    public static void readData(String path, String delimiter) {
        // Special Case
        if (path == null || path.length() == 0) {
            return;
        }
        if(delimiter == null || delimiter.length() == 0) {
            delimiter = ",";
        }
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            //Read the next line until end of file
            for (String line; (line = br.readLine()) != null;) {
                //Parse the line
                String values = line.split(delimiter)[5];
                if (data.containsKey(values)){
                    data.put(values, (data.get(values) + 1));
                } else {
                    data.put(values, 1);
                }
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Integer[] getData(){
        Integer[] warnings = new Integer[4];
        warnings[0] = data.get("FLASH FLOOD");
        warnings[1] = data.get("SEVERE THUNDERSTORM");
        warnings[2] = data.get("SPECIAL MARINE");
        warnings[3] = data.get("TORNADO");

        return warnings;
    }
    public Integer[] noData(){
        return ( (Integer[]) data.values().toArray());
    }
}
