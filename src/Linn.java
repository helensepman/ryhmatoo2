import java.net.*;
import java.io.*;

public class Linn {
    public static void main(String[] args) throws Exception {
        System.out.println("Algus");
        URL oracle = new URL("http://api.openweathermap.org/data/2.5/weather?q=Tartu,ee&APPID=d87e964760570c1332f3d5a453769811");
        BufferedReader in = new BufferedReader(
                new InputStreamReader(oracle.openStream()));

        String inputLine;
        while ((inputLine = in.readLine()) != null)
            System.out.println(inputLine);
        in.close();
        System.out.println();
        Virmalised virm = new Virmalised();
        System.out.println(virm.päikesetuul(virm.puhastatud(getData("https://services.swpc.noaa.gov/text/ace-swepam.txt"))));

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
