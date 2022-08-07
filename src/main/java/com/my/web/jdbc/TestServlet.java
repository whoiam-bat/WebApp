package com.my.web.jdbc;

import javax.annotation.Resource;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

@WebServlet("/test-servlet")
public class TestServlet extends HttpServlet {

    // Define datasource/connection pool for Resource Injection
    @Resource(name="jdbc/web_student_tracker")
    private DataSource dataSource;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws  IOException {

        // Step 1: Set up the printwriter
        PrintWriter out = resp.getWriter();
        resp.setContentType("text/plain");

        // Step 2: Get a connection to the database
        Connection con;
        Statement stmt;
        ResultSet res;

        try {
            // Step 3: Create a SQL statements
            con = dataSource.getConnection();
            String sql = "SELECT * FROM students;";
            stmt = con.createStatement();
            // Step 4: Execute SQL query
            res = stmt.executeQuery(sql);

            // Step 5: Process the result set
            while(res.next()) {
                String email = res.getString("email");
                out.println(email);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
