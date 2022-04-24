<%--
  Created by IntelliJ IDEA.
  User: david
  Date: 2/28/2022
  Time: 11:49 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>User Page | Otter</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
  <link rel="stylesheet" href="<%=request.getContextPath()%>/css/style.css">
</head>
<body>
<div class="container py-4" id="outer-container">
  <div class="container" id="inner-container">
    <jsp:include page = "/jsp/components/nav.jsp" />
    <main class="container">
      <div class="row mb-4">
        <div class="row" id="user-summary">
          <div class="col-12 col-md-4 col-lg-2 d-flex justify-content-center align-items-center">
              <img
                  class="rounded"
                  width="200" height="250"
                  src="${user.profileImage}"
                  alt="profile pic"
                  style="object-fit: cover;"
              >
          </div> <!-- end .col-4 -->
          <div class="col-12 col-md-8 col-lg-10">
            <h4 class="display-6 fw-normal mt-2 text-center">${user.firstName} ${user.lastName}</h4>
            <p class="p-3 mx-5 fst-italic">${user.summary}</p>
          </div>
        </div>
      </div>  <!-- end .row g-5 -->
      <c:if test="${not empty user.posts}">
      <div class="row mb-2">
        <h2 class="display-6 text-center p-2">${user.firstName}'s Recent Posts</h2>
        <c:forEach var="post" items="${user.posts}">
          <section class="col-12 col-xl-6">
            <div class="row g-0 border rounded overflow-hidden flex-md-row mb-4 shadow-sm h-md-250 position-relative">
              <div class="col p-4 d-flex flex-column position-static">
                <div id="tags">
                  <c:forEach var="tag" items="${post.tags}">
                    <a href="<%=request.getContextPath()%>/tags/${tag.id}" class="tag-link text-color-${tag.color}">
                      <strong class="d-inline mb-2">${tag.name} &nbsp;</strong>
                    </a>
                  </c:forEach>
                </div>  <!-- end #tags -->
                <h3 class="mb-0">${post.title}</h3>
                <div class="mb-1 text-muted">
                  <tags:localDate date="${post.dateCreated}" pattern='${"MMM d, yyyy \'at\' h:mm a"}'/>
                </div>  <!-- end .mb-1 -->
                <p class="card-text mb-auto">${post.summary}</p>
                <a href="../posts/${post.id}" class="link-secondary">Read More</a>
              </div> <!-- end .col -->
            </div> <!-- end .row -->
          </section> <!-- end .col-md-6 -->
        </c:forEach>
      </div> <!-- end .row mb-2 -->
      </c:if>
    </main>
  </div> <!-- end #outer-container -->
  <jsp:include page="/jsp/components/footer.jsp" />
</div>  <!-- end #inner-container -->
</body>
</html>
