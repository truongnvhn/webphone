<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
        <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto|Varela+Round">
        <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
<c:import url="./inc/header.jsp" />
<c:import url="./inc/navbar.jsp" />
<div class="container my-5">
<header class="mb-4">
    <h3>Order History</h3>
</header>
<table class="table">
  <thead class="table-dark">
  <th>ID</th>
  <th>UserID</th>
  <th>Order Date</th>
  <th>Total Price</th>
  <th style="text-align: center">Detail</th>
  </thead>
  <tbody>
      <c:forEach items="${lstOrder}" var="o">
      <tr>
          <td>${o.id}</td>
          <td>${o.userId}</td>
          <td>${o.orderDate}</td>
          <td>${o.totalPrice}</td>
          <td style="text-align: center">
              <a href="orderDetail?orderId=${o.id}"><i class="material-icons" data-toggle="tooltip" title="Detail">&#xe24c;</i>
          </td>
      </tr>
      </c:forEach>
  </tbody>
</table>
</div>