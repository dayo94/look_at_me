package service.face;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface FileService {

	/**
	 * 파일 업로드 처리
	 * @param req -요청정보객체
	 * @param resp - 응답정보객체
	 */
	public void fileupload(HttpServletRequest req, HttpServletResponse resp);
	
	
	
	
	
	
	
	
}
