package bitcamp.java89.ems2.servlet.teacher;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bitcamp.java89.ems2.dao.impl.MemberMysqlDao;
import bitcamp.java89.ems2.dao.impl.TeacherMysqlDao;
import bitcamp.java89.ems2.domain.Member;
import bitcamp.java89.ems2.domain.Teacher;

@WebServlet("/teacher/add")
public class TeacherAddServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response) 
      throws ServletException, IOException {

    request.setCharacterEncoding("UTF-8");

    Teacher teacher = new Teacher();

    teacher.setEmail(request.getParameter("email"));
    teacher.setPassword(request.getParameter("password"));
    teacher.setName(request.getParameter("name"));
    teacher.setTel(request.getParameter("tel"));
    teacher.setHomepage(request.getParameter("homepage"));
    teacher.setFacebook(request.getParameter("facebook"));
    teacher.setTwitter(request.getParameter("twitter"));

    response.setHeader("Refresh", "1;url=list");
    response.setContentType("text/html;charset=UTF-8");
    PrintWriter out = response.getWriter();

    out.println("<!DOCTYPE html>");
    out.println("<html>");
    out.println("<head>");
    out.println("<meta charset='UTF-8'>");
    out.println("<title>강사관리-등록</title>");
    out.println("</head>");
    out.println("<body>");
    out.println("<h1>등록 결과</h1>");

    try {
      TeacherMysqlDao teacherDao = TeacherMysqlDao.getInstance();

      if (teacherDao.exist(teacher.getEmail())) {
        throw new Exception("같은 사용자 아이디가 존재합니다. 등록을 취소합니다.");
      }
      
      MemberMysqlDao memberDao = MemberMysqlDao.getInstance();
      
      if (!memberDao.exist(teacher.getEmail())) { // 강사나 매니저로 등록되지 않았다면,
        memberDao.insert(teacher);
      } else {  // 이미 등록된 사용자라면 기존의 회원번호를 사용한다.
        Member member = memberDao.getOne(teacher.getEmail());
        teacher.setMemberNo(member.getMemberNo());
      }

      teacherDao.insert(teacher);
      out.println("<p>등록하였습니다.</p>");

    } catch (Exception e) {
      out.printf("<p>%s</p>\n", e.getMessage());
    }

    out.println("</body>");
    out.println("</html>");
  }
}