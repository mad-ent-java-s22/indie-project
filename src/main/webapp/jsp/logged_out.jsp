<%--
  Author: David Calabrese
  Date: 3/23/2022, 10:20 AM
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Logged Out | Otter </title>
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
      <div class="p-4 p-md-5 mb-4 text-white rounded bg-info">
        <div class="col-md-12 px-0">
          <h1 class="display-4 w-100">You have been signed out!</h1>
          <p class="lead my-3 fst-italic">Thank you for using Otter.</p>
        </div>
      </div>
      <div class="col-md-12 px-0">
        <a class="btn btn-outline-info" href="http://blogenv2-env.eba-zg5dcynj.us-east-2.elasticbeanstalk.com/">
          Sign back in
        </a>
      </div>
    </main>
  </div> <!-- end #outer-container -->
  <jsp:include page="/jsp/components/footer.jsp" />
</div>  <!-- end #inner-container -->
</body>
</html>
