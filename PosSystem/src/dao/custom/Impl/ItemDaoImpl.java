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
        return false;
    }

    @Override
    public boolean delete(String s) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean update(Item item) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public Item search(String s) {
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