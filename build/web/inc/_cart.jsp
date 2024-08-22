<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<section class="h-100 h-custom" style="background-color: #eee;">
    <div class="container py-5 h-100">
        <div class="row d-flex justify-content-center align-items-center h-100">
            <div class="col">
                <div class="card">
                    <div class="card-body p-4">

                        <div class="row">

                            <div class="col-lg-7">
                                <h5 class="mb-3">
                                    <a href="home" class="text-body"><i
                                            class="fas fa-long-arrow-alt-left me-2"></i>Continue shopping</a>
                                    <a href="cart?clear=OK" class="text-body"><i
                                            class="fas fa-long-arrow-alt-left me-2"></i>Empty cart</a>

                                </h5>


                                <hr>

                                <div class="d-flex justify-content-between align-items-center mb-4">
                                    <div>
                                        <c:if test="${message != null}">
                                            <p style="color: green;">
                                                ${message}
                                            </p>
                                        </c:if>
                                        <p class="mb-1">Shopping cart</p>
                                        <p class="mb-0">You have ${cart!=null ?cart.size():0} items in your cart</p>
                                    </div>
                                    <div>
                                    </div>
                                </div>
                                <c:set var="total" value="0"></c:set>
                                <c:forEach items="${cart}" var="product">
                                    <div class="card mb-3">
                                        <div class="card-body">
                                            <div class="d-flex justify-content-between">
                                                <div class="d-flex flex-row align-items-center">
                                                    <div>
                                                        <img
                                                            src="${product.image}"
                                                            class="img-fluid rounded-3" alt="Shopping item" style="width: 65px;">
                                                    </div>
                                                    <div class="ms-3">
                                                        <h5>${product.name}</h5>
                                                    </div>
                                                </div>
                                                <div class="d-flex flex-row align-items-center">
                                                    <form action="cart" method="post" style="width: 220px;">
                                                        <div style="float:left; width: 25%;">
                                                            <h5 class="mb-0">
                                                                <input type="hidden" name="id_product" value="${product.id}">
                                                                <input type="text" name="quantity" value="${product.quantity}" style="width:30px; text-align: right;">  
                                                                ${product.quantity}
                                                            </h5>
                                                        </div>
                                                        <div style="float: left; width: 40%;">
                                                            <h5 class="mb-0">$${product.price}</h5>
                                                        </div>
                                                        <div style="float:left; width: 35%;">
                                                            <h5 class="mb-0">
                                                                <button type="submit" name="action" value="update">
                                                                    <img src="./assets/icon/update.png" width="16" height="16"/>      
                                                                </button>
                                                                <button type="submit" name="action" value="delete">
                                                                    <img src="./assets/icon/delete.png" width="16" height="16"/>      
                                                                </button>
                                                            </h5>
                                                        </div>
                                                    </form>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <c:set var="total" value="${total + product.quantity*product.price}"></c:set>

                                </c:forEach>

                            </div>
                            <div class="col-lg-5">

                                <div class="card bg-primary text-white rounded-3">
                                    <div class="card-body">
                                        <form class="mt-4" action="checkout" method="get">
                                            <div class="d-flex justify-content-between align-items-center mb-4">
                                                <h5 class="mb-0">Card details</h5>
                                            </div>

                                            <hr class="my-4">
                                            <fmt:formatNumber var="formattedTotal" value="${total*1.1}" pattern="0.0" />
                                            <fmt:formatNumber var="formattedShipping" value="${total*0.1}" pattern="0.0" />
                                            <div class="d-flex justify-content-between">
                                                <p class="mb-2">Subtotal</p>
                                                <p class="mb-2">$${total}</p>
                                            </div>

                                            <div class="d-flex justify-content-between">
                                                <p class="mb-2">Shipping</p>
                                                <p class="mb-2">$${formattedShipping}</p>
                                            </div>

                                            <div class="d-flex justify-content-between mb-4">
                                                <p class="mb-2">Total(Incl. taxes)</p>
                                                <p class="mb-2">$${formattedTotal}</p>
                                            </div>
                                            <!--<a href="checkout">-->
                                            <button type="submit" class="btn btn-info btn-block btn-lg">
                                                <div class="d-flex justify-content-between">

                                                    <span>Checkout <i class="fas fa-long-arrow-alt-right ms-2"></i></span>
                                                    <span>$${formattedTotal}</span>
                                                </div>
                                            </button>
                                            <!--</a>-->
                                        </form>
                                    </div>
                                </div>

                            </div>

                        </div>

                    </div>
                </div>
            </div>
        </div>
    </div>
</section>