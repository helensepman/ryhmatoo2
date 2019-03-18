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
    private long dt; // aeg mil viimati ilmateate andmeid uuendati
    private int cityExict;

    public Linn(String linn) throws Exception {
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
        } catch (FileNotFoundException teade) {
            this.name = linn;
            this.cityExict = 0;
        }
    }

    @Override
    public String toString() {

        SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss z");
        sdf.setTimeZone(java.util.TimeZone.getTimeZone("GMT+2"));

        if (cityExict <= 0) {
            return "\"" + name + "\" linna ei eksisteeri";
        } else {
            return "Linn{" +
                    "lon=" + lon +
                    ", lat=" + lat +
                    ", ilm='" + weather+
                    ", temperatuur=" + (Double.valueOf(temp-273)) + " C" +
                    "\nõhurõhk=" + pressure + " hPa" +
                    ", õhuniiskus=" + humidity +
                    ", nähtavus=" + visibility +
                    ", tuule kiirus=" + windSpeed + " m/s" +
                    "\nTuule suund=" + windDeg + " kraadi" +
                    ", pilvisus=" + clouds + "%" +
                    ", päike tõuseb=" + sdf.format(sunrise * 1000L) +
                    ", päike loojub=" + sdf.format(sunset * 1000L) +
                    ", dt=" + sdf.format(dt * 1000L) +
                    '}';
        }
    }



    public static String getData(String url) throws Exception {
        URL webpage = new URL(url);
        BufferedReader in = new BufferedReader(
                new InputStreamReader(webpage.openStream()));

        String inputLine;
        String rida = "";
        while ((inputLine = in.readLine()) != null)
            rida = inputLine;
        in.close();
        return rida;
    }

    public String eestiVirmalised() throws Exception{
        Virmalised virmalised = new Virmalised();
        double kpIndeks = virmalised.kpIndeks();
        double päikesetuul = virmalised.päikesetuulMagnetväli()[0];
        double magnetväli = virmalised.päikesetuulMagnetväli()[1];
        String põhiinfo = "Päikesetuul on " + päikesetuul + " km/s, magnetväli " + magnetväli + " nT ning Kp indeks on " + kpIndeks;
        if (magnetväli >= 0 && päikesetuul > 532) {
            return põhiinfo + "\nGeomagneetiline aktiivsus on tõusnud, kuid kuna magnetväli on positiivne, siis ilmselt virmalisi ei näe.";
        }
        if (magnetväli<0 && (päikesetuul>393 || kpIndeks >= 4)){
            return põhiinfo + "\nGeomagneetiline aktiivsus on tõusnud; väike tõenäosus näha virmalisi";
        }
        if (magnetväli<0 && (päikesetuul>532 || kpIndeks >= 5)){
            return põhiinfo + "\nGeomagneetiline aktiivsus on kõrge; keskmine kuni suur tõenäosus näha virmalisi";
        }
        if (magnetväli<0 && (päikesetuul>602 || kpIndeks >= 6)){
            return põhiinfo + "Geomagneetiline aktiivsus on väga kõrge; suur tõenäosus näha virmalisi";
        }
        return "Geomagneetiline aktiivsus on madal, virmalisi pole näha"; //default-lause kui ükski tene if lause ei sobi
    }



}
