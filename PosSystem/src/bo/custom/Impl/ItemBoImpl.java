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

    @Override
    public boolean addItem(ItemDTO itemDTO) throws SQLException, ClassNotFoundException {

        boolean add = itemDao.add(new Item(itemDTO.getItemCode(),
                itemDTO.getItemName(),
                itemDTO.getUnitPrice(),
                itemDTO.getBuyingPrice(),
                itemDTO.getPackSize(),
                itemDTO.getQuantity()));


        return add;
    }

    @Override
    public ItemDTO searchItem(String itemCode) throws SQLException, ClassNotFoundException {
        Item search = itemDao.search(itemCode);
        ItemDTO itemDTO = new ItemDTO(search.getItemCode(),
                search.getItemName(),
                search.getUnitPrice(),
                search.getBuyingPrice(),
                search.getPackSize(),
                search.getQuantity());

        return itemDTO;

    }

    @Override
    public boolean updateItem(ItemDTO itemDTO) throws SQLException, ClassNotFoundException {
        boolean update = itemDao.update(new Item(itemDTO.getItemCode(), itemDTO.getItemName(), itemDTO.getUnitPrice(), itemDTO.getBuyingPrice(),
                itemDTO.getPackSize(), itemDTO.getQuantity()));

        return  update;

    }

    @Override
    public boolean deleteItem(String s) throws SQLException, ClassNotFoundException {
        boolean delete = itemDao.delete(s);
        return  delete;

    }
}
