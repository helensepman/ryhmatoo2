
import javax.swing.JOptionPane;

public class Ilmajaam {
    public static void main(String[] args) throws Exception {
        System.out.println("Programm küsib linna nime ning tagastab antud linna ilmateate." +
                "\nKui sisestad sõna \"virmalised\", saad teada, kas Eestis on hetkel lootust näha virmalisi." +
                "\nProgrammist väljumiseks kirjuta \"välju\"");

        Virmalised eesti = new Virmalised();
        while (true) {
            try {
                String sisestatakse = JOptionPane.showInputDialog(null, "Sisesta linna nimi ", "Linn",
                        JOptionPane.QUESTION_MESSAGE);
                if (sisestatakse.equalsIgnoreCase("välju")) {
                    break;
                } else if (sisestatakse.equalsIgnoreCase("virmalised")) {
                    eesti.updateVirmalised();
                    System.out.println(eesti.toString());
                } else {
                    Linn linn = new Linn(sisestatakse);
                    System.out.println(linn.toString());
                }
            } catch (NullPointerException teade) {
                break;
            }

        }
    }
}
