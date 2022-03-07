<jsp:useBean id="post" scope="request" type="org.davidcalabrese.entity.Post"/>
<%--
  Created by IntelliJ IDEA.
  User: david
  Date: 2/24/2022
  Time: 12:12 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Otter</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/style.css">
</head>
<body>
<div class="container py-4" id="outer-container">
    <div class="container" id="inner-container">
        <jsp:include page = "/jsp/components/nav.jsp" />
        <main class="container">
            <div class="row g-5">
                <div class="col-lg-8">
                    <article class="blog-post">
                        <h2 class="blog-post-title">${post.title}</h2>
                        <p class="blog-post-meta">${post.dateCreated} by <a href="../users/${post.user.id}">${post.user.firstName} ${post.user.lastName}</a></p>
                        <p class="blog-post-meta" id="tags">
                            <c:forEach var="tag" items="${post.tags}">
                                <a href="<%=request.getContextPath()%>/tags/${tag.id}" class="tag-link text-color-${tag.color}">
                                    <strong class="d-inline mb-2">${tag.name} &nbsp;</strong>
                                </a>
                            </c:forEach>
                        </p>
                        <p class="display-6" id="summary">${post.summary}</p>
                        <hr>
                        <p id="content">${post.content}</p>
                    </article>
                </div>  <!-- end .col-md-8 -->
                <div class="col-lg-4">
                    <section
                            id="user-summary"
                            class="d-flex flex-column align-items-center justify-content-center">
                        <img
                             class="rounded"
                             width="200" height="250"
                             src="../img/${post.user.profileImage}"
                             alt="profile pic">
                        <h4 class="display-6 fw-normal mt-2">${post.user.firstName} ${post.user.lastName}</h4>
                        <p class="mx-5 fst-italic">${post.user.summary}</p>
                    </section> <!-- end .d-flex -->
                </div>  <!-- end .col-md-4 -->
            </div>  <!-- end .row g-5 -->
        </main>
    </div> <!-- end #outer-container -->
    <jsp:include page="/jsp/components/footer.jsp" />
</div>  <!-- end #inner-container -->
</body>
</html>
