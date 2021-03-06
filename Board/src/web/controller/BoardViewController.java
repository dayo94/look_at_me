package web.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import web.dto.Board;
import web.dto.BoardFile;
import web.service.face.BoardService;
import web.service.impl.BoardServiceImpl;

@WebServlet("/board/view")
public class BoardViewController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	//BoardService 객체 생성
	private BoardService boardService = new BoardServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		//전달파라미터 얻기 - boardno
		Board boardno = boardService.getBoardno(req);

		//상세보기 결과 조회
		Board viewBoard = boardService.view(boardno);
		
		//조회결과 MODEL값 전달
		req.setAttribute("viewBoard", viewBoard);
		
		//닉네임 전달
		req.setAttribute("nick", boardService.getNick(viewBoard));
				
				
		//첨부파일 정보 조회
		BoardFile boardFile = boardService.viewFile(viewBoard);
				
		//첨부파일 정보 MODEL값 전달
		req.setAttribute("boardFile", boardFile);
		
		//VIEW 지정 및 응답 - forward
		req.getRequestDispatcher("/WEB-INF/views/board/view.jsp").forward(req, resp);		

	}
	
}
