<nav class="navbar navbar-expand-lg navbar-light bg-light shadow">
    <div class="container-fluid">
        <a class="navbar-brand" href="${pageContext.request.contextPath}/Home/Index">
            <i class= "fa fa-shopping-bag fa-2x" aria-hidden="true"></i>
        </a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent"
                aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav me-auto my-2 my-lg-0 navbar-nav-scroll" style="--bs-scroll-height: 100px;">
                <li class="nav-item">
                    <a class="nav-link active" aria-current="page" href="${pageContext.request.contextPath}/Home/Index">Home</a>
                </li>
                <li class="nav-item ">
                    <a class="nav-link active" aria-current="page" href="${pageContext.request.contextPath}/Home/Category/Index">Category</a>
                </li>
            </ul>

            <form class="form-inline mt-2 mr-4 mb-4 ml-auto">
                <%--<i class= "fa fa-shopping-cart car2x" aria-hidden="true"></i>--%>
                <input class="form-control mr-sm-2" type="search" placeholder="Search" aria-label="Search">
                <button class="btn btn-outline-success my-2 my-sm-0" type="submit">Search</button>
                <ul class="navbar-nav me-auto my-2 my-lg-0 navbar-nav-scroll" style="--bs-scroll-height: 100px;">
                    <li class="nav-item">

                        <a class="nav-link active" aria-current="page" href="${pageContext.request.contextPath}/Home/Login">Login</a>
                    </li>
                </ul>
            </form>
        </div>
    </div>
</nav>