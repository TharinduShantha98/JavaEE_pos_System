package controllers;

import bo.custom.CustomerBo;
import bo.custom.Impl.CustomerBoImpl;
import model.CustomerDTO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;


@WebServlet(urlPatterns = "/customer")
public class CustomerServlet extends HttpServlet {

    CustomerBo customerBo = new CustomerBoImpl();



    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        try {
            ArrayList<CustomerDTO> allCustomer = customerBo.getAllCustomer();

            for (CustomerDTO c1 : allCustomer
                 ) {


                System.out.println(c1.getCustomerId());
            }



        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }


    }



}
