<%@ page import="mvc.models.User" %>
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
Current user:
<c:out value="${login}"></c:out>
Current role:
<c:out value="${UserRole}"></c:out>

<c:if test="${UserRole=='ADMIN'}">
    <form action="${pageContext.request.contextPath}/" method=post>
        <input type=hidden name=whatToDo value="add"/>
        Name : <input type=text name=name><br/>
        Login: <input type=text name=login><br/>
        Email: <input type=text name=email><br/>
        Role : <input list="roles">
        <datalist id="roles">
            <c:forEach items="${Roles}" var="role">
            <option value="${role.name}">
                </c:forEach>
        </datalist>
        <br/>
        <input type=submit value="--------- Add user ---------"/>
    </form>
</c:if>
<br/>

<form action="${pageContext.request.contextPath}/signout" method=post>
    <input type=submit value="--------- EXIT ---------"/>
</form>

<table style="border: solid blue;" cellpadding="1" cellspacing="1" border="1">
    <tr>
        <th>Login</th>
        <th>Name</th>
        <th>Email</th>
        <th>Role</th>
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
                <c:out value="${user.role}"></c:out>
            </td>

            <%--<c:set var = "viewAll" scope = "session" value = "${UserRole=='ADMIN'}"/>--%>
            <%--<c:set var = "viewOnleHim" scope = "session" value = "${user.login==login}"/>--%>

            <c:if test="${user.login==login || UserRole=='ADMIN'}">
            <td>
                <form action="${pageContext.request.contextPath}/updateusersmvc" method=post>
                    <input type=hidden name=whatToDo value="update"/>
                    <input type=hidden name=status value="redirectToDoGet"/>
                    <input type=hidden name=login value= <c:out value="${user.login}"></c:out>>
                    <input type=submit value="Update user"/>
                </form>
            </td>

            <td>
                <form action="${pageContext.request.contextPath}/" method=post>
                    <input type=hidden name=whatToDo value="delete"/>
                    <input type=hidden name=login value=<c:out value="${user.login}"></c:out>>
                    <input type=submit value="Delete user"/>
                </form>
            </td>
            </c:if>
        </tr>
    </c:forEach>

</table>

</body>

</html>
