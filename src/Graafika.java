

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Ilmateade extends Application {
    public void start(Stage peaLava) throws Exception {

        BorderPane piir = new BorderPane();

        Label label = new Label("Hetkeilma teadasaamiseks sisesta mõne Eesti linna nimi:");
        TextField tekst = new TextField();
        tekst.setPrefSize(80, 20); //miks see ei tööta?
        Text lõik = new Text("See programm kasutab seda ja seda ja siia linna nime sisestades \n saad hetkelise ilmateate kätte");

        Button nupp = new Button("saada");
        VBox vb = new VBox();
        vb.getChildren().addAll(label, tekst, nupp);
        vb.setSpacing(20);
        vb.setMaxWidth(200);
        piir.setCenter(vb);
        piir.setTop(lõik);

        tekst.setOnKeyPressed(new EventHandler<javafx.scene.input.KeyEvent>() {
            @Override
            public void handle(javafx.scene.input.KeyEvent event) { //See on vaja siduda nupuvajutusega, et ta teeks sama asja
                if(event.getCode() == KeyCode.ENTER) {
                    nupp.fire();
                    event.consume();
                }
            }
        });


        nupp.setOnMouseClicked(new EventHandler<javafx.scene.input.MouseEvent>() {
            @Override
            public void handle(javafx.scene.input.MouseEvent event) {
                VBox vbox = new VBox();
                HBox hb = new HBox();
                Label uuslinn= new Label("Sisesta uus linn: ");
                Label linn;
                Text sisu ;
                Text virm;
                if (!tekst.getText().isEmpty()) { //siia peaks panema selle kontrolli, et kas linn eksisteerib
                    linn = new Label("Sisestatud linn: " + tekst.getText());
                    sisu = new Text("Linna ilma info siia");
                    virm = new Text("Virmaliste info siia");
                    vbox.getChildren().addAll(linn, sisu);
                    hb.getChildren().addAll(uuslinn, tekst);
                    hb.setTranslateX(70);
                    piir.setTop(hb);
                    piir.setRight(virm);
                    piir.setCenter(vbox);
                    Scene stseen2 = new Scene(vbox, 400, 500);

                    peaLava.setScene(stseen2);
                    peaLava.show();
                }
                else {
                    Label tühi = new Label("Linna ei sisestatud");
                    if (!vb.getChildren().contains(tühi)) {  //Tahtsin, et ta ühe korra ainult näitaks seda kui linna ei sisestatud...
                        vb.getChildren().add(tühi);
                    }
                }

            }
        });

        Scene stseen1 = new Scene(piir, 400, 400);
        peaLava.setTitle("Ilmateade");
        peaLava.setScene(stseen1);
        peaLava.show();
    }
}
