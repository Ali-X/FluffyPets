<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Profile</title>
  <link rel="stylesheet" href="css/BasicRed.css">
  <style>
    .h100 {
      height: 100%;
    }
  </style>
</head>
<body>

<div class="container-fluid">

  <div class="row">
    <div class="col-md-4 navbar-default h100">
      <div class="pull-right">
        <h1>Operations</h1>
        <ul class="nav">
          <li><a href="#">Categories</a></li>
          <li><a href="#">Projects</a></li>
          <li><a href="#">Blog</a></li>
          <li><a href="#">Forum</a></li>
        </ul>
      </div>
    </div>
    <div class="col-md-6">

      <div>
        <h2>Create Categories</h2>

        <form class="form-horizontal well" action="/servlet/category" method="POST">

          <div class="form-group">
            <label class="control-label" for="categoryName">Category Name</label>
            <input type="text" class="form-control" name="categoryName">
          </div>

          <div class="form-group">
            <button class="btn btn-primary" type="submit">Submit</button>
          </div>

        </form>
      </div>
    </div>
  </div>

</div>
</body>
</html>