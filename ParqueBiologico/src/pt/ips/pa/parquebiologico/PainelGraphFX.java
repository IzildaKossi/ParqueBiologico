/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pt.ips.pa.parquebiologico;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import pt.ips.pa.parquebiologico.model.Config;
import pt.ips.pa.parquebiologico.model.ParqueBiologico;
import pt.ips.pa.parquebiologico.model.Percurso;
import pt.ips.pa.parquebiologico.model.Ponto;
import pt.ips.pa.parquebiologico.tads.Vertex;
import pt.ips.pa.parquebiologico.ui.EstatisticaFX;
import pt.ips.pa.parquebiologico.ui.PercursoFX;

/**
 *
 * @author
 */
public class PainelGraphFX {

    private ParqueBiologico parqueBiologico;
    private BorderPane root;

    PainelGraphFX(ParqueBiologico parqueBiologico, BorderPane root) {
        this.parqueBiologico = parqueBiologico;
        this.root = root;
    }

    public void initComponents() {
        menuBar();
        painelGraph();
    }

    private void menuBar() {
        //barra de menu     
        MenuBar menuBar = new MenuBar();
        menuBar.setId("menu-bar");
        //menu ficheiro
        Menu menuFicheiro = new Menu("Ficheiro");

        //menu item sair
        MenuItem sair = new MenuItem("Sair", null);
        sair.setId("menu-item");
        sair.setMnemonicParsing(true);
        sair.setOnAction((ActionEvent e) -> {
            Platform.exit();
        });
        //
        menuFicheiro.getItems().add(sair);

        //menu Percurso
        Menu menuPercurso = new Menu("Percurso");

        MenuItem verPercurso = new MenuItem("Lista de Percursos", null);
        verPercurso.setId("menu-item");
        verPercurso.setMnemonicParsing(true);
        verPercurso.setOnAction((ActionEvent e) -> {

            if (parqueBiologico.getMapa() != null) {
                if (parqueBiologico.getMapa().getPercurso() != null) {
                    painelPercursoFX(parqueBiologico, root, this);
                } else {
                    mostrarInf(Alert.AlertType.ERROR, "Erro", "Nao existem percurso calculados");
                }
            } else {
                mostrarInf(Alert.AlertType.ERROR, "Erro", "Nao existem percurso calculados");
            }
        });
        menuPercurso.getItems().add(verPercurso);

        //menu Faturaçao
        Menu menuEstatistica = new Menu("Estatistica");
        MenuItem criarEstatistica = new MenuItem("Visualizar Estatistica", null);
        criarEstatistica.setId("menu-item");
        criarEstatistica.setMnemonicParsing(true);
        criarEstatistica.setOnAction((ActionEvent e) -> {

            if (parqueBiologico.getMapa() != null) {
                if (parqueBiologico.getMapa().getPercurso() != null) {
                    painelEstatisticaFX(parqueBiologico, root, this);
                } else {
                    mostrarInf(Alert.AlertType.ERROR, "Erro", "Nao existem percurso calculados");
                }
            } else {
                mostrarInf(Alert.AlertType.ERROR, "Erro", "Nao existem percurso calculados");
            }
        });
        menuEstatistica.getItems().add(criarEstatistica);

        //menu ajuda
        Menu menuAjuda = new Menu("Ajuda");
        //menu item ajuda
        MenuItem ajuda = new MenuItem("Sobre aplicação", null);
        ajuda.setId("menu-item");
        ajuda.setMnemonicParsing(true);
        ajuda.setOnAction((ActionEvent e) -> {
            mostrarInf(Alert.AlertType.INFORMATION, "Sobre", "Venda de Bilhetes para o Parque Biológico"
                    + "\nPA 2017/18"
                    + "\nProgramação Avançada"
                    + "\nÉpoca Recurso versão B");
        });

        menuAjuda.getItems().add(ajuda);

        //
        menuBar.getMenus().addAll(menuFicheiro, menuPercurso, menuEstatistica, menuAjuda);
        //coloca no topo da borderpane
        root.setTop(menuBar);
    }

    private void painelGraph() {

        GridPane gridGraph = new GridPane();
        gridGraph.setAlignment(Pos.TOP_CENTER);
        gridGraph.setHgap(10);
        gridGraph.setVgap(10);

        HBox hboxGraph = new HBox();
        hboxGraph.setPrefSize(500, 500);
        gridGraph.add(hboxGraph, 0, 1);//insere o graph

        GridPane gridOperacao = new GridPane();
        gridOperacao.setAlignment(Pos.TOP_CENTER);
        gridOperacao.setHgap(10);
        gridOperacao.setVgap(10);

        ComboBox<String> comboBox1 = new ComboBox<>();
        comboBox1.setId("combobox");
        comboBox1.setValue("Escolha a mapa");
        comboBox1.getItems().addAll("Mapa 1", "Mapa 2", "Mapa 3");
        comboBox1.setPrefSize(200, 30);

        ComboBox<Ponto> comboBox2 = new ComboBox<>();
        comboBox2.setId("combobox");
        //comboBox2.setValue("Passagem por");
        comboBox2.setPrefSize(200, 30);

        ComboBox<Ponto> comboBox3 = new ComboBox<>();
        comboBox3.setId("combobox");
        //comboBox3.setValue("Passagem por");
        comboBox3.setPrefSize(200, 30);

        ComboBox<Ponto> comboBox4 = new ComboBox<>();
        comboBox4.setId("combobox");
        //comboBox4.setValue("Passagem por");
        comboBox4.setPrefSize(200, 30);
        comboBox4.setDisable(true);

        RadioButton radioButton1 = new RadioButton("A pé");
        RadioButton radioButton2 = new RadioButton("De Bicicleta");

        ToggleGroup radioGroup1 = new ToggleGroup();

        radioButton1.setToggleGroup(radioGroup1);
        radioButton2.setToggleGroup(radioGroup1);

        HBox hboxButtonRadio1 = new HBox(65);
        hboxButtonRadio1.getChildren().addAll(radioButton1, radioButton2);

        RadioButton radioButton3 = new RadioButton("Menor Custo");
        RadioButton radioButton4 = new RadioButton("Mais Curto");

        ToggleGroup radioGroup2 = new ToggleGroup();

        radioButton3.setToggleGroup(radioGroup2);
        radioButton4.setToggleGroup(radioGroup2);

        HBox hboxButtonRadio2 = new HBox(20);
        hboxButtonRadio2.getChildren().addAll(radioButton3, radioButton4);

        Button btnCalcular = new Button("Calcular Percurso");
        btnCalcular.setPrefSize(200, 30);

        TextArea textPercurso = new TextArea("Percurso:");
        textPercurso.setPrefSize(250, 100);

        TextArea textCustoTotal = new TextArea("Custo Total:");
        textCustoTotal.setPrefSize(250, 100);

        comboBox1.setOnAction((ActionEvent e) -> {

            switch (comboBox1.getValue()) {
                case "Mapa 1":
                    parqueBiologico.desenharParqueBiologico(comboBox1.getValue());
                    hboxGraph.getChildren().clear();
                    hboxGraph.getChildren().add(parqueBiologico.getMapa().getGraphDraw());//insere o graph
                    break;
                case "Mapa 2":
                    parqueBiologico.desenharParqueBiologico(comboBox1.getValue());
                    hboxGraph.getChildren().clear();
                    hboxGraph.getChildren().add(parqueBiologico.getMapa().getGraphDraw());//insere o graph
                    break;
                case "Mapa 3":
                    parqueBiologico.desenharParqueBiologico(comboBox1.getValue());
                    hboxGraph.getChildren().clear();
                    hboxGraph.getChildren().add(parqueBiologico.getMapa().getGraphDraw());//insere o graph
                    break;
            }
            //limpa as combobox
            comboBox2.getItems().clear();
            //comboBox2.setValue("Passagem por");
            comboBox3.getItems().clear();
            //comboBox3.setValue("Passagem por");
            //comboBox4.getItems().clear();
            //comboBox4.setValue("Passagem por");

            //limpa as escolhas no radio button
            radioButton1.setSelected(false);
            radioButton2.setSelected(false);
            radioButton3.setSelected(false);
            radioButton4.setSelected(false);

            ArrayList<Ponto> pontos = new ArrayList<>();

            //atualiza as vertices na combobox
            for (Vertex<Ponto> ponto : parqueBiologico.getMapa().getMyGraph().vertices()) {
                pontos.add(ponto.element());
            }
            Collections.sort(pontos, new Comparator<Ponto>() {
                @Override
                public int compare(Ponto p1, Ponto p2) {
                    if (p1.getId() > p2.getId()) {
                        return 1;
                    } else if (p1.getId() < p2.getId()) {
                        return -1;
                    }
                    return 0;
                }
            });

            for (Ponto ponto : pontos) {
                comboBox2.getItems().add(ponto);
                comboBox3.getItems().add(ponto);
                // comboBox4.getItems().add(ponto);
            }

            //atualiza a textarea para valores iniciais
            textPercurso.setText("Percurso:");
            textCustoTotal.setText("Custo Total:");
        });

        comboBox2.setOnAction((ActionEvent e) -> {
            comboBox2.setValue(comboBox2.getSelectionModel().getSelectedItem());
            System.out.println("Box2: " + comboBox2.getValue());
        });

        comboBox3.setOnAction((ActionEvent e) -> {
            comboBox3.setValue(comboBox3.getSelectionModel().getSelectedItem());
            System.out.println("Box3: " + comboBox3.getValue());
        });

        btnCalcular.setOnAction((ActionEvent e) -> {
            Config config = null;
            int mapaId = 0;
            if (parqueBiologico.getMapa() != null) {
                mapaId = parqueBiologico.getMapa().getMapaId();
            }
            System.out.println("Mapa ID:" + mapaId);
            String mapa = comboBox1.getSelectionModel().getSelectedItem();
            System.out.println("Mapa: " + mapa);
            Ponto origem = comboBox2.getSelectionModel().getSelectedItem();
            Ponto destino = comboBox3.getSelectionModel().getSelectedItem();

            boolean ape = radioButton1.isSelected();
            boolean bicicleta = radioButton2.isSelected();
            boolean menorCusto = radioButton3.isSelected();
            boolean maisCurto = radioButton4.isSelected();

            if (mapa != null && mapaId != 0) {
                if (origem != null && destino != null) {
                    if (!origem.equals(destino)) {
                        if (ape == true) {
                            if (menorCusto == true) {
                                config = new Config(mapaId, origem, destino, radioButton1.getText(), radioButton3.getText());
                                System.out.println(config);
                            } else if (maisCurto == true) {
                                config = new Config(mapaId, origem, destino, radioButton1.getText(), radioButton4.getText());
                                System.out.println(config);
                            } else {
                                mostrarInf(Alert.AlertType.ERROR, "Erro",
                                        "Falta escolher o menor custo de trajeto e/ou "
                                        + "pelo caminho mais curto.");
                            }
                        } else if (bicicleta == true) {
                            if (menorCusto == true) {
                                config = new Config(mapaId, origem, destino, radioButton2.getText(), radioButton3.getText());
                                System.out.println(config);
                            } else if (maisCurto == true) {
                                config = new Config(mapaId, origem, destino, radioButton2.getText(), radioButton4.getText());
                                System.out.println(config);
                            } else {
                                mostrarInf(Alert.AlertType.ERROR, "Erro",
                                        "Falta escolher o menor custo de trajeto e/ou "
                                        + "pelo caminho mais curto.");
                            }
                        } else {
                            mostrarInf(Alert.AlertType.ERROR, "Erro",
                                    "Falta escolher o trajeto a pé ou de bicicleta"
                                    + "pelo caminho mais curto.");
                        }

                    } else {
                        mostrarInf(Alert.AlertType.ERROR, "Erro",
                                "Origem e Destino não podem ser iguais!"
                                + "\nTenta outro destino.");
                    }
                } else {
                    mostrarInf(Alert.AlertType.ERROR, "Erro",
                            "Falta escolher a origem e/ou destino.");
                }
            } else {
                mostrarInf(Alert.AlertType.ERROR, "Erro",
                        "Falta escolher o mapa do parque biológico.");
            }

            if (config != null) {
                Percurso percurso = new Percurso(parqueBiologico, config);
                //envia a referencia da classe percurso
                parqueBiologico.getMapa().setPercurso(percurso);
                parqueBiologico.getMapa().calcularPercurso();

                hboxGraph.getChildren().clear();
                hboxGraph.getChildren().add(parqueBiologico.getMapa().getGraphDraw());//insere o graph

                textPercurso.setText("Percurso:\n" + percurso.getCaminho() + "\n\nDistancia Total: " + percurso.getDistanciaTotal());
                textCustoTotal.setText("Custo Total:\n" + percurso.getCustoTotal());

            } else {
                mostrarInf(Alert.AlertType.ERROR, "Erro",
                        "Não foi possivel calcular o percurso "
                        + "porque falta selecionar alguma opção.");
            }
        });
        VBox vboxOperacao = new VBox(20);
        vboxOperacao.getChildren()
                .addAll(comboBox1,
                        comboBox2,
                        comboBox3,
                        comboBox4,
                        hboxButtonRadio1,
                        hboxButtonRadio2,
                        btnCalcular,
                        textPercurso,
                        textCustoTotal);
        gridOperacao.add(vboxOperacao, 0, 1);//insere o graph

        HBox hBox = new HBox(30);
        hBox.getChildren().addAll(gridGraph, gridOperacao);
        root.setCenter(hBox);
    }

    private void mostrarInf(Alert.AlertType tipo, String titulo, String mensagem) {
        Alert dialogo = new Alert(tipo);
        dialogo.setTitle(titulo);
        dialogo.setHeaderText(null);
        dialogo.setContentText(mensagem);
        dialogo.showAndWait();
    }

    private StackPane painelPercursoFX(ParqueBiologico parqueBiologico, BorderPane root, PainelGraphFX painel) {
        return new PercursoFX(parqueBiologico, root, painel);
    }

    private StackPane painelEstatisticaFX(ParqueBiologico parqueBiologico, BorderPane root, PainelGraphFX painel) {
        return new EstatisticaFX(parqueBiologico, root, painel);
    }

}
