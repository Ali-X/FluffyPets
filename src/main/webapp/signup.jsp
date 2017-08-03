<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Sign Up</title>
  <link rel="stylesheet" href="css/BasicRed.css">
</head>
<body>
<div class="container">
  <div class="row">
    <div class="col-md-offset-4 col-md-4">
      <form method="POST" action="/servlet/signup" class="well">

        <div class="form-group">
          <label class="control-label">User Name</label>
          <input id="userName" type="text" class="form-control" name="userName">
        </div>

        <div class="form-group">
          <label class="control-label">Email</label>
          <input type="email" class="form-control" name="email">
        </div>

        <div class="form-group">
          <label class="control-label">Password</label>
          <input type="password" class="form-control" name="password">
        </div>

        <div class="form-group">
          <button class="btn btn-primary">Register</button>
        </div>
      </form>
    </div>
  </div>
</div>
</body>
</html>