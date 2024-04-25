package org.porto.pessoa;

import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class WindowManager {
    private static final List<Stage> openStages = new ArrayList<>();

    public static void addStage(Stage stage) {
        openStages.add(stage);
    }

    public static void closeAllAndReopenMenu() {
        openStages.forEach(Stage::close);
        openStages.clear();
        DataStore.getInstance().clear();
        openMenu();
    }

    public static void openMenu() {
        try {
            FXMLLoader loader = new FXMLLoader(WindowManager.class.getResource("menu.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Menu Principal");
            stage.show();
            stage.setOnCloseRequest(e -> Platform.exit());
            addStage(stage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}