
import javax.swing.JOptionPane;

public class Ilmajaam {
    public static void main(String[] args) throws Exception{
        System.out.println("Programm küsib linna nime ning tagastab antud linna ilmateate.\nKui sisestad sõna virmalised, saad teada, kas Eestis on hetkel lootust näha virmalisi.\nProgrammist väljumiseks kirjuta välju");

        while (true){
        String sisestatakse = JOptionPane.showInputDialog(null, "Sisesta linna nimi ", "Linn",
                JOptionPane.QUESTION_MESSAGE);
        if (sisestatakse.equalsIgnoreCase("välju")){
            break;
        }

        if (sisestatakse.equalsIgnoreCase("virmalised")){
            Linn linn = new Linn();
            System.out.println(linn.eestiVirmalised());
        }

        Linn linn = new Linn();
        //linnameetodid kust saadakse ilma andmed kätte
            System.out.println(/*linn.getIlmateade vmt*/);

    }
    }
}
