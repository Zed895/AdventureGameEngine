package com.mycompany.adventuregameengine;

/**
 * The POJO made according to this class represents the protagonist of the game.
 * @author Zed895
 */
public class Hero {
    
    private int hp = 10;
    private int score = 0;
    private int currentRoom = 0;
    private boolean vanBackpack = false;

    /**
    * Constructor to create a hero object.
    */
    public Hero() {
    }

    //getters and setters
    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp += hp;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score += score;
    }

    public int getCurrentRoom() {
        return currentRoom;
    }

    public void setCurrentRoom(int currentRoom) {
        this.currentRoom = currentRoom;
    }

    public boolean isVanBackpack() {
        return vanBackpack;
    }

    public void setVanBackpack(boolean vanBackpack) {
        this.vanBackpack = vanBackpack;
    }

}
