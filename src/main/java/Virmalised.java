import org.json.JSONArray;
import org.json.JSONException;

import java.io.FileNotFoundException;


public class Virmalised {
    private double paikesetuul; //skaala, mille põhjal saab anda esimese hinnangu
    private double magnetvali; //vaadatakse Bz ehk põhjasuunalist komponenti ainult; see peab olema negatiivne
    private double kpIndeks; //skaala, mille põhjal saab anda esimese hinnangu

    public Virmalised() throws Exception{
        paikesetuulMagnetvali();
        kpIndeks();
    }

     public void updateVirmalised() throws Exception{
         paikesetuulMagnetvali();
         kpIndeks();
    }

    @Override
    public String toString() {
        String pohiinfo = "\nPäikesetuul on " + paikesetuul + " km/s, magnetväli " + magnetvali + " nT ning Kp indeks on " + kpIndeks;
        if (magnetvali >= 0 && paikesetuul > 532) {
            return pohiinfo + "\nGeomagneetiline aktiivsus on tõusnud, kuid kuna magnetväli on positiivne, siis ilmselt virmalisi ei näe.";
        }else if (magnetvali<0 && (paikesetuul>393 || kpIndeks >= 4)){
            return pohiinfo + "\nGeomagneetiline aktiivsus on tõusnud; väike tõenäosus näha virmalisi";
        }else if (magnetvali<0 && (paikesetuul>532 || kpIndeks >= 5)){
            return pohiinfo + "\nGeomagneetiline aktiivsus on kõrge; keskmine kuni suur tõenäosus näha virmalisi";
        }else if (magnetvali<0 && (paikesetuul>602 || kpIndeks >= 6)){
            return pohiinfo + "\nGeomagneetiline aktiivsus on väga kõrge; suur tõenäosus näha virmalisi";
        }else{
            return pohiinfo + "\nGeomagneetiline aktiivsus on madal, virmalisi pole näha"; //default-lause kui ükski teine if lause ei sobi
        }
    }

    private void paikesetuulMagnetvali() throws Exception{

        String andmed = "https://services.swpc.noaa.gov/products/geospace/propagated-solar-wind-1-hour.json";

        try {
            GetData data = new GetData();
            JSONArray geoAndmed = new JSONArray(data.getData(andmed));
            this.paikesetuul = geoAndmed.getJSONArray(geoAndmed.length()-1).getDouble(1); //võetakse päikesetuule andmed
            this.magnetvali = geoAndmed.getJSONArray(geoAndmed.length()-1).getDouble(6); //võetakse magnetvälja Bz komponendi andmed
        }catch (FileNotFoundException teade){
            System.out.println("Kontakteeru projekti autoritega! Solar-wind error");
        }
        catch (JSONException j){
            System.out.println("Halb");
        }
    }

    private void kpIndeks() throws Exception {

        String andmed = "https://services.swpc.noaa.gov/products/geospace/planetary-k-index-dst-1-hour.json";
        try {
            GetData data = new GetData();
            JSONArray kpAndmed = new JSONArray(data.getData(andmed));
            this.kpIndeks =  kpAndmed.getJSONArray(kpAndmed.length()-1).getDouble(1);//võetakse Kp indeksi väärtus
        } catch (FileNotFoundException teade) {
            System.out.println("Kontakteeru projekti autoritega! kpIndeks error");
        }

    }
}
