package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import common.JDBCTemplate;
import dto.Attachment_profile;
import dto.User_info;
import service.face.MypageService;
import service.impl.MypageServiceImpl;

/**
 * Servlet implementation class MypageLoginController
 */
@WebServlet("/login")
public class MypageLoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	private MypageService mypageService = new MypageServiceImpl();
	
	
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

	req.getRequestDispatcher("/WEB-INF/my/login.jsp").forward(req, resp);
	
	
	}
	
	
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		
		//전달파라미터 얻기 - 로그인 정보
		User_info user_info = mypageService.getLoginUser_info(req);
	
		//로그인 인증
		boolean login = mypageService.login(user_info);
		
		if(login) {
			
			user_info = mypageService.info(user_info);
		
			int user_no = user_info.getUser_no();
			
			System.out.println(user_info);
			
			
			//세션정보 저장하기
			HttpSession session = req.getSession();
			session.setAttribute("login", login);
//			session.setAttribute("user_email", user_info.getUser_email());
//			session.setAttribute("user_nickname", user_info.getUser_nickname());
			
			session.setAttribute("user_info", user_info);
			
			
			Attachment_profile attachment_profile = mypageService.getFile(user_no);
			
			session.setAttribute("attachment_profile", attachment_profile);
			
			
			session.setMaxInactiveInterval(60 * 30); 
		}
		
		
		resp.sendRedirect("/main");
		
		
	
	}
	
	
	
	
	
}
