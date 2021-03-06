<jsp:useBean id="post" scope="request" type="org.davidcalabrese.entity.Post"/>
<%--
  Author: David Calabrese
  Date: 2/24/2022, 12:12 PM
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Read Post | Otter </title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
  <!-- font awesome -->
  <link rel='stylesheet' href='https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta2/css/all.min.css' integrity='sha512-YWzhKL2whUzgiheMoBFwW8CKV4qpHQAEuvilg9FAn5VJUDwKZZxkJNuGM4XkWuk94WCrrwslk8yWNGmY1EduTA==' crossorigin='anonymous' referrerpolicy='no-referrer' />
  <link rel="stylesheet" href="<%=request.getContextPath()%>/css/style.css">
  <link rel="icon" href="/favicon-32x32.png" sizes="any">
</head>
<body>
<div class="container py-4" id="outer-container">
  <div class="container" id="inner-container">
    <jsp:include page = "/jsp/components/nav.jsp" />
    <main class="container mx-3">
      <div class="row g-5">
        <div class="col-lg-8">
          <article class="blog-post">
            <h2 class="blog-post-title">${post.title}</h2>
            <p class="blog-post-meta">${post.dateCreated} by <a href="../users/${post.user.id}">${post.user.firstName} ${post.user.lastName}</a></p>
            <p class="blog-post-meta" id="tags">
              <c:forEach var="tag" items="${post.tags}">
                <a href="<%=request.getContextPath()%>/tags/${tag.id}" class="tag-link text-color-${tag.color}">
                  <strong class="d-inline mb-2">${tag.name} &nbsp;</strong>
                </a>
              </c:forEach>
            </p>
            <p class="display-6" id="summary">${post.summary}</p>
            <hr>
            <p id="post-content">${post.content}</p>
          </article>
          <c:if test = "${userId == post.user.id || user.accessPrivileges.equals('admin')}">
            <div id="btns" class="mt-3 mb-3">
              <a
                  href="<%=request.getContextPath()%>/display_update_post/${post.id}"
                  class="btn btn-outline-secondary"
              > Edit Post</a>
              <a
                  type="button"
                  class="btn btn-outline-warning"
                  data-bs-toggle="modal"
                  data-bs-target="#confirmDeleteModal"
              >Delete Post</a>
            </div> <!-- end #btns -->
          </c:if>
        </div>  <!-- end .col-md-8 -->
        <div class="col-lg-4">
          <section
              id="user-summary"
              class="d-flex flex-column align-items-center justify-content-center">
            <img
                class="rounded"
                width="200" height="250"
                src="${post.user.profileImage}"
                alt="profile pic"
                style="object-fit: cover;"
            >
            <h4 class="display-6 fw-normal mt-2">${post.user.firstName} ${post.user.lastName}</h4>
            <p class="mx-5 fst-italic">${post.user.summary}</p>
          </section> <!-- end .d-flex -->
        </div>  <!-- end .col-md-4 -->
      </div>  <!-- end .row g-5 -->

      <section class="container mt-3 mb-5">
        <div class="row height d-flex justify-content-start align-items-center">
          <div class="col-md-7">
            <div>
              <div class="p-3 pb-0">
                <h3 class="display-6">Comments</h3>
              </div>
              <div class="mt-3 d-flex flex-row align-items-center p-3 pb-0 form-color">
                <img
                    src="${user.profileImage}"
                    width="62"
                    height="50"
                    class="rounded-pill me-2 mb-3"
                    style="object-fit: contain;"
                >
                <form class="w-100" id="form" method="POST" action="<%=request.getContextPath()%>/create_comment">
                  <div class="d-flex flex-row justify-content-center align-items-center">
                    <input
                        type="text"
                        class="form-control w-100"
                        placeholder="Enter your comment..."
                        id="content"
                        name="content"
                    >
                    <input
                        type="hidden"
                        id="post_id"
                        name="post_id"
                        value="${post.id}"
                    >
                    <div class="d-flex m-2">
                      <button class="btn btn-success" type="submit">Post</button>
                    </div>
                  </div>
                </form>

              </div>
              <c:choose>
                <c:when test="${empty comments}">
                  <p class="d-flex justify-content-center align-items-center mt-3">No Comments Yet</p>
                </c:when>
                <c:otherwise>
                  <c:forEach var="comment" items="${comments}">
                    <div class="mt-2">
                      <div class="d-flex flex-row p-3">
                        <img
                            src="${comment.user.profileImage}"
                            width="40"
                            height="32"
                            class="rounded-pill me-3"
                            style="object-fit: cover;"
                        >
                        <div class="w-100">
                          <div class="d-flex justify-content-between align-items-center">
                            <div class="d-flex flex-row align-items-center">
                              <span class="mr-2">${comment.user.firstName} ${comment.user.lastName}</span>
                            </div>
                            <small>
                              <tags:localDate date="${comment.dateCreated}" pattern='${"MMM d, yyyy"}'/>
                            </small>
                          </div>
                          <p class="text-justify comment-text mb-0 d-flex flex-row">${comment.content}
                            <c:if test = "${user.id == comment.user.id || user.accessPrivileges.equals('admin')}">
                            <a href="/delete_comment/${comment.id}" class="ms-auto">
                              <i class="fa-solid fa-trash"></i>
                            </a>
                            </c:if>
                          </p>
                        </div>
                      </div>
                    </div> <!-- end mt-2 -->
                  </c:forEach>
                </c:otherwise>
              </c:choose>

            </div>
          </div>
        </div>
      </section>

      <!-- Modal -->
      <div
          class="modal fade"
          id="confirmDeleteModal"
          tabindex="-1"
          aria-labelledby="confirmDeleteModalLabel"
          aria-hidden="true"
      >
        <div class="modal-dialog">
          <div class="modal-content">
            <div class="modal-header">
              <h5 class="modal-title" id="confirmDeleteModalLabel">
                Delete Post
              </h5>
              <button
                  type="button"
                  class="btn-close"
                  data-bs-dismiss="modal"
                  aria-label="Close"
              ></button>
            </div>
            <div class="modal-body">
              <p class="lead">Are you sure you want to delete this post?</p>
              <p class="my-3">This action cannot be undone.</p>
            </div>
            <div class="modal-footer">
              <button
                  type="button"
                  class="btn btn-secondary"
                  data-bs-dismiss="modal"
              >
                Cancel
              </button>
              <a
                  type="button"
                  class="btn btn-danger"
                  href="<%=request.getContextPath()%>/delete_post/${post.id}"
              >
                Confirm Delete
              </a>
            </div>
          </div>
        </div>
      </div>
    </main>
  </div> <!-- end #outer-container -->
  <jsp:include page="/jsp/components/footer.jsp" />
</div>  <!-- end #inner-container -->
</body>
</html>
