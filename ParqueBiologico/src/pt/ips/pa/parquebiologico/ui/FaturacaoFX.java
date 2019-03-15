/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pt.ips.pa.parquebiologico.ui;

import java.util.ArrayList;
import java.util.List;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
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
public class FaturacaoFX extends StackPane {

    private Utilizador utilizador;
    private Utilizador utilizadorBD;
    private Bilhete bilhete;

    public FaturacaoFX(ParqueBiologico parqueBiologico, BorderPane root, PainelGraphFX painel, String percurso) {

        setAlignment(Pos.CENTER);

        GridPane grid = new GridPane();
        grid.setAlignment(Pos.TOP_CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));
        //menu listar amigo
        criarFatura(parqueBiologico, root, grid, painel, percurso);
        //coloca no centro da borderpane
        root.setCenter(grid);
    }

    private void criarFatura(ParqueBiologico parqueBiologico, BorderPane root, GridPane grid, PainelGraphFX painel, String percurso) {
        Text titulo = new Text("Criar Bilhete e Fatura");
        titulo.setId("titulo-text");
        titulo.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        grid.add(titulo, 0, 0, 4, 1);

        Label nome = new Label("Nome");
        nome.setFont(Font.font("Cambria", FontWeight.NORMAL, 14));
        nome.setId("label");

        TextField nomeTextField = new TextField();
        nomeTextField.getStyleClass().add("textfield");
        nomeTextField.setPrefSize(325, 30);

        HBox hboxNome = new HBox(20);
        hboxNome.getChildren().addAll(nome, nomeTextField);

        Label nifLabel = new Label("Nif");
        nifLabel.setId("label");

        TextField nifTextField = new TextField();
        nifTextField.getStyleClass().add("textfield");
        nifTextField.setPrefSize(325, 30);

        HBox hboxTextfield = new HBox(38);
        hboxTextfield.getChildren().addAll(nifLabel, nifTextField);

        Label nifFatura = new Label("Pretende incluir nif na fatura?");
        nifFatura.setId("label");

        //A aplicação deverá permitir consultar o ranking dos jogadores, por estratégia de pontuação.
        CheckBox checkBox = new CheckBox();
        checkBox.setId("chkbox");

        HBox hboxCheckBox = new HBox(35);
        hboxCheckBox.getChildren().addAll(checkBox, nifFatura);

        checkBox.setOnAction((ActionEvent e) -> {
            //desativa checkbox A e B
            checkBox.setDisable(true);
        });

        VBox vboxFormulario = new VBox(20);
        vboxFormulario.getChildren().addAll(hboxNome,
                hboxTextfield,
                hboxCheckBox);
        grid.add(vboxFormulario, 0, 2);

        Button btnVoltar = new Button("Voltar");
        btnVoltar.setPrefSize(120, 30);

        Button btnCriarBilhete = new Button("Imprimir Bilhete");
        btnCriarBilhete.setPrefSize(120, 30);

        Button btnLimpar = new Button("Limpar");
        btnLimpar.setPrefSize(120, 30);

        HBox hbBtn = new HBox(10);
        hbBtn.setAlignment(Pos.BOTTOM_LEFT);
        hbBtn.getChildren().addAll(btnCriarBilhete, btnLimpar, btnVoltar);
        grid.add(hbBtn, 0, 7);

        btnVoltar.setOnAction((ActionEvent e) -> {
            //volta para painel percurso
            painelPercursoFX(parqueBiologico, root, painel);
        });

        btnCriarBilhete.setOnAction((ActionEvent e) -> {
            //se nome nao for  vazio
            if ((nomeTextField.getText().isEmpty() != true)
                    //e nif nao for vazio
                    && (nifTextField.getText().isEmpty() != true)) {
                //se numero tiver igual que 9 digitos
                if (nifTextField.getText().length() == 9) {
                    //se checkbox estiver selecionada
                    if (checkBox.isSelected()) {//incluir nif na fatura
                        utilizador = new Utilizador(nomeTextField.getText(), Integer.valueOf(nifTextField.getText()));
                        parqueBiologico.getParqueBiologicoDAO().insert("utilizador", utilizador);
                        System.out.println("utilizador\n" + utilizador.toString());

                        utilizadorBD = (Utilizador) parqueBiologico.getParqueBiologicoDAO().select("utilizador", utilizador);
                        System.out.println("utilizadorBD\n" + utilizadorBD.toString());

                        bilhete = new Bilhete(utilizadorBD, parqueBiologico.getMapa(), percurso, true);
                        parqueBiologico.getParqueBiologicoDAO().insert("bilhete", bilhete);

                        PDF pdf1 = new PDF(parqueBiologico.getParqueBiologicoDAO(), bilhete);
                        PDF pdf2 = new PDF(parqueBiologico.getParqueBiologicoDAO(), bilhete.getFatura());
                        System.out.println(bilhete.toString());

                        mostrarInf(Alert.AlertType.INFORMATION, "INFORMAÇAO", "Bilhete e Fatura foi criado e "
                                + " imprimido com sucesso");
                        //volta para painel percurso
                        painelPercursoFX(parqueBiologico, root, painel);

                    }//e se nao 
                    else {//nao incluir nif na fatura
                        utilizador = new Utilizador(nomeTextField.getText(), Integer.valueOf(nifTextField.getText()));
                        parqueBiologico.getParqueBiologicoDAO().insert("utilizador", utilizador);
                        System.out.println("utilizador\n" + utilizador.toString());

                        utilizadorBD = (Utilizador) parqueBiologico.getParqueBiologicoDAO().select("utilizador", utilizador);
                        System.out.println("utilizadorBD\n" + utilizadorBD.toString());

                        bilhete = new Bilhete(utilizador, parqueBiologico.getMapa(), percurso, false);
                        parqueBiologico.getParqueBiologicoDAO().insert("bilhete", bilhete);

                        PDF pdf1 = new PDF(parqueBiologico.getParqueBiologicoDAO(), bilhete);
                        PDF pdf2 = new PDF(parqueBiologico.getParqueBiologicoDAO(), bilhete.getFatura());
                        System.out.println(bilhete.toString());

                        mostrarInf(Alert.AlertType.INFORMATION, "INFORMAÇAO", "Bilhete e Fatura foi criado e "
                                + " imprimido com sucesso");
                        //volta para painel percurso
                        painelPercursoFX(parqueBiologico, root, painel);
                    }
                } else {
                    mostrarInf(Alert.AlertType.ERROR, "Erro", "Nif deve ter 9 digitos");
                }
            } else {
                mostrarInf(Alert.AlertType.ERROR, "Erro", "Falta preencher os dados em falta!");
            }
        }
        );

        btnLimpar.setOnAction(
                (ActionEvent e) -> {
                    nomeTextField.clear();
                    nifTextField.clear();
                    //se checkbox A estiver selecionado
                    if (checkBox.isSelected()) {
                        checkBox.setDisable(false);
                        checkBox.setSelected(false);
                    }
                }
        );
    }

    private StackPane painelPercursoFX(ParqueBiologico parqueBiologico, BorderPane root, PainelGraphFX painel) {
        return new PercursoFX(parqueBiologico, root, painel);
    }

    private void mostrarInf(Alert.AlertType tipo, String titulo, String mensagem) {
        Alert dialogo = new Alert(tipo);
        dialogo.setTitle(titulo);
        dialogo.setHeaderText(null);
        dialogo.setContentText(mensagem);
        dialogo.showAndWait();
    }
}
