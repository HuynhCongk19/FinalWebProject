<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:useBean id="product" scope="request" type="com.ute.FinalProject.beans.Product"/>

<t:main>
    <jsp:body>
        <div class="card-body">
            <form action="" method="post">
                <h4 class="card-header">Edit #${product.proID} - ${product.proName}</h4>
                <div class="form-group">
                    <label for="txtProID"># ID</label>
                    <input type="text" class="form-control" id="txtProID" name="CatID" readonly value="${product.proID}">
                    <label for="txtProName">Product</label>
                    <input type="text" class="form-control" id="txtProName" name="ProName" autofocus value="${product.proName}">
                    <label for="txtTinyDes">TinyDes</label>
                    <input type="text" class="form-control" id="txtTinyDes" name="TinyDes" autofocus value="${product.tinyDes}">
                    <label for="txtFullDes">FullDes</label>
                    <input type="text" class="form-control" id="txtFullDes" name="FullDes" autofocus value="${product.fullDes}">
                    <label for="txtPrice">Price</label>
                    <input type="text" class="form-control" id="txtPrice" name="Price" autofocus value="${product.price}">
                    <label for="txtPriceDifference">PriceDifference</label>
                    <input type="text" class="form-control" id="txtPriceDifference" name="PriceDifference" autofocus value="${product.priceDifference}">
                    <label for="txtCatID">CatID</label>
                    <input type="text" class="form-control" id="txtCatID" name="CatID" autofocus value="${product.catID}">
                </div>
                <div class="card-footer">
                    <a class="btn btn-outline-primary" href="${pageContext.request.contextPath}/home/product" role="button">
                        <i class="fa fa-backward" aria-hidden="true"></i>
                        List
                    </a>
                    <button type="submit" class="btn btn-outline-success" formaction="${pageContext.request.contextPath}/home/product/Update" role="button">
                        <i class="fa fa-check" aria-hidden="true"></i>
                        Update
                    </button>
                    <button type="submit" class="btn btn-outline-danger" formaction="${pageContext.request.contextPath}/home/product/Delete" role="button">
                        <i class="fa fa-trash" aria-hidden="true"></i>
                        Delete
                    </button>
                </div>

            </form>
        </div>
    </jsp:body>

</t:main>