/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pt.ips.pa.parquebiologico;

import javafx.application.*;
import javafx.scene.*;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import pt.ips.pa.parquebiologico.model.*;

/**
 *
 * @author
 */
public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        ParqueBiologico parqueBiologico = new ParqueBiologico();
        BorderPane root = new BorderPane();
        PainelGraphFX painel = new PainelGraphFX(parqueBiologico, root);
        painel.initComponents();

        Scene scene = new Scene(root, 800, 600);
        scene.getStylesheets().addAll(this.getClass().getResource("/pt/ips/pa/parquebiologico/ui/resources/style.css").toExternalForm());

        primaryStage.setTitle("Parque Biologico");
        primaryStage.getIcons().add(new Image("/pt/ips/pa/parquebiologico/ui/resources/favicon.png"));
        primaryStage.setResizable(false);
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    /**
     * /
     *
     **
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
}
