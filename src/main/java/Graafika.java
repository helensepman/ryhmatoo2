import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.ToolBar;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

import javax.swing.*;


public class Graafika extends Application {
    private boolean teineStseen = false;
    public static void main(String[] args) {
        launch(args);
    }

    public void start(Stage peaLava) throws Exception {

        Text label = new Text("Hetkeilma teadasaamiseks sisesta mõne Eesti linna nimi:");
        Label uuslinn = new Label("Sisesta uus linn: ");

        TextField searchBox = new TextField();

        Text pealkiri = new Text("Ilmajaam");
        pealkiri.setFont(Font.font ("Comic Sans MS", 30));
        pealkiri.setFill(Color.GREEN);
        Text tutvustus = new Text("See programm laseb sisestada mõne Eesti linna nime ning annab infot selle linna ilma kohta. \nLisaks antakse infot virmaliste näitajate kohta. \n\n\nLinna info kuvamiseks vajuta nuppu 'Näita' või klahvi 'ENTER'.");

        Text sisu = new Text("Linna ilma info siia");
        Text virm = new Text("Virmaliste info siia");

        Button nupp = new Button("Näita");
        Button nupp2 = new Button("Näita");
        Button välju = new Button("Välju");

        VBox vbox = new VBox();
        VBox vb = new VBox();
        HBox hb = new HBox();
        HBox hbox = new HBox();
        BorderPane piir = new BorderPane();
        BorderPane piir2 = new BorderPane();
        piir2.setPadding(new Insets(15, 12, 15, 12));

        Virmalised eesti = new Virmalised();

        //Stseen1
        vb.getChildren().addAll(label, searchBox, nupp, välju);
        vb.setSpacing(20);
        searchBox.setMaxSize(200, 20);
        vb.setPadding(new Insets(25, 12, 15, 12));
        vb.setMaxWidth(300);

        hbox.setSpacing(70);
        hbox.setPadding(new Insets(50, 20, 30, 20));

        vbox.getChildren().addAll(pealkiri, tutvustus);
        vbox.setAlignment(Pos.CENTER);
        vbox.setSpacing(20);
        piir.setCenter(vb);
        piir.setTop(vbox);
        piir.setPadding(new Insets(15, 12, 15, 12));


        nupp2.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event) {
                try {
                    Linn linn = new Linn(searchBox.getText());
                    eesti.updateVirmalised();

                    if (!searchBox.getText().isEmpty() & linn.getCityExict()) {
                        sisu.setText(linn.ilusTekst());
                        virm.setText(eesti.toString());
                        searchBox.clear();
                    } else {
                        JOptionPane.showMessageDialog(null, "Linna ei sisestatud või linn pole saadaval!");
                    }
                } catch (Exception e) {
                    //throw new RuntimeException();
                    System.out.println(e);
                }
            }


        });

        välju.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event){
                peaLava.close();
            }

            });

        nupp.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event){
                try {
                    Linn linn = new Linn(searchBox.getText());
                    eesti.updateVirmalised();

                    if (!searchBox.getText().isEmpty() & linn.getCityExict()) { //siia peaks panema selle kontrolli, et kas linn eksisteerib

                        //Steen2
                        searchBox.clear();
                        sisu.setText(linn.ilusTekst());
                        virm.setText(eesti.toString());
                        hb.getChildren().addAll(uuslinn, searchBox, nupp2);
                        hbox.getChildren().addAll(sisu, virm);
                        hb.setSpacing(20);
                        piir2.setTop(hb);
                        piir2.setCenter(hbox);
                       // piir.setRight(virm);
                        piir2.setBottom(välju);

                        teineStseen = true;

                        Scene stseen2 = new Scene(piir2, 700, 400);


                        peaLava.setScene(stseen2);
                        //peaLava.show();
                    } else {
                        JOptionPane.showMessageDialog(null, "Linna ei sisestatud või linn pole saadaval!");
                    }
                }catch (Exception e){
                    //throw new RuntimeException();
                    System.out.println(e);
                }

            }
        });



        searchBox.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if (event.getCode() == KeyCode.ENTER) {
                    if (teineStseen){
                        nupp2.fire();
                    }
                    else nupp.fire();
                    event.consume();
                }
            }
        });

        Scene stseen1 = new Scene(piir, 700, 400);
        peaLava.setTitle("Ilmateade");
        peaLava.setScene(stseen1);
        peaLava.show();
    }
    //@Override
    /*public void start(Stage primaryStage) throws Exception {
        BorderPane root = new BorderPane();

        root.setPadding(new Insets(15, 20, 10, 10));

        // TOP
        Button btnTop = new Button("Top");
        btnTop.setPadding(new Insets(10, 10, 10, 10));
        root.setTop(btnTop);
        // Set margin for top area.
        BorderPane.setMargin(btnTop, new Insets(10, 10, 10, 10));


        // LEFT
        Button btnLeft = new Button("Left");
        btnLeft.setPadding(new Insets(5, 5, 5, 5));
        root.setLeft(btnLeft);
        // Set margin for left area.
        BorderPane.setMargin(btnLeft, new Insets(10, 10, 10, 10));

        // CENTER
        Button btnCenter = new Button("Center");
        btnCenter.setPadding(new Insets(5, 5, 5, 5));
        root.setCenter(btnCenter);
        // Alignment.
        BorderPane.setAlignment(btnCenter, Pos.BOTTOM_CENTER);

        // RIGHT
        Button btnRight = new Button("Right");
        btnRight.setPadding(new Insets(5, 5, 5, 5));
        root.setRight(btnRight);
        // Set margin for right area.
        BorderPane.setMargin(btnRight, new Insets(10, 10, 10, 10));

        // BOTTOM
        Button btnBottom = new Button("Bottom");
        btnBottom.setPadding(new Insets(5, 5, 5, 5));
        root.setBottom(btnBottom);
        // Alignment.
        BorderPane.setAlignment(btnBottom, Pos.TOP_RIGHT);

        // Set margin for bottom area.
        BorderPane.setMargin(btnBottom, new Insets(10, 10, 10, 10));

        Scene scene = new Scene(root, 550, 250);

        primaryStage.setTitle("BorderPane Layout Demo");
        primaryStage.setScene(scene);
        primaryStage.show();
    }*/

    /*public void start(Stage peaLava) throws Exception {
            /*BorderPane piir = new BorderPane();

            Label label = new Label("Hetkeilma teadasaamiseks sisesta mõne Eesti linna nimi:");
             TextField tekst = new TextField();
            tekst.setPrefSize(80, 20); //miks see ei tööta?
            Text lõik = new Text("See programm kasutab seda ja seda ja siia linna nime sisestades \n saad hetkelise ilmateate kätte");


            VBox vb = new VBox();
            vb.getChildren().addAll(label, tekst, nupp);
            vb.setSpacing(20);
            vb.setMaxWidth(200);
            piir.setCenter(vb);
            piir.setTop(lõik);
            Button nupp = new Button("saada");

            piir.setCenter(nupp);

            BorderPane borderPane = new BorderPane();
            //ToolBar toolbar = new ToolBar();
            //HBox statusbar = new HBox();
            //borderPane.setTop(toolbar);
            //borderPane.setBottom(statusbar);

            Scene stseen1 = new Scene(borderPane, 400, 400);
            peaLava.setTitle("Ilmateade");
            peaLava.setScene(stseen1);
            peaLava.show();
        }*/
}
