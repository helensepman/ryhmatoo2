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
    }
}
