package com.my.web.jdbc;

import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.IOException;
import java.util.List;

@WebServlet("/controller")
public class StudentControllerServlet extends HttpServlet {
    private StudentDBUtil dbUtil;

    @Resource(name="jdbc/web_student_tracker")
    private DataSource dataSource;

    @Override
    public void init() throws ServletException {
        // create our student db util ... and pass in the connection pool / datasource
        try {
            dbUtil = new StudentDBUtil(dataSource);
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        // read the 'command' parameter
        String command = req.getParameter("command");

        // if the command is missing, then default to listing students
        if(command == null) command = "LIST";

        // route to the appropriate method
        switch (command) {
            case "LIST": {
                // list the students ... in MVC fashion
                listStudents(req, resp);
                break;
            }
            case "ADD": {
                addStudent(req, resp);
                break;
            }
            case "LOAD": {
                loadStudent(req, resp);
                break;
            }
            case "UPDATE": {
                updateStudent(req, resp);
                break;
            }
            case "DELETE": {
                deleteStudent(req, resp);
                break;
            }

            default: {
                listStudents(req, resp);
            }
        }

    }

    private void deleteStudent(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // read student id from form data
        String id = req.getParameter("studentID");

        // add the student to the database
        dbUtil.deleteStudent(id);

        // send back to main page (student list)
        listStudents(req, resp);
    }

    private void updateStudent(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        // read student info from form data
        int id = Integer.parseInt(req.getParameter("id"));
        String firstName = req.getParameter("firstName");
        String lastName = req.getParameter("lastName");
        String email = req.getParameter("email");

        // create a new student object
        Student student = new Student(id, firstName, lastName, email);

        // add the student to the database
        dbUtil.updateStudent(student);

        // send back to main page (student list)
        listStudents(req, resp);
    }

    private void loadStudent(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        // read student id from form data
        String id = req.getParameter("studentID");

        // get student from database
        Student student = dbUtil.getStudents(id);

        // place student in the request attribute
        req.setAttribute("STUDENT", student);

        // send to jsp page: update-student-form.jsp
        RequestDispatcher dispatcher = req.getRequestDispatcher("update-student-form.jsp");
        dispatcher.forward(req, resp);
    }

    private void addStudent(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        // read student info from form data
        String firstName = req.getParameter("firstName");
        String lastName = req.getParameter("lastName");
        String email = req.getParameter("email");

        // create a new student object
        Student student = new Student(firstName, lastName, email);

        // add the student to the database
        dbUtil.addStudent(student);

        // send back to main page (student list)
        listStudents(req, resp);
    }

    private void listStudents(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        // get students from db util
        List<Student> studentList = dbUtil.getStudents();

        // add students to the request
        req.setAttribute("STUDENT_LIST", studentList);

        // send to JSP page (view)
        RequestDispatcher dispatcher = req.getRequestDispatcher("/list-students.jsp");
        dispatcher.forward(req, resp);
    }
}
