<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="jakarta.tags.core" %>

<html>
<head>
    <title>Title</title>
</head>
<body>

  <form method="get">
    <input type="text" name="query">
    <button type="submit">Find</button>
  </form>

  <c:forEach var="content" items="${requestScope.content}">
    <div>
      <img style="width: 100px; height: 100px" src="<c:url value="/upload-image/${content.id}"/>" class="card-img-top">
      <p>${content.name}</p>
      <p>${content.tag1}</p>
      <p>${content.tag2}</p>
        <form method="post">
            <button type="submit" name="add" value="${content.id}">Like</button>
        </form>
    </div>
  </c:forEach>
</body>
</html>
