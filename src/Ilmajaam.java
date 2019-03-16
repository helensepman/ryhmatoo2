
import javax.swing.JOptionPane;

public class Ilmajaam {
    public static void main(String[] args) {
        /*üldinfo programmi kohta
        Ilmajaam - sisestad linna nime, siis saad teada ...
        Kui sisestad lihtsalt virmalised, siis saad üldiselt hetke Eesti ilma kohta teada
        Kui sisestad linna ja virmalised, siis saad teada, kuidas on praegune ilm selles linnas virmaliste vaatamiseks
         */
        String sisestatakse = JOptionPane.showInputDialog(null, "Sisesta midagi ", "Andmete sisestamine",
                JOptionPane.QUESTION_MESSAGE);

    }
}
