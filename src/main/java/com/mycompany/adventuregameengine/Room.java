package com.mycompany.adventuregameengine;

import javafx.scene.image.Image;

/**
 * POJOs made according to this class represent a place in the game.
 * The hero can move between these rooms by clicking on the direction buttons of the GUI.
 * @author Zed895
 */
public class Room {
    private int id;
    private String description = "";
    private int eszakra;
    private int keletre;
    private int delre;
    private int nyugatra;
    private Image pic;
    private int fel = -9;
    private int le = -9;
    
    /**
    * Constructor to create a room object from the DB.
    */
    public Room(){};
    
    /**
    * Constructor to create a room object from the Controller.
    * @param id id number of the room.
    * @param description the description of the room.
    * @param eszakra specifies which room the hero can go to the north from the current one into.
    * @param keletre specifies which room the hero can go to the east from the current one into.
    * @param delre specifies which room the hero can go to the south from the current one into.
    * @param nyugatra specifies which room the hero can go to the west from the current one into.
    * @param image the image belonging to this room.
    */
    public Room(int id, String description, int eszakra, int keletre, int delre, int nyugatra, Image image) {
        this.description = description;
        this.eszakra = eszakra;
        this.keletre = keletre;
        this.delre = delre;
        this.nyugatra = nyugatra;
        this.pic = image;
        this.id = id;
    }
    
    /**
    * Constructor to create a room object from the Controller.
    * @param id specifies which room it is in.
    * @param description the name of the item
    * @param eszakra specifies which room the hero can go to the north from the current one into.
    * @param keletre specifies which room the hero can go to the east from the current one into.
    * @param delre specifies which room the hero can go to the south from the current one into.
    * @param nyugatra specifies which room the hero can go to the west from the current one into.
    * @param image the image belonging to this room.
    * @param fel specifies which room the hero can go upward from the current one into.
    * @param le specifies which room the hero can go downward from the current one into.
    */
    public Room(int id, String description, int eszakra, int keletre, int delre, int nyugatra, Image image, int fel, int le) {
        this(id, description, eszakra, keletre, delre, nyugatra, image);
        this.fel = fel;
        this.le = le;
    }

    //getters and setters
    public int getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public int getEszakra() {
        return eszakra;
    }

    public int getKeletre() {
        return keletre;
    }

    public int getDelre() {
        return delre;
    }

    public int getNyugatra() {
        return nyugatra;
    }

    public int getFel() {
        return fel;
    }

    public int getLe() {
        return le;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setEszakra(int eszakra) {
        this.eszakra = eszakra;
    }

    public void setKeletre(int keletre) {
        this.keletre = keletre;
    }

    public void setDelre(int delre) {
        this.delre = delre;
    }

    public void setNyugatra(int nyugatra) {
        this.nyugatra = nyugatra;
    }

    public void setFel(int fel) {
        this.fel = fel;
    }

    public void setLe(int le) {
        this.le = le;
    }
        
    public Image getPic() {
        return pic;
    }

    public void setPic(Image pic) {
        this.pic = pic;
    }

    @Override
    public String toString() {
        return "Room{" + "description=" + description + ", north=" + eszakra + ", east=" + keletre + ", south=" + delre + ", west=" + nyugatra + ", up=" + fel + ", down=" + le + '}';
    }
    
}
