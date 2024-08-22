<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:import url="./inc/header.jsp" />
<c:import url="./inc/navbar.jsp" />
<div class="container my-5">
<header class="mb-4">
    <h3>Order History</h3>
</header>
<table class="table">
  <thead class="table-dark">
  <th>Order ID</th>
  <th>Product ID</th>
  <th>Product Name</th>
  <th>Category</th>
  <th>Image</th>
  <th>Unit Price</th>
  <th>Quantity</th>
  </thead>
  <tbody>
      <c:forEach items="${lstOrderDetail}" var="o">
      <tr>
          <td>${o.orderId}</td>
          <td>${o.productId}</td>
          <td>${o.productName}</td>
          <td>${o.categoryName}</td>
          <td><img src="${o.image}" alt="Product" style="width: 150px"></td>
          <td>${o.unitPrice}</td>
          <td>${o.quantity}</td>
      </tr>
      </c:forEach>
  </tbody>
</table>
</div>