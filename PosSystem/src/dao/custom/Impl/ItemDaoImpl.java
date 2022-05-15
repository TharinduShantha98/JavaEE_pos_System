package dao.custom.Impl;

import dao.CrudUtil;
import dao.custom.ItemDao;
import entitiy.Item;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ItemDaoImpl implements ItemDao {
    @Override
    public boolean add(Item item) throws SQLException, ClassNotFoundException {
        boolean b = CrudUtil.executeUpdate("INSERT INTO item VALUES(?,?,?,?,?,?)",
                item.getItemCode(), item.getItemName(), item.getUnitPrice(), item.getPackSize(), item.getBuyingPrice(), item.getQuantity());

        return b;


    }

    @Override
    public boolean delete(String s) throws SQLException, ClassNotFoundException {
        boolean b = CrudUtil.executeUpdate("DELETE FROM item WHERE itemCode = ?", s);
        return b;
    }

    @Override
    public boolean update(Item item) throws SQLException, ClassNotFoundException {
        boolean b = CrudUtil.executeUpdate("UPDATE item SET itemName = ?, unitPrice= ?, buyingPrice= ?, packSise= ?, itemQty= ? WHERE itemCode = ?",
                item.getItemName(),item.getUnitPrice(),item.getBuyingPrice(),item.getPackSize(),item.getQuantity(),item.getItemCode());

        return b;
    }

    @Override
    public Item search(String s) throws SQLException, ClassNotFoundException {

        ResultSet resultSet = CrudUtil.executeQuery("SELECT * FROM item WHERE itemCode = ?", s);
        Item item;

        while (resultSet.next()){
            item = new Item(resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getBigDecimal(3),
                    resultSet.getBigDecimal(4),
                    resultSet.getString(5),
                    resultSet.getDouble(6));


            return  item;
        }



        return null;
    }

    @Override
    public ArrayList<Item> getAll() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = CrudUtil.executeQuery("SELECT * FROM item");
        ArrayList<Item> itemArrayList = new ArrayList<>();

        while (resultSet.next()){
            itemArrayList.add(new Item(resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getBigDecimal(3),
                    resultSet.getBigDecimal(4),
                    resultSet.getString(5),
                    resultSet.getDouble(6)));
        }

        return itemArrayList;





    }
}
