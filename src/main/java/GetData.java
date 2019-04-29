import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

public class GetData {
    public String getData(String url) throws Exception {
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
}
