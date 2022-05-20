package controller;

import bo.BOFactory;
import bo.custom.Impl.ItemBoImpl;
import bo.custom.ItemBo;
import model.ItemDTO;

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


@WebServlet(urlPatterns = "/item")
public class ItemServlet extends HttpServlet {

    @Resource(name = "java:comp/env/jdbc/pool")
    public static DataSource dataSource;

    private final ItemBo itemBo = (ItemBo) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.ITEM);


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
                JsonObjectBuilder objectBuilder = Json.createObjectBuilder();
                try {
                    ItemDTO itemDTO = itemBo.searchItem(itemCode);


                    if(itemDTO != null){

                        objectBuilder.add("itemCode",itemDTO.getItemCode());
                        objectBuilder.add("itemName",itemDTO.getItemName());
                        objectBuilder.add("unitPrice",itemDTO.getUnitPrice());
                        objectBuilder.add("buyingPrice",itemDTO.getBuyingPrice());
                        objectBuilder.add("packSize",itemDTO.getPackSize());
                        objectBuilder.add("quantity",itemDTO.getQuantity());


                        //System.out.println(itemDTO.getItemName());

                    }


                    objectBuilder1.add("data",objectBuilder.build());
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


              //  System.out.println(itemCode);



                break;

            case "GET_ITEM_CODE":


                try {
                    String itemCode1 = itemBo.getItemCode();
                    JsonObjectBuilder objectBuilder2 = Json.createObjectBuilder();
                    if(itemCode1 != null){

                        System.out.println(itemCode1);
                        objectBuilder2.add("itemCode", itemCode1);
                        objectBuilder2.add("message", "successfully");
                        objectBuilder2.add("status",200);
                        writer.print(objectBuilder2.build());

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

        String itemCode = req.getParameter("itemCode");
        String unitPrice = req.getParameter("unitPrice");
        String itemName = req.getParameter("itemName");
        String buyingPrice = req.getParameter("buyingPrice");
        String packSize = req.getParameter("itemPackSize");
        String itemQuantity = req.getParameter("itemQuantity");


        Double up  = new Double(unitPrice);
        BigDecimal itemUnitPrice = BigDecimal.valueOf(up);


        Double bp = new Double(buyingPrice);
        BigDecimal itemBuyingPrice =  BigDecimal.valueOf(bp);


        resp.setContentType("application/json");

        JsonObjectBuilder objectBuilder = Json.createObjectBuilder();
        PrintWriter writer = resp.getWriter();

        try {
            boolean b = itemBo.addItem(new ItemDTO(itemCode, itemName, itemUnitPrice,
                    itemBuyingPrice,packSize , Double.parseDouble(itemQuantity)));



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


    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        resp.setContentType("application/json");
        JsonReader reader = Json.createReader(req.getReader());
        JsonObject jsonObject = reader.readObject();

        String itemCode = jsonObject.getString("itemCode");
        String itemName = jsonObject.getString("itemName");
        String unitPrice = jsonObject.getString("unitPrice");
        String buyingPrice = jsonObject.getString("buyingPrice");
        String packSize = jsonObject.getString("packSize");
        String qty = jsonObject.getString("Qty");



        Double unitPriceDouble  = new Double(unitPrice);
        BigDecimal unitPriceBigDecimal =  BigDecimal.valueOf(unitPriceDouble);

        Double buyingPriceDouble  = new Double(buyingPrice);
        BigDecimal buyingPriceBigDecimal  = BigDecimal.valueOf(buyingPriceDouble);


        Double qtyDouble =  new Double(qty);


        JsonObjectBuilder objectBuilder = Json.createObjectBuilder();
        PrintWriter writer = resp.getWriter();



        //System.out.println(unitPriceBigDecimal.getClass().getSimpleName());

        try {
            boolean b = itemBo.updateItem(new ItemDTO(itemCode, itemName, unitPriceBigDecimal, buyingPriceBigDecimal,
                    packSize, qtyDouble));


            if(b){

               objectBuilder.add("data", "");
               objectBuilder.add("message", "successfully  updated");
               objectBuilder.add("status", 200);
               writer.print(objectBuilder.build());




            }





        } catch (SQLException throwables) {
            resp.setStatus(HttpServletResponse.SC_OK);
            objectBuilder.add("data", "");
            objectBuilder.add("message",throwables.getLocalizedMessage());
            objectBuilder.add("status", 400);
            writer.print(objectBuilder.build());


            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            resp.setStatus(HttpServletResponse.SC_OK);
            objectBuilder.add("data", "");
            objectBuilder.add("message",e.getLocalizedMessage());
            objectBuilder.add("status", 500);
            writer.print(objectBuilder.build());


            e.printStackTrace();
        }


    }


    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String itemCode = req.getParameter("itemCode");
        resp.setContentType("application/json");
        JsonObjectBuilder objectBuilder = Json.createObjectBuilder();
        PrintWriter writer = resp.getWriter();


        try {
            boolean b = itemBo.deleteItem(itemCode);


            if(b){
                objectBuilder.add("data","");
                objectBuilder.add("message","deleted successfully");
                objectBuilder.add("status",200);
                writer.print(objectBuilder.build());


            }



        } catch (SQLException throwables) {
            resp.setStatus(HttpServletResponse.SC_OK);
            objectBuilder.add("data","");
            objectBuilder.add("message",throwables.getLocalizedMessage());
            objectBuilder.add("status",400);
            writer.print(objectBuilder.build());


            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            resp.setStatus(HttpServletResponse.SC_OK);
            objectBuilder.add("data","");
            objectBuilder.add("message",e.getLocalizedMessage());
            objectBuilder.add("status",500);
            writer.print(objectBuilder.build());
            e.printStackTrace();
        }







    }
}
