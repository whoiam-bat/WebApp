package com.my.web.jdbc;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class StudentDBUtil {

    private final DataSource dataSource;

    public StudentDBUtil(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public List<Student> getStudents() {
        List<Student> students = new ArrayList<>();

        try(// get a connection
            Connection con = dataSource.getConnection();
            // create SQL statement
            Statement stmt = con.createStatement();
            // execute query
            ResultSet rs = stmt.executeQuery("SELECT * FROM students ORDER BY lastname;")) {

            while(rs.next()) {

                // retrieve data from the result set row
                int id = rs.getInt("id");
                String firstName = rs.getString("firstName");
                String lastName = rs.getString("lastName");
                String email = rs.getString("email");

                // create new student object
                Student student =  new Student(id, firstName, lastName, email);

                // add it to the list of students
                students.add(student);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return students;
    }

}
