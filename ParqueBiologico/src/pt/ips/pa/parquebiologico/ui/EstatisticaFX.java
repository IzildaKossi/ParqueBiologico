/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pt.ips.pa.parquebiologico.ui;

import java.util.ArrayList;
import java.util.List;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import pt.ips.pa.parquebiologico.PainelGraphFX;
import pt.ips.pa.parquebiologico.model.Bilhete;
import pt.ips.pa.parquebiologico.model.PDF;
import pt.ips.pa.parquebiologico.model.ParqueBiologico;
import pt.ips.pa.parquebiologico.model.Utilizador;

/**
 *
 * @author
 */
public class EstatisticaFX extends StackPane {

    public EstatisticaFX(ParqueBiologico parqueBiologico, BorderPane root, PainelGraphFX painel) {

        setAlignment(Pos.CENTER);

        GridPane grid = new GridPane();
        grid.setAlignment(Pos.TOP_CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));
        //menu listar amigo
        consultarEstatistica(parqueBiologico, root, grid, painel);
        //coloca no centro da borderpane
        root.setCenter(grid);
    }

    private void consultarEstatistica(ParqueBiologico parqueBiologico, BorderPane root, GridPane grid, PainelGraphFX painel) {

        Text titulo = new Text("Estatistica");
        titulo.setId("titulo-text");
        titulo.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        grid.add(titulo, 0, 0, 4, 1);

        Bilhete lista = (Bilhete) parqueBiologico.getParqueBiologicoDAO().select("bilhete", 1);
        ArrayList<String> listaPonto = new ArrayList<>();

        Button btnVoltar = new Button("Voltar");
        btnVoltar.setPrefSize(120, 30);

        grid.add(btnVoltar, 0, 7);

        btnVoltar.setOnAction((ActionEvent e) -> {
            //volta para painel percurso
            painelPercursoFX(parqueBiologico, root, painel);
        });

    }

    private StackPane painelPercursoFX(ParqueBiologico parqueBiologico, BorderPane root, PainelGraphFX painel) {
        return new PercursoFX(parqueBiologico, root, painel);
    }

}
