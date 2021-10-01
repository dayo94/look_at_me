package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.Member;

@WebServlet("/ajax/test02")
public class AajxTestController02 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("/ajax/test02 [GET]");
		
		req.getRequestDispatcher("/WEB-INF/views/ajax/test02.jsp")
			.forward(req, resp);
		
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("/ajax/test02 [POST]");

		//응답 데이터로 사용될 list 객체 생성
		List<Member> list = new ArrayList<>();
		
		Member m1 = new Member();
		m1.setId("A");
		m1.setPw("Apple");
		
		Member m2 = new Member();
		m2.setId("B");
		m2.setPw("Banana");
		
		Member m3 = new Member();
		m3.setId("C");
		m3.setPw("Cherry");
		
		list.add(m1);
		list.add(m2);
		list.add(m3);
		
		//모델값 지정
		req.setAttribute("list", list);
		
		//VIEW 지정 및 forward
		req.getRequestDispatcher("/WEB-INF/views/ajax/result.jsp")
			.forward(req, resp);
	}	
}













