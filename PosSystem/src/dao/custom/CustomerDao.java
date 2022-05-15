package dao.custom;

import dao.CrudDao;
import entitiy.Customer;

import java.util.ArrayList;

public interface CustomerDao extends CrudDao<Customer, String> {

    ArrayList<String> getAllIds();


}
