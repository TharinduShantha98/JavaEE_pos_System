package bo;

import bo.custom.Impl.CustomerBoImpl;
import bo.custom.Impl.ItemBoImpl;
import bo.custom.Impl.OrderBoImpl;

public class BOFactory {
    private  static  BOFactory boFactory;


    private BOFactory(){



    }


    public static  BOFactory getBoFactory(){

        if(boFactory == null){
            boFactory = new BOFactory();
        }
        return boFactory;

    }


    public  enum BOTypes{

        CUSTOMER, ITEM, ORDER
    }

    public SuperBo getBO(BOTypes types){

        switch (types){
            case CUSTOMER:
                return  new CustomerBoImpl();
            case ITEM:
                return  new ItemBoImpl();
            case ORDER:
                return new OrderBoImpl();
            default:
                return null;

        }

    }


}
