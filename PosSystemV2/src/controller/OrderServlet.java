package controller;

import bo.custom.CustomerBo;
import bo.custom.Impl.CustomerBoImpl;
import bo.custom.Impl.ItemBoImpl;
import bo.custom.Impl.OrderBoImpl;
import bo.custom.ItemBo;
import bo.custom.OrderBo;
import model.OrderDTO;
import model.OrderDetailDTO;

import javax.annotation.Resource;
import javax.json.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;

@WebServlet(urlPatterns = "/order")
public class OrderServlet extends HttpServlet {

    CustomerBo customerBo = new CustomerBoImpl();
    ItemBo itemBo = new ItemBoImpl();
    OrderBo orderBo = new OrderBoImpl();



    @Resource(name = "java:comp/env/jdbc/pool")
    public static DataSource dataSource;



    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String option = req.getParameter("option");
        //System.out.println(option);

        PrintWriter writer = resp.getWriter();
        resp.setContentType("application/json");



        switch (option){

            case "GETALLCUSTOMERIDS":
                JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();

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
            case "GETALLITEMCODES":
                JsonArrayBuilder arrayBuilder1 = Json.createArrayBuilder();

                try {
                    ArrayList<String> allItemsCodes = itemBo.getAllItemsCodes();


                    for (String ids: allItemsCodes
                         ) {
                        JsonObjectBuilder objectBuilder = Json.createObjectBuilder();
                        objectBuilder.add("id",ids);
                        arrayBuilder1.add(objectBuilder.build());

                    }


                    JsonObjectBuilder objectBuilder = Json.createObjectBuilder();
                    objectBuilder.add("data",arrayBuilder1.build());
                    objectBuilder.add("message", "success get All item Codes");
                    objectBuilder.add("status", 200);

                    writer.print(objectBuilder.build());





                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }


                break;


            case "GET_ORDER_ID":


                JsonObjectBuilder objectBuilder = Json.createObjectBuilder();
                try {
                    String orderId = orderBo.getOrderId();


                    if(orderId != null){

                        System.out.println(orderId);
                        objectBuilder.add("orderId", orderId);
                        objectBuilder.add("message", "successfully");
                        objectBuilder.add("status",200);
                        writer.print(objectBuilder.build());

                    }


                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }


                break;

            case "GET_ALL_ORDERS":


                JsonArrayBuilder arrayBuilder2 = Json.createArrayBuilder();



                try {
                    ArrayList<OrderDTO> allOrders = orderBo.getQAllOrders();

                    for (OrderDTO order: allOrders
                         ) {
                        JsonObjectBuilder objectBuilder1 = Json.createObjectBuilder();
                        objectBuilder1.add("orderId",order.getOrderId());
                        objectBuilder1.add("customerId",order.getCustomerId());
                        objectBuilder1.add("sale",order.getSale());
                        objectBuilder1.add("profit",order.getProfit());
                        objectBuilder1.add("date_time",order.getData_time());
                        arrayBuilder2.add(objectBuilder1.build());



                    }

                    JsonObjectBuilder objectBuilder1 = Json.createObjectBuilder();
                    objectBuilder1.add("data", arrayBuilder2.build());
                    objectBuilder1.add("message", "SuccessFully");
                    objectBuilder1.add("status",200);

                    writer.print(objectBuilder1.build());



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

        JsonReader reader = Json.createReader(req.getReader());
        JsonObject jsonObject = reader.readObject();

        String orderId = jsonObject.getString("orderId");
        String customerId = jsonObject.getString("customerId");
        String sale = jsonObject.getString("sale");
        //String profit = jsonObject.getString("profit");
        String dateAndTime = jsonObject.getString("dateAndTime");
        JsonArray itemDetail = jsonObject.getJsonArray("itemDetail");

        ArrayList<OrderDetailDTO> orderDetailDTOS = new ArrayList<>();



        for(JsonValue jsonValue: itemDetail){
            String itemCode = jsonValue.asJsonObject().getString("itemCode");
            String salePrice = jsonValue.asJsonObject().getString("price");
            String saleQty =  jsonValue.asJsonObject().getString("saleQty");

            orderDetailDTOS.add(new OrderDetailDTO(orderId,
                    itemCode,
                    Double.parseDouble(saleQty),
                    Double.parseDouble(salePrice),
                    10)
            );



        }

        System.out.println("orderDetailDTOS" + orderDetailDTOS.size());




        Double saleDouble  = new Double(sale);
        BigDecimal saleBigDecimal = BigDecimal.valueOf(saleDouble);


        BigDecimal profitBigDecimal = BigDecimal.valueOf(Double.parseDouble("12"));


        OrderDTO orderDTO = new OrderDTO(
                orderId,
                customerId,
                saleBigDecimal,
                profitBigDecimal,
                dateAndTime,
                orderDetailDTOS);


        boolean b = orderBo.addOrder(orderDTO);

        if(b){
            System.out.println("ammooooo eka nm hari giya");


        }


































        /*int profit = jsonObject.getInt("profit");
        String dateAndTime = jsonObject.getString("dateAndTime");
        int sale = jsonObject.getInt("sale");*/



        System.out.println(orderId);
        System.out.println(customerId);
        System.out.println(sale);
        //System.out.println(profit);
        System.out.println(dateAndTime);



       /*System.out.println(dateAndTime);
        System.out.println(sale);
        System.out.println(profit);*/







    }
}
