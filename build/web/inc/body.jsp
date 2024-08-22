<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
 <section>
  <div class="container my-5">
    <header class="mb-4">
      <h3>${category == null ? "All sneaker" : category.name}</h3>
    </header>

    <div class="row">
        <c:forEach items="${listProduct}" var="product">
            <div class="col-lg-3 col-md-6 col-sm-6 d-flex">
              <div class="card w-100 my-2 shadow-2-strong">
                <img src="${product.image}" class="card-img-top"/>
                <div class="card-body d-flex flex-column">
                    <a href="detail?productId=${product.id}" style="text-decoration: none;color: black;">
                        ${product.name}
                    </a>
                  <p class="card-text">$${product.price}</p>
                  <div class="card-footer d-flex align-items-end pt-3 px-0 pb-0 mt-auto">
                    <a href="
                       <c:if test="${user==null}">
                           login
                       </c:if>
                       <c:if test="${user!=null}">
                           home?id_product=${product.id}&id_category=${category.id}
                       </c:if>
                       " class="btn btn-primary shadow-0 me-1">Add to cart</a>
                    <a href="#!" class="btn btn-light border px-2 pt-2 icon-hover">
                        <i class="fas fa-heart fa-lg text-secondary px-1">
                            <img src="./assets/icon/heart.png" width="25" height="25"/>
                        </i></a>
                  </div>
                </div>
              </div>
            </div>
        </c:forEach>
        
      <div class="row">
        <div class="col-12">
          <!-- Pagination -->
          <nav aria-label="navigation">
            <ul class="pagination justify-content-end mt-50">
              <c:forEach begin="1" end="${totalPage}" var="i">
                <li class="page-item ${pageIndex == i ? "active":""}"><a class="page-link" href="home?id_category=${category.id}&txtSearch=${txtSearch}&pageIndex=${i}">${i}</a></li>
              </c:forEach>
            </ul>
          </nav>
        </div>
      </div>
        
    </div>
  </div>
 </section>
    
    