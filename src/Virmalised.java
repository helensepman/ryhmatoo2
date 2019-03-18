import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class Virmalised {

    public double[] päikesetuulMagnetväli() throws Exception{
        double päikesetuul;
        double magnetväli; //vaadatakse Bz ehk põhjasuunalist komponenti ainult; see peab olema negatiivne


        String andmed = String.format("https://services.swpc.noaa.gov/products/geospace/propagated-solar-wind-1-hour.json");
        try {
            JSONObject GeoAndmed = new JSONObject(getData(andmed));
            päikesetuul = Double.valueOf()//vaja saada viimase massiivi [1] elementi
            magnetväli = Double.valueOf()//vaja saada viimase massiivi [6] elementi
        }
        finally { //kas siia peab midagi panema?
        }

        double[] andmeteMassiiv = {päikesetuul,magnetväli};
        return andmeteMassiiv;
    }

    public double kpIndeks() throws Exception{
        double kpIndeks;

        String andmed = String.format("https://services.swpc.noaa.gov/products/geospace/planetary-k-index-dst-1-hour.json");
        try {
            JSONObject kpAndmed = new JSONObject(getData(andmed));
            kpIndeks =  Double.valueOf()//viimase massiivi [1] element
        }
        finally {
        } //kas siia peaks midagi tulema?
        return kpIndeks;
        }




  /* public String[] puhastatud(String rida) {
        while (rida.indexOf("  ")>0)
            rida = rida.replaceAll("  "," ");
        String[] uusrida = rida.split(" ");
        return uusrida;
    }

    public String päikesetuulToString(String[] massiiv){
        return "päikesetuule väärtus on " + massiiv[8];
    }

     public String parameetrid(){
        double kpindeks;
        double päikesetuul;

        if (kpindeks<=3) {
            return "Kp indeks on " + kpindeks +"\nGeomagneetiline aktiivsus on madal.";
        }
        if (3<kpindeks && kpindeks<5) {
            return "Kp indeks on " + kpindeks + "\nGeomagneetiline aktiivsus on kasvanud, kuid virmaliste nägemise tõenäosus on väike.";
        }
        if (kpindeks >= 5) {
            return "Kp indeks on " + kpindeks + "\nGeomagneetiline aktiivsus on kõrgendatud ning võib näha virmalisi.";
        }

    }
    /* public String virmalisteParameetrid(String virm) throws Exception {

        String kpIndeksandmed = String.format("https://services.swpc.noaa.gov/products/geospace/planetary-k-index-dst-1-hour.json", virm);
        try {
            JSONObject kpIndeks = new JSONObject(getData(kpIndeksandmed));
            this.name = virm;
            this.weather = kpIndeks.getJSONObject();

        }
    }
    public String[] eestiVirmalised() throws Exception {
        String url = "https://services.swpc.noaa.gov/text/ace-swepam.txt";
        Virmalised virmalised = new Virmalised();
        String[] virm = virmalised.puhastatud(getData(url));
        return virm;
    }

    public void virmalisedLinnas(String url, Linn linn) throws Exception {
        String gurl = new String();
    } */


}
