<%@ page import="jsp.User" %>
<%@ page import="jsp.UserStore" %><%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 11.01.2018
  Time: 15:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>

<body>

<form action="<%=request.getContextPath()%>/addusersjsp" method=post>
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
    <%for (User user : UserStore.getInstance().getAllUsers()) {%>
    <tr>
    <td>
        <%=user.getLogin()%>
    </td>

    <td>
        <%=user.getName()%>
    </td>

    <td>
        <%=user.getEmail()%>
    </td>

    <td>
        <form action="<%=request.getContextPath()%>/updateusersjsp" method=post>
            <input type=hidden name=status value="redirectToDoGet"/>
            <input type=hidden name=login value=<%=user.getLogin()%>/>
            <input type=submit value="Update user"/>
        </form>
    </td>

    <td>
        <form action="<%=request.getContextPath()%>/deleteusersjsp" method=post>
            <input type=hidden name=login value=<%=user.getLogin()%>/>
            <input type=submit value="Delete user"/>
        </form>
    </td>
    </tr>
    <%}%>

</table>

</body>

</html>
