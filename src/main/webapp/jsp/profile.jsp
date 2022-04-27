<%--
  Author: David Calabrese
  Date: 3/11/2022, 6:48 PM
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Create your Profile | Otter</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
  <link rel="stylesheet" href="<%=request.getContextPath()%>/css/style.css">
  <script src="<%=request.getContextPath()%>/js/tinymce/js/tinymce/tinymce.min.js"></script>
  <script>tinymce.init({ selector: 'textarea#about', });</script>
</head>
<body>
<div class="container py-4" id="outer-container">
    <jsp:include page = "/jsp/components/nav.jsp" />
  <main>
    <c:choose>
      <c:when test="${showProfileForm == true}">
        <!-- if user hasn't been added to db yet - display form (with username and email filled in from cognito values) -->
        <div class="row flex-lg-nowrap">
          <div class="col">
            <div class="row container mx-auto">
              <div class="col mb-3">
                <div class="card">
                  <div class="card-body">
                    <div class="e-profile">
                      <div class="row">
                        <div class="col-12 col-sm-auto mb-3">
                          <div class="mx-auto" style="width: 120px">
                            <div
                                class="d-flex justify-content-center align-items-center rounded"
                                style="height: 140px; background-color: rgb(233, 236, 239)"
                            >
                          <c:choose>
                            <c:when test="${empty user.profileImage}">
                              <img src="../img/default_profile_pic.jpg" style="height: 140px; width: 120px" alt="profile pic" />
                            </c:when>
                          <c:otherwise>
                              <img src="${user.profileImage}" style="height: 140px; width: 120px; object-fit: cover;" alt="profile pic" />
                            </c:otherwise>
                          </c:choose>
                            </div>
                          </div>
                        </div>
                        <div class="col d-flex flex-column flex-sm-row justify-content-between mb-3">
                          <div class="text-center text-sm-left mb-2 mb-sm-0">
                            <c:choose>
                              <c:when test="${empty user}">
                                <h4 class="pt-sm-2 pb-1 mb-0 text-nowrap">New User</h4>
                              </c:when>
                              <c:otherwise>
                                <h4 class="pt-sm-2 pb-1 mb-0 text-nowrap">${user.firstName} ${user.lastName}</h4>
                              </c:otherwise>
                            </c:choose>
                            <p class="mb-0 text-start">@${userName}</p>
                          </div>
                          <div class="text-center text-sm-right">
                            <c:if test="${not empty user.dateCreated}">
                              <div class="text-muted">
                                <small>Joined Otter
                                  <tags:localDate date="${user.dateCreated}" pattern='${"MMM d, yyyy"}'/>
                                </small>
                              </div>
                            </c:if>
                          </div>
                        </div>
                      </div>
                      <div class="pt-3">
                        <form class="form" method="POST" action="/update_profile" novalidate="">
                          <div class="row">
                            <div class="col">
                              <div class="form-floating mb-3">
                                <input
                                    type="text"
                                    name="profile_image"
                                    id="profile_image"
                                    class="form-control"
                                    placeholder="Profile URL"
                                    <c:if test="${not empty user.profileImage}">
                                      value="${user.profileImage}"
                                    </c:if>
                                />
                                <label for="profile_image">Profile Image URL</label>
                              </div>
                            </div>
                          </div>
                          <div class="row">
                            <div class="col">
                              <div class="form-floating mb-3">
                                <input
                                    type="text"
                                    name="first_name"
                                    id="first_name"
                                    class="form-control"
                                    placeholder="First name"
                                    aria-label="First name"
                                    <c:if test="${not empty user.firstName}">
                                      value="${user.firstName}"
                                    </c:if>
                                />
                                <label for="first_name">First name</label>
                              </div>
                            </div>
                            <div class="col">
                              <div class="form-floating mb-3">
                                <input
                                    type="text"
                                    name="last_name"
                                    id="last_name"
                                    class="form-control"
                                    placeholder="Last name"
                                    aria-label="Last name"
                                    <c:if test="${not empty user.lastName}">
                                      value="${user.lastName}"
                                    </c:if>
                                />
                                <label class="form-label" for="last_name">Last name</label>
                              </div>
                            </div>
                          </div>
                          <fieldset disabled="disabled">
                            <div class="row mt-3">
                              <div class="col">
                                <div class="form-floating mb-3">
                                  <input
                                      name="userName"
                                      id="userName"
                                      type="text"
                                      class="form-control"
                                      placeholder="username"
                                      value="${userName}" />
                                  <label class="form-label" for="userName">username</label>
                                </div>
                              </div>
                              <div class="col">
                                <div class="form-floating mb-3">
                                  <input
                                      type="email"
                                      id="email"
                                      name="email"
                                      value="${email}"
                                      class="form-control"
                                      placeholder="email"
                                      aria-label="email"
                                  />
                                  <label class="form-label" for="email">Email</label>
                                </div>
                              </div>
                            </div>
                          </fieldset>
                          <div class="row mt-3">
                            <div class="col">
                              <label class="form-label" for="about">User Summary</label>
                              <textarea
                                  name="about"
                                  id="about"
                                  cols="30"
                                  rows="10"
                                  class="form-control"
                                  style="height: 109px;"
                              ><c:if test="${not empty user.summary}">${user.summary}</c:if></textarea>
                            </div>
                          </div>

                          <div class="row mt-3">
                            <div class="col d-flex justify-content-end">
                              <button class="btn btn-primary" type="submit">Save Changes</button>
                            </div>
                          </div>
                        </form>
                      </div>
                    </div>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
      </c:when>
      <c:otherwise>
        <!-- otherwise user has been added to db - display user profile -->
        <div class="row flex-lg-nowrap">
          <div class="col">
            <div class="row container mx-auto">
              <div class="col mb-3">
                <div class="card">
                  <div class="card-body">
                    <div class="e-profile">
                      <div class="row">
                        <div class="col-12 col-sm-auto mb-3">
                          <div class="mx-auto" style="width: 120px">
                            <div
                                class="d-flex justify-content-center align-items-center rounded"
                                style="height: 140px; background-color: rgb(233, 236, 239)"
                            >
                              <img src="${user.profileImage}" style="height: 140px; width: 120px" alt="" />
                            </div>
                          </div>
                        </div>
                        <div class="col d-flex flex-column flex-sm-row justify-content-between mb-3">
                          <div class="text-center text-sm-left mb-2 mb-sm-0">
                            <c:if test="${not empty user.firstName}">
                              <h4 class="pt-sm-2 pb-1 mb-0 text-nowrap">${user.firstName} ${user.lastName}</h4>
                            </c:if>
                            <p class="mb-0 text-start">@${userName}</p>
                          </div>
                          <div class="text-center text-sm-right">
                            <c:if test="${not empty user.dateCreated}">
                              <div class="text-muted">
                                <small>Joined
                                  <tags:localDate date="${user.dateCreated}" pattern='${"MMM d, yyyy"}'/>
                                </small>
                              </div>
                            </c:if>

                          </div>
                        </div>
                      </div>
                      <div class="pt-3">
                        <section class="">
                          <div class="row">
                            <div class="col">
                              <p class="user-info p-3">${user.firstName}</p>
                            </div>
                            <div class="col">
                              <p class="user-info p-3">${user.lastName}</p>
                            </div>
                          </div>
                          <fieldset disabled="disabled">
                            <div class="row mt-3">
                              <div class="col">
                                <p class="user-info p-3">${user.userName}</p>
                              </div>
                              <div class="col">
                                <p class="user-info p-3">${user.email}</p>
                              </div>
                            </div>
                          </fieldset>
                          <div class="row mt-3">
                            <div class="col">
                              <h3 class="fw-light">User Summary</h3>
                              <p class="user-info p-3">${user.summary}</p>
                            </div>
                          </div>
                          <div class="row mt- mb-3">
                            <div class="col d-flex justify-content-end">
                              <a class="btn btn-primary me-2"
                                 href="<%=request.getContextPath()%>/display_edit_profile"
                              >Edit Profile</a>
                              <a
                                  type="button"
                                  class="btn btn-outline-warning"
                                  data-bs-toggle="modal"
                                  data-bs-target="#confirmDeleteModal"
                              >Delete Profile</a>
                            </div>
                          </div>
                        </section>
                      </div>
                    </div>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
      </c:otherwise>
    </c:choose>
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
            <p class="lead">Are you sure you want to delete this profile?</p>
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
                href="<%=request.getContextPath()%>/delete_profile"
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
</body>
</html>
