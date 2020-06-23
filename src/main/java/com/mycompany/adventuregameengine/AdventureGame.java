package com.mycompany.adventuregameengine;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import static javafx.scene.input.KeyCode.ESCAPE;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

public class AdventureGame extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
    
        Parent root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
        
        Scene scene = new Scene(root);
        
        stage.setTitle("Adventure Game: The Whip of the Archaeologist");      
        
        stage.setWidth(1920);
        stage.setHeight(1080);

        stage.setScene(scene);

        stage.setFullScreenExitHint("");
        stage.centerOnScreen();
        //stage.setAlwaysOnTop(true);
        stage.show();
        stage.setFullScreen(true);
        
        EventHandler<KeyEvent> eventHandlerStageESC = new EventHandler<KeyEvent>() { 
            @Override 
            public void handle(KeyEvent event) {
                if(event.getCode() == ESCAPE) {//the pressed button is ESC by KeyCode
                    //System.out.println(event.getCode());
                    stage.setFullScreen(true);
                }
            }
        };
        stage.addEventHandler(KeyEvent.KEY_PRESSED, eventHandlerStageESC);        
    }
    
    public static void main(String[] args) {
        launch(args);
    }
    
}
