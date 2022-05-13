package bo.custom;

import model.ItemDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface ItemBo {

    ArrayList<ItemDTO> getAllItem() throws SQLException, ClassNotFoundException;
    boolean addItem(ItemDTO itemDTO) throws SQLException, ClassNotFoundException;


}
