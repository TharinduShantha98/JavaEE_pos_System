package dao.custom;

import dao.CrudDao;
import entitiy.Item;

import java.sql.SQLException;
import java.util.ArrayList;

public interface ItemDao  extends CrudDao<Item, String> {

    ArrayList<String> getAllItemCodes() throws SQLException, ClassNotFoundException;
    boolean updateItemQty(String itemCode, double qty) throws SQLException, ClassNotFoundException;
    String getItemCode() throws SQLException, ClassNotFoundException;


}
