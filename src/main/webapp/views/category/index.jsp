<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<jsp:useBean id="categories" scope="request" type="java.util.List<com.ute.FinalProject.beans.Category>"/>

<t:main>
  <jsp:body>
    <div class="card">
      <thead>
      <tr>
        <th scope="col">#</th>
        <th scope="col">Product Type</th>
        <th scope="col">
          <a name="" id="" class="btn btn-sm btn-outline-success" href="${pageContext.request.contextPath}/admin/category/add" role="button">
            <i class="fa fa-plus" aria-hidden="true"></i>
          </a>
        </th>
      </tr>
      </thead>
      <c:choose>
        <c:when test="${categories.size() == 0}">
          <div class="card-body">
            <p class="card-text">Không có dữ liệu.</p>
          </div>
        </c:when>
        <c:otherwise>
          <div class="card-body">
            <table class="table table-hover">
              <thead>
              <tr>
                <th>#</th>
                <th>Category</th>
                <th>&nbsp;</th>
              </tr>
              </thead>
              <tbody>
              <c:forEach items="${categories}" var="c">
                <tr>
                  <td>${c.catID}</td>
                  <td>${c.catName}</td>
                  <td class="text-right">
                    <a class="btn btn-sm btn-outline-primary" href="${pageContext.request.contextPath}/admin/category/edit?id=${c.catID}" role="button">
                      <i class="fa fa-pencil" aria-hidden="true"></i>
                    </a>
                  </td>
                </tr>
              </c:forEach>
              </tbody>
            </table>
          </div>
        </c:otherwise>
      </c:choose>
    </div>
  </jsp:body>

</t:main>