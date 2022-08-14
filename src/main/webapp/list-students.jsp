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

        <!-- put new button: Add student -->
        <input type="button" value="Add Student"
               onclick="window.location.href='add-student-form.jsp'; return false;"
               class="add-student-button"
        />

        <table>
            <tr>
                <th>First Name</th>
                <th>Last Name</th>
                <th>Email</th>
                <th>Action</th>
            </tr>

            <c:forEach var="it" items="${STUDENT_LIST}">

                <!-- set up a link for each student -->
                <c:url var="tempLink" value="controller">
                    <c:param name="command" value="LOAD"/>
                    <c:param name="studentID" value="${it.id}"/>
                </c:url>

                <!-- set up a link to delete a student -->
                <c:url var="deleteLink" value="controller">
                    <c:param name="command" value="DELETE"/>
                    <c:param name="studentID" value="${it.id}"/>
                </c:url>

                <tr>
                    <td> ${it.firstName} </td>
                    <td> ${it.lastName} </td>
                    <td> ${it.email} </td>
                    <td> <a href="${tempLink}">Update</a> | <a href="${deleteLink}"
                    onclick="if(!(confirm('Are you sure you want to delete this student?'))) return false">Delete</a> </td>
                </tr>
            </c:forEach>
        </table>
    </div>
</div>

</body>
</html>
