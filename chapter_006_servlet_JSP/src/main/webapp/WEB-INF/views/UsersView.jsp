<%@ page import="mvc.models.User" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 11.01.2018
  Time: 15:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Title</title>
</head>

<body>

<form action="${pageContext.request.contextPath}/" method=post>
    <input type=hidden name=whatToDo value="add"/>
    Name : <input type=text name=name><br/>
    Login : <input type=text name=login><br/>
    Email : <input type=text name=email><br/>
    <br/>
    <input type=submit value="--------- Add user ---------"/>
</form>

<table style="border: solid blue;" cellpadding="1" cellspacing="1" border="1">
    <tr>
        <th>Login</th>
        <th>Name</th>
        <th>Email</th>
        <th>Update</th>
        <th>Delete</th>
    </tr>
    <c:forEach items="${Users}" var="user">

        <tr>
            <td>
                <c:out value="${user.login}"></c:out>
            </td>

            <td>
                <c:out value="${user.name}"></c:out>
            </td>

            <td>
                <c:out value="${user.email}"></c:out>
            </td>

            <td>
                <form action="${pageContext.request.contextPath}/updateusersmvc" method=post>
                    <input type=hidden name=whatToDo value="update"/>
                    <input type=hidden name=status value="redirectToDoGet"/>
                    <input type=hidden name=login value= <c:out value="${user.login}"></c:out>/>
                    <input type=submit value="Update user"/>
                </form>
            </td>

            <td>
                <form action="${pageContext.request.contextPath}/" method=post>
                    <input type=hidden name=whatToDo value="delete"/>
                    <input type=hidden name=login value=<c:out value="${user.login}"></c:out>/>
                    <input type=submit value="Delete user"/>
                </form>
            </td>
        </tr>
    </c:forEach>

</table>

</body>

</html>
