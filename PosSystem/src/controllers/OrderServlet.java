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

@WebServlet(urlPatterns = "/order")
public class OrderServlet extends HttpServlet {

    CustomerBo customerBo = new CustomerBoImpl();



    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String option = req.getParameter("option");
        System.out.println(option);
        JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();
        PrintWriter writer = resp.getWriter();

        resp.setContentType("application/json");



        switch (option){

            case "GETALLCUSTOMERIDS":

                try {

                    ArrayList<String> customerIds = customerBo.allCustomerIds();

                    for (String cusId:customerIds
                         ) {
                        JsonObjectBuilder objectBuilder2 = Json.createObjectBuilder();
                        objectBuilder2.add("id", cusId);

                        arrayBuilder.add(objectBuilder2.build());

                    }


                    JsonObjectBuilder objectBuilder = Json.createObjectBuilder();
                    objectBuilder.add("data",arrayBuilder.build());
                    objectBuilder.add("message", "successfully get all customers ids");
                    objectBuilder.add("status",200);
                    writer.print(objectBuilder.build());




                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }


                break;




        }





    }
}
