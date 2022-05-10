<%--
  Author: David Calabrese
  Date: 2/21/2022, 4:33 PM
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>All Posts | Otter</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
  <link rel="stylesheet" href="<%=request.getContextPath()%>/css/style.css">
  <link rel="icon" href="/favicon-32x32.png" sizes="any">
</head>
<body>
<div class="container py-4" id="outer-container">
  <div class="container" id="inner-container">
    <jsp:include page = "/jsp/components/nav.jsp" />
    <main class="container">
      <jsp:include page="/jsp/components/leading_story.jsp" />

      <div class="row mb-2">
        <c:forEach var="post" items="${posts}">

          <div class="col-12 col-xxl-6">
            <div class="row g-0 border rounded overflow-hidden flex-md-row mb-4 shadow-sm h-md-250 position-relative">
              <div class="col p-4 d-flex flex-column position-static">
                <div id="tags">
                  <c:forEach var="tag" items="${post.tags}">
                    <a href="<%=request.getContextPath()%>/tags/${tag.id}" class="tag-link text-color-${tag.color}">
                      <strong class="d-inline mb-2">${tag.name} &nbsp; </strong>
                    </a>
                  </c:forEach>
                </div>  <!-- end #tags -->
                <div class="position-relative">
                  <h3 class="mb-0">${post.title}</h3>
                  <div class="mb-1 text-muted">
                    <tags:localDate date="${post.dateCreated}" pattern='${"MMM d, yyyy"}'/>
                  </div>  <!-- end .mb-1 -->
                  <p class="card-text mb-auto">${post.summary}</p>
                  <a href="posts/${post.id}" class="link-secondary stretched-link">Read More</a>
                </div> <!-- end .position-relative -->
              </div> <!-- end .col -->
              <div class="col-auto d-none d-md-block" id="post-img">
                <a href="users/${post.user.id}">
                  <img
                      class="bd-placeholder-img"
                      width="200" height="250"
                      src="${post.user.profileImage}"
                      alt="profile pic"
                      style="object-fit: cover;"
                  >
                </a>
              </div> <!-- end #post-img -->
            </div> <!-- end .row -->
          </div> <!-- end .col-12 col-xxl-6 -->
        </c:forEach>
      </div> <!-- end .row mb-2 -->
    </main>

  </div> <!-- end #outer-container -->
  <jsp:include page="/jsp/components/footer.jsp" />
</div>  <!-- end #inner-container -->
</body>
</html>
