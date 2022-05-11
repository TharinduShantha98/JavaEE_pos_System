package bo.custom.Impl;

import bo.custom.CustomerBo;
import dao.custom.CustomerDao;
import dao.custom.Impl.CustomerDaoImpl;
import entitiy.Customer;
import model.CustomerDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public class CustomerBoImpl implements CustomerBo {



    CustomerDao customerDao = new CustomerDaoImpl();


    @Override
    public ArrayList<CustomerDTO> getAllCustomer() throws SQLException, ClassNotFoundException {
        ArrayList<CustomerDTO> customerDTOArrayList =  new ArrayList<>();


        ArrayList<Customer> all = customerDao.getAll();
        for (Customer c1 : all
             ) {
            customerDTOArrayList.add(new CustomerDTO(c1.getCustomerId(),
                    c1.getFirstName(),
                    c1.getLastName(),
                    c1.getAddress(),
                    c1.getEmail(),
                    c1.getTelNo())
            );


        }


        return customerDTOArrayList;


    }

    @Override
    public boolean addCustomer(CustomerDTO customerDTO) throws SQLException, ClassNotFoundException {
        boolean add = customerDao.add(new Customer(customerDTO.getCustomerId(),
                customerDTO.getFirstName(),
                customerDTO.getLastName(),
                customerDTO.getAddress(),
                customerDTO.getEmail(),
                customerDTO.getTelNo()));


        return add;

    }


}
