package controllers;

import bo.custom.CustomerBo;
import bo.custom.Impl.CustomerBoImpl;
import model.CustomerDTO;

import javax.json.*;
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
        String option = req.getParameter("option");




        switch (option){

            case "GETALL":
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

                break;
            case "SEARCH":

                String customerId = req.getParameter("customerId");

               // System.out.println(customerId);


                JsonObjectBuilder objectBuilder1 = Json.createObjectBuilder();
                PrintWriter writer = resp.getWriter();



                try {
                    CustomerDTO customerDTO = customerBo.searchCustomer(customerId);
                    JsonObjectBuilder objectBuilder = Json.createObjectBuilder();
                    if(customerDTO != null){

                        objectBuilder.add("itemCode",customerDTO.getCustomerId());
                        objectBuilder.add("itemFName",customerDTO.getFirstName());
                        objectBuilder.add("itemLName",customerDTO.getLastName());
                        objectBuilder.add("address",customerDTO.getAddress());
                        objectBuilder.add("email",customerDTO.getEmail());
                        objectBuilder.add("telNo",customerDTO.getTelNo());


                       // System.out.println(customerDTO.getFirstName());


                    }


                    objectBuilder1.add("data",objectBuilder.build());
                    objectBuilder1.add("message","success search");
                    objectBuilder1.add("status", 200);
                    writer.print(objectBuilder1.build());








                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }


                break;
            case "GET_CUSTOMER_CODE":

                try {
                    String customerId1 = customerBo.getCustomerId();
                    JsonObjectBuilder objectBuilder2 = Json.createObjectBuilder();

                    if(customerId1 != null){

                        System.out.println(customerId1);
                        objectBuilder2.add("customerCode", customerId1);
                        objectBuilder2.add("message", "successfully");
                        objectBuilder2.add("status",200);
                        PrintWriter writer1 = resp.getWriter();
                        writer1.print(objectBuilder2.build());

                    }




                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }


                break;



        }











    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //System.out.println("hello post");
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


    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        JsonReader reader = Json.createReader(req.getReader());
        JsonObject jsonObject = reader.readObject();

        String customerId = jsonObject.getString("customerId");
        String customerFName = jsonObject.getString("customerFName");
        String customerLName = jsonObject.getString("customerLName");
        String customerAddress = jsonObject.getString("customerAddress");
        String customerEmail = jsonObject.getString("customerEmail");
        String customerTelNo = jsonObject.getString("customerTelNo");
        resp.setContentType("application/json");

        PrintWriter writer = resp.getWriter();
        JsonObjectBuilder objectBuilder = Json.createObjectBuilder();



        try {
            boolean b = customerBo.updateCustomer(new CustomerDTO(
                    customerId, customerFName, customerLName, customerAddress, customerEmail, customerTelNo));


            if(b){

                objectBuilder.add("data","");
                objectBuilder.add("message","successFully updated");
                objectBuilder.add("status", 200);
                writer.print(objectBuilder.build());


            }else {
                objectBuilder.add("data","");
                objectBuilder.add("message","not successFully updated");
                objectBuilder.add("status", 400);
                writer.print(objectBuilder.build());



            }


        } catch (SQLException throwables) {
            resp.setStatus(HttpServletResponse.SC_OK);
            objectBuilder.add("data","");
            objectBuilder.add("message",throwables.getLocalizedMessage());
            objectBuilder.add("status", 500);
            writer.print(objectBuilder.build());
            throwables.printStackTrace();


        } catch (ClassNotFoundException e) {

            resp.setStatus(HttpServletResponse.SC_OK);
            objectBuilder.add("data","");
            objectBuilder.add("message",e.getLocalizedMessage());
            objectBuilder.add("status", 500);
            writer.print(objectBuilder.build());
            e.printStackTrace();

        }




    }


    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
      //  System.out.println("hello delete");


        String cusId = req.getParameter("cusId");
        resp.setContentType("application/json");

        PrintWriter writer = resp.getWriter();
        JsonObjectBuilder objectBuilder = Json.createObjectBuilder();


        try {
            boolean b = customerBo.deleteCustomer(cusId);



            if(b){
                objectBuilder.add("data","");
                objectBuilder.add("message","successFully deleted");
                objectBuilder.add("status", 200);
                writer.print(objectBuilder.build());

            }





        } catch (SQLException throwables) {
            resp.setStatus(HttpServletResponse.SC_OK);
            objectBuilder.add("data","");
            objectBuilder.add("message",throwables.getLocalizedMessage());
            objectBuilder.add("status", 500);
            writer.print(objectBuilder.build());


            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            resp.setStatus(HttpServletResponse.SC_OK);
            objectBuilder.add("data","");
            objectBuilder.add("message",e.getLocalizedMessage());
            objectBuilder.add("status", 500);
            writer.print(objectBuilder.build());

            e.printStackTrace();
        }

    }




}
