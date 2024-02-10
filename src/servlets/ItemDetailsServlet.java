package servlets;

import db.DbManager;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import models.Item;

import java.io.IOException;

@WebServlet("/item-details")
public class ItemDetailsServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long id = Long.parseLong(req.getParameter("item_id"));
        Item item = DbManager.getItemById(id);
        req.setAttribute("tovar", item);
        req.getRequestDispatcher("details.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long id = Long.parseLong(req.getParameter("item_id"));
        String name = req.getParameter("item_name");
        String description = req.getParameter("item_description");
        Double price = Double.parseDouble(req.getParameter("item_price"));
        Item item = new Item(id, name, description, price);
        DbManager.editItem(item);
        resp.sendRedirect("/home");

    }
}
