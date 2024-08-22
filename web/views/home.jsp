<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<jsp:include page="../inc/header.jsp"></jsp:include>
<jsp:include page="../inc/navbar.jsp"></jsp:include>
<jsp:include page="../inc/jumbotron.jsp"></jsp:include>
    <section>
        <div class="container my-5">
            <div class="row">
                <div class="col-lg-2 col-md-2 col-sm-2">
                    <div class="widget brands mb-50">
                        <!-- Widget Title -->
                        <header class="mb-4">
                            <h3>Price</h3>
                        </header>

                        <div class="widget-desc">
                            <!-- Single Form Check -->
                            <div class="form-check">
                                <input class="form-check-input" type="radio" value="0" id="hi" name="price" checked="" onchange="filterPrice(0)"/>
                                <label class="form-check-label" for="hi" style="color: black">All price</label>
                            </div>
                            <!-- Single Form Check -->
                            <div class="form-check">
                                <input class="form-check-input" type="radio" value="1" id="amado" name="price" onchange="filterPrice(1)" <c:if test="${priceRange eq '1'}">checked</c:if>/>
                                <label class="form-check-label" for="amado" style="color: black">Under $300</label>
                            </div>
                            <!-- Single Form Check -->
                            <div class="form-check">
                                <input class="form-check-input" type="radio" value="2" id="furniture" name="price" onchange="filterPrice(2)" <c:if test="${priceRange eq '2'}">checked</c:if>/>
                                <label class="form-check-label" for="furniture" style="color: black">$300 - $500</label>
                            </div>
                            <!-- Single Form Check -->
                            <div class="form-check">
                                <input class="form-check-input" type="radio" value="3" id="factory" name="price" onchange="filterPrice(3)" <c:if test="${priceRange eq '3'}">checked</c:if>/>
                                <label class="form-check-label" for="factory" style="color: black">$500 - $1000</label>
                            </div>
                            <!-- Single Form Check -->
                            <div class="form-check">
                                <input class="form-check-input" type="radio" value="4" id="artdeco" name="price" onchange="filterPrice(4)" <c:if test="${priceRange eq '4'}">checked</c:if>/>
                                <label class="form-check-label" for="artdeco" style="color: black">$1000 +</label>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="col-lg-10 col-md-10 col-sm-10">
                    <header class="mb-4">
                        <div style="display: flex; justify-content: space-between">
                            <div>
                                <h3>${category == null ? "All sneaker" : category.name}</h3>
                        </div>
                        <div>

                            <form action="#" method="get">
                                <span>Sort by Price: </span>
                                <select name="select" id="sortByPrice" onchange="sort()">
                                    <option value="asc" ${sort eq "asc" ? "selected" : ""}>Asc</option>
                                    <option value="desc" ${sort eq "desc" ? "selected" : ""}>Desc</option>
                                </select>
                            </form>
                        </div>
                    </div>
                </header>

                <div class="row">
                    <c:forEach items="${listProduct}" var="product">
                        <div class="col-lg-4 col-md-6 col-sm-6 d-flex">
                            <div class="card w-100 my-2 shadow-2-strong">
                                <img src="${product.image}" class="card-img-top"/>
                                <div class="card-body d-flex flex-column">
                                    <a href="detail?productId=${product.id}" style="text-decoration: none;color: black;">
                                        <h5 class="cart-titlte">${product.name}</h5>
                                    </a>
                                    <p class="card-text">$${product.price}</p>
                                    <div class="card-footer d-flex align-items-end pt-3 px-0 pb-0 mt-auto" style="justify-content: space-between">
                                        <div>
                                            <c:if test="${product.quantity > 0}">
                                            <a href="
                                               <c:if test="${user==null}">
                                                   login
                                               </c:if>
                                               <c:if test="${user!=null}">
                                                   home?id_product=${product.id}&id_category=${category.id}
                                               </c:if>
                                               " class="btn btn-primary shadow-0 me-1">Add to cart</a>
                                            </c:if>
                                            <a href="#!" class="btn btn-light border px-2 icon-hover" style="padding-top: 3px">
                                                <i class="fas fa-heart fa-lg text-secondary px-1">
                                                    <img src="./assets/icon/heart.png" width="25" height="25"/>
                                                </i></a></div>
                                                <c:if test="${product.quantity > 0}">
                                            <div style="color: green;line-height: 38px;">In Stock</div>
                                        </c:if>
                                        <c:if test="${product.quantity <= 0}">
                                            <div style="color: red;line-height: 38px;">Out Stock</div>
                                        </c:if>
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
                                        <li class="page-item ${pageIndex == i ? "active":""}"><a class="page-link" href="home?id_category=${category.id}&txtSearch=${txtSearch}&pageIndex=${i}&priceRange=${priceRange}&sort=${sort}">${i}</a></li>
                                        </c:forEach>
                                </ul>
                            </nav>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section>
<jsp:include page="../inc/footer.jsp"></jsp:include>
    <script type="text/javascript">
        function filterPrice(priceRange) {
            window.location.href = 'home?id_category=${category.id}&txtSearch=${txtSearch}&sort=${sort}&priceRange=' + priceRange;
        }
        function sort() {
            var sort = document.getElementById("sortByPrice");
            window.location.href = 'home?id_category=${category.id}&txtSearch=${txtSearch}&priceRange=${priceRange}&sort=' + sort.value;
        }
</script>