package com.my.web.jdbc;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentDBUtil {

    private final DataSource dataSource;

    public StudentDBUtil(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public List<Student> getStudents() {
        List<Student> students = new ArrayList<>();

        try (// get a connection
             Connection con = dataSource.getConnection();
             // create SQL statement
             Statement stmt = con.createStatement();
             // execute query
             ResultSet rs = stmt.executeQuery("SELECT * FROM students ORDER BY lastname;")) {

            while (rs.next()) {

                // retrieve data from the result set row
                int id = rs.getInt("id");
                String firstName = rs.getString("firstName");
                String lastName = rs.getString("lastName");
                String email = rs.getString("email");

                // create new student object
                Student student = new Student(id, firstName, lastName, email);

                // add it to the list of students
                students.add(student);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return students;
    }

    public void addStudent(Student student) {
        try (// get a connection
             Connection con = dataSource.getConnection();
             // create SQL statement
             PreparedStatement stmt = con.prepareStatement("INSERT INTO students (id, firstname, lastname, email)" +
                     " VALUES (?, ?, ?, ?);");) {
            stmt.setInt(1, student.getId());
            stmt.setString(2, student.getFirstName());
            stmt.setString(3, student.getLastName());
            stmt.setString(4, student.getEmail());

            stmt.executeUpdate();
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    public Student getStudents(String id) {
        Student student = null;
        try (Connection con = dataSource.getConnection();
             PreparedStatement pstmt = con.prepareStatement("SELECT * FROM students WHERE id = ?;");) {

            pstmt.setInt(1, Integer.parseInt(id));
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                student = new Student(rs.getInt("id"), rs.getString("firstname"),
                        rs.getString("lastname"), rs.getString("email"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return student;
    }

    public void updateStudent(Student student) {

        try(Connection con = dataSource.getConnection();
        PreparedStatement pstmt = con.prepareStatement("UPDATE students SET firstname = ?, lastname = ?, email = ?" +
                " WHERE id = ?;");) {

            pstmt.setString(1, student.getFirstName());
            pstmt.setString(2, student.getLastName());
            pstmt.setString(3, student.getEmail());
            pstmt.setInt(4, student.getId());

            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteStudent(String id) {
        try(Connection con = dataSource.getConnection();
            PreparedStatement pstmt = con.prepareStatement("DELETE FROM students WHERE id = ?;");) {

            pstmt.setInt(1, Integer.parseInt(id));

            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
