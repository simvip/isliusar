<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 19.01.2018
  Time: 11:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title></title>
</head>
<body>
<c:if test="${error != ''}">
    <div style="background-color: red">
        <c:out value="${error}" />
    </div>
</c:if>
<form action="${pageContext.request.contextPath}/signin" method=post>
    Login    : <input type=text name=login><br/>
    Password : <input type=password name=password><br/>
    <br/>
    <input type=submit value="--------- login ---------"/>
</form>
</body>
</html>
