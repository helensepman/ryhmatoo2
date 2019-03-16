
public class Virmalised {

    public String[] puhastatud(String rida) {
        while (rida.indexOf("  ")>0)
            rida = rida.replaceAll("  "," ");
        String[] uusrida = rida.split(" ");
        return uusrida;
    }

    public String päikesetuul(String[] massiiv){
        return "päikesetuule väärtus on " + massiiv[8];
    }



}
