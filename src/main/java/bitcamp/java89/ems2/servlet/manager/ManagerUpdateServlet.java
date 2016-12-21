package bitcamp.java89.ems2.servlet.manager;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bitcamp.java89.ems2.dao.impl.ManagerMysqlDao;
import bitcamp.java89.ems2.dao.impl.MemberMysqlDao;
import bitcamp.java89.ems2.domain.Manager;

@WebServlet("/manager/update")
public class ManagerUpdateServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response) 
      throws ServletException, IOException {

    request.setCharacterEncoding("UTF-8");
    
    Manager manager = new Manager();
    manager.setMemberNo(Integer.parseInt(request.getParameter("memberNo")));
    manager.setEmail(request.getParameter("email"));
    manager.setPassword(request.getParameter("password"));
    manager.setName(request.getParameter("name"));
    manager.setTel(request.getParameter("tel"));
    manager.setPosition(request.getParameter("position"));
    manager.setFax(request.getParameter("fax"));
    manager.setPhotoPath(request.getParameter("photoPath"));
    
    response.setHeader("Refresh", "1;url=list");
    response.setContentType("text/html;charset=UTF-8");
    PrintWriter out = response.getWriter();
    
    out.println("<!DOCTYPE html>");
    out.println("<html>");
    out.println("<head>");
    out.println("<meta charset='UTF-8'>");
    out.println("<title>매니저관리-변경</title>");
    out.println("</head>");
    out.println("<body>");
    out.println("<h1>매니저 결과</h1>");
    
    try {
      ManagerMysqlDao managerDao = ManagerMysqlDao.getInstance();
      
      if (!managerDao.exist(manager.getMemberNo())) {
        throw new Exception("사용자를 찾지 못했습니다.");
      }
      
      MemberMysqlDao memberDao = MemberMysqlDao.getInstance();
      memberDao.update(manager);
      managerDao.update(manager);
      
      out.println("<p>변경 하였습니다.</p>");
      
    } catch (Exception e) {
      out.printf("<p>%s</p>\n", e.getMessage());
    }
    
    out.println("</body>");
    out.println("</html>");
  }
}