<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
<html>
<head>
  <link rel="stylesheet" href="<c:url value="/css/BasicRed.css"/>">
  <style>

    .row {
      margin-right: 0;
      margin-left: 0;
    }

    .side-menu .navbar-nav {
      height: 100%;
    }

    .side-menu .navbar-nav li {
      display: block;
      width: 100%;
    }

  </style>
</head>
<body>

<%@include file="head.jsp"%>

<div class="container">

  <div class="row">
    <div class="jumbotron">
      <h1>Welcome to kickstarter!</h1>
      <p>...</p>
      <p><a class="btn btn-primary btn-lg" href="#" role="button">Learn more</a></p>
    </div>
  </div>

  <div class="row">

    <c:forEach var="c" items="${categories}">

      <div class="col-md-4">
        <div class="thumbnail">
          <a href="#">
            <div class="caption">
              <h3><c:out value="${c.name}"/></h3>
            </div>
          </a>
        </div>
      </div>

    </c:forEach>

  </div>
</div>

</body>
</html>