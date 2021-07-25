package collection.quiz2;

import java.util.HashMap;
import java.util.Set;

public class BookManagerMap implements BookManagerMapInterface {

		//Field
//		- booksMap : HashMap	//도서번호를 키로 사용함
	
	private HashMap<String, Book> booksMap;

		//기본생성자 : 맵 객체 생성함
	
	public BookManagerMap() {
		
		booksMap = new HashMap();

	}
	

		//매개변수있는 생성자 : 다른 맵을 전달받아. 
		//		booksMap 객체의 초기값으로 생성함
	
	public BookManagerMap(HashMap<String, Book> map) {
		
			booksMap = map;
		
	}


	@Override
	public void putBook(Book book) {
		/**
		 * 새 도서 추가
		 * 	맵에 새로운 도서를 추가한다
		 * 
		 * @param book - Book, 새롭게 추가할 책 정보를 담고있는 객체
		 */
//		+ putBook(book:Book) : void	//맵에 객체 추가, 도서번호를 키로 사용함
		booksMap.put(book.getbNo(),book);
		
	}


	@Override
	public void removeBook(String bNo) {
		/**
		 * 도서 삭제
		 * 	책 번호를 전달받아 맵에서 도서 정보를 삭제한다
		 * 
		 * 
		 * @param bNo - String, 삭제할 도서의 이름 
		 */
		booksMap.remove(bNo);
	}


	@Override
	public String searchBook(String bTitle) {
		/**
		 * 도서 정보 검색
		 * 	책 이름을 전달받아 해당 책의 책번호를 반환한다
		 *	존재하지 않는 책이라면 null을 반환한다
		 * 
		 * @param bTitle - String, 검색하려는 책 이름
		 * @return
		 * 	String - 검색된 책번호
		 * 	null - 검색된 책 정보가 존재하지 않을 때
		 */
//		+ searchBook(String bTitle) : String	: entrySet() 사용
//		도서명이 일치하는 객체의 도서번호를 리턴
//		도서명이 객체가 맵에 없으면, null 리턴함		
//		booksMap.entrySet("bTitle",(bTitle,booksMap));
		return null;
	}


	@Override
	public void displayAll() {
		/**
		 * 모든 책의 정보를 출력한다
		 * 	printBook() 활용
		 *  
		 */
//		+ displayAll():void		//맵정보 모두 출력 : keySet() 사용
		Set<String> key = booksMap.keySet();
		System.out.println("도서번호\t카테고리\t책제목\t저자");
		for(String k : key) {
			printBook(k);
		}
	}

	@Override
	public void printBook(String bNo) {
		/**
		 * 책 한권의 정보를 출력한다
		 * 
		 * @param bNo - String, 출력하려는 책의 책번호
		 */
//		+ printBook(key:String) : void	//key에 해당하는 Book 출력
		System.out.print(booksMap.get(bNo).getbNo()+"    \t");
		System.out.print(booksMap.get(bNo).getCategory()+"    \t");
		System.out.print(booksMap.get(bNo).getTitle()+"   \t");
		System.out.print(booksMap.get(bNo).getAuthor()+"\n\n");
		
		

		 }



	}
	


		
		
		


