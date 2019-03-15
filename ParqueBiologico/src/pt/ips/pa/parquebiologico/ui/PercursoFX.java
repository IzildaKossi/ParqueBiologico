/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pt.ips.pa.parquebiologico.ui;

import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import pt.ips.pa.parquebiologico.PainelGraphFX;
import pt.ips.pa.parquebiologico.model.Conexao;
import pt.ips.pa.parquebiologico.model.ParqueBiologico;
import pt.ips.pa.parquebiologico.model.Ponto;
import pt.ips.pa.parquebiologico.model.Utilizador;
import pt.ips.pa.parquebiologico.tads.Edge;

/**
 *
 * @author
 */
public class PercursoFX extends StackPane {

    public PercursoFX(ParqueBiologico parqueBiologico, BorderPane root, PainelGraphFX painel) {

        setAlignment(Pos.CENTER);

        GridPane grid = new GridPane();
        grid.setAlignment(Pos.TOP_CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));
        //menu listar amigo
        listarPercursos(parqueBiologico, root, grid, painel);
        //coloca no centro da borderpane
        root.setCenter(grid);
    }

    private void listarPercursos(ParqueBiologico parqueBiologico, BorderPane root, GridPane grid, PainelGraphFX painel) {

        Text titulo = new Text("Listagem de Percursos");
        titulo.setId("titulo-text");
        titulo.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        grid.add(titulo, 0, 0, 4, 1);

        List<Object> lista = parqueBiologico.getParqueBiologicoDAO().selectAll("conexao");
        ArrayList<String> listaPonto = new ArrayList<>();

        for (Object object : lista) {
            if (object instanceof Conexao) {
                Conexao conexao = (Conexao) object;
                String percurso = conexao.getPontoOrigem().getNome() + " - " + conexao.getPontoDestino().getNome();
                listaPonto.add(percurso);
            }
        }

        //observable list para obter amigos do utilizador
        ObservableList<String> percursos = FXCollections.observableArrayList(listaPonto);
        //listview para visualizar a lista de amigos
        ListView<String> listaPercurso = new ListView<>();
        listaPercurso.setStyle("-fx-border-color: black; -fx-border-width: 1; ");
        listaPercurso.setItems(percursos);

        VBox vbox = new VBox();
        vbox.getChildren().add(listaPercurso);
        grid.add(vbox, 0, 3);

        Button btnVoltar = new Button("Voltar");
        btnVoltar.setPrefSize(200, 30);

        Button btnCriarBilhete = new Button("Criar Bilhete");
        btnCriarBilhete.setPrefSize(200, 30);

        HBox hbBtn = new HBox(10);
        hbBtn.setAlignment(Pos.BOTTOM_LEFT);
        hbBtn.getChildren().addAll(btnCriarBilhete, btnVoltar);
        grid.add(hbBtn, 0, 7);

        /**
         * Evento do botão para cancelar
         */
        btnVoltar.setOnAction((ActionEvent e) -> {
            //volta para painel inicial
            painel.initComponents();
        });

        btnCriarBilhete.setOnAction((ActionEvent e) -> {
            String result = listaPercurso.getSelectionModel().getSelectedItem();

            if (result != null) {
                painelFaturacaoFX(parqueBiologico, root, painel, result);
            } else {
                mostrarInf(Alert.AlertType.ERROR, "Erro", "Falta seleccionar o percurso");
            }
        });
    }

    private StackPane painelFaturacaoFX(ParqueBiologico parqueBiologico, BorderPane root, PainelGraphFX painel, String percurso) {
        return new FaturacaoFX(parqueBiologico, root, painel, percurso);
    }

    private void mostrarInf(Alert.AlertType tipo, String titulo, String mensagem) {
        Alert dialogo = new Alert(tipo);
        dialogo.setTitle(titulo);
        dialogo.setHeaderText(null);
        dialogo.setContentText(mensagem);
        dialogo.showAndWait();
    }
}
