package bo.custom.Impl;

import bo.custom.ItemBo;
import dao.custom.Impl.ItemDaoImpl;
import dao.custom.ItemDao;
import entitiy.Item;
import model.ItemDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public class ItemBoImpl implements ItemBo {


    ItemDao itemDao = new ItemDaoImpl();

    @Override
    public ArrayList<ItemDTO> getAllItem() throws SQLException, ClassNotFoundException {

        ArrayList<Item> all = itemDao.getAll();
        ArrayList<ItemDTO>  itemDTOArrayList = new ArrayList<>();

        for (Item item: all
             ) {
            itemDTOArrayList.add(new ItemDTO(item.getItemCode(),
                    item.getItemName(),
                    item.getUnitPrice(),
                    item.getBuyingPrice(),
                    item.getPackSize(),
                    item.getQuantity()));

        }


        return itemDTOArrayList;





    }
}
