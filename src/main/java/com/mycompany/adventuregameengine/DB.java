package com.mycompany.adventuregameengine;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * This class represents the Database
 * @author Zed895
 */
public class DB {
    
    final String JDBC_DRIVER = "org.apache.derby.jdbc.EmbeddedDriver";
    final String URL = "jdbc:derby:advDB;create=true";
    //final String USERNAME = "";
    //final String PASSWORD = "";
    
    Connection conn = null;
    Statement createStatement = null;
    DatabaseMetaData dbmd = null;
    
    public DB(){
        try {
            conn = DriverManager.getConnection(URL);
            System.out.println("There is a connection.");
        } catch (SQLException ex) {
            System.err.println("Connection error: " + ex);
        }
        
        if (conn != null){
            try {
                createStatement = conn.createStatement(); //create a statement (auto)
            } catch (SQLException ex) {
                System.err.println("Statement error: " + ex);
            }
        }
        
        //is the database empty, so the program runs first time?
        try {
            dbmd = conn.getMetaData();
//        } catch (SQLException ex) {
//            System.err.println("Can not get the metadata error: " + ex);
//        }
//        
//        try {
            ResultSet rs = dbmd.getTables(null, "APP", "HERO", null); //capital letter!
            if(!rs.next()){
                createStatement.execute("create table hero(hp INT, score INT, currentRoom INT, vanBackpack boolean)");
                createStatement.execute("create table rooms(description varchar(200), eszakra INT, keletre INT, delre INT, nyugatra INT, fel INT, le INT, id INT)");
                createStatement.execute("create table items(roomnumber INT, name varchar(30), description varchar(200), felveheto boolean, vizsgal varchar(200), pozX INT, pozY INT)");
            }
        } catch (SQLException ex) {
            System.err.println("ResultSet getTables/create table error: " + ex);
        }
    }
    
    /**
     * This function inserts the Hero into the hero table of the database via SQL as one record. 
     * I have to call this function only once when the DB is going to be created.
     * @param hp the hp attribute of the Hero POJO.
     * @param score the score attribute of the Hero POJO.
     * @param currentRoom the currentRoom attribute of the Hero POJO.
     * @param vanBackpack the vanBackpack attribute of the Hero POJO.
     */
    public void addHero(int hp, int score, int currentRoom, boolean vanBackpack){
        try {
            //String sql = "insert into hero values (" + hp + "," + score + "," + currentRoom + "," +vanBackpack+ ")";
            //createStatement.execute(sql);   
            String sql = "insert into hero values (?,?,?,?)";
            PreparedStatement preparedStatement = conn.prepareStatement(sql); //faster and datatype-check, so safer then the createStatement
            preparedStatement.setInt(1, hp);
            preparedStatement.setInt(2, score);
            preparedStatement.setInt(3, currentRoom);
            preparedStatement.setBoolean(4, vanBackpack);
            preparedStatement.execute();      
        } catch (SQLException ex) {
            System.err.println("addHero error: " + ex);
        }       
    }
    
    /**
     * This function inserts a room into the rooms table of the database via SQL.
     * Every room is a single record, so use a loop on the controller side.
     * I have to call this function only once when the DB is going to be created.
     * The requested params are the attributes of the same name of the Room POJO.
     */
    public void addRoom(String description, int eszakra, int keletre, int delre, int nyugatra, int fel, int le, int id){
        try {
            String sql = "insert into rooms values (?,?,?,?,?,?,?,?)";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, description);
            preparedStatement.setInt(2, eszakra);
            preparedStatement.setInt(3, keletre);
            preparedStatement.setInt(4, delre);
            preparedStatement.setInt(5, nyugatra);
            preparedStatement.setInt(6, fel);
            preparedStatement.setInt(7, le);
            preparedStatement.setInt(8, id);
            preparedStatement.execute();      
        } catch (SQLException ex) {
            System.err.println("addRoom error: " + ex);
        }       
    }
    
    /**
     * This function inserts an item into the items table of the database via SQL.
     * Every item is a single record, so use a loop on the controller side.
     * I have to call this function only once when the DB is going to be created.
     * The requested params are the attributes of the same name of the Item POJO.
     */
    public void addItem(int roomnumber, String name, String description, boolean felveheto, String vizsgal, int pozX, int pozY){
        try {
            String sql = "insert into items values (?,?,?,?,?,?,?)";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1, roomnumber);
            preparedStatement.setString(2, name);
            preparedStatement.setString(3, description);
            preparedStatement.setBoolean(4, felveheto);
            preparedStatement.setString(5, vizsgal);
            preparedStatement.setInt(6, pozX);
            preparedStatement.setInt(7, pozY);
            preparedStatement.execute();      
        } catch (SQLException ex) {
            System.err.println("addItem error: " + ex);
        }       
    }
    
    /**
     * This function gets the metadata of the Database from the result table (rs) and shows it.
     * Use it only for debugging.
     */
    public void showMeta(){
        String sqlHero = "select * from hero";
        String sqlRooms = "select * from rooms";
        String sqlItems = "select * from items";
        ResultSet rs = null;
        ResultSetMetaData rsmd = null;
        try {
            rs = createStatement.executeQuery(sqlHero);
            rsmd = rs.getMetaData();
            int columnCount = rsmd.getColumnCount();
            for (int i=1; i<= columnCount; i++){
                System.out.print(rsmd.getColumnName(i) + " | ");
            }
            System.out.println();
        } catch (SQLException ex) {
            System.err.println("showHeroMeta error: " + ex);
        }
        try {
            rs = createStatement.executeQuery(sqlRooms);
            rsmd = rs.getMetaData();
            int columnCount = rsmd.getColumnCount();
            for (int i=1; i<= columnCount; i++){
                System.out.print(rsmd.getColumnName(i) + " | ");
            }
            System.out.println();
        } catch (SQLException ex) {
            System.err.println("showRoomsMeta error: " + ex);
        }
        try {
            rs = createStatement.executeQuery(sqlItems);
            rsmd = rs.getMetaData();
            int columnCount = rsmd.getColumnCount();
            for (int i=1; i<= columnCount; i++){
                System.out.print(rsmd.getColumnName(i) + " | ");
            }
            System.out.println();
        } catch (SQLException ex) {
            System.err.println("showItemsMeta error: " + ex);
        }
    }
    
    /**
     * This function gets the records of the hero table and shows it.
     * Use it only for debugging.
     */
    public void showHero(){
        String sql = "select * from hero";
        try {
            ResultSet rs = createStatement.executeQuery(sql);
            while (rs.next()){ //let me see if there are more heros by accident during debug
                Integer hp = rs.getObject("hp", Integer.class); //the "hp" is the field (column) name, I get the value of it
                int score = rs.getInt("score"); 
                Integer currentRoom = rs.getObject("currentRoom", Integer.class); 
                boolean vanBackpack = rs.getObject("vanBackpack", Boolean.class);
                System.out.println(hp + " | "+ score+ " | "+currentRoom + " | "+ vanBackpack);    
            }
        } catch (SQLException ex) {
            System.err.println("showHero error: " + ex);
        }
    }
    
    /**
     * Selects the hero record from the hero table of the database.
     * @return a Hero object.
     */
    public Hero getHero(){
        String sql = "select * from hero";
        Hero hero = new Hero();
        try {
            ResultSet rs = createStatement.executeQuery(sql);
            rs.next(); //rs.next is a reference to the row delivered by the createStatement.
                hero.setHp(rs.getInt("hp"));
                hero.setScore(rs.getInt("score"));
                hero.setCurrentRoom(rs.getInt("currentRoom"));
                hero.setVanBackpack(rs.getBoolean("vanBackpack"));           
        } catch (SQLException ex) {
            System.err.println("getHero error: " + ex);
        }
        return hero;
    }
    
    /**
     * Updates the hero record of the hero table of the database.
     * Call it as part of the saving of the current status of the game.
     * @param hp the hp attribute of the Hero POJO.
     * @param score the score attribute of the Hero POJO.
     * @param currentRoom the currentRoom attribute of the Hero POJO.
     * @param vanBackpack the vanBackpack attribute of the Hero POJO.
     */
    public void setHero(int hp, int score, int currentRoom, boolean vanBackpack){
        try {  
            String sql = "update hero set hp = ?, score = ?, currentRoom = ?, vanBackpack = ?";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1, hp);
            preparedStatement.setInt(2, score);
            preparedStatement.setInt(3, currentRoom);
            preparedStatement.setBoolean(4, vanBackpack);
            preparedStatement.execute();      
        } catch (SQLException ex) {
            System.err.println("setHero error: " + ex);
        }       
    }
    
    /**
     * Selects everything from the rooms table of the database.
     * Call it as part of the loading of the previous status of the game.
     * On the controller side you can assign the elements of its list to the actual list of the rooms.
     * @return an ArrayList containing the Room POJOs.
     */
    public ArrayList<Room> getAllRoom(){
        String sql = "select * from rooms";
        ArrayList<Room> rooms = null; //if I receive null that means mistake.
        try {
            ResultSet rs = createStatement.executeQuery(sql);
            rooms = new ArrayList<>(); //if I receive this means no mistake but the datatable is empty.
            
            while (rs.next()){
                Room actualRoom = new Room(); //it could send back a simple list but why not create a (partial) Room and put that into a list? 
                actualRoom.setDescription(rs.getString("description"));
                actualRoom.setEszakra(rs.getInt("eszakra"));
                actualRoom.setKeletre(rs.getInt("keletre"));
                actualRoom.setDelre(rs.getInt("delre"));
                actualRoom.setNyugatra(rs.getInt("nyugatra"));
                actualRoom.setFel(rs.getInt("fel"));
                actualRoom.setLe(rs.getInt("le"));
                rooms.add(actualRoom);          
            }
        } catch (SQLException ex) {
            System.err.println("getAllRoom error: " + ex);
        }
        return rooms;
    }
    
    /**
     * Updates a room record of the rooms table of the database.
     * Call it as part of the saving of the previous status of the game.
     * Every room is a single record, so use a loop on the controller side.
     * The requested params are the attributes of the same name of the Room POJO.
     */
    public void setRoom(String description, int eszakra, int keletre, int delre, int nyugatra, int fel, int le, int id){
        try {
            String sql = "update rooms set description = ?, eszakra = ?, keletre = ?, delre = ?, nyugatra = ?, fel = ?, le = ? where id = ?";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, description);
            preparedStatement.setInt(2, eszakra);
            preparedStatement.setInt(3, keletre);
            preparedStatement.setInt(4, delre);
            preparedStatement.setInt(5, nyugatra);
            preparedStatement.setInt(6, fel);
            preparedStatement.setInt(7, le);
            preparedStatement.setInt(8, id);
            preparedStatement.execute();      
        } catch (SQLException ex) {
            System.err.println("setRoom error: " + ex);
        }
    }
    
    /**
     * Selects everything from the items table of the database.
     * Call it as part of the loading of the previous status of the game.
     * On the controller side you can assign the elements of its list to the actual list of the items.
     * @return an ArrayList containing the Item POJOs.
     */
    public ArrayList<Item> getAllItem(){
        String sql = "select * from items";
        ArrayList<Item> items = null; //if I receive null that means mistake.
        try {
            ResultSet rs = createStatement.executeQuery(sql);
            items = new ArrayList<>(); //if I receive this means no mistake but the datatable is empty.
            
            while (rs.next()){
                Item actualItem = new Item();
                actualItem.setRoomnumber(rs.getInt("roomnumber"));
                actualItem.setName(rs.getString("name"));
                actualItem.setDescription(rs.getString("description"));
                actualItem.setFelveheto(rs.getBoolean("felveheto"));
                actualItem.setVizsgal(rs.getString("vizsgal"));
                actualItem.setPozX(rs.getInt("pozX"));
                actualItem.setPozY(rs.getInt("pozY"));
                items.add(actualItem);   
            }
        } catch (SQLException ex) {
            System.err.println("getAllItem error: " + ex);
        }
        return items;
    }
    
    /**
     * Updates an item record of the items table of the database.
     * Call it as part of the saving of the previous status of the game.
     * Every item is a single record, so use a loop on the controller side.
     * The requested params are the attributes of the same name of the Item POJO.
     */
    public void setItem(int roomnumber, String description, boolean felveheto, String vizsgal, int pozX, int pozY, String name){
        try {
            String sql = "update items set roomnumber = ?, description = ?, felveheto = ?, vizsgal = ?, pozX = ?, pozY = ? where name = ?";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1, roomnumber);
            preparedStatement.setString(2, description);
            preparedStatement.setBoolean(3, felveheto);
            preparedStatement.setString(4, vizsgal);
            preparedStatement.setInt(5, pozX);
            preparedStatement.setInt(6, pozY);
            preparedStatement.setString(7, name);
            preparedStatement.execute();      
        } catch (SQLException ex) {
            System.err.println("setItem error: " + ex);
        }
    }
    
}
