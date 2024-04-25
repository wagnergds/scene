package org.porto.pessoa;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ControllerMenu implements Initializable {

    @FXML
    private VBox rootVBox;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void exibirAlertaAviso(String mensagem) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Aviso");
        alert.setHeaderText("Cabeçalho do Alerta de Aviso");
        alert.setContentText(mensagem);
        alert.showAndWait();
    }
    @FXML
    private void abrirCadastroPessoas() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("cadastroPessoas.fxml"));
            Parent root = loader.load();

            Controller controller = loader.getController();

            Stage stage = new Stage();
            stage.initModality(Modality.NONE);
            stage.setTitle("Cadastro de Pessoas");

            Scene scene = new Scene(root);
            stage.setScene(scene);

            controller.setPreviousStage((Stage) root.getScene().getWindow());
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
            exibirAlertaAviso("Não foi possível carregar o cadastro de pessoas: " + e.getMessage());
        }
    }


    @FXML
    public void abrirRelatorio() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("relatorio.fxml"));
            Parent root = loader.load();
            Controller controller = loader.getController();  // Certifique-se de que o FXML está usando Controller

            controller.updateRelatorio();  // Atualiza os labels com dados de DataStore

            Stage stage = new Stage();
            stage.initModality(Modality.NONE);
            stage.setTitle("Relatório de Cadastro");
            stage.setScene(new Scene(root));

            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
            exibirAlertaAviso("Não foi possível carregar o relatório: " + e.getMessage());
        }
    }


    public void abrirCadastroPais() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("cadastroPais.fxml"));
            Parent root = loader.load();

            Stage stage = new Stage();
            stage.initModality(Modality.NONE);
            stage.setTitle("Cadastro de Pais");

            Scene scene = new Scene(root);
            stage.setScene(scene);

            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void fecharAplicacao() {
        Platform.exit();

        System.exit(0);
    }
}
