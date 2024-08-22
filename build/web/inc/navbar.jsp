<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page import="ultils.MD5" %>

 <nav class="navbar navbar-expand-lg bg-body-tertiary sticky-top">
  <div class="container-fluid">
      <a class="navbar-brand" href="#">
          <img src="https://dvm9jp3urcf0o.cloudfront.net/logo-ideas/technology-logo/mobile-studio.png" width="70" height="70" />
       </a>
    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
      <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarSupportedContent" >
      <ul class="navbar-nav me-auto mb-2 mb-lg-0" style="margin: 0 auto">
        <li class="nav-item">
          <a class="nav-link active" aria-current="page" href="home">Home</a>
        </li>
        <li class="nav-item dropdown">
          <a class="nav-link dropdown-toggle" href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false">
            Category
          </a>
          <ul class="dropdown-menu">
              <li><a class="dropdown-item" href="./home">All sneaker</a></li>
            <c:forEach items="${listCategory}" var="category">
                <li><a class="dropdown-item" href="./home?id_category=${category.id}">${category.name}</a></li>
            </c:forEach> 
            </ul>
        </li>
        <c:if test="${user!=null}">
        <li class="nav-item">
          <a class="nav-link" aria-current="page" href="./orderHistory">Order History</a>
        </li>
        </c:if>
        <c:if test="${user==null}">
         <li class="nav-item">
          <a class="nav-link" href="login">Login</a>
        </li>
        </c:if>
        <c:if test="${user!=null}">
            <li class="nav-item dropdown">
          <a class="nav-link dropdown-toggle" href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false">
            Hi 
            <c:set var="s" value="${user.getName()}"></c:set>
            <%
                String name = (String)pageContext.getAttribute("s");
                out.print(MD5.getName(name));
            %>
          </a>
          <ul class="dropdown-menu">           
                <li><a class="dropdown-item" href="#">Profile</a></li>
                <li><a class="dropdown-item" href="./views/logout.jsp">Logout</a></li>
            </ul>
        </li>
      </c:if>
         <li class="nav-item">
             <a class="nav-link" href="cart">
            <img src="./assets/icon/cart.png" width="25" height="25" />
            <i>${cart.size()}</i>
             </a>
        </li>
        
      </ul>
      <form class="d-flex" role="search" action="home" method="get">
        <input class="form-control me-2" type="hidden" name="id_category" value="${category.id}">
        <input class="form-control me-2" type="hidden" name="sort" value="${sort}">
        <input class="form-control me-2" type="hidden" name="priceRange" value="${priceRange}">
        <input class="form-control me-2" type="search" placeholder="Search" aria-label="Search" name="txtSearch" value="${txtSearch}">
        <button class="btn btn-outline-success" type="submit">Search</button>
      </form>
    </div>
  </div>
</nav>