<%@ page import="models.Item" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: Райхан
  Date: 03.02.2024
  Time: 15:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
   <%@include file="head.jsp"%>
</head>
<body>
<%@include file="navbar.jsp"%>
<div class="col-8 mx-auto">
   <form action="add-item" method="post">
      <div class="mb-3">
         <label for="exampleInputName" class="form-label">Name: </label>
         <input name = "item_name" type="text" class="form-control" id="exampleInputName" >

      </div>
      <div class="mb-3">
         <label for="exampleInputDescription" class="form-label">Description: </label>
         <input name = "item_description" type="text" class="form-control" id="exampleInputDescription" >

      </div>
      <div class="mb-3">
         <label for="exampleInputPrice" class="form-label">Price: </label>
         <input name = "item_price" type="number" class="form-control" id="exampleInputPrice" >

      </div>


      <button type="submit" class="btn btn-primary">Add item</button>
   </form>
   <table class="table table-striped">
      <thead>
      <th>ID</th>
      <th>NAME</th>
      <th>DESCRIPTION</th>
      <th>PRICE</th>
      <th>PRICE</th>

      </thead>
      <tbody>
      <%
         List<Item> items = (List<Item>) request.getAttribute("tovary");
         for (Item item : items) {
      %>
      <tr>
         <td><%=item.getId()%></td>
         <td><%=item.getName()%></td>
         <td><%=item.getDescription()%></td>
         <td><%=item.getPrice()%></td>
         <td> <a class="btn btn-dark" href="/item-details?item_id=<%=item.getId()%>">DETAILS</a> </td>
      </tr>
      <%
         }
      %>
      </tbody>
   </table>
</div>
</body>
</body>
</html>
