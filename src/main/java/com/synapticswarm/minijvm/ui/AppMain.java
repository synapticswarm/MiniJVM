package com.synapticswarm.minijvm.ui;

import java.io.FileInputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class AppMain extends Application{

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Application.launch(AppMain.class, (java.lang.String[]) null);
    }

    @Override
    public void start(Stage primaryStage) {
        try {

            //AnchorPane page = (AnchorPane) FXMLLoader.load()
            AnchorPane page = (AnchorPane) FXMLLoader.load(AppMain.class.getResource("miniJVM.fxml"));

            //AnchorPane page = (AnchorPane)FXMLLoader.load(new FileInputStream(new File("")));


            Scene scene = new Scene(page);
            primaryStage.setScene(scene);
            primaryStage.setTitle("miniJVM");
            primaryStage.show();
        } catch (Exception ex) {
            ex.printStackTrace();
            Logger.getLogger(AppMain.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
