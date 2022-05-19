package bo.custom;

import model.ItemDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface ItemBo {

    ArrayList<ItemDTO> getAllItem() throws SQLException, ClassNotFoundException;
    boolean addItem(ItemDTO itemDTO) throws SQLException, ClassNotFoundException;
    ItemDTO searchItem(String itemCode) throws SQLException, ClassNotFoundException;
    boolean updateItem(ItemDTO itemDTO) throws SQLException, ClassNotFoundException;
    boolean deleteItem(String s) throws SQLException, ClassNotFoundException;
    ArrayList<String> getAllItemsCodes() throws SQLException, ClassNotFoundException;
    String getItemCode() throws SQLException, ClassNotFoundException;
}