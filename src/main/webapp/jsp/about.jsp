<%--
  Author: David Calabrese
  Date: 3/23/2022, 10:20 AM
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
        <p class="mt-2">
          Do you feel like you need to take a shower after spending time on conventional social media sites like Facebook or Twitter? That's because those sites make you feel dirty! Otter won't make you feel dirty! It's just good clean fun! Create an account, share some articles and get on with your life. We promise we won't hijack your attention or rewire your brain when you use our site. Just check it out, have some fun, and move on with your life!
        </p>
        <p class="mt-2">This blog is my Enterprise Java final project. It was a lot of fun to build and I hope you find it a lot of fun to use.</p>
        <p class="mt-2">
          One of Otter's more unconventional features is that it will generate content for you! Everyone wants to write a blog, but who has time to think up ideas and type out the articles? That's where AI comes in! Otter has access to GPT-3, OpenAI's latest and most powerful language model generator. Just give it a few topics and it will create your articles for you. You'll be on your way to a lucrative blogging career in no time.
        </p>
      </div>
    </main>
  </div> <!-- end #outer-container -->
  <jsp:include page="/jsp/components/footer.jsp" />
</div>  <!-- end #inner-container -->
</body>
</html>
