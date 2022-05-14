package controllers;

import bo.custom.Impl.ItemBoImpl;
import bo.custom.ItemBo;
import model.ItemDTO;

import javax.json.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;


@WebServlet(urlPatterns = "/item")
public class ItemServlet  extends HttpServlet {


    ItemBo itemBo = new ItemBoImpl();


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        String option = req.getParameter("option");
        System.out.println(option);

        JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();
        PrintWriter writer = resp.getWriter();
        resp.setContentType("application/json");


        switch (option){
            case "GETALL":

                try {

                    ArrayList<ItemDTO> allItem = itemBo.getAllItem();

                    for (ItemDTO itemDTO: allItem){
                        JsonObjectBuilder objectBuilder = Json.createObjectBuilder();
                        objectBuilder.add("itemCode",itemDTO.getItemCode());
                        objectBuilder.add("itemName",itemDTO.getItemName());
                        objectBuilder.add("unitPrice",itemDTO.getUnitPrice());
                        objectBuilder.add("packSize",itemDTO.getPackSize());
                        objectBuilder.add("buyingPrice",itemDTO.getBuyingPrice());
                        objectBuilder.add("quantity",itemDTO.getQuantity());
                        arrayBuilder.add(objectBuilder.build());

                    }


                    JsonObjectBuilder objectBuilder2 = Json.createObjectBuilder();
                    objectBuilder2.add("data",arrayBuilder.build());
                    objectBuilder2.add("message","successfully get all items");
                    objectBuilder2.add("status", 200);
                    writer.print(objectBuilder2.build());





                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }



                break;
            case "SEARCH":

                JsonObjectBuilder objectBuilder1 = Json.createObjectBuilder();


                String itemCode = req.getParameter("itemCode");
                try {
                    ItemDTO itemDTO = itemBo.searchItem(itemCode);


                    if(itemDTO != null){
                        JsonObjectBuilder objectBuilder = Json.createObjectBuilder();
                        objectBuilder.add("itemCode",itemDTO.getItemCode());
                        objectBuilder.add("itemName",itemDTO.getItemName());
                        objectBuilder.add("unitPrice",itemDTO.getUnitPrice());
                        objectBuilder.add("buyingPrice",itemDTO.getBuyingPrice());
                        objectBuilder.add("packSize",itemDTO.getPackSize());
                        objectBuilder.add("quantity",itemDTO.getQuantity());


                        System.out.println(itemDTO.getItemName());

                    }


                    objectBuilder1.add("data",objectBuilder1.build());
                    objectBuilder1.add("message","search successfully");
                    objectBuilder1.add("status",200);

                    writer.print(objectBuilder1.build());













                } catch (SQLException throwables) {
                    resp.setStatus(HttpServletResponse.SC_OK);
                    objectBuilder1.add("data","");
                    objectBuilder1.add("message", throwables.getLocalizedMessage());
                    objectBuilder1.add("status",404);
                    writer.print(objectBuilder1.build());


                    throwables.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }


                System.out.println(itemCode);



                break;






        }







    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String itemCode = req.getParameter("itemCode");
        String unitPrice = req.getParameter("unitPrice");
        String itemName = req.getParameter("itemName");
        String buyingPrice = req.getParameter("buyingPrice");
        String PackSize = req.getParameter("itemPackSize");
        String itemQuantity = req.getParameter("itemQuantity");


        Double up  = new Double(unitPrice);
        BigDecimal itemUnitPrice = BigDecimal.valueOf(up);


        Double bp = new Double(buyingPrice);
        BigDecimal itemPackSize =  BigDecimal.valueOf(bp);


        resp.setContentType("application/json");
        PrintWriter writer = resp.getWriter();

        JsonObjectBuilder objectBuilder = Json.createObjectBuilder();

        try {
            boolean b = itemBo.addItem(new ItemDTO(itemCode, itemName, itemUnitPrice,
                    itemUnitPrice, buyingPrice, Double.parseDouble(itemQuantity)));



            if(b){

                objectBuilder.add("data","");
                objectBuilder.add("message","successFully added");
                objectBuilder.add("status",200);
                writer.print(objectBuilder.build());


            }


        } catch (SQLException throwables) {
            resp.setStatus(HttpServletResponse.SC_OK);
            objectBuilder.add("data","");
            objectBuilder.add("message",throwables.getLocalizedMessage());
            objectBuilder.add("status",500);
            writer.print(objectBuilder.build());



            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            resp.setStatus(HttpServletResponse.SC_OK);
            objectBuilder.add("data","");
            objectBuilder.add("message",e.getLocalizedMessage());
            objectBuilder.add("status",400);
            writer.print(objectBuilder.build());
            e.printStackTrace();
        }


    }
}
