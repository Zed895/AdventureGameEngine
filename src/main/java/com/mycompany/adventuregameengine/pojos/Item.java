package com.mycompany.adventuregameengine.pojos;

import javafx.scene.control.Button;

/**
 * POJOs made according to this class represent an item in the game.
 * The hero can interact with these items through the action buttons of the GUI.
 * @author Zed895
 */
public class Item {
    private int roomnumber;
    private String name = "";
    private String description = ""; //no need, remove it later
    private boolean felveheto = false;
    private Button gombja;
    private int pozX;
    private int pozY; //original is from fxml, only need one which is overwritten by pickup and drop.
    private String vizsgal = "There is nothing special about it.";
    
    /**
    * Constructor to create an item object from the Controller.
    * @param roomnumber specifies which room it is in.
    * @param name the name of the item
    * @param description the description of the item
    * @param felveheto can the Hero pick it up? true or false
    * @param gombja the Button which belongs to this item on the View
    * @param pozX the position of the item on the X axis in the View
    * @param pozY the position of the item on the Y axis in the View
    */
    public Item(int roomnumber, String name, String description, boolean felveheto, Button gombja, int pozX, int pozY) {
        this.roomnumber = roomnumber;
        this.name = name;
        this.description = description;
        this.felveheto = felveheto;
        this.gombja = gombja;
        this.pozX = pozX;
        this.pozY = pozY;
    }
    
    /**
    * Constructor to create an item object from the DB.
    */
    public Item(){};
    
    /**
    * Constructor to create an item object from the Controller.
    * @param roomnumber specifies which room it is in.
    * @param name the name of the item
    * @param description the description of the item
    * @param felveheto can the Hero pick it up? true or false
    * @param gombja the Button which belongs to this item on the View
    * @param pozX the position of the item on the X axis in the View
    * @param pozY the position of the item on the Y axis in the View
    * @param vizsgal the information what the hero receive about this item if he investigate it closely
    */
    public Item(int roomnumber, String name, String description, boolean felveheto, Button gombja, int pozX, int pozY, String vizsgal) {
        this(roomnumber, name, description, felveheto, gombja, pozX, pozY);
        this.vizsgal = vizsgal;
    }

    //getters and setters
    public int getRoomnumber() {
        return roomnumber;
    }

    public void setRoomnumber(int roomnumber) {
        this.roomnumber = roomnumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isFelveheto() {
        return felveheto;
    }

    public void setFelveheto(boolean felveheto) {
        this.felveheto = felveheto;
    }

    public String getVizsgal() {
        return vizsgal;
    }

    public void setVizsgal(String vizsgal) {
        this.vizsgal = vizsgal;
    }

    public Button getGombja() {
        return gombja;
    }

    public int getPozX() {
        return pozX;
    }

    public void setPozX(int pozX) {
        this.pozX = pozX;
    }

    public int getPozY() {
        return pozY;
    }

    public void setPozY(int pozY) {
        this.pozY = pozY;
    }
    
    @Override
    public String toString() {
        return "Item{" + "roomnumber=" + roomnumber + ", name=" + name + ", description=" + description + ", felveheto=" + felveheto + ", lookAt=" + vizsgal + '}';
    }
    
}
