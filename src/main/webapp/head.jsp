<nav-menu>
  <div class="navbar navbar-inverse">
    <div class="container">
      <a href="/servlet/" class="navbar-brand">KickStarter</a>
      <ul class="nav navbar-nav navbar-right">

        <c:choose>

          <c:when test="${isLoggedIn}">
            <li><a href="/servlet/profile"><c:out value="${username}"/></a></li>
          </c:when>

          <c:otherwise>
            <li><a href="/servlet/signup">Sign Up</a></li>
          </c:otherwise>
        </c:choose>

      </ul>
    </div>
  </div>
</nav-menu>