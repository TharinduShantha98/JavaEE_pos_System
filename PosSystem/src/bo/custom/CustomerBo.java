package bo.custom;

import model.CustomerDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface CustomerBo {

    ArrayList<CustomerDTO> getAllCustomer() throws SQLException, ClassNotFoundException;
    boolean addCustomer(CustomerDTO customerDTO) throws SQLException, ClassNotFoundException;
    boolean updateCustomer(CustomerDTO customerDTO) throws SQLException, ClassNotFoundException;
    boolean deleteCustomer(String customerId) throws SQLException, ClassNotFoundException;

}
