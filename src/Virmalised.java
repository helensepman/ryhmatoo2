import org.json.JSONArray;

import java.io.FileNotFoundException;


public class Virmalised {
    private double paikesetuul;
    private double magnetvali; //vaadatakse Bz ehk põhjasuunalist komponenti ainult; see peab olema negatiivne
    private double kpIndeks;

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
            return pohiinfo + "Geomagneetiline aktiivsus on väga kõrge; suur tõenäosus näha virmalisi";
        }else{
            return "Geomagneetiline aktiivsus on madal, virmalisi pole näha"; //default-lause kui ükski tene if lause ei sobi
        }
    }

    private void paikesetuulMagnetvali() throws Exception{

        String andmed = "https://services.swpc.noaa.gov/products/geospace/propagated-solar-wind-1-hour.json";

        try {
            GetData data = new GetData();
            JSONArray geoAndmed = new JSONArray(data.getData(andmed));
            //System.out.println(geoAndmed.getJSONArray(geoAndmed.length()-1));
            this.paikesetuul = geoAndmed.getJSONArray(geoAndmed.length()-1).getDouble(1);//vaja saada viimase massiivi [1] elementi
            this.magnetvali = geoAndmed.getJSONArray(geoAndmed.length()-1).getDouble(6);//vaja saada viimase massiivi [6] elementi
        }catch (FileNotFoundException teade){
            System.out.println("Kontakteeru projekti autoritega! Solar-wind error");
        }

        //double[] andmeteMassiiv = {paikesetuul,magnetvali};
        //return andmeteMassiiv;
    }

    private void kpIndeks() throws Exception {

        String andmed = "https://services.swpc.noaa.gov/products/geospace/planetary-k-index-dst-1-hour.json";
        try {
            GetData data = new GetData();
            JSONArray kpAndmed = new JSONArray(data.getData(andmed));
            //System.out.println(kpAndmed.getJSONArray(kpAndmed.length()-1));
            this.kpIndeks =  kpAndmed.getJSONArray(kpAndmed.length()-1).getDouble(1);//viimase massiivi [1] element
        } catch (FileNotFoundException teade) {
            System.out.println("Kontakteeru projekti autoritega! kpIndeks error");
        }

    }
}
