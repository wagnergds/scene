package org.porto.pessoa;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    @FXML private ComboBox<String> cbestado;
    @FXML private TextField estadoTextField;
    @FXML private TextField cepField;
    @FXML private TextField cpfField;
    @FXML private TextField nameField;
    @FXML private TextField enderecoField;
    @FXML private TextField numeroField;
    @FXML private DatePicker dataField;
    @FXML private TextField dataTextField;
    @FXML private TextField complementoField;
    @FXML private TextField bairroField;
    @FXML private TextField cidadeField;
    @FXML private TextField paiField;
    @FXML private TextField maeField;

    private Stage previousStage;

    public void setPreviousStage(Stage stage) {
        this.previousStage = stage;
    }

    private DataStore dataStore = DataStore.getInstance();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if (!location.toString().endsWith("relatorio.fxml")) {
            inicializarComboBoxEstados();
            addTextPropertyListeners();
        }
        updateLabels();
    }
    private void inicializarComboBoxEstados() {
        if (cbestado !=null) {
            cbestado.getItems().addAll("AC", "AL", "AP", "AM", "BA", "CE",
                    "DF", "ES", "GO", "MA",
                    "MT", "MS", "MG", "PA",
                    "PB", "PR", "PE", "PI", "RJ",
                    "RN", "RS", "RO",
                    "RR", "SC", "SP", "SE", "TO");
            cbestado.getSelectionModel().selectFirst();
        }
    }

    private void addTextPropertyListeners() {
        if (cpfField != null && cepField.textProperty() != null) {
            cepField.textProperty().addListener((obs, old, nw) -> {
                if (!nw.matches("\\d{0,5}([-]?\\d{0,3})?")) {
                    cepField.setText(old);
                } else if (nw.length() == 5 && !old.endsWith("-")) {
                    cepField.setText(nw + "-");
                }
            });

            cpfField.textProperty().addListener((obs, old, nw) -> {
                if (nw != null && !nw.equals(old)) {
                    formatCPF(nw);
                }
            });
        }
    }

    private void formatCPF(String text) {
        String numericOnly = text.replaceAll("[^\\d]", "");
        numericOnly = numericOnly.substring(0, Math.min(numericOnly.length(), 11));
        StringBuilder formatted = new StringBuilder();
        for (int i = 0; i < numericOnly.length(); i++) {
            if (i == 3 || i == 6) formatted.append(".");
            if (i == 9) formatted.append("-");
            formatted.append(numericOnly.charAt(i));
        }
        cpfField.setText(formatted.toString());
    }

    public void updateLabels() {
        if (nameField != null && dataStore.getNome() != null) {
            nameField.setText(dataStore.getNome());
        }
        if (cpfField != null && dataStore.getCpf() != null) {
            cpfField.setText(dataStore.getCpf());
        }
        if (dataField != null && dataStore.getDataNascimento() != null) {
            dataField.setValue(dataStore.getDataNascimento());
        }
        if (enderecoField != null && dataStore.getEndereco() != null) {
            enderecoField.setText(dataStore.getEndereco());
        }
        if (numeroField != null && dataStore.getNumero() != null) {
            numeroField.setText(dataStore.getNumero());
        }
        if (complementoField != null && dataStore.getComplemento() != null) {
            complementoField.setText(dataStore.getComplemento());
        }
        if (bairroField != null && dataStore.getBairro() != null) {
            bairroField.setText(dataStore.getBairro());
        }
        if (cidadeField != null && dataStore.getCidade() != null) {
            cidadeField.setText(dataStore.getCidade());
        }
        if (cbestado != null && dataStore.getEstado() != null) {
            cbestado.setValue(dataStore.getEstado());
        }
        if (cepField != null && dataStore.getCep() != null) {
            cepField.setText(dataStore.getCep());
        }
        if (paiField != null && dataStore.getPai() != null) {
            paiField.setText(dataStore.getPai());
        }
        if (maeField != null && dataStore.getMae() != null) {
            maeField.setText(dataStore.getMae());
        }
    }


    public void updateRelatorio() {
        if ( dataStore.getNome() != null) {
            nameField.setText(dataStore.getNome());
        }
        if ( dataStore.getCpf() != null) {
            cpfField.setText(dataStore.getCpf());
        }
        if ( dataStore.getDataNascimento() != null) {
            dataTextField.setText(dataStore.getDataNascimento().toString());
        }
        if (dataStore.getEndereco() != null) {
            enderecoField.setText(dataStore.getEndereco());
        }
        if ( dataStore.getNumero() != null) {
            numeroField.setText(dataStore.getNumero());
        }
        if ( dataStore.getComplemento() != null) {
            complementoField.setText(dataStore.getComplemento());
        }
        if ( dataStore.getBairro() != null) {
            bairroField.setText(dataStore.getBairro());
        }
        if ( dataStore.getCidade() != null) {
            cidadeField.setText(dataStore.getCidade());
        }
        if ( dataStore.getEstado() != null) {
            estadoTextField.setText(dataStore.getEstado());
        }
        if ( dataStore.getCep() != null) {
            cepField.setText(dataStore.getCep());
        }
        if ( dataStore.getPai() != null) {
            paiField.setText(dataStore.getPai());
        }
        if ( dataStore.getMae() != null) {
            maeField.setText(dataStore.getMae());
        }
    }

    public void limparCampos() {
        cbestado.getItems().clear();
        cpfField.setText("");
        nameField.setText("");
        dataField.setValue(null);
        enderecoField.setText("");
        numeroField.setText("");
        complementoField.setText("");
        bairroField.setText("");
        cidadeField.setText("");
        cepField.setText("");
        inicializarComboBoxEstados();
        dataStore.clear();
    }

    public void exibirAlertaAviso(String mensagem) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Aviso");
        alert.setHeaderText("Cabeçalho do Alerta de Aviso");
        alert.setContentText(mensagem);
        alert.showAndWait();
    }
    public void PreenchidosCampos() {
        if (cbestado.getItems().isEmpty()){
            exibirAlertaAviso("Estado está em branco!");
            return;
        }
        if (cpfField.getText().isEmpty()){
            exibirAlertaAviso("Cpf está em branco!");
            return;
        }
        if (nameField.getText().isEmpty()){
            exibirAlertaAviso("Nome está em branco!");
            return;
        }
        if (dataField.getValue() == null){
            exibirAlertaAviso("Data de Nascimento está vazia!");
            return;
        }
        if (numeroField.getText().isEmpty()){
            exibirAlertaAviso("Número está vazio!");
            return;
        }
        if (complementoField.getText().isEmpty()){
            exibirAlertaAviso("Complemento está vazio!");
            return;
        }

        if (bairroField.getText().isEmpty()){
            exibirAlertaAviso("Bairro está vazio!");
            return;
        }

        salvarDadosNoDataStore();

        abrirCadastroPais();
    }

    public void abrirCadastroPais() {
        try {
            salvarDadosNoDataStore();

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

    private void salvarDadosNoDataStore() {
        dataStore.setNome(nameField.getText());
        dataStore.setCpf(cpfField.getText());
        dataStore.setEndereco(enderecoField.getText());
        dataStore.setNumero(numeroField.getText());
        dataStore.setComplemento(complementoField.getText());
        dataStore.setBairro(bairroField.getText());
        dataStore.setCidade(cidadeField.getText());
        dataStore.setEstado(cbestado.getValue());
        dataStore.setCep(cepField.getText());
        dataStore.setDataNascimento(LocalDate.parse(dataField.getValue().toString()));
    }

    @FXML
    public void salvarDadosNoDataStore2(ActionEvent event) {
        dataStore.setPai(paiField.getText());
        dataStore.setMae(maeField.getText());
        /*Node source = (Node) event.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();

        if (previousStage != null) {
            previousStage.close();
        }
         */
        ControllerMenu controller = new ControllerMenu();
        controller.abrirRelatorio();
    }

    @FXML
    public void cancelarTela(ActionEvent event) {
        Node source = (Node) event.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
        nameField.requestFocus();
    }

    @FXML
    public void limparDados(ActionEvent event){
        dataStore.clear();
        cancelarTela(event);
    }
}