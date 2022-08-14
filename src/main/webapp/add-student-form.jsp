<%--
  Created by IntelliJ IDEA.
  User: Oleksii_Drabchak
  Date: 8/10/2022
  Time: 3:30 PM
  To change this template use File | Settings | File Templates.
--%>
<!DOCTYPE html>
<html>
<head>
    <title>Add student form</title>

    <link type="text/css" rel="stylesheet" href="css/style.css">
    <link type="text/css" rel="stylesheet" href="css/add-student-style.css">
</head>
<body>

    <div id="wrapper">
        <div id="header">
            <h2>FooBar University</h2>
        </div>
    </div>


    <div id="container">
        <h3>Add Student</h3>

            <form action="controller" method="get">

                <input type="hidden" name="command" value="ADD"/>

                <table>
                    <tbody>
                        <tr>
                            <td><label>First name:</label></td>
                            <td><input type="text" name="firstName"/></td>
                        </tr>

                        <tr>
                            <td><label>Last name:</label></td>
                            <td><input type="text" name="lastName"/></td>
                        </tr>

                        <tr>
                            <td><label>Email:</label></td>
                            <td><input type="text" name="email"/></td>
                        </tr>

                        <tr>
                            <td><label></label></td>
                            <td><input type="submit" value="Save" class="save"/></td>
                        </tr>
                    </tbody>
                </table>
            </form>

        <div style="clear: both;"></div>
        <a href="controller">Back to list</a>
    </div>

</body>
</html>

