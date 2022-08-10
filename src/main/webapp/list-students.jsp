<%--
  Created by IntelliJ IDEA.
  User: Oleksii_Drabchak
  Date: 8/9/2022
  Time: 4:03 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>

<html>
<head>
    <title>Student Tracker App</title>
    <link type="text/css" rel="stylesheet" href="css/style.css">
</head>

<body>
<div id="wrapper">
    <div id="header">
        <h2>FooBar University</h2>
    </div>
</div>

<div id="container">
    <div id="content">
        <table>
            <tr>
                <th>First Name</th>
                <th>Last Name</th>
                <th>Email</th>
            </tr>

            <c:forEach var="it" items="${STUDENT_LIST}">
                <tr>
                    <td> ${it.firstName} </td>
                    <td> ${it.lastName} </td>
                    <td> ${it.email} </td>
                </tr>
            </c:forEach>
        </table>
    </div>
</div>

</body>
</html>
