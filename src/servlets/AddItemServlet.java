package servlets;

import db.DbManager;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import models.Item;

import java.io.IOException;

@WebServlet("/add-item")
public class AddItemServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String  name = req.getParameter("item_name");
        String description = req.getParameter("item_description");
        Double price = Double.parseDouble(req.getParameter("item_price"));
        Item item = new Item();
        item.setName(name);
        item.setDescription(description);
        item.setPrice(price);
        DbManager.addItem(item);
        resp.sendRedirect("/home");
    }
}
