package com.mycompany.adventuregameengine;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.KeyFrame;
import javafx.animation.PathTransition;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Rectangle2D;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import static javafx.scene.input.KeyCode.ESCAPE;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Screen;
import javafx.util.Duration;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

/**
 * This is the controller class.
 * @author Zed895
 */
public class FXMLDocumentController implements Initializable {
    //<editor-fold defaultstate="collapsed" desc="FXML class variables">
    @FXML
    private AnchorPane AnchorPane;
    @FXML
    private Rectangle rectangle;
    @FXML
    private Rectangle coverRectangle;
    @FXML
    private Pane basePane;
    @FXML
    private ImageView pic1;
    @FXML
    private ImageView backpackFrame;
    @FXML
    private Label RoomDescription;
    @FXML
    private Label outputLabel;
    @FXML
    private Label backpackLabel;
    @FXML
    private Label scoreLabel;
    @FXML
    private Label hpLabel;
    
    @FXML
    private Pane introPane;
    @FXML
    private Text welcomeText;
    @FXML
    private Button introButton;
    
    @FXML
    private Pane actionPane;
    @FXML
    private Label actionText;
    @FXML
    private Button actionPaneButton;
    @FXML
    private Button actionPaneCancel;
    
    @FXML
    private Pane helpPane;
    @FXML
    private Label helpText;
    @FXML
    private Button helpPaneButton;
    @FXML
    private ImageView helpPic;
    
    @FXML
    private Pane creditsPane;
    @FXML
    private Label creditsText;
    @FXML
    private Button creditsPaneButton;
    
    //item buttons
    @FXML
    private Button knifeButton;
    @FXML
    private Button paracetamolButton;
    @FXML
    private Button beerButton;
    @FXML
    private Button keyButton;
    @FXML
    private Button bedButton;
    @FXML
    private Button tableButton;
    @FXML
    private Button poisonButton;
    @FXML
    private Button watchButton;
    @FXML
    private Button pictureButton;
    @FXML
    private Button backpackButton;
    @FXML
    private Button wolfButton;
    
    //action buttons
    @FXML
    private GridPane commandGrid;
    @FXML
    private Button digButton;
    @FXML
    private Button takeButton;
    @FXML
    private Button dropButton;
    @FXML
    private Button lookatButton;
    @FXML
    private Button useButton;
    @FXML
    private Button giveButton;
    @FXML
    private Button talktoButton;
    @FXML
    private Button pushButton;
    @FXML
    private Button pullButton;
    
    //direction buttons
    @FXML
    private Button northButton;
    @FXML
    private Button eastButton;
    @FXML
    private Button southButton;
    @FXML
    private Button westButton;
    @FXML
    private Button upButton;
    @FXML
    private Button downButton;
    
    @FXML
    private Button loadButton;
    @FXML
    private Button saveButton;
    @FXML
    private Button helpButton;
    @FXML
    private Button quitButton;
    @FXML
    private Button creditButton;
    //</editor-fold>
    
    /**
    * This function handles the click on the introButton.
    * It closes the introPane and stop the clip but shows the basePane.
    * @param event received from the onAction of the introButton
    */
    @FXML
    private void handleIntroButton(ActionEvent event){
        basePane.setDisable(false);
        basePane.setVisible(true);
        introPane.setVisible(false);
        introPane.setDisable(true);
        clip.stop();
    }
    
    /**
    * This method handles the ESC key.
    * It closes every pane/clip and shows the basePane.
    */
    public void handleESCButton(){
        basePane.setDisable(false);
        basePane.setVisible(true);
        introPane.setVisible(false);
        introPane.setDisable(true);
        clip.stop();
        basePane.setOpacity(1);
        actionPane.setVisible(false);
        helpPane.setVisible(false);
        creditsPane.setVisible(false);
    }
    
    //<editor-fold defaultstate="collapsed" desc="The functions which handle the movement to the 6 directions in the game.">
    @FXML
    private void handleNorthAction(ActionEvent event) {
        String kiirando = "";
        int nextRoom;
        nextRoom = roomList.get(hero.getCurrentRoom()).getEszakra();
                if (nextRoom == -9)
                    kiirando = "You can not go that way.";
                else if (nextRoom == -10){
                    hero.setHp(hero.getHp()-2*hero.getHp());
                    kiirando = "The wolf leaps towards you, you raise your hand to defend yourself, but it is pointless. The wolf kills you. You were not able to acquire the Whip the the Archaeologist.";
                }
                else
                    hero.setCurrentRoom(nextRoom); 
        outputLabel.setText(kiirando);
        timelineOutput.play();
        RoomDescription.setText(roomList.get(hero.getCurrentRoom()).getDescription());timelineDescription.play();
        //inputField.clear();
        //roomPicChanger();
        pic1.setImage(roomList.get(hero.getCurrentRoom()).getPic());
        itemKijelzo();
        akcioKikapcsolo();
    }
    @FXML
    private void handleEastAction(ActionEvent event) {
        String kiirando = "";
        int nextRoom;
        nextRoom = roomList.get(hero.getCurrentRoom()).getKeletre();
                if (nextRoom == -9)
                    kiirando = "You can not go that way.";
                else if (nextRoom == -10){
                    hero.setHp(hero.getHp()-2*hero.getHp());
                    kiirando = "The wolf leaps towards you, you raise your hand to defend yourself, but it is pointless. The wolf kills you. You were not able to acquire the Whip the the Archaeologist.";
                }
                else
                    hero.setCurrentRoom(nextRoom);  
        outputLabel.setText(kiirando);
        timelineOutput.play();
        RoomDescription.setText(roomList.get(hero.getCurrentRoom()).getDescription());timelineDescription.play();
        pic1.setImage(roomList.get(hero.getCurrentRoom()).getPic());
        itemKijelzo();
        akcioKikapcsolo();
    }
    @FXML
    private void handleSouthAction(ActionEvent event) {
        String kiirando = "";
        int nextRoom;
        nextRoom = roomList.get(hero.getCurrentRoom()).getDelre();
                if (nextRoom == -9)
                    kiirando = "You can not go that way.";
                else if (nextRoom == -10){
                    hero.setHp(hero.getHp()-2*hero.getHp());
                    kiirando = "The wolf leaps towards you, you raise your hand to defend yourself, but it is pointless. The wolf kills you. You were not able to acquire the Whip the the Archaeologist.";
                }
                else
                    hero.setCurrentRoom(nextRoom);
        outputLabel.setText(kiirando);
        timelineOutput.play();
        RoomDescription.setText(roomList.get(hero.getCurrentRoom()).getDescription());timelineDescription.play();
        pic1.setImage(roomList.get(hero.getCurrentRoom()).getPic());
        itemKijelzo();
        akcioKikapcsolo();
    }
    @FXML
    private void handleWestAction(ActionEvent event) {
        String kiirando = "";
        int nextRoom;
        nextRoom = roomList.get(hero.getCurrentRoom()).getNyugatra();
                if (nextRoom == -9)
                    kiirando = "You can not go that way.";
                else if (nextRoom == -10){
                    hero.setHp(hero.getHp()-2*hero.getHp());
                    kiirando = "The wolf leaps towards you, you raise your hand to defend yourself, but it is pointless. The wolf kills you. You were not able to acquire the Whip the the Archaeologist.";
                }
                else
                    hero.setCurrentRoom(nextRoom);
        outputLabel.setText(kiirando);
        timelineOutput.play();
        RoomDescription.setText(roomList.get(hero.getCurrentRoom()).getDescription());timelineDescription.play();
        pic1.setImage(roomList.get(hero.getCurrentRoom()).getPic());
        itemKijelzo();
        akcioKikapcsolo();
    }
    @FXML
    private void handleUpAction(ActionEvent event) {
        String kiirando = "";
        int nextRoom;
        nextRoom = roomList.get(hero.getCurrentRoom()).getFel();
                if (nextRoom == -9)
                    kiirando = "You can not go that way.";
                else if (nextRoom == -10){
                    hero.setHp(hero.getHp()-2*hero.getHp());
                    kiirando = "You try to climb up a tree, but the wolf leaps towards you and it's faster than you. The wolf kills you. You were not able to acquire the Whip the the Archaeologist.";
                }
                else
                    hero.setCurrentRoom(nextRoom);
        outputLabel.setText(kiirando);
        timelineOutput.play();
        RoomDescription.setText(roomList.get(hero.getCurrentRoom()).getDescription());timelineDescription.play();
        pic1.setImage(roomList.get(hero.getCurrentRoom()).getPic());
        itemKijelzo();
        akcioKikapcsolo();
    }
    @FXML
    private void handleDownAction(ActionEvent event) {
        String kiirando = "";
        int nextRoom;
        nextRoom = roomList.get(hero.getCurrentRoom()).getLe();
                if (nextRoom == -9)
                    kiirando = "You can not go that way.";
                else
                    hero.setCurrentRoom(nextRoom);
        outputLabel.setText(kiirando);
        timelineOutput.play();
        RoomDescription.setText(roomList.get(hero.getCurrentRoom()).getDescription());timelineDescription.play();
        pic1.setImage(roomList.get(hero.getCurrentRoom()).getPic());
        itemKijelzo();
        akcioKikapcsolo();
    }
    //</editor-fold>
    
    /**
    * This method is called by the onMouseClicked on the pic1 ImageView.
    * It determines the outcome based on the active action button.
    */
    @FXML
    private void handlePicItem(){
        if (lookatNyomott == true) {
            if (hero.getCurrentRoom()== 0 || hero.getCurrentRoom()== 1){
                outputLabel.setText("It is a quite nice forest, but you can't see any special about it.");timelineOutput.play(); //move these to the Room POJO as a String attrib later
            }
            if (hero.getCurrentRoom()== 9 || hero.getCurrentRoom()== 10){
               outputLabel.setText("You investigate the reed closely. Yes, it is indeed a reed!");timelineOutput.play();
            }
            if (hero.getCurrentRoom()== 12){
                outputLabel.setText("The shack is in a quite bad condition.");timelineOutput.play();
            }
        }
        if (takeNyomott == true) {
            if (hero.getCurrentRoom()== 3){
                outputLabel.setText("Actually it is a quite heavy castle, I doubt that you would be able to pick it up.");timelineOutput.play();
            }
        }
        akcioKikapcsolo();
    }
    
    //<editor-fold defaultstate="collapsed" desc="The functions which handle the action events on the items of the game.">
    @FXML
    private void handleKnifeItem(ActionEvent event){
        if (takeNyomott == true) {
            itemList.get(0).setRoomnumber(-1);
            knifeButton.setLayoutX(830);
            knifeButton.setLayoutY(115);
            if (hero.getCurrentRoom() == 0){outputLabel.setText("You sheathed the knife into your belt.");timelineOutput.play();}
        }
        if (dropNyomott == true) {
            if (itemList.get(0).getRoomnumber() == -1 ){//is it in the inv?  
                itemList.get(0).setRoomnumber(hero.getCurrentRoom());
                knifeButton.setLayoutX(19); //it can be hardcoded for now, later write a move(int whichroom) function
                knifeButton.setLayoutY(372);
            }
        }
        if (lookatNyomott == true) {
            outputLabel.setText(itemList.get(0).getVizsgal());timelineOutput.play();
        }
        if (useNyomott == true) {
            switch (hero.getCurrentRoom()) {
                case 13: {                    
                    if (roomList.get(13).getEszakra() == -10){
                        outputLabel.setText("The wolf leaps towards you but you manage to pierce its neck with your knife just before it would reach your neck! The wolf is dead now, and you survived.");timelineOutput.play();
                        hero.setScore(1);timelineScore.play();
                        //timelineScore.setCycleCount(1);
                        musicDing();
                        roomList.get(1).setEszakra(14);
                        roomList.get(13).setEszakra(14);
                        roomList.get(13).setKeletre(14);
                        roomList.get(13).setDelre(1);
                        roomList.get(13).setNyugatra(14);
                        roomList.get(13).setFel(-9);
                        //now just switch wolf off (room -9), later handleWolfItem() will handle it depending if it is dead or not.
                        itemList.get(10).setRoomnumber(-9);
                        itemKijelzo();
                    }
                    else outputLabel.setText("You can not use it here.");timelineOutput.play();
                    break;
                }
                default: {
                    outputLabel.setText("You can not use it here.");timelineOutput.play();
                    break;
                }
            }      
        }
        if (giveNyomott == true) {
            //check room
            if (hero.getCurrentRoom() == 13){
                outputLabel.setText("It does not want it.");timelineOutput.play();
            } else
            outputLabel.setText("To whom?");timelineOutput.play();
        }
        if (talktoNyomott == true) {
            outputLabel.setText("You told a heartfelt story to the knife but it seems it is not interested in it.");timelineOutput.play();
        }
        if (pushNyomott == true) {
            //todo
        }
        if (pullNyomott == true) {
            //todo
        }
        akcioKikapcsolo();
    }
    @FXML
    private void handleKeyItem(ActionEvent event){
        if (takeNyomott == true) {
            if (hero.isVanBackpack() == true) {
            itemList.get(3).setRoomnumber(-1);
            keyButton.setLayoutX(870);
            keyButton.setLayoutY(180);
            } else {
            outputLabel.setText("First you would need something to put your items into.");timelineOutput.play();
            }
        }
        if (dropNyomott == true) {
            if (itemList.get(3).getRoomnumber() == -1 ){
                itemList.get(3).setRoomnumber(hero.getCurrentRoom());
                keyButton.setLayoutX(213);
                keyButton.setLayoutY(372);
            }
        }
        if (lookatNyomott == true) {
            outputLabel.setText(itemList.get(3).getVizsgal());timelineOutput.play();
        }
        if (useNyomott == true) {
            switch (hero.getCurrentRoom()) {
                case 3: {
                    outputLabel.setText("You opened the gate of the castle!");
                    if (roomList.get(3).getEszakra() == -9){
                        hero.setScore(1); timelineScore.play();
                        musicDing();
                    }
                    roomList.get(3).setDescription("You are standing in front of a castle. Its gate is open.");
                    roomList.get(3).setEszakra(3);
                    break;
                }
                case 12: {
                    outputLabel.setText("You do not have to open it, the shack has no lock... neither a door, actually.");timelineOutput.play();
                    break;
                }
                default: {
                    outputLabel.setText("You can not use it here.");timelineOutput.play();
                    break;
                }
            }
        }        
        if (giveNyomott == true) {
            //check room
            if (hero.getCurrentRoom() == 13){
                outputLabel.setText("It does not want it.");timelineOutput.play();
            } else
            outputLabel.setText("To whom?");timelineOutput.play();
        }
        if (talktoNyomott == true) {
            outputLabel.setText("You told a heartfelt story to the key but it seems it is not interested in it.");timelineOutput.play();
        }
        akcioKikapcsolo();
    }
    @FXML
    private void handleBeerItem(ActionEvent event){
        if (takeNyomott == true) {
            if (hero.isVanBackpack() == true) {
            itemList.get(2).setRoomnumber(-1);
            beerButton.setLayoutX(834);
            beerButton.setLayoutY(147);
            } else {outputLabel.setText("First you would need something to put your items into.");timelineOutput.play();}
        }
        if (dropNyomott == true) {
            if (itemList.get(2).getRoomnumber() == -1 ){
                outputLabel.setText("You do not want to throw it away.");timelineOutput.play();
            }
        }
        if (lookatNyomott == true) {
            outputLabel.setText(itemList.get(2).getVizsgal());timelineOutput.play();
        }
        if (useNyomott == true) {
            itemList.get(2).setRoomnumber(-9);
            itemKijelzo();
            outputLabel.setText("You drank the beer. It is tasty and refreshing!");timelineOutput.play();
            hero.setHp(2); timelineHp.play();
        }
        if (giveNyomott == true) {
            if (hero.getCurrentRoom() == 13){
                outputLabel.setText("It does not want it.");timelineOutput.play();
            } else
            outputLabel.setText("To whom?");timelineOutput.play();
        }
        if (talktoNyomott == true) {
            outputLabel.setText("You told a heartfelt story to the beer but it seems it is not interested in it.");timelineOutput.play();
        }
        if (pushNyomott == true) {
            itemList.get(2).setRoomnumber(-9);
            itemKijelzo();
            outputLabel.setText("You pushed the mug and the beer spilled... you could have drunk it, you could have given it to somebody... but you spilled it.");timelineOutput.play();
        }
        if (pullNyomott == true) {
            outputLabel.setText("You pulled the beer closer to yourself. It is better now, is not it?");timelineOutput.play();
        }
        akcioKikapcsolo();
    }
    @FXML
    private void handlePoisonItem(ActionEvent event){
        if (takeNyomott == true) {
            if (hero.isVanBackpack() == true) {
            itemList.get(6).setRoomnumber(-1);
            poisonButton.setLayoutX(835);
            poisonButton.setLayoutY(205);
            } else {outputLabel.setText("First you would need something to put your items into.");timelineOutput.play();}
        }
        if (dropNyomott == true) {
            if (itemList.get(6).getRoomnumber() == -1 ){
                itemList.get(6).setRoomnumber(hero.getCurrentRoom());
                poisonButton.setLayoutX(356);
                poisonButton.setLayoutY(362);
            }
        }
        if (lookatNyomott == true) {
            outputLabel.setText(itemList.get(6).getVizsgal());timelineOutput.play();
        }
        if (useNyomott == true) {
            itemList.get(6).setRoomnumber(-9);
            itemKijelzo();
            outputLabel.setText("You drank the poison. You have a bad feeling about it.");timelineOutput.play();
            hero.setHp(-10);
            timelineHp.play();
        }
        if (giveNyomott == true) {
            if (hero.getCurrentRoom() == 13){
                outputLabel.setText("It does not want it.");timelineOutput.play();
            } else
            outputLabel.setText("To whom?");timelineOutput.play();            
        }
        if (talktoNyomott == true) {
            outputLabel.setText("You told a heartfelt story to the vial but it seems it is not interested in it.");timelineOutput.play();
        }
        if (pushNyomott == true) {
            //todo
        }
        if (pullNyomott == true) {
            //todo
        }
        akcioKikapcsolo();
    }
    @FXML
    private void handleBackpackItem(ActionEvent event){
        if (takeNyomott == true || useNyomott == true) {
            itemList.get(9).setRoomnumber(-9);
            itemKijelzo(); //because it should not be shown anymore
            backpackLabel.setDisable(false);
            backpackFrame.setOpacity(0.8);
            hero.setVanBackpack(true);
            hero.setScore(1);
            timelineScore.play();
            musicDing();
            outputLabel.setText("You can put the things you find into something now!");timelineOutput.play();
        }
        if (lookatNyomott == true) {
            outputLabel.setText(itemList.get(9).getVizsgal());
        }
        if (talktoNyomott == true) {
            outputLabel.setText("You told a heartfelt story to the backpack but it is still empty...");timelineOutput.play();
        }
        akcioKikapcsolo();
    }
    @FXML
    private void handleWolfItem(ActionEvent event){
        if (takeNyomott == true) {
            outputLabel.setText("You decided to take the wolf. As you try to catch it it bits your hand, then grab your neck. The wolf killed you. You were not able to acquire the Whip of the Archaeologist.");timelineOutput.play();
            hero.setHp(hero.getHp()*-1);
            timelineHp.play();
        }
        if (dropNyomott == true) {
            //todo
        }
        if (lookatNyomott == true) {
            outputLabel.setText(itemList.get(10).getVizsgal());timelineOutput.play();
        }
        if (useNyomott == true) {
            outputLabel.setText("It seems it is a quite useless wolf.");timelineOutput.play();
        }
        if (giveNyomott == true) {
            //todo
        }
        if (talktoNyomott == true) {
            outputLabel.setText("You told a funny story to the wolf and it seems it likes it!");timelineOutput.play();
        }
        if (pushNyomott == true) {
            outputLabel.setText("You decided to push the wolf out of your way. As you try it it bits your hand, then grab your neck. The wolf killed you. You were not able to acquire the Whip of the Archaeologist.");timelineOutput.play();
            hero.setHp(hero.getHp()-2*hero.getHp());
            timelineHp.play();
        }
        if (pullNyomott == true) {
            outputLabel.setText("You decided to pull the tail of the wolf. As you try it it bits your hand, then grab your neck. The wolf killed you. You were not able to acquire the Whip of the Archaeologist.");timelineOutput.play();
            hero.setHp(hero.getHp()-2*hero.getHp());
            timelineHp.play();
        }
        akcioKikapcsolo();
    }
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="The functions which handle the action events on the action buttons of the GUI.">
    @FXML
    private void handleDigAction(ActionEvent event) {
        //if has shovel...
        //if currentRoom ==... else
        outputLabel.setText("With what? With your bare hands? C'mon you should have some backbone...");timelineOutput.play();
        akcioKikapcsolo();
        digNyomott = true;
        digButton.setOpacity(0.5);
    }
    @FXML
    private void handlePickupAction(ActionEvent event){
        akcioKikapcsolo();
        //if (hero.isVanBackpack() == true || itemList.get(9).getRoomnumber() == 0 && hero.getCurrentRoom() == 0){
        takeNyomott = true;
        takeButton.setOpacity(0.5);
        //} else {
        //    outputLabel.setText("First you would need something to put your items into.");
        //}
    }
    @FXML
    private void handleDropAction(ActionEvent event){
        akcioKikapcsolo();
        dropNyomott = true;
        dropButton.setOpacity(0.5);
    }
    @FXML
    private void handleLookatAction(ActionEvent event){
        akcioKikapcsolo();
        lookatNyomott = true;
        lookatButton.setOpacity(0.5);
    }
    @FXML
    private void handleUseAction(ActionEvent event){
        akcioKikapcsolo();
        useNyomott = true;
        useButton.setOpacity(0.5);
    }
    @FXML
    private void handleGiveAction(ActionEvent event){
        akcioKikapcsolo();
        giveNyomott = true;
        giveButton.setOpacity(0.5);
    }
    @FXML
    private void handleTalktoAction(ActionEvent event){
        akcioKikapcsolo();
        talktoNyomott = true;
        talktoButton.setOpacity(0.5);
    }
    @FXML
    private void handlePushAction(ActionEvent event){
        akcioKikapcsolo();
        pushNyomott = true;
        pushButton.setOpacity(0.5);
    }
    @FXML
    private void handlePullAction(ActionEvent event){
        akcioKikapcsolo();
        pullNyomott = true;
        pullButton.setOpacity(0.5);
    }
    //</editor-fold>
    
    //functions of the menu on the right side of the basePane
    /**
    * Shows the creditsPane.
    */
    @FXML
    private void handleCreditsAction(ActionEvent event){
        basePane.setDisable(true);
        basePane.setOpacity(0.3);
        creditsPane.setVisible(true); //"Credits \n \n" + 
        creditsText.setText("Code\n..." +
                "\n\nMusic\n" + "Tyops from https://freesound.org/s/414046" + 
                "\n\nSounds\n" + "Aiwha from https://freesound.org/s/196106" +
                "\n\nGraphics are based on the images of\n" +
                "pzUH, Jay Mayu, clayster2012, Rafaelchm, AhNinniah and Maugh from opengameart.org");
    }
    
    /**
    * Closes the creditsPane.
    */
    @FXML
    private void handleCreditsPaneButton(ActionEvent event){
        basePane.setDisable(false);
        basePane.setOpacity(1);
        creditsPane.setVisible(false);
    }
    
    /**
    * Shows a confirmation request for loading on the actionPane.
    */
    @FXML
    private void loadAction(ActionEvent event){
        basePane.setDisable(true);
        basePane.setOpacity(0.3);
        actionPane.setVisible(true);
        actionText.setText("Do you really want to load a previous status?");
    }
    
    /**
    * Shows a reply for a saving attempt on the actionPane depending on the hp of the hero.
    */
    @FXML
    private void saveAction(ActionEvent event){
        basePane.setDisable(true);
        basePane.setOpacity(0.3);
        actionPane.setVisible(true);
        actionText.setText(hero.getHp() < 1 ? "You are dead, can not save.": "Do you really want to save the current status?");
    }
    
    /**
    * This function handle the affirmative button on the actionPane.
    * Depending on what the user selected it either save, load or quit the game. 
    */
    @FXML
    private void handleActionPaneButton(ActionEvent event){
        basePane.setDisable(false);
        basePane.setOpacity(1);
        actionPane.setVisible(false);
        //System.out.println(saveText.getText());
        
        //put them out as class variables later
        String textQuit = "Do you want to quit?"; 
        String textSave = "Do you really want to save the current status?";
        String textLoad = "Do you really want to load a previous status?";
        
        if (actionText.getText().equals(textQuit)){
            System.exit(0);
        }
        
        else if (actionText.getText().equals(textSave)) {
            database.setHero(hero.getHp(), hero.getScore(), hero.getCurrentRoom(), hero.isVanBackpack());    
            //for updating the Rooms in the DB via setRoom()
            String desc = "";
            int eszakra;
            int keletre;
            int delre;
            int nyugatra;
            int fel;
            int le;
            int id;
            for (int i=0; i<roomList.size(); i++){
                desc = roomList.get(i).getDescription();
                eszakra = roomList.get(i).getEszakra();
                keletre = roomList.get(i).getKeletre();
                delre = roomList.get(i).getDelre();
                nyugatra = roomList.get(i).getNyugatra();
                fel = roomList.get(i).getFel();
                le = roomList.get(i).getLe();
                id = roomList.get(i).getId();
                database.setRoom(desc, eszakra, keletre, delre, nyugatra, fel, le, id); //variables are moved out not like in case of database.setHero()
            }
            //for updating the Items in the DB via setItem()
            int roomnumber;
            String name = "";
            String description = "";
            boolean felveheto;
            int pozX;
            int pozY;
            String vizsgal = "";
            for (int i=0; i<itemList.size(); i++){
                roomnumber = itemList.get(i).getRoomnumber();
                name = itemList.get(i).getName();
                description = itemList.get(i).getDescription();
                felveheto = itemList.get(i).isFelveheto();
                pozX = (int) itemList.get(i).getGombja().getLayoutX();//from the getLayout since it could have been changed meantime
                pozY = (int) itemList.get(i).getGombja().getLayoutY();
                vizsgal = itemList.get(i).getVizsgal();
                database.setItem(roomnumber, description, felveheto, vizsgal, pozX, pozY, name);
            }
        }
        
        else if (actionText.getText().equals(textLoad)) {
            hero = database.getHero(); //setHp() adjusts it by the param, not redefine, so the below correction is needed
            hero.setHp(-10);

            ArrayList<Room> rooms = database.getAllRoom();
            for(int i=0; i<roomList.size(); i++){
                roomList.get(i).setDescription(rooms.get(i).getDescription());
                roomList.get(i).setEszakra(rooms.get(i).getEszakra());
                roomList.get(i).setKeletre(rooms.get(i).getKeletre());
                roomList.get(i).setDelre(rooms.get(i).getDelre());
                roomList.get(i).setNyugatra(rooms.get(i).getNyugatra());
                roomList.get(i).setFel(rooms.get(i).getFel());
                roomList.get(i).setLe(rooms.get(i).getLe());
            }

            ArrayList<Item> items = database.getAllItem();
            for(int i=0; i<itemList.size(); i++){
                itemList.get(i).setRoomnumber(items.get(i).getRoomnumber());
                itemList.get(i).setName(items.get(i).getName());
                itemList.get(i).setDescription(items.get(i).getDescription());
                itemList.get(i).setFelveheto(items.get(i).isFelveheto());
                itemList.get(i).getGombja().setLayoutX(items.get(i).getPozX()); //from the DB since I do not update the pozX,Y of the POJO
                itemList.get(i).getGombja().setLayoutY(items.get(i).getPozY());
                itemList.get(i).setVizsgal(items.get(i).getVizsgal());
            }

            RoomDescription.setText(roomList.get(hero.getCurrentRoom()).getDescription());
            pic1.setImage(roomList.get(hero.getCurrentRoom()).getPic());
            akcioKikapcsolo();
            itemKijelzo();
            outputLabel.setText("");

            //it checks if there is a backpack after the load and set the frame and color of the inv if needed
            if (hero.isVanBackpack()){
                backpackLabel.setDisable(false);
                backpackFrame.setOpacity(0.8);
            } else { //ha alapallast toltene vissza
                backpackLabel.setDisable(true);
                backpackFrame.setOpacity(0.2);
            }
            
            commandGrid.setDisable(false);
            northButton.setDisable(false);
            eastButton.setDisable(false);
            southButton.setDisable(false);
            westButton.setDisable(false);
            upButton.setDisable(false);
            downButton.setDisable(false);
        }
    }
    
    /**
    * This function handle the cancel button on the actionPane.
    * It closes the actionPane and shows the basePane.
    */
    @FXML
    private void handleActionPaneCancel(ActionEvent event){
        basePane.setDisable(false);
        basePane.setOpacity(1);
        actionPane.setVisible(false);
    }
    
    /**
    * This function shows the helpPane.
    */
    @FXML
    private void handleHelpAction(ActionEvent event){
        basePane.setDisable(true);
        basePane.setOpacity(0.3);
        helpPane.setVisible(true);
        helpText.setText("To do something press a command button then if needed click on an object either in your inventory or on the scene to indicate on what item you want to do the given action.\nE.g.: select the 'Look at' button then the backpack on the ground.\n\nIf you are stuck check and try everything thoroghly!"); //ezt majd kilehet alapba tenni, ha nem akarom, hogy helyzet szerint valtozzon.
    }
    
    /**
    * This function closes the helpPane.
    */
    @FXML
    private void handleHelpPaneButton(ActionEvent event){
        basePane.setDisable(false);
        basePane.setOpacity(1);
        helpPane.setVisible(false);
    }
    
    /**
    * Shows a confirmation-request on the actionPane to close the game.
    */
    @FXML
    private void handleQuitAction(ActionEvent event){
        basePane.setDisable(true);
        basePane.setOpacity(0.3);
        actionPane.setVisible(true);
        actionText.setText("Do you want to quit?");
    }
    
    //Non-FXML class variables
    Hero hero = new Hero();
    ArrayList<Room> roomList = new ArrayList();
    ArrayList<Item> itemList = new ArrayList();
    Clip clip;
    DB database = new DB();
    
    boolean digNyomott;
    boolean takeNyomott;
    boolean dropNyomott;
    boolean lookatNyomott;
    boolean useNyomott;
    boolean giveNyomott;
    boolean talktoNyomott;
    boolean pushNyomott;
    boolean pullNyomott;
    //boolean[] actionArray = {digNyomott, takeNyomott, dropNyomott, lookatNyomott, useNyomott, giveNyomott, talktoNyomott, pushNyomott, pullNyomott};
    
    //<editor-fold defaultstate="collapsed" desc="Images">
    //action images
    Image imageDig = new Image(getClass().getResourceAsStream("pic/dig150.png"));
    Image imageUse = new Image(getClass().getResourceAsStream("pic/use150.png"));
    Image imageDrop = new Image(getClass().getResourceAsStream("pic/drop150.png"));
    Image imageLookat = new Image(getClass().getResourceAsStream("pic/lookat150.png"));
    Image imagePickup = new Image(getClass().getResourceAsStream("pic/pickup150.png"));
    Image imageGive = new Image(getClass().getResourceAsStream("pic/give150.png"));
    Image imageTalkto = new Image(getClass().getResourceAsStream("pic/talkto150.png"));
    Image imagePush = new Image(getClass().getResourceAsStream("pic/push150.png"));
    Image imagePull = new Image(getClass().getResourceAsStream("pic/pull150.png"));
    
    //direction images
    Image imageNorth = new Image(getClass().getResourceAsStream("pic/north.png"));
    Image imageEast = new Image(getClass().getResourceAsStream("pic/east.png"));
    Image imageSouth = new Image(getClass().getResourceAsStream("pic/south.png"));
    Image imageWest = new Image(getClass().getResourceAsStream("pic/west.png"));
    Image imageUp = new Image(getClass().getResourceAsStream("pic/up.png"));
    Image imageDown = new Image(getClass().getResourceAsStream("pic/down.png"));
    
    //icons on the right side
    Image imagePipa = new Image(getClass().getResourceAsStream("pic/pipa.png"));
    Image imageCredit = new Image(getClass().getResourceAsStream("pic/credit.png"));
    Image imageLoad = new Image(getClass().getResourceAsStream("pic/load.png"));
    Image imageSave = new Image(getClass().getResourceAsStream("pic/save.png"));
    Image imageHelp = new Image(getClass().getResourceAsStream("pic/help.png"));
    Image imageQuit = new Image(getClass().getResourceAsStream("pic/quit.png"));
    
    //room pictures
    Image imageStart = new Image(getClass().getResourceAsStream("pic/room/akA9QjaK.png"));
    Image imageErdoszele = new Image(getClass().getResourceAsStream("pic/room/erdo-szele-resized.png"));
    Image imageCastle = new Image(getClass().getResourceAsStream("pic/room/castle-resized.png"));
    Image imageRitkaserdo = new Image(getClass().getResourceAsStream("pic/room/ritkas-erdo-uttal-2.png"));
    Image imageMezouttal = new Image(getClass().getResourceAsStream("pic/room/mezo-uttal-2-color.png"));
    Image imageMezokuttal = new Image(getClass().getResourceAsStream("pic/room/mezo-well-resized.png"));
    Image imageIngovanyoserdo = new Image(getClass().getResourceAsStream("pic/room/ingovanyos-erdo-resized-colorless.png"));
    Image imageErdosmocsar = new Image(getClass().getResourceAsStream("pic/room/erdos-mocsar-2.png"));
    Image imageReed = new Image(getClass().getResourceAsStream("pic/room/reed-resized.png"));
    Image imageNadasuttal = new Image(getClass().getResourceAsStream("pic/room/nadas-uttal4.png"));
    Image imageMocsarikunyho = new Image(getClass().getResourceAsStream("pic/room/swamp-kunyho-resized.png"));
    Image imageForestWolf = new Image(getClass().getResourceAsStream("pic/room/wolf_resized_pix.png"));//
    Image imageWolflessForest = new Image(getClass().getResourceAsStream("pic/room/wolflessForest_resized.png"));
    
    //item pictures
    Image imageKnife = new Image(getClass().getResourceAsStream("pic/item/knife.png"));
    Image imageBeer = new Image(getClass().getResourceAsStream("pic/item/beer3.png"));
    Image imagePoison = new Image(getClass().getResourceAsStream("pic/item/poison.png"));
    Image imageKey = new Image(getClass().getResourceAsStream("pic/item/secretkey.png"));
    Image imageBackpack = new Image(getClass().getResourceAsStream("pic/item/backpack.png"));
    //</editor-fold>
    
    /**
     * This method creates the rooms of the game and add them to the ArrayList roomList containing the rooms.
     */
    public void roomHozzaado(){
        Room room = new Room(0, "You are in a forest. It seems it is more dense to the north but light shines through the foliage of the trees from the east.", 1, 4, 7, 2, imageStart);
        roomList.add(room);
        room = new Room(1, "You are wandering in a lush forest. As if you could hear running water from the north.", 13, 1, 0, 1, imageStart);
        roomList.add(room);
        room = new Room(2, "This is the western edge of the forest. You see a silhouette of a large building to the west.", -9, 0, -9, 3, imageErdoszele);
        roomList.add(room);
        room = new Room(3, "You are standing in front of a castle. It seems its gate is closed.", -9, 2, -9, -9, imageCastle);
        roomList.add(room);
        room = new Room(4, "You are in a sparse forest. There is a pathway leading to the south.", -9, -9, 5, 0, imageRitkaserdo);
        roomList.add(room);
        room = new Room(5, "You reached a field. You can see that there is something to the south.", 4, -9, 6, -9, imageMezouttal);
        roomList.add(room);
        room = new Room(6, "You found a well on the field.", 5, -9, -9, -9, imageMezokuttal);
        roomList.add(room);
        room = new Room(7, "You are in a swampy woods. As far as you can see there are less woods to the south but more swamp.", 0, -9, 8, -9, imageIngovanyoserdo);
        roomList.add(room);
        room = new Room(8, "You are in a swamp forest. But landscape is changing and you see only reeds ahead.", 7, 9, 9, 9, imageErdosmocsar);
        roomList.add(room);
        room = new Room(9, "You are in a bog. Reeds are everywhere.", 8, 10, 10, 10, imageReed);
        roomList.add(room);
        room = new Room(10, "You are wandering in the bog. Reeds are everywhere. And only reeds...", 9, 11, 9, 9, imageReed);
        roomList.add(room);
        room = new Room(11, "You found a pathway leading to the north among the reed!", 12, 10, 10, 10, imageNadasuttal);
        roomList.add(room);
        room = new Room(12, "The pathway led to a shack in the swamp...", -9, 10, 11, 9, imageMocsarikunyho);
        roomList.add(room);
        room = new Room(13, "Suddenly a wolf appears on your way. It's staring at you but you can't judge its intention...", -10, -10, -10, -10, imageForestWolf, -10, -9);
        roomList.add(room);
        room = new Room(14, "You are wandering in a forest, which appears to be wolfless. A rivulet closes your way to the north.", -9, -9, 1, -9, imageWolflessForest);
        roomList.add(room);
    }
    
    /**
     * This method creates the items of the game and add them to the ArrayList itemList containing the items.
     */
    public void itemHozzaado(){
        Item item = new Item(0, "kes", "Itt egy rozsdas kes. ", true, knifeButton, 14, 352, "It approx. 7 cm long.");
        itemList.add(item);
        item = new Item(-2, "paracetamol", "Egy doboz paracetamolt talalsz az asztalon. ", true, paracetamolButton, 65, 352, "The pills are red.");
        itemList.add(item);
        item = new Item(2, "sor", "Egy doboz sor van az agyon. ", true, beerButton, 165, 352, "It looks tasty.");
        itemList.add(item);
        item = new Item(6, "kulcs", "Egy kulcs hever az ejjeliszekrenyen. ", true, keyButton, 213, 352);
        itemList.add(item);
        item = new Item(3, "agy", " ", false, bedButton, 259, 352, "Common double bed, with red blanket.");
        itemList.add(item);
        item = new Item(1, "asztal", " ", false, tableButton, 302, 352, "Small, round table.");
        itemList.add(item);
        item = new Item(2, "mereg", "Egy uvegcse mereg van a sarokban.", true, poisonButton, 356, 352, "The label on the vial says: \"poison\".");
        itemList.add(item);
        item = new Item(-2, "karora", "A kep hatuljara ragasztva talaltal egy karorat.", true, watchButton, 461, 352);
        itemList.add(item);
        item = new Item(5, "kep", " ", false, pictureButton, 520, 352, "Tasteless. And it does not hang on the wall on a smooth way.");
        itemList.add(item);
        item = new Item(0, "backpack", " ", true, backpackButton, 550, 320, "Your backpack. But is seems it's empty.");
        itemList.add(item);
        item = new Item(13, "wolf", " ", false, wolfButton, 214, 107, "Seems bloodthirsty.");
        itemList.add(item);
    }

    /**
     * This method shows the items which are in the currentRoom or in the inventory, switch off the others.
     */
    public void itemKijelzo(){
        for (Item e : itemList){
            if (e.getRoomnumber() == hero.getCurrentRoom() || e.getRoomnumber() == -1) {
                String neve = "wolf";
                if (e.getName().equals(neve)){
                    e.getGombja().setOpacity(0.01);
                    e.getGombja().setDisable(false);
                } else {
                e.getGombja().setOpacity(1.0);
                e.getGombja().setDisable(false);
                }
            } else {
                e.getGombja().setOpacity(0.0);
                e.getGombja().setDisable(true);
            }            
        }
    }
    
    /**
     * This method sets and shows the basic status of the GUI elements like action and direction buttons and hp, score information. 
     */
    private void akcioKikapcsolo() {
        digNyomott = false;
        digButton.setOpacity(1);
        takeNyomott = false;
        takeButton.setOpacity(1);
        dropNyomott = false;
        dropButton.setOpacity(1);
        lookatNyomott = false;
        lookatButton.setOpacity(1);
        useNyomott = false;
        useButton.setOpacity(1);
        giveNyomott = false;
        giveButton.setOpacity(1);
        talktoNyomott = false;
        talktoButton.setOpacity(1);
        pushNyomott = false;
        pushButton.setOpacity(1);
        pullNyomott = false;
        pullButton.setOpacity(1);
        
        hpLabel.setText(""+hero.getHp());
        scoreLabel.setText(""+hero.getScore());

        if(hero.getHp() < 1){
            commandGrid.setDisable(true); //to do: decrease the opacity of these as well then set back at load
            northButton.setDisable(true);
            eastButton.setDisable(true);
            southButton.setDisable(true);
            westButton.setDisable(true);
            upButton.setDisable(true);
            downButton.setDisable(true);
        }
//        for (boolean e : actionArray){ //either this or the one-by-one method above
//           e = false;
//        }          
    }
    
    /**
     * This method plays the 'ding' music. 
     */
    public void musicDing() {     
        try {
            AudioFormat format;
            DataLine.Info info;
            //Clip clip;
            
            InputStream audioSrc = getClass().getResourceAsStream("sounds/196106AiwhaDingVagott.au");
            InputStream bufferedIn = new BufferedInputStream(audioSrc);
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(bufferedIn);

            format = audioStream.getFormat();
            info = new DataLine.Info(Clip.class, format);
            clip = (Clip) AudioSystem.getLine(info);
            clip.open(audioStream);
            clip.start();
        }
        catch (IOException | LineUnavailableException | UnsupportedAudioFileException e) {
            System.out.println(e);
        }
    }
    
    /**
     * This method plays the intro music. 
     */
    public void musicIntro() {     
        try {
            AudioFormat format;
            DataLine.Info info;
            //Clip clip;
            
            InputStream audioSrc = getClass().getResourceAsStream("sounds/media.io_414046__tyops__fantasy-gaming-intro.au");
            InputStream bufferedIn = new BufferedInputStream(audioSrc);
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(bufferedIn);

            format = audioStream.getFormat();
            info = new DataLine.Info(Clip.class, format);
            clip = (Clip) AudioSystem.getLine(info);
            clip.open(audioStream);
            
            try {
                Thread.sleep(800);
            } catch (InterruptedException ex) {
                Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
            }
      
            clip.start();
        }
        catch (Exception e) {
            System.out.println(e);
        }
    }
    
    /**
     * This method uploads the rooms into the SQL database when the game starts the first time and the DB is going to be created.
     * No need to call it later unless the DB is deleted.
     */
    public void roomFeltolto(){
        String desc = "";
        int eszakra;
        int keletre;
        int delre;
        int nyugatra;
        int fel;
        int le;
        int id;
        for (int i=0; i<roomList.size(); i++){
            desc = roomList.get(i).getDescription();
            eszakra = roomList.get(i).getEszakra();
            keletre = roomList.get(i).getKeletre();
            delre = roomList.get(i).getDelre();
            nyugatra = roomList.get(i).getNyugatra();
            fel = roomList.get(i).getFel();
            le = roomList.get(i).getLe();
            id = roomList.get(i).getId();
            database.addRoom(desc, eszakra, keletre, delre, nyugatra, fel, le, id);
        }
    }
    
    /**
     * This method uploads the items into the SQL database when the game starts the first time and the DB is going to be created.
     * No need to call it later unless the DB is deleted.
     */
    public void itemFeltolto(){
        int roomnumber;
        String name = "";
        String description = "";
        boolean felveheto;
        int pozX;
        int pozY;
        String vizsgal = "";
        for (int i=0; i<itemList.size(); i++){
            roomnumber = itemList.get(i).getRoomnumber();
            name = itemList.get(i).getName();
            description = itemList.get(i).getDescription();
            felveheto = itemList.get(i).isFelveheto();
            pozX = itemList.get(i).getPozX();
            pozY = itemList.get(i).getPozY();
            vizsgal = itemList.get(i).getVizsgal();
            database.addItem(roomnumber, name, description, felveheto, vizsgal, pozX, pozY);
        }
    }      
    
    Timeline timelineHp = new Timeline(new KeyFrame(Duration.seconds(0.00), evt -> hpLabel.setVisible(false)),
                                       new KeyFrame(Duration.seconds(0.5), evt -> hpLabel.setVisible(true)));
    Timeline timelineScore = new Timeline(new KeyFrame(Duration.seconds(0.00), evt -> scoreLabel.setVisible(false)),
                                       new KeyFrame(Duration.seconds(0.5), evt -> scoreLabel.setVisible(true)));
    Timeline timelineOutput = new Timeline(new KeyFrame(Duration.seconds(0.00), evt -> outputLabel.setVisible(false)),
                                       new KeyFrame(Duration.seconds(0.2), evt -> outputLabel.setVisible(true)));
    Timeline timelineDescription = new Timeline(new KeyFrame(Duration.seconds(0.00), evt -> RoomDescription.setVisible(false)),
                                       new KeyFrame(Duration.seconds(0.2), evt -> RoomDescription.setVisible(true)));
    
    /**
     * Starts the transition of the intro.
     */
    public void introTransition(){
        final Path path = new Path();
        path.getElements().add(new MoveTo(403, 140));//move here from the default, the centre of the object will be here, original: 403, 300
        path.getElements().add(new LineTo(403, -320)); //from moveTo
        //System.out.println("LayoutX: " + introPane.getLayoutX() + " LayoutY: " + introPane.getLayoutY());
        final PathTransition pathTransition = new PathTransition();
        pathTransition.setDuration(Duration.seconds(10.0)); //8 origin
        pathTransition.setDelay(Duration.seconds(.5)); //2 for test
        pathTransition.setPath(path);
        pathTransition.setNode(welcomeText);
        pathTransition.setCycleCount(1);
        pathTransition.setAutoReverse(false);
        pathTransition.play();
    }
    
    /**
     * Initializes the GUI elements.
     */
    public void guiInitializer(){
        Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();
        //System.out.println("Width: " + primaryScreenBounds.getWidth());
        //System.out.println("Height: " + primaryScreenBounds.getHeight()); 
        basePane.setLayoutX(primaryScreenBounds.getWidth()/2-640);
        basePane.setLayoutY(primaryScreenBounds.getHeight()/2-360); //half of the 720
        rectangle.setLayoutX(primaryScreenBounds.getWidth()/2-640); //half of the 1280
        rectangle.setLayoutY(primaryScreenBounds.getHeight()/2-360); //half of the 720
        coverRectangle.setLayoutX(primaryScreenBounds.getWidth()/2-640);
        coverRectangle.setLayoutY(primaryScreenBounds.getHeight()/2+360);
        actionPane.setLayoutX(primaryScreenBounds.getWidth()/2-300); //half of the 600 as the alertPane is 600 wide
        actionPane.setLayoutY(primaryScreenBounds.getHeight()/2-175); //and 350 high
        helpPane.setLayoutX(primaryScreenBounds.getWidth()/2-300); //like alertPane
        helpPane.setLayoutY(primaryScreenBounds.getHeight()/2-340); //like alertPane
        introPane.setLayoutX(primaryScreenBounds.getWidth()/2-640); //half of the 1280
        introPane.setLayoutY(primaryScreenBounds.getHeight()/2-360); //half of the 720
        creditsPane.setLayoutX(primaryScreenBounds.getWidth()/2-300); //like alertPane
        creditsPane.setLayoutY(primaryScreenBounds.getHeight()/2-175); //like alertPane
        
        if (primaryScreenBounds.getHeight() < 721){ //needed due to the too low height. would need the real 720 height and adjust with that
            basePane.setLayoutY(0); //if the trace would be on the left side (like in case of default Ubuntu), then set the X to zero later.
            rectangle.setLayoutY(0);
            introPane.setLayoutY(0);
            coverRectangle.setLayoutY(720);
            actionPane.setLayoutY(primaryScreenBounds.getHeight()/2-140);
            helpPane.setLayoutY(primaryScreenBounds.getHeight()/2-340);
            creditsPane.setLayoutY(primaryScreenBounds.getHeight()/2-160); //decreased by 15 since the real height is less than 720. Later adjust it with the real 720
        }
        basePane.setDisable(true); //set it true by default from fxml later, keep it now for more comfortable coding
        basePane.setVisible(false); //set it false by default from fxml later, keep it now for more comfortable coding
        
        //<editor-fold defaultstate="collapsed" desc="GUI buttons">
        digButton.setGraphic(new ImageView(imageDig));
        digButton.setPadding(Insets.EMPTY);
        digButton.setBackground(Background.EMPTY);
        takeButton.setGraphic(new ImageView(imagePickup));
        takeButton.setPadding(Insets.EMPTY);
        takeButton.setBackground(Background.EMPTY);
        dropButton.setGraphic(new ImageView(imageDrop));
        dropButton.setPadding(Insets.EMPTY);
        dropButton.setBackground(Background.EMPTY);
        lookatButton.setGraphic(new ImageView(imageLookat));
        lookatButton.setPadding(Insets.EMPTY);
        lookatButton.setBackground(Background.EMPTY);
        useButton.setGraphic(new ImageView(imageUse));
        useButton.setPadding(Insets.EMPTY);
        useButton.setBackground(Background.EMPTY);
        giveButton.setGraphic(new ImageView(imageGive));
        giveButton.setPadding(Insets.EMPTY);
        giveButton.setBackground(Background.EMPTY);
        talktoButton.setGraphic(new ImageView(imageTalkto));
        talktoButton.setPadding(Insets.EMPTY);
        talktoButton.setBackground(Background.EMPTY);
        pushButton.setGraphic(new ImageView(imagePush));
        pushButton.setPadding(Insets.EMPTY);
        pushButton.setBackground(Background.EMPTY);
        pullButton.setGraphic(new ImageView(imagePull));
        pullButton.setPadding(Insets.EMPTY);
        pullButton.setBackground(Background.EMPTY);
        
        northButton.setGraphic(new ImageView(imageNorth));
        northButton.setPadding(Insets.EMPTY);
        northButton.setBackground(Background.EMPTY);
        eastButton.setGraphic(new ImageView(imageEast));
        eastButton.setPadding(Insets.EMPTY);
        eastButton.setBackground(Background.EMPTY);
        southButton.setGraphic(new ImageView(imageSouth));
        southButton.setPadding(Insets.EMPTY);
        southButton.setBackground(Background.EMPTY);
        westButton.setGraphic(new ImageView(imageWest));
        westButton.setPadding(Insets.EMPTY);
        westButton.setBackground(Background.EMPTY);
        upButton.setGraphic(new ImageView(imageUp));
        upButton.setPadding(Insets.EMPTY);
        upButton.setBackground(Background.EMPTY);
        downButton.setGraphic(new ImageView(imageDown));
        downButton.setPadding(Insets.EMPTY);
        downButton.setBackground(Background.EMPTY);
        
        introButton.setGraphic(new ImageView(imagePipa));
        introButton.setPadding(Insets.EMPTY);
        introButton.setBackground(Background.EMPTY);
        actionPaneButton.setGraphic(new ImageView(imagePipa));
        actionPaneButton.setPadding(Insets.EMPTY);
        actionPaneButton.setBackground(Background.EMPTY);
        actionPaneCancel.setGraphic(new ImageView(imageQuit));
        actionPaneCancel.setPadding(Insets.EMPTY);
        actionPaneCancel.setBackground(Background.EMPTY);
        helpPaneButton.setGraphic(new ImageView(imagePipa));
        helpPaneButton.setPadding(Insets.EMPTY);
        helpPaneButton.setBackground(Background.EMPTY);
        creditsPaneButton.setGraphic(new ImageView(imagePipa));
        creditsPaneButton.setPadding(Insets.EMPTY);
        creditsPaneButton.setBackground(Background.EMPTY);
        
        creditButton.setGraphic(new ImageView(imageCredit));
        creditButton.setPadding(Insets.EMPTY);
        creditButton.setBackground(Background.EMPTY);
        loadButton.setGraphic(new ImageView(imageLoad));
        loadButton.setPadding(Insets.EMPTY);
        loadButton.setBackground(Background.EMPTY);
        saveButton.setGraphic(new ImageView(imageSave));
        saveButton.setPadding(Insets.EMPTY);
        saveButton.setBackground(Background.EMPTY);
        helpButton.setGraphic(new ImageView(imageHelp));
        helpButton.setPadding(Insets.EMPTY);
        helpButton.setBackground(Background.EMPTY);
        quitButton.setGraphic(new ImageView(imageQuit));
        quitButton.setPadding(Insets.EMPTY);
        quitButton.setBackground(Background.EMPTY);
        
        keyButton.setGraphic(new ImageView(imageKey));
        keyButton.setPadding(Insets.EMPTY);
        keyButton.setBackground(Background.EMPTY);
        beerButton.setGraphic(new ImageView(imageBeer));
        beerButton.setPadding(Insets.EMPTY);
        beerButton.setBackground(Background.EMPTY);
        poisonButton.setGraphic(new ImageView(imagePoison));
        poisonButton.setPadding(Insets.EMPTY);
        poisonButton.setBackground(Background.EMPTY);
        knifeButton.setGraphic(new ImageView(imageKnife));
        knifeButton.setPadding(Insets.EMPTY);
        knifeButton.setBackground(Background.EMPTY);
        backpackButton.setGraphic(new ImageView(imageBackpack));
        backpackButton.setPadding(Insets.EMPTY);
        backpackButton.setBackground(Background.EMPTY);
        //</editor-fold>
        
        welcomeText.setText("Welcome to the ... adventure game! \n \n" +
        "This is a placeholder for the story. \n" +
        "A savage war rages,...\n" +
        "... young hero sent back to the past... \n" +
        "...your task is to retrieve the magical tool...");
        
        EventHandler<KeyEvent> eventHandlerContESC = new EventHandler<KeyEvent>() { 
            @Override 
            public void handle(KeyEvent event) {
                if(event.getCode() == ESCAPE) {
                    //System.out.println(event.getCode()+"from fxml");
                    handleESCButton();
                }
            }  
        };
        AnchorPane.addEventHandler(KeyEvent.KEY_PRESSED, eventHandlerContESC);
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        guiInitializer();
        roomHozzaado();
        RoomDescription.setText(roomList.get(hero.getCurrentRoom()).getDescription());
        itemHozzaado();
        itemKijelzo();
        musicIntro();
        introTransition();
        //checks if this is the first start of the game, if yes, then call DB uploader methods as well.
        ArrayList<Room> rooms = database.getAllRoom();
        if (rooms.isEmpty()){
            database.addHero(10, 0, 0, false);
            roomFeltolto();
            itemFeltolto();
        }
    }
}
