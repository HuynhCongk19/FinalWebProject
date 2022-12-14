<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:useBean id="product" scope="request" type="com.ute.FinalProject.beans.Product"/>
<jsp:useBean id="listproduct" scope="request" type="java.util.List<com.ute.FinalProject.beans.Product>"/>
<jsp:useBean id="listbidding" scope="request" type="java.util.List<com.ute.FinalProject.beans.Bidding>"/>
<jsp:useBean id="comment" scope="request" type="java.util.List<com.ute.FinalProject.beans.CommentPro>"/>
<jsp:useBean id="AuthUser" scope="session" type="com.ute.FinalProject.beans.User"/>
<jsp:useBean id="user" scope="request" type="java.util.List<com.ute.FinalProject.beans.User>"/>
<jsp:useBean id="score" scope="request" type="java.util.List<com.ute.FinalProject.beans.Score>"/>


<t:main>
    <jsp:body>
        <div class="card-body ">
        <div class="card border border-dark">
        <div id="carouselExampleFade" class="carousel slide carousel-fade" data-ride="carousel">
            <div class="carousel-inner">
                <div class="carousel-item active">
                    <img src="${pageContext.request.contextPath}/public/imgs/sp/${product.proID}/main.jpg" alt="${product.proName}" title="${product.proName}" class="d-block w-100" >
                </div>
                <div class="carousel-item">
                    <img src="${pageContext.request.contextPath}/public/imgs/sp/${product.proID}/sub1.jpg" alt="${product.proName}" title="${product.proName}"  class="d-block w-100" >
                </div>
                <div class="carousel-item">
                    <img src="${pageContext.request.contextPath}/public/imgs/sp/${product.proID}/sub2.jpg" alt="${product.proName}" title="${product.proName}"  class="d-block w-100" >
                </div>
                <div class="carousel-item">
                    <img src="${pageContext.request.contextPath}/public/imgs/sp/${product.proID}/sub3.jpg" alt="${product.proName}" title="${product.proName}"  class="d-block w-100" >
                </div>
            </div>
            <button class="carousel-control-prev" type="button" data-target="#carouselExampleFade" data-slide="prev">
                <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                <span class="sr-only">Previous</span>
            </button>
            <button class="carousel-control-next" type="button" data-target="#carouselExampleFade" data-slide="next">
                <span class="carousel-control-next-icon" aria-hidden="true"></span>
                <span class="sr-only">Next</span>
            </button>
        </div>
        <div class="card-body bg-light">
            <form action="" method="post">
                <c:set var = "Max" scope = "session" value = "${0}"/>
                <c:set var = "Value" scope = "session" value = "${0}"/>
                <c:forEach items="${listbidding}" var="b">
                    <c:if test="${b.proID == product.proID}">
                        <c:if test="${Max < b.price}">
                            <c:set var = "Max" scope = "session" value = "${b.price}"/>
                        </c:if>
                    </c:if>
                </c:forEach>
                <h4 class="text-dark" style="font-size:25px; ">${product.proName}</h4>
                <h6 class="card-subtitle mb-2 text-dark" style="font-size:40px;">$ ${Max}</h6>

                <c:set var="diem" value="${0}"> </c:set>
                <c:set var="allscore" value="${0}"> </c:set>
                <c:forEach items="${score}" var="s">
                <c:forEach items="${user}" var="u">
                        <c:if test="${s.userIDgive == u.id && s.userIDget == AuthUser.id}">
                            <c:choose>
                                <c:when test="${s.score==1}">
                                    <c:set var="diem" value="${diem + 1}"> </c:set>
                                    <c:set var="allscore" value="${allscore + 1}"> </c:set>
                                </c:when>
                                <c:otherwise>
                                    <c:set var="allscore" value="${allscore + 1}"> </c:set>
                                </c:otherwise>
                            </c:choose>
                        </c:if>
                </c:forEach>
                </c:forEach>
                <c:choose>
                    <c:when test="${allscore != 0 }">
                         <c:set  var="ketqua" value="${(diem / allscore)*100 }"> </c:set>
                    </c:when>
                    <c:otherwise>
                        <c:set var="ketqua" value="${-1}"> </c:set>
                    </c:otherwise>
                </c:choose>
<%--                <p>score: ${ketqua} %</p>--%>
                <c:choose>
                    <c:when test="${product.currentPrice != 0}">
                        <p class="card-text text-primary"><b>Buy Now Price:</b><p class="card-subtitle mb-2 text-dark" style="font-size:20px;"> $ ${product.currentPrice}</p></p>
                    </c:when>
                </c:choose>
                <c:choose>
                    <c:when test="${Auth == true}">
                        <c:choose>
                            <c:when test="${localDateNotFormatted gt product.endDay || product.status != 0 }">
                                <div>&nbsp;</div>
                            </c:when>
                            <c:otherwise>
                                <c:choose>
                                    <c:when test="${ketqua >= 80 || ketqua == -1}">
                                <label for="NewPrice"><b>Enter Price for Bidding (Minimum valid price offer: $ ${Max+product.priceDifference})</b></label>
                                <input type="number" class="form-inline w-25" id="NewPrice" name="NewPrice">
                                <button onclick="return myFunction()" type="submit" class="btn btn-outline-success btn-sm w-25"  formaction="${pageContext.request.contextPath}/admin/product/addBid"  role="button">Bid</button>
                                    </c:when>
                                    <c:otherwise>
                                        <p>You are not good enough</p>
                                    </c:otherwise>
                                </c:choose>
                            </c:otherwise>
                        </c:choose>

                    </c:when>
                    <c:otherwise>
                        <div>&nbsp;</div>
                    </c:otherwise>
                </c:choose>


                <p class="card-text text-success"><b><i class="fa fa-check" aria-hidden="true"></i> Start Day:</b> ${product.startDay}</p>
                <p class="card-text text-danger"><b><i class="fa fa-times" aria-hidden="true"></i> End Day:</b> ${product.endDay}</p>
                <input type="hidden" class="form-control" id="Price" name="Price" readonly value="${Max}">
                <c:if test="${localDateNotFormatted gt product.endDay || product.status != 0 }">
                    <p><i class="fa fa-times" aria-hidden="true"></i> This Product cannot be auctioned any more</p>
                            <c:if test="${AuthUser.id eq product.userID}">
                                <a id="" class="btn btn-outline-success " href="${pageContext.request.contextPath}/admin/product/resultProID?ProID=${product.proID}" role="button">
                                    <i class="fa fa-check" aria-hidden="true"></i> Check result
                                </a>
                            </c:if>
                </c:if>
                <c:if test="${product.endDay gt localDateNotFormatted }">
                    <p><i class="fa fa-check" aria-hidden="true"></i> This Product can be auctioned</p>
                </c:if>

                <c:set var = "Number" scope = "session" value = "${-1}"/>
                <c:forEach items="${listbidding}" var="b">
                    <c:if test="${b.proID == product.proID}">
                        <c:set var = "Number" scope = "session" value ="${Number+1}"/>
                    </c:if>
                </c:forEach>
                <c:forEach items="${user}" var="u">
                    <c:if test="${u.id == product.userID}">
                        <p><b>Name Seller:</b> <a href="${pageContext.request.contextPath}/assess/index?UserId=${u.id}"> ${u.name} </a></p>
                        <input type="hidden" class="form-control" id="localday" name="Day" readonly value="${localDate}">
                        <input type="hidden" class="form-control" id="SellerID" name="SellerID" readonly value="${u.id}">
                    </c:if>
                </c:forEach>
                <c:set var = "Bienthu" scope = "session" value = "${0}"/>
                <c:choose>
                    <c:when test="${Number != 0}">
                        <c:forEach items="${listbidding}" var="b">
                            <c:forEach items="${user}" var="u">
                                <c:if test="${b.price == Max}">
                                    <c:if test="${b.proID == product.proID}">
                                        <c:if test="${u.id == b.userID}">
                                            <c:if test="${Bienthu==0}">
                                                <p><b>Name Bidder:</b> <a href="${pageContext.request.contextPath}/assess/index?UserId=${u.id}"> ${u.name} </a></p>
                                                <c:set var = "Bienthu" scope = "session" value = "${1}"/>
                                            </c:if>
                                        </c:if>
                                    </c:if>
                                </c:if>
                            </c:forEach>
                        </c:forEach>
                    </c:when>
                </c:choose>
                <p><b>Description:</b></p>
                <div class="card border border-dark">
                <p>${product.fullDes}</p>
                </div>
                <br/>
                <c:set var = "BienThu" scope = "session" value = "${0}"/>
                <p class=" text-center" style="font-size:20px;"><b>Auction History</b></p>
                <table class="table table-hover">
                    <thead class="thead-dark">
                    <tr>
                        <th scope="col">#</th>
                        <th scope="col">DateTime</th>
                        <th scope="col">Name</th>
                        <th scope="col">Price</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:set var = "STT" scope = "session" value = "${1}"/>
                    <c:choose>
                        <c:when test="${Number != 0}">
                            <c:forEach items="${listbidding}" var="b">
                                <c:forEach items="${user}" var="u">
                                    <c:if test="${b.proID == product.proID}">
                                        <c:if test="${u.id == b.userID}">
                                            <c:choose>
                                                <c:when test="${BienThu != 0}">
                                                    <tr>
                                                        <th scope="row">${STT}</th>
                                                        <td>${b.day}</td>
                                                        <td>${u.name}</td>
                                                        <td>${b.price}</td>

                                                        <c:set var = "STT" scope = "session" value = "${STT+1}"/>
                                                    </tr>
                                                </c:when>
                                                <c:otherwise>
                                                    <c:set var = "BienThu" scope = "session" value = "${1}"/>
                                                </c:otherwise>
                                            </c:choose>
                                        </c:if>
                                    </c:if>
                                </c:forEach>
                            </c:forEach>
                        </c:when>
                    </c:choose>
                    </tbody>
                </table>


                <input type="hidden" class="form-control" id="ProID" name="ProID" readonly value="${product.proID}">
                <input type="hidden" class="form-control" id="UserID" name="UserID" readonly value="${AuthUser.id}">
                <input type="hidden" class="form-control" id="PriceDifference" name="PriceDifference" readonly value="${product.priceDifference}">

                <br>
                <br>
                <c:set var = "GIOIHAN" scope = "session" value = "${1}"/>
                <div class="card-body">
                    <div class="row ">
                        <c:forEach items="${listproduct}" var="c">
                            <c:if test="${GIOIHAN <= 5}">
                                <c:if test="${c.proID != product.proID}">
                                    <c:set var = "GIOIHAN" scope = "session" value = "${GIOIHAN+1}"/>
                                    <div class="col-sm-4 mb-3 ">
                                        <div class="card bg-light border border-dark">
                                            <img src="${pageContext.request.contextPath}/public/imgs/sp/${c.proID}/main.jpg" alt="${c.proName}" title="${c.proName}" class="card-img-top">
                                            <div class="card-body">
                                                <a href="${pageContext.request.contextPath}/admin/product/byProID?ProId=${c.proID}" class="text-dark" style="font-size:25px; ">${c.proName}</a>
                                                <c:set var = "Max" scope = "session" value = "${0}"/>
                                                <c:forEach items="${listbidding}" var="b">
                                                    <c:if test="${b.proID == c.proID}">
                                                        <c:if test="${Max < b.price}">
                                                            <c:set var = "Max" scope = "session" value = "${b.price}"/>
                                                        </c:if>
                                                    </c:if>
                                                </c:forEach>
                                                <h6 class="card-subtitle mb-2 text-dark" style="font-size:40px;">$ ${Max}</h6>
                                                <c:choose>
                                                    <c:when test="${c.currentPrice != 0}">
                                                        <p class="card-text"><b>Buy Now Price:</b><p class="card-subtitle mb-2 text-dark" style="font-size:20px;"> $ ${c.currentPrice}</p></p>
                                                    </c:when>
                                                </c:choose>
                                                <c:set var = "Number" scope = "session" value = "${-1}"/>
                                                <c:forEach items="${listbidding}" var="b">
                                                    <c:if test="${b.proID == c.proID}">
                                                        <c:set var = "Number" scope = "session" value ="${Number+1}"/>
                                                    </c:if>
                                                </c:forEach>
                                                <c:choose>
                                                    <c:when test="${Number != 0}">
                                                        <p class="card-text"><b>Number Of Bids:</b> ${Number}</p>
                                                        <c:forEach items="${listbidding}" var="b">
                                                            <c:forEach items="${user}" var="u">
                                                                <c:if test="${b.price == Max}">
                                                                    <c:if test="${b.proID == c.proID}" >
                                                                        <c:if test="${u.id == b.userID}">
                                                                            <p><b>Name Bidder:</b> ${u.name}</p>
                                                                        </c:if>
                                                                    </c:if>
                                                                </c:if>
                                                            </c:forEach>
                                                        </c:forEach>
                                                    </c:when>
                                                    <c:otherwise>
                                                        <p class="card-text"><b>No One Bid Yet</b></p>
                                                    </c:otherwise>
                                                </c:choose>
                                                <p class="card-text"><b>Start Day:</b> ${c.startDay}</p>
                                                <p class="card-text">${c.tinyDes}</p>
                                            </div>
                                        </div>
                                    </div>


                                </c:if>
                            </c:if>
                        </c:forEach>
                    </div>
                </div>
                    <%--                        <label for="CommentID">Comment</label>--%>
                    <%--                        <input type="text" class="form-control" id="CommentID" name="comment">--%>
                    <%--                        <button type="submit" class="btn btn-outline-primary btn-sm w-25" formaction="${pageContext.request.contextPath}/admin/product/addCommentPro" role="button">Comment</button>--%>
                    <%--                        <div class="card">--%>
                    <%--                            <div class="card-body">--%>
                    <%--                                <c:forEach items="${comment}" var="c">--%>
                    <%--                                <c:forEach items="${user}" var="u">--%>
                    <%--                                    <c:choose>--%>
                    <%--                                        <c:when test="${c.userID == u.id}">--%>
                    <%--                                    <p>User Name:${u.userName}</p>--%>
                    <%--                                    <p>Comment:${c.text}</p>--%>
                    <%--                                    </br>--%>
                    <%--                                        </c:when>--%>
                    <%--                                    </c:choose>--%>
                    <%--                                </c:forEach>--%>
                    <%--                                </c:forEach>--%>
                    <%--                            </div>--%>
                    <%--                        </div>--%>
                    <%--                    </form>--%>
                    <%--                </div>--%>
                    <%--            </div>--%>
            </form>
        </div>
        <script>
            function myFunction(){
            var result = confirm("Are you sure you want to bid?");
            if (result == true) {
                return true;
            }else {
                return false;
            }
            }
        </script>

    </jsp:body>
</t:main>