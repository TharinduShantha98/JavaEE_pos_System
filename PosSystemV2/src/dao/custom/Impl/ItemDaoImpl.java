package dao.custom.Impl;

import controller.ItemServlet;
import dao.CrudUtil;
import dao.custom.ItemDao;
import entitiy.Item;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ItemDaoImpl implements ItemDao {
    @Override
    public boolean add(Item item) throws SQLException, ClassNotFoundException {
        Connection connection = ItemServlet.dataSource.getConnection();

        boolean b = CrudUtil.executeUpdate("INSERT INTO item VALUES(?,?,?,?,?,?)",connection,
                item.getItemCode(), item.getItemName(), item.getUnitPrice(), item.getPackSize(), item.getBuyingPrice(), item.getQuantity());

        connection.close();
        return b;


    }

    @Override
    public boolean delete(String s) throws SQLException, ClassNotFoundException {
        Connection connection = ItemServlet.dataSource.getConnection();
        boolean b = CrudUtil.executeUpdate("DELETE FROM item WHERE itemCode = ?",connection, s);
        connection.close();

        return b;
    }

    @Override
    public boolean update(Item item) throws SQLException, ClassNotFoundException {
        Connection connection = ItemServlet.dataSource.getConnection();
        boolean b = CrudUtil.executeUpdate("UPDATE item SET itemName = ?, unitPrice= ?, buyingPrice= ?, packSise= ?, itemQty= ? WHERE itemCode = ?",
                connection,item.getItemName(),item.getUnitPrice(),item.getBuyingPrice(),item.getPackSize(),item.getQuantity(),item.getItemCode());

        connection.close();

        return b;
    }


    @Override
    public Item search(String s) throws SQLException, ClassNotFoundException {
        Connection connection = ItemServlet.dataSource.getConnection();

        ResultSet resultSet = CrudUtil.executeQuery("SELECT * FROM item WHERE itemCode = ?",connection, s);
        Item item;

        if (resultSet.next()){
            item = new Item(resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getBigDecimal(3),
                    resultSet.getBigDecimal(4),
                    resultSet.getString(5),
                    resultSet.getDouble(6));

            connection.close();
            return  item;
        }

        connection.close();

        return null;
    }

    @Override
    public ArrayList<Item> getAll() throws SQLException, ClassNotFoundException {
        Connection connection = ItemServlet.dataSource.getConnection();
        ResultSet resultSet = CrudUtil.executeQuery("SELECT * FROM item",connection);
        ArrayList<Item> itemArrayList = new ArrayList<>();

        while (resultSet.next()){
            itemArrayList.add(new Item(resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getBigDecimal(3),
                    resultSet.getBigDecimal(4),
                    resultSet.getString(5),
                    resultSet.getDouble(6)));
        }

        connection.close();
        return itemArrayList;





    }

    @Override
    public ArrayList<String> getAllItemCodes() throws SQLException, ClassNotFoundException {
        Connection connection = ItemServlet.dataSource.getConnection();
        ResultSet resultSet = CrudUtil.executeQuery("SELECT itemCode FROM item ",connection);

        ArrayList<String> getAllCustomer = new ArrayList<>();

        while (resultSet.next()){
            getAllCustomer.add(resultSet.getString(1));

        }

        connection.close();
        return getAllCustomer;


    }

    @Override
    public boolean updateItemQty(String itemCode, double qty) throws SQLException, ClassNotFoundException {
        Connection connection = ItemServlet.dataSource.getConnection();
        boolean b = CrudUtil.executeUpdate("UPDATE item SET itemQty = (itemQty - ?) WHERE itemCode = ?",connection, qty, itemCode);

        connection.close();
        return b;


    }

    @Override
    public String getItemCode() throws SQLException, ClassNotFoundException {
        Connection connection = ItemServlet.dataSource.getConnection();
        ResultSet resultSet = CrudUtil.executeQuery("SELECT itemCode FROM item ORDER BY  itemCode  DESC LIMIT 1",connection);

            if(resultSet.next()){
                int tempId = Integer.parseInt(resultSet.getString(1).split("-")[1]);
                tempId = tempId +1;
                connection.close();
                return  "I-" + tempId;
            }else{
                connection.close();
                return "I-100";
            }

    }
}
