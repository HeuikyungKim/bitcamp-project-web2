package bitcamp.java89.ems2.servlet.teacher;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bitcamp.java89.ems2.dao.impl.TeacherMysqlDao;
import bitcamp.java89.ems2.domain.Teacher;



@WebServlet("/teacher/list")
public class TeacherListServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response) 
      throws ServletException, IOException {

    response.setContentType("text/html;charset=UTF-8");
    PrintWriter out = response.getWriter();

    out.println("<!DOCTYPE html>");
    out.println("<html>");
    out.println("<head>");
    out.println("<meta charset='UTF-8'>");
    out.println("<title>강사관리-목록</title>");
    out.println("</head>");
    out.println("<body>");
    out.println("<h1>강사 정보</h1>");
    try {
      TeacherMysqlDao teacherDao = TeacherMysqlDao.getInstance();
      ArrayList<Teacher> list = teacherDao.getList();

      out.println("<a href='form.html'>추가</a><br>");
      out.println("<table border='1'>");
      out.println("<tr>");
      out.println("  <th>회원번호</th>");
      out.println("  <th>이름</th>");
      out.println("  <th>전화</th>");
      out.println("  <th>홈페이지</th>");
      out.println("  <th>페이스북</th>");
      out.println("  <th>트위터</th>");
      out.println("</tr>");

      for (Teacher teacher : list) {
        out.println("<tr> ");
        out.printf("  <td>%d</td>"
            + "<td><a href='detail?memberNo=%1$d'>%s</a></td>"
            + "<td>%s</td>"
            + "<td>%s</td>"
            + "<td>%s</td>"
            + "<td>%s</td>\n",
            teacher.getMemberNo(),
            teacher.getName(),
            teacher.getTel(),
            teacher.getHomepage(),
            teacher.getFacebook(),
            teacher.getTwitter());
        out.println("</tr>");
      }

      out.println("</table>");
      out.println("</body>");
      out.println("</html>");

    } catch (Exception e) {
      throw new ServletException(e);
    }

  }
}