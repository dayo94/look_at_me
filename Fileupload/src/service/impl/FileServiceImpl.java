package service.impl;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import common.JDBCTemplate;
import dao.face.FileDao;
import dao.impl.FileDaoImpl;
import dto.ParamData;
import dto.UploadFile;
import service.face.FileService;

public class FileServiceImpl implements FileService {

	//DAO객체
	private FileDao fileDao = new FileDaoImpl();
	
	
	
	
	@Override
	public void fileupload(HttpServletRequest req, HttpServletResponse resp) {
	
		System.out.println("FileService - fileupload()");
		
		//응답객체 설정하기
		resp.setContentType("text/html; charset=UTF-8");

		//응답출력스트림 객체얻기
		PrintWriter out = null;
		
		try {
			out = resp.getWriter();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		//----------------------------------------------------------
		


		//1.파일업로드형식이 맞는지 검사
		// -> 요청메시지의 contest-type이 멀티파트 폼데이터인지 확인한다
		
		
		boolean isMultipart = ServletFileUpload.isMultipartContent(req);
		
		//1-1. 멀티 그게 아니면 파일업로드처리 중단하기
		
		
		if(!isMultipart) {
			
			out.append("<h1>enctype이 멀티그게 아닙니다<h1>");
			out.append("<a href='/commons/fileupload'>업로드페이지로이동하기</a>");
			
			//fileupload() 메소드 중단하기
			return;
			
		}
		
		//1-2. 정상 처리하기
		
		//fileitem
		// 클라이언트에서 전송된 데이터를 객체로 만든것
		//	 폼필드(인풋태그 데이터), 파일점부를 객체화시킨다
		
		// FileItemFactory
		//업로드도니 데이터(fileitem)를 처리하는 방식(생성하는방식) 을 설정하는 클래스
		
		//DiskFileItemFactory
		// 디스크(HDD)를 기반으로 파일 아이템을 처리하는 팩토리 클래스
		// 업로드된 파일을 디스크에 임시저장하여 처리하도록 되어있따
		
		
		DiskFileItemFactory factory = new DiskFileItemFactory();
		
		
		//3. 업로드 된 파일 아이템의 용량이 설정값보다 작으면 메모리에서 처리한다
		
		int maxMem = 1 * 1024 * 1024; //1mb
		
		factory.setSizeThreshold(maxMem);
		
		
		//4. 파일아이템의 용량이 설정값보다 크면 임시파일( HDD처리)을 만들어서 처리한다
		// -> 임시파일을 저장할 폴더를 설정할 수 있다
		
		//서블릿컨텍스트 객체
		ServletContext context = req.getServletContext();
		
		//서버가 배포된(설치된) 폴더의 실제 경로에서 tmp폴더를 나타내기
		String path = context.getRealPath("tmp");
		
		//tmp폴더 경로 확인
		System.out.println(path);
		
		//임시저장폴더 객체
		File tmpRepository = new File(path);
		
		//임시저장폴더생성
		tmpRepository.mkdir();
		
		//임시파일을 저장하는 폴더를 팩토리 객체에 설정하기
		factory.setRepository(tmpRepository);
		
		//----------------------------------------------------
		
		//5. 파일업로드를 수행할 객체 생성
//		 ->업로드된 파일이 제한용령을 넘으면 업로드되지않도록 설정

		
		//파일업로드를 수행하는 객체 생성
		// DiskFileItemFactory객체에 젖아해놓은 설정값을 사용한다
		ServletFileUpload upload = new ServletFileUpload(factory);
		
		//최대 업로드 허용 사이즈
		int maxFile = 10 * 1024 * 1024; //10mb
		
		
		//파일 업로드 용량제한 사이즈 설정
		upload.setFileSizeMax(maxFile);
		
		//-----------------------------------------------------------
		
//		파일업로드 준비완료
		
		//-----------------------------------------------------------

		
		//칵테일 정보 받아서 디비에 넣는거처럼!디비에 폼정보 넣기
		
		
		//업로드된 데이터 추출하기 (파싱)
		// -> 임시파일업로드도 같이 수행된다
		
		List<FileItem> items = null; 
		
		try {
			//요청객체에 담겨있는 전달파라미터들을 파싱한다
			items = upload.parseRequest(req);
		} catch (FileUploadException e) {
			e.printStackTrace();
		}
		

//		for(FileItem item : items) {
//			System.out.println(item);
//			
//		}
		
		
		//-----------------------------------------------------------
		
		//7. 파싱(추출)된 전달파라미터 데이터 처리하기
		// -> List<FileItem>객체에 파일과 폼필드데이터가 파싱되러 들어있음
		
		// -> 요청정보의 3가지 상태 고려하기
		// 1. 빈파일(용량이0인 파일)
		// 2. 폼데이터 (폼필드, 전달 파라미터)
		// 3. 파일
		
		//파일아이템 반복자
		
		Iterator<FileItem> iter = items.iterator();
		//폼필드 전달데이터를 저장할 DTO객체 생성
		ParamData paramData = new ParamData();
		
		UploadFile uploadFile = new UploadFile();
		
		
		
		while(iter.hasNext()) {
			
			FileItem item = iter.next();
			
			//1. 빈파일에 대한 처리
			if(item.getSize() <= 0 ) {
				continue; //빈 파일은ㅇ 무시하고 다음 FileItem처리로 넘긴다
				
			}
			//2. 폼데이터에 대한 처리
			
			
			if( item.isFormField()) {
				
				//폼데이터 처리방법
				//폼필드는 키=밸류 쌍으로 전달된다
				
				//key는 item.getFieldName()메소드로 얻어온다
				//value는 item.getString()메소드로 얻어온다
				
				// ** req.getCharacterEncoding("UTF-8"); 코드가 적용되지 않는다
				//여기에는 적용되지 않는다
				
				//item.getString("UTF-8"); 방식으로 사용해야 한글 인코딩이 적용된다
				
				// 기본 처리 방식
//				
//				out.println("---폼필드--- <br>");
//				out.println("키 : " + item.getFieldName() + "<br>");
//				try {
//					out.println("값 : " + item.getString("UTF-8") + "<br>");
//				} catch (UnsupportedEncodingException e) {
//					e.printStackTrace();
//				}
//				
				
				//키값(전달파라미터 name) 에 따른 처리 방식
				//최종적으로 추출된 데이터를 DTO객체에 저장한다
				
				//키 추출하기
				
				String key = item.getFieldName();
				
				
				//값 추출하기
				String value = null;
				
				try {
					value = item.getString("UTF-8");
				} catch (UnsupportedEncodingException e1) {
					e1.printStackTrace();
				}
				
				
				if( "title".equals(key) ) {
						paramData.setTitle(value);
				} else if ( "data1".equals(key) ) {
					paramData.setData1( value );
					
				} else if ( "data2".equals(key) ) {
					paramData.setData2( value );
					
				}
				
			}//if( item.isFormField() ) end
			
			
			
			
			
			//3. 파일에 대한 처리
		
			if ( !item.isFormField() ) {
				
				//--- 업로드된 파일을 처리(보관)하는 방법 ---
				
				//1. 파일을 웹서버의 로컬디스트(HDD)에 저장한다
				//	파일의 저장위치(path)를 DB에 기록한다
				
				//2. 테이블의 컬럼으로 파일의 내용을 저장한다
				//	BLOB타입의 컬럼을 만들어서 사용한다
				
				
				//**1번만

				//---------------------------------
				
				//--- 업로드된 파일의 이름을 시간을 이용해서 변경하기 ---
				// -> 업로드된 파일이 저장될 때 중복된 이름을 사용하지 않도록 시간ㅇ르이용해 조절한다

				// -> "년월일시분초밀리초.확장자" 로 변경한다
			
				// java.util.Date -> String 변환
				// -> SimpleDateFormat 클래스 이용
				SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssS");
				String rename = sdf.format(new Date());
				
				//확장자는 원본파일 확장자로 얻어온다
				String origin = item.getName(); //원본파일의 이름
				int dotIdx = origin.lastIndexOf("."); // 뒤쪽에서 부터 찾은"."의 인덱스
				
				//확장자
				String ext = origin.substring(dotIdx + 1);
				
				//서버에 저장할 파일의 이름
				String stored = rename + "." + ext;
				
				
				System.out.println("[TEST] 원본 파일명 : " + origin);
				System.out.println("[TEST] 저장될 파일명 : " + stored);
			
				
			
				//--- 업로드 된 임시파일을 실제 보관(저장소)에 옮기기 ---
				// 1mb이하 파일은 메모리에 임시파일이 저장되어있음
				// 이상 10이하 파일은 HDD에 임시파일이 저장되어있음
				
				File uploadFolder = new File(context.getRealPath("upload"));
				uploadFolder.mkdir(); //폴더생성
				
				//실제로 저장될 파일 객체
				File up = new File(uploadFolder, stored);
				
				try {
					item.write(up);  //실제 업로드(임시파일을 최종결과파일로 생성함)
					item.delete(); //임시파일 삭제 (안해도 알아서 지워줌 가끔 안돼서 써줘야함)
				} catch (Exception e) {
					e.printStackTrace();
				}
				
				//-------------------------------------------------------
				
				//---업로드된 파일의 정보를 DTO객체에 저장하기
				uploadFile.setOrginName(origin);
				uploadFile.setStoredName(stored);
				
				
			}//if
		
		} //while end
		
		System.out.println("[TEST]" + paramData);
		System.out.println("[TEST]" + uploadFile);
		
		
		
		//--- DB에 최종 데이터 삽입하기 ---
		
		Connection conn = JDBCTemplate.getConnection();
		
		int res = 0;
		
		//파라미터 데이터 삽입
		
		res = fileDao.insertParam(conn, paramData);
		if( res > 0 ) {
			JDBCTemplate.commit(conn);
		} else {
			JDBCTemplate.rollback(conn);
		}
		
		
		//파일 데이터 삽입
		
		res = fileDao.insertParam(conn, uploadFile);
		if( res > 0 ) {
			JDBCTemplate.commit(conn);
		} else {
			JDBCTemplate.rollback(conn);
		}
		
		//--------------------------
		
		
		
	}//fileupload end
	
	
}//class
