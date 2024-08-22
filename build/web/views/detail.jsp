<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto|Varela+Round">
<link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
<jsp:include page="../inc/header.jsp"></jsp:include>
<jsp:include page="../inc/navbar.jsp"></jsp:include>
    <div class="container my-5">
        <header class="mb-4">
            <h3>Product Detail</h3>
        </header>
        <div style="display: flex">
            <div>
                <img src="${product.image}" class="card-img-top"/>
        </div>
        <div style="padding-left: 50px">
            <h5 class="cart-titlte">${product.name}</h5>
            <div class="single_product_desc">
                <!-- Product Meta Data -->
                <div class="product-meta-data">
                    <div class="line"></div>
                    <p class="product-price">$${product.price}</p>
                    <!-- Ratings & Review -->
                    <div class="ratings-review mb-15 d-flex align-items-center justify-content-between">
                        <div class="ratings">
                            <i class="fa fa-star" aria-hidden="true"></i>
                            <i class="fa fa-star" aria-hidden="true"></i>
                            <i class="fa fa-star" aria-hidden="true"></i>
                            <i class="fa fa-star" aria-hidden="true"></i>
                            <i class="fa fa-star" aria-hidden="true"></i>
                        </div>
                        <div class="review">
                            <a href="#">Write A Review</a>
                        </div>
                    </div>
                    <!-- Avaiable -->
                    <c:if test="${product.quantity > 0}">
                        <p class="avaibility" style="color: green"><i class="fa fa-circle"></i> In Stock</p>
                    </c:if>
                    <c:if test="${product.quantity <= 0}">
                        <p class="avaibility" style="color: red"><i class="fa fa-circle"></i> Out Stock</p>
                    </c:if>

                </div>
                <!-- Add to Cart Form -->
                <a href="cart?id=${product.id}&action=add"><button type="button" name="addtocart" value="${product.id}" class="btn btn-primary">Add to cart</button></a>
            </div>
        </div>
    </div>
</div>
<jsp:include page="../inc/footer.jsp"></jsp:include>