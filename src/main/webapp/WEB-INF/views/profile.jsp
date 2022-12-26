<%@page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>Profile</title>
</head>
<body>
  <h2>Profile</h2>

  <c:set var="profile" value="${sessionScope.profile}"></c:set>

  <h5><c:out value="${profile.firstName}"></c:out> <c:out value="${profile.secondName}"/></h5>
  <p><c:out value="${profile.email}"></c:out></p>

  <a href="<c:url value="/signout"/>">Sign out</a>
  <br>

  <h2>Post content</h2>
  <form method="post" enctype="multipart/form-data">
    <input type="text" name="contentName" id="contentName" placeholder="Name" required/>
    <label class="form-label" for="contentName">Name</label>
    <br>
    <c:if test="${sessionScope.profile.isAdmin eq true}">
      <input type="checkbox" name="isNSFW" id="NSFW" class="form-control" placeholder="NSFW" />
      <label class="form-label" for="NSFW">Is NSFW?</label>
    </c:if>
    <br>
    <input type="text" name="tag1" placeholder="TAG1" required>
    <br>
    <input type="text" name="tag2" placeholder="TAG2" required>
    <br>
    <input type="file" name="image">
    <br>
    <br>
    <button type="submit">Post</button>
  </form>

  <br>
  <h2>Favorites</h2>

  <c:forEach var="content" items="${applicationScope.serviceMapper.findContent(sessionScope.profile.id)}">
    <div>
      <img style="width: 100px; height: 100px" src="<c:url value="/upload-image/${content.id}"/>">
      <p>${content.name}</p>
    </div>
  </c:forEach>
</body>
</html>
