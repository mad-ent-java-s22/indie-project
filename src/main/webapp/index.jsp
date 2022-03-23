<%--
  Created by IntelliJ IDEA.
  User: david
  Date: 2/14/2022
  Time: 7:24 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Otter</title>
    <link rel="stylesheet" href="css/style.css">
    <link href="https://fonts.googleapis.com/css2?family=Allura&display=swap" rel="stylesheet">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
</head>
<body>
<div class="container-xxl py-4" id="outer-container">
    <div class="container-xxl" id="inner-container">
    <jsp:include page = "/jsp/components/nav.jsp" />
    <main class="container-xxl d-flex justify-content-center align-items-lg-center" id="otters">
        <div class="px-5 mb-4 rounded-3">
            <div class="container-fluid py-5 above">
                <h1 class="display-4 text-white">Welcome to <span class="o-glyph">O</span>tter</h1>
                <p class="col-md-8 fs-4 text-white">Ethical social media.</p>
                <c:choose>
                    <c:when test="${empty userName}">
                        <a href="/logIn" class="btn btn-primary btn-lg" type="button">Sign in</a>
                        <a href="/logIn" class="btn btn-success btn-lg" type="button">Sign up</a>
                    </c:when>
                    <c:otherwise>
                        <div class="d-flex justify-content-center align-items-center flex-column">
                            <h3 class="display-5 text-white">Welcome ${userName}!</h3>
                            <a href="all_posts" class="btn btn-success btn-lg" type="button">View Posts</a>
                        </div>
                    </c:otherwise>
                </c:choose>
            </div>
        </div>
    </main>
    </div> <!-- end #outer-container -->
    <jsp:include page="/jsp/components/footer.jsp" />
</div>  <!-- end #inner-container -->
</body>
</html>
