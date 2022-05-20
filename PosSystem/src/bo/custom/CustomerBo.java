package bo.custom;

import bo.SuperBo;
import model.CustomerDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface CustomerBo  extends SuperBo {

    ArrayList<CustomerDTO> getAllCustomer() throws SQLException, ClassNotFoundException;
    boolean addCustomer(CustomerDTO customerDTO) throws SQLException, ClassNotFoundException;
    boolean updateCustomer(CustomerDTO customerDTO) throws SQLException, ClassNotFoundException;
    boolean deleteCustomer(String customerId) throws SQLException, ClassNotFoundException;
    CustomerDTO searchCustomer(String s) throws SQLException, ClassNotFoundException;
    ArrayList<String> allCustomerIds() throws SQLException, ClassNotFoundException;
    String getCustomerId() throws SQLException, ClassNotFoundException;

}
