package controllers;

import bo.custom.CustomerBo;
import bo.custom.Impl.CustomerBoImpl;
import model.CustomerDTO;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObjectBuilder;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;


@WebServlet(urlPatterns = "/customer")
public class CustomerServlet extends HttpServlet {

    CustomerBo customerBo = new CustomerBoImpl();



    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        resp.setContentType("application/json");


        try {
            JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();

            ArrayList<CustomerDTO> allCustomer = customerBo.getAllCustomer();

            for (CustomerDTO c1 : allCustomer
                 ) {
                JsonObjectBuilder objectBuilder = Json.createObjectBuilder();
                objectBuilder.add("id",c1.getCustomerId());
                objectBuilder.add("firstName",c1.getFirstName());
                objectBuilder.add("lastName",c1.getLastName());
                objectBuilder.add("address",c1.getAddress());
                objectBuilder.add("email",c1.getEmail());
                objectBuilder.add("TelNo",c1.getTelNo());

                arrayBuilder.add(objectBuilder.build());



            }

            PrintWriter writer = resp.getWriter();

            JsonObjectBuilder objectBuilder2 = Json.createObjectBuilder();
            objectBuilder2.add("data",arrayBuilder.build());
            objectBuilder2.add("message","done");
            objectBuilder2.add("status","200");
            writer.print(objectBuilder2.build());



        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }


    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        System.out.println("hello post");
        String customerId = req.getParameter("cusId");
        String customerFName = req.getParameter("cusFName");
        String customerLName = req.getParameter("cusLName");
        String customerAddress = req.getParameter("cusAddress");
        String customerEmail = req.getParameter("cusEmail");
        String customerTelNo = req.getParameter("cusTelNo");
        resp.setContentType("application/json");


        JsonObjectBuilder objectBuilder = Json.createObjectBuilder();
        PrintWriter writer = resp.getWriter();



        try {
            boolean b = customerBo.addCustomer(new CustomerDTO(
                    customerId,
                    customerFName,
                    customerLName,
                    customerAddress,
                    customerEmail,
                    customerTelNo));


            if(b){

                objectBuilder.add("data","");
                objectBuilder.add("message","successfully added ");
                objectBuilder.add("status",200);
                writer.print(objectBuilder.build());



            }


        } catch (SQLException throwables) {
            throwables.printStackTrace();
            resp.setStatus(HttpServletResponse.SC_OK);

            objectBuilder.add("data","");
            objectBuilder.add("message",throwables.getLocalizedMessage());
            objectBuilder.add("status", 400);
            writer.print(objectBuilder.build());






        } catch (ClassNotFoundException e) {
            e.printStackTrace();

            resp.setStatus(HttpServletResponse.SC_OK);

            objectBuilder.add("data","");
            objectBuilder.add("message",e.getLocalizedMessage());
            objectBuilder.add("status", 500);
            writer.print(objectBuilder.build());
        }

    }
}
