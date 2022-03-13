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
  <title>Otter | Create your Profile</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
  <link rel="stylesheet" href="<%=request.getContextPath()%>/css/style.css">
</head>
<body>
<div class="container py-4" id="outer-container">
    <jsp:include page = "/jsp/components/nav.jsp" />
  <main>
    <div class="row flex-lg-nowrap">
      <div class="col">
        <div class="row">
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
                          <img src="../img/default_profile_pic.jpg" style="height: 140px; width: 120px" alt="" />
                        </div>
                      </div>
                    </div>
                    <div class="col d-flex flex-column flex-sm-row justify-content-between mb-3">
                      <div class="text-center text-sm-left mb-2 mb-sm-0">
                        <c:if test="${not empty user.firstName}">
                          <h4 class="pt-sm-2 pb-1 mb-0 text-nowrap">${user.firstName} ${user.lastName}</h4>
                        </c:if>
                        <p class="mb-0">@${userName}</p>
                        <div class="mt-2">
                          <button class="btn btn-primary" type="button">
                            <i class="fa fa-fw fa-camera"></i>
                            <span>Change Photo</span>
                          </button>
                        </div>
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
                    <form class="form" method="POST" action="/edit_profile" novalidate="">
                      <div class="row">
                        <div class="col">
                          <input
                              type="text"
                              name="first_name"
                              class="form-control"
                              placeholder="First name"
                              aria-label="First name"
                          />
                        </div>
                        <div class="col">
                          <input
                              name="last_name"
                              type="text"
                              class="form-control"
                              placeholder="Last name"
                              aria-label="Last name"
                          />
                        </div>
                      </div>
                      <fieldset disabled="disabled">
                        <div class="row mt-3">
                          <div class="col">
                            <input name="userName" type="text" class="form-control" placeholder="username" value="${userName}" />
                          </div>
                          <div class="col">
                            <input
                                value="${email}"
                                name="email"
                                type="email"
                                class="form-control"
                                placeholder="email"
                                aria-label="email"
                            />
                          </div>
                        </div>
                      </fieldset>
                      <div class="row mt-3">
                        <div class="col">
                          <label for="about">User Summary</label>
                          <textarea name="about" id="about" cols="30" rows="10" class="form-control"></textarea>
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
  </main>

  </div> <!-- end #outer-container -->
  <jsp:include page="/jsp/components/footer.jsp" />
</body>
</html>
