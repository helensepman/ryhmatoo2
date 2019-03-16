import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.net.URL;
import java.text.SimpleDateFormat;

public class Linn {
    private String name;
    private double lon;
    private double lat;
    private String weather;
    private double temp;
    private int pressure;
    private int humidity;
    private int visibility;
    private double windSpeed;
    private int windDeg;
    private int clouds;
    private long sunrise;
    private long sunset;
    private long dt; // aeg mil ilmateate andmeid uuendati
    private int cityExict;

    public Linn(String linn) throws Exception{
        String ilmateade = String.format("http://api.openweathermap.org/data/2.5/weather?q=%s,ee&APPID=d87e964760570c1332f3d5a453769811", linn);
        try {
            JSONObject hetkeilm = new JSONObject(getData(ilmateade));

            this.name = linn;
            this.lon = hetkeilm.getJSONObject("coord").getDouble("lon");
            this.lat = hetkeilm.getJSONObject("coord").getDouble("lat");
            this.weather = hetkeilm.getJSONArray("weather").getJSONObject(0).getString("description");
            this.temp = hetkeilm.getJSONObject("main").getDouble("temp");
            this.pressure = hetkeilm.getJSONObject("main").getInt("pressure");
            this.humidity = hetkeilm.getJSONObject("main").getInt("humidity");
            this.visibility = hetkeilm.getInt("visibility");
            this.windSpeed = hetkeilm.getJSONObject("wind").getDouble("speed");
            this.windDeg = hetkeilm.getJSONObject("wind").getInt("deg");
            this.clouds = hetkeilm.getJSONObject("clouds").getInt("all");
            this.sunrise = hetkeilm.getJSONObject("sys").getLong("sunrise");
            this.sunset = hetkeilm.getJSONObject("sys").getLong("sunset");
            this.dt = hetkeilm.getLong("dt");
            this.cityExict = 1;
        }catch (FileNotFoundException teade){
            this.name = linn;
            this.cityExict = 0;
        }
    }

    @Override
    public String toString() {

        SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss z");
        sdf.setTimeZone(java.util.TimeZone.getTimeZone("GMT+2"));

        if (cityExict <= 0){
            return "\"" + name + "\" linna ei eksisteeri";
        }else {
            return "Linn{" +
                    "lon=" + lon +
                    ", lat=" + lat +
                    ", weather='" + weather +
                    ", temp=" + temp +
                    ", pressure=" + pressure +
                    ", humidity=" + humidity +
                    ", visibility=" + visibility +
                    ", windSpeed=" + windSpeed +
                    ", windDeg=" + windDeg +
                    ", clouds=" + clouds +
                    ", sunrise=" + sdf.format(sunrise*1000L) +
                    ", sunset=" + sdf.format(sunset*1000L) +
                    ", dt=" + sdf.format(dt*1000L) +
                    '}';
        }
    }



    public String[] eestiVirmalised() throws Exception{
        String url = "https://services.swpc.noaa.gov/text/ace-swepam.txt";
        Virmalised virmalised = new Virmalised();
        String[] virm = virmalised.puhastatud(getData(url));
        return virm;
    }

    public void virmalisedLinnas(String url, Linn linn) throws Exception{
        String gurl = new String();
    }

    public static String getData(String url) throws Exception{
        URL oracle = new URL(url);
        BufferedReader in = new BufferedReader(
                new InputStreamReader(oracle.openStream()));

        String inputLine;
        String rida = "";
        while ((inputLine = in.readLine()) != null)
            rida = inputLine;
        in.close();
        return rida;
    }
}
