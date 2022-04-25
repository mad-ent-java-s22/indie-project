<%--
  Author: David Calabrese
  Date: 4/4/2022, 5:01 AM
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" isErrorPage="true" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
  <title>404 Error | Otter/title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
  <link rel="stylesheet" href="<%=request.getContextPath()%>/css/style.css">
</head>
<body>
<div class="container py-4" id="outer-container">
  <div class="container" id="inner-container">
    <jsp:include page = "/jsp/components/nav.jsp" />
    <main class="container">
      <div class="d-flex justify-content-center align-items-center">
        <img class="img-fluid" src="<%=request.getContextPath()%>/img/otter_not_found.png" alt="surprised otter">
      </div>
      <div class="mt-3">
        <h1 class="display-5">Uh Oh! We can't find that page!</h1>
        <button class="btn btn-secondary" onclick="history.back()">Back to Previous Page</button>
        <br />
        <p><b>Error code:</b> <span class="text-danger"> ${pageContext.errorData.statusCode} </span></p>
        <p><b>Request URI:</b> ${pageContext.request.scheme}://${header.host}${pageContext.errorData.requestURI}</p>
        <br />
      </div>
    </main>
  </div> <!-- end #outer-container -->
  <jsp:include page="/jsp/components/footer.jsp" />
</div>  <!-- end #inner-container -->
</body>
</html>