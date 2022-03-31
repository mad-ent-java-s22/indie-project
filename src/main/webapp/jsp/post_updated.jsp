<%--   
    {____       {__    {__                   
  {__    {__    {__    {__                   
{__        {__{_{_ {_{_{_ {_   {__    {_ {___ User: david 
{__        {__  {__    {__   {_   {__  {__    Date: 3/29/2022, 2:28 PM
{__        {__  {__    {__  {_____ {__ {__    
  {__     {__   {__    {__  {_         {__   
    {____        {__    {__   {____   {___      --%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Post Added</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
  <link rel="stylesheet" href="<%=request.getContextPath()%>/css/style.css">
</head>
<body>
<div class="container py-4" id="outer-container">
  <div class="container" id="inner-container">
    <jsp:include page = "/jsp/components/nav.jsp" />
    <main class="container">
      <div class="p-4 p-md-5 mb-4 text-white rounded bg-success">
        <div class="col-md-12 px-0">
          <h1 class="display-4 w-100">Your post has been updated!</h1>
          <p class="lead my-3 fst-italic">Thank you for your contribution.</p>
          <p class="lead mb-0"><a href="<%=request.getContextPath()%>/posts/${post.id}" class="btn btn-dark">View Post</a></p>
        </div>
      </div>
    </main>
  </div> <!-- end #outer-container -->
  <jsp:include page="/jsp/components/footer.jsp" />
</div>  <!-- end #inner-container -->
</body>
</html>
