<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%--
 Author: David Calabrese
 Date: 4/21/2022, 1:37 PM
--%>
<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Generate a post </title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
  <link rel="stylesheet" href="<%=request.getContextPath()%>/css/style.css">
</head>
<body>
<div class="container py-4" id="outer-container">
  <jsp:include page = "/jsp/components/nav.jsp" />
  <main class="container">
    <div class="row g-5 mx-xl-5 px-lg-5">
      <div class="col-12">
        <div class="container">
          <h1 class="display-6 text-center">Generate post</h1>
        </div>
        <form class="px-lg-5" id="form" method="POST" action="/generate_post">
          <div class="mb-3">
            <label for="tags" class="form-label">Select up to 3 topics for your post</label>
            <div id="tagsHelp" class="form-text">Hold control to select more than one tag</div>
            <select name="tags" id="tags" multiple class="form-select">
                <option value="politics">Politics</option>
                <option value="education">Education</option>
                <option value="movies">Movies</option>
                <option value="books">Books</option>
                <option value="entertainment">Entertainment</option>
                <option value="food">Food</option>
                <option value="personal">Personal</option>
                <option value="technology">Technology</option>
                <option value="business">Business</option>
                <option value="sports">Sports</option>
                <option value="opinion">Opinion</option>
                <option value="science">Science</option>
                <option value="health">Health</option>
                <option value="travel">Travel</option>
                <option value="art">Art</option>
              </select>
          </div>
          <button id="submit" type="submit" class="btn btn-outline-primary">Generate a post</button>
        </form>
      </div>
    </div>
  </main>

</div> <!-- end #outer-container -->
<jsp:include page="/jsp/components/footer.jsp" />
</body>
</html>
