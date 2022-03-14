<%--
  Created by IntelliJ IDEA.
  User: david
  Date: 3/11/2022
  Time: 6:48 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Otter | Create a new post</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
  <script src="tinymce/js/tinymce/tinymce.min.js"></script>
  <script>tinymce.init({ selector: 'textarea#content', });</script>
  <link rel="stylesheet" href="<%=request.getContextPath()%>/css/style.css">
</head>
<body>
<div class="container py-4" id="outer-container">
  <jsp:include page = "/jsp/components/nav.jsp" />
  <main class="container">
    <div class="row g-5 mx-xl-5 px-lg-5">
      <div class="col-12">
        <div class="container">
          <h1 class="display-6 text-center">Create a new blog post</h1>
        </div>
        <form class="px-lg-5" id="form">
          <div class="mb-3">
            <label for="title" class="form-label">Post Title</label>
            <input type="text" name="title" class="form-control" id="title">
          </div>
          <div class="mb-3">
            <label for="summary" class="form-label">Post Summary</label>
            <input type="text" name="summary" class="form-control" id="summary">
            <div id="summaryHelp" class="form-text">Enter a sentence that sums up the post</div>
          </div>
          <div class="mb-3">
            <label for="content" class="form-label">Post Content</label>
            <textarea name="content" id="content" cols="30" rows="10" class="form-control"></textarea>
          </div>
          <button id="submit" type="submit" class="btn btn-outline-primary">Submit</button>
        </form>
      </div>
    </div>
  </main>

</div> <!-- end #outer-container -->
<jsp:include page="/jsp/components/footer.jsp" />
</body>
</html>
