package controllers;

import bo.custom.Impl.ItemBoImpl;
import bo.custom.ItemBo;
import model.ItemDTO;

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


@WebServlet(urlPatterns = "/item")
public class ItemServlet  extends HttpServlet {


    ItemBo itemBo = new ItemBoImpl();


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();
        PrintWriter writer = resp.getWriter();
        resp.setContentType("application/json");


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
    }
}
