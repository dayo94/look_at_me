package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import dto.Member;

@WebServlet("/ajax/test01")
public class AjaxTestController01 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("/ajax/test01");
		
		request.getRequestDispatcher("/WEB-INF/views/ajax/test01.jsp")
			.forward(request, response);
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("/ajax/test01 [POST]");
		
		//응답 데이터 형식 지정하기 (MIME타입)
//		resp.setContentType("text/html; charset=utf-8"); //HTML응답
		resp.setContentType("application/json; charset=utf-8"); //JSON응답
		
		//응답 출력 스트립
		PrintWriter out = resp.getWriter();
		
		//----------------------------------------------------------
		
//		out.println("\"Response Data Send\""); //단순 문자열 전송
		
		//JSON Text로 String데이터 전송
//		out.println(new Gson().toJson("Response Data Send"));
		
		//----------------------------------------------------------
		
		//JSON Text로 직접 작성하여 전송 (객체)
//		out.println("{\"data\":\"JSON DATA\", \"count\": 123}");
		
		//----------------------------------------------------------
		
//		//응답 데이터로 사용될 map 객체 생성
//		HashMap<String, Object> map = new HashMap<>();
//		
//		map.put("data", "JSON MAP DATA");
//		map.put("count", 456);
//		
//		//map객체를 JSON Text로 응답 (Gson라이브러리이용한 마샬링)
//		out.println(new Gson().toJson(map));
		
		//----------------------------------------------------------
		
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
		
		//list를 JSON Text로 응답
		out.println(new Gson().toJson(list));
		
	}

}














