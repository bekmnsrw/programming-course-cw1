<%@page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>Sign Up</title>
</head>
<body>
  <h2>Sign up</h2>

  <form method="post">
    <input type="text" name="signUpFirstName" id="signUpFirstName" placeholder="First Name" required />
    <label for="signUpFirstName">First name</label>
    <br>
    <input type="text" name="signUpSecondName" id="signUpSecondName" placeholder="Second Name" required />
    <label for="signUpSecondName">Second name</label>
    <br>
    <input type="email" name="signUpEmail" id="signUpEmail" placeholder="Email" required />
    <label for="signUpEmail">Email</label>
    <br>
    <input type="password" name="signUpPassword" id="signUpPassword" placeholder="Password" required />
    <label for="signUpPassword">Password</label>
    <br>
    <button type="submit">Sign up</button>
  </form>

  <p class="small fw-bold">Already have an account?
    <a href="<c:url value="/signin"/>">Sign In</a>
  </p>
</body>
</html>
