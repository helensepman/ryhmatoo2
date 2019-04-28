
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

    public class Graafika extends Application{
        public class Ilmateade extends Application {
        public void start(Stage peaLava) throws Exception {
            //Group juur = new Group();

            BorderPane piir = new BorderPane();

            Label label = new Label("Hetkeilma teadasaamiseks sisesta mõne Eesti linna nimi:");
            TextField tekst = new TextField();
            tekst.setPrefSize(200, 50); //miks see ei tööta?
            Button nupp = new Button("Välju");
            VBox vb = new VBox();
            vb.getChildren().addAll(label, tekst, nupp);
            vb.setSpacing(20);
            piir.setCenter(vb);



            nupp.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    peaLava.hide();
                    Stage uus = new Stage();
                    uus.show();
                }
            });
            

            Scene stseen = new Scene(piir, 500, 500);
            peaLava.setTitle("Ilmateade");
            peaLava.setScene(stseen);
            peaLava.show();
        }


    }

}
