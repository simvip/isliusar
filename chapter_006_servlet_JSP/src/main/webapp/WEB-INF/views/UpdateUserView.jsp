<%@ page import="mvc.User" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 11.01.2018
  Time: 15:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="mvc.models.Role" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Title</title>
</head>

<body>
UPDATE USER


<form action="${pageContext.request.contextPath}/" method=post>


    Name : <input type=text value=<c:out value="${user.name}"></c:out>><br/>
    Login: <input type=text value=<c:out value="${user.login}"></c:out>><br/>
    Email: <input type=text value=<c:out value="${user.email}"></c:out>><br/>
    Role : <input list="roles">

    <datalist id="roles">
        <c:if test="${user.role=='ADMIN'}">
        <c:forEach items="${Roles}" var="role">
        <option value="${role.name}">
            </c:forEach>
            </c:if>
            <c:if test="${user.role!='ADMIN'}">
        <option value="${user.role}">
            </c:if>
    </datalist>


    <br/>
    <input type=submit value="--------- Add user ---------"/>
</form>

<br/>

<form action="${pageContext.request.contextPath}/signout" method=post>
    <input type=submit value="--------- EXIT ---------"/>
</form>

</body>

</html>
