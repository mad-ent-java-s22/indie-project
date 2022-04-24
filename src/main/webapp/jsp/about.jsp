<%--
  Created by IntelliJ IDEA.
  User: david
  Date: 3/23/2022
  Time: 10:20 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Otter | About </title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
  <link rel="stylesheet" href="<%=request.getContextPath()%>/css/style.css">
</head>
<body>
<div class="container py-4" id="outer-container">
  <div class="container" id="inner-container">
    <jsp:include page = "/jsp/components/nav.jsp" />
    <main class="container">
      <div class="row g-5 m-3">
        <h1 class="display-6 text-center mt-5">About Otter</h1>
        <p class="lead">Otter is a social media application conceived of and created with ethical design principles.</p>
        <p class="mt-2">Lorem ipsum dolor sit amet, consectetur adipisicing elit. Omnis inventore accusamus corrupti tempora excepturi rem! Illo enim sequi voluptate mollitia odio et repudiandae rem voluptas. Libero, eum architecto nemo mollitia reiciendis voluptates voluptate odio facere, cum repellat, veniam reprehenderit beatae sit laborum sequi assumenda nesciunt placeat! Labore dolorum accusamus facilis?</p>
        <p class="mt-2">Lorem ipsum, dolor sit amet consectetur adipisicing elit. Ad sapiente ab saepe expedita aperiam vel, unde, neque dolore delectus iure, debitis nihil. Eius, amet. Fugit velit non in voluptas eum exercitationem cupiditate corporis. Non praesentium rem minima quis saepe nisi?</p>
      </div>
    </main>
  </div> <!-- end #outer-container -->
  <jsp:include page="/jsp/components/footer.jsp" />
</div>  <!-- end #inner-container -->
</body>
</html>
