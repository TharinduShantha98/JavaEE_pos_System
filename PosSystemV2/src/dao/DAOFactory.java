package dao;

import dao.custom.Impl.CustomerDaoImpl;
import dao.custom.Impl.ItemDaoImpl;
import dao.custom.Impl.OrderDaoImpl;

public class DAOFactory {

    private  static  DAOFactory daoFactory;

    private DAOFactory(){

    }

    public enum DAOTypes{

        CUSTOMER,ITEM,ORDER
    }


    public static  DAOFactory getDaoFactory(){

        if(daoFactory == null){
            daoFactory = new DAOFactory();
        }
        return daoFactory;

    }


    public SuperDao getDao(DAOTypes types){

        switch (types){
            case ITEM:
                return new ItemDaoImpl();
            case ORDER:
                return new OrderDaoImpl();
            case CUSTOMER:
                return new CustomerDaoImpl();
            default:
                return null;

        }

    }



}
