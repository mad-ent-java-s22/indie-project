<%--
  Created by IntelliJ IDEA.
  User: david
  Date: 2/21/2022
  Time: 4:33 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Spry</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
</head>
<body>
<div class="container py-4" id="outer-container">
    <div class="container" id="inner-container">
        <jsp:include page = "/jsp/components/nav.jsp" />
        <main class="container">
            <jsp:include page="/jsp/components/leading_story.jsp" />

            <div class="row mb-2">
                <c:forEach var="post" items="${posts}">

                    <div class="col-12 col-xl-6">
                        <div class="row g-0 border rounded overflow-hidden flex-md-row mb-4 shadow-sm h-md-250 position-relative">
                            <div class="col p-4 d-flex flex-column position-static">
                                <div id="tags">
                                    <c:forEach var="tag" items="${post.tags}">
                                        <c:choose>
                                            <c:when test="${tag.getName() == 'politics'}">
                                                <strong class="d-inline mb-2 text-primary">${tag.getName()}</strong> &nbsp;
                                            </c:when>
                                            <c:when test="${tag.getName() == 'entertainment'}">
                                                <strong class="d-inline mb-2 text-warning">${tag.getName()}</strong> &nbsp;
                                            </c:when>
                                            <c:when test="${tag.getName() == 'sports'}">
                                                <strong class="d-inline mb-2 text-success">${tag.getName()}</strong> &nbsp;
                                            </c:when>
                                            <c:when test="${tag.getName() == 'movies'}">
                                                <strong class="d-inline mb-2 text-secondary">${tag.getName()}</strong> &nbsp;
                                            </c:when>
                                            <c:when test="${tag.getName() == 'education'}">
                                                <strong class="d-inline mb-2 text-info">${tag.getName()}</strong> &nbsp;
                                            </c:when>
                                            <c:otherwise>
                                                <strong class="d-inline mb-2 text-danger">${tag.getName()}</strong> &nbsp;
                                            </c:otherwise>
                                        </c:choose>
                                    </c:forEach>
                                </div>  <!-- end #tags -->
                                <h3 class="mb-0">${post.title}</h3>
                                <div class="mb-1 text-muted">
                                    <tags:localDate date="${post.dateCreated}" pattern='${"MMM d, yyyy \'at\' h:mm a"}'/>
                                </div>  <!-- end .mb-1 -->
                                <p class="card-text mb-auto">${post.summary}</p>
                                <a href="#" class="link-primary">${post.user.getUserName()}</a>
                                <a href="indie_project_war/posts/${post.id}" class="stretched-link"></a>
                            </div> <!-- end .col -->
                            <div class="col-auto d-none d-md-block" id="post-img">
                                <img class="bd-placeholder-img" width="200" height="250" src="img/${post.user.profileImage}" alt="profile pic">
                            </div> <!-- end #post-img -->
                        </div> <!-- end .row -->
                    </div> <!-- end .col-md-6 -->
                </c:forEach>
            </div> <!-- end .row mb-2 -->
        </main>

    </div> <!-- end #outer-container -->
    <jsp:include page="/jsp/components/footer.jsp" />
</div>  <!-- end #inner-container -->
</body>
</html>
