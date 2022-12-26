<%@page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>Sign In</title>
</head>
<body>
  <h2>Sign into your account</h2>

  <form method="post">
    <input type="email" name="signInEmail" id="signInEmail" placeholder="Email" required/>
    <label class="form-label" for="signInEmail">Email</label>
    <br>
    <input type="password" name="signInPassword" id="signInPassword" class="form-control" placeholder="Password" required/>
    <label class="form-label" for="signInPassword">Password</label>
    <br>
    <button type="submit">Sign in</button>
  </form>

  <p class="small fw-bold mt-2 pt-1 mb-0">Don't have an account?
    <a href="<c:url value="/signup"/>">Register</a>
  </p>
</body>
</html>
