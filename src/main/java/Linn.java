import org.json.JSONObject;

import java.io.FileNotFoundException;
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
    private String tuulesuund;
    private int clouds;
    private long sunrise;
    private long sunset;
    private long dt; // aeg mil viimati ilmateate andmeid uuendati
    private boolean cityExict;

    public Linn(String linn) throws Exception {
        String ilmateade = String.format("http://api.openweathermap.org/data/2.5/weather?q=%s,ee&APPID=d87e964760570c1332f3d5a453769811", linn);
        try {
            GetData data = new GetData();
            JSONObject hetkeilm = new JSONObject(data.getData(ilmateade));

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
            this.cityExict = true;

        } catch (FileNotFoundException teade) {
            this.name = linn;
            this.cityExict = false;
        }
    }

    public boolean getCityExict() {
        return cityExict;
    }

    @Override
    public String toString() {

        SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss z");
        sdf.setTimeZone(java.util.TimeZone.getTimeZone("GMT+2"));

        if (!cityExict) {
            return "\n\"" + name + "\" linna ei eksisteeri";
        } else {
            return "\nLinna andmed: " +
                    "\n\nidapikkus=" + lon +
                    "\npõhjalaius=" + lat +
                    "\nilma kirjeldus='" + weather+
                    "\ntemperatuur=" + String.format("%.2f",temp-273) + " C" +
                    "\nõhurõhk=" + pressure + " hPa" +
                    "\nõhuniiskus=" + humidity + "%"+
                    "\nnähtavus=" + visibility + " m"+ //nähtavus kirjeldab mitme meetri kaugusele on näha
                    "\ntuule kiirus=" + windSpeed + " m/s" +
                    "\ntuule suund=" + windDeg + " kraadi" +
                    "\npilvisus=" + clouds + "%" +
                    "\npäike tõuseb=" + sdf.format(sunrise * 1000L) +
                    "\npäike loojub=" + sdf.format(sunset * 1000L) +
                    "\nAndmeid uuendati viimati=" + sdf.format(dt * 1000L);
        }
    }
}
