package db;

import models.Item;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class DbManager {
    private  static Connection connection;

    static {

        try{
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(
                    "jdbc:postgresql://localhost:5432/postgres",
                    "postgres",
                    "meruert1995"

            );
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    public static List<Item> getItems() {
        List<Item> items = new ArrayList<>();
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT * FROM ITEMS"
            );
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                Item item = new Item();
                item.setId(resultSet.getLong("ID"));
                item.setName(resultSet.getString("NAME"));
                item.setDescription(resultSet.getString("DESCRIPTION"));
                item.setPrice(resultSet.getDouble("PRICE"));
                items.add(item);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return items;
    }
    public static void addItem(Item item){
        try{
            PreparedStatement statement = connection.prepareStatement(
                    "INSERT INTO  ITEMS(NAME, DESCRIPTION, PRICE)" +
                            "VALUES(? ,?, ?)"
            );
            statement.setString(1, item.getName());
            statement.setString(2, item.getDescription());
            statement.setDouble(3, item.getPrice());
            statement.executeUpdate();


        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public static Item getItemById(long id){
        Item item = null;
        try{
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT * FROM ITEMS WHERE id = ?"
            );
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next()){
                item = new Item();
                item.setId(resultSet.getLong("ID"));
                item.setName(resultSet.getString("NAME"));
                item.setDescription(resultSet.getString("DESCRIPTION"));
                item.setPrice(resultSet.getDouble("PRICE"));
            }
            statement.close();
        } catch (Exception e){
            e.printStackTrace();
        }
        return item;
    }
    public static void editItem(Item item) {
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "UPDATE ITEMS SET NAME = ?, DESCRIPTION = ?, PRICE =? " +
                            "WHERE ID = ?"
            );
            statement.setString(1, item.getName());
            statement.setString(2, item.getDescription());
            statement.setDouble(3, item.getPrice());
            statement.setLong(4, item.getId());
            statement.executeUpdate();
            statement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
