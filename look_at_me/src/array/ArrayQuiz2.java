package array;

import java.util.Scanner;

public class ArrayQuiz2 {

	public static void main(String[] args) {


		//--- 상수 선언 ---
		//	자주 사용되는 값
		//	고정으로 사용되는 값
		final int FLOOR = 8; //건물의 층 수
		final int FEE = 15000; //인당 관리비
		//-----------------


		//--- 변수 선언 ---
		int[] people = new int[FLOOR+1]; //각 층 인원 + 총 인원
		int[] expense = new int[FLOOR+1]; //각 층 관리비 + 총 관리비

		Scanner sc = new Scanner(System.in); //표준 입력 객체
		//-----------------


		//--- 각 층의 인원 입력 ---
		System.out.println("- - - 각 층의 인원 입력 - - -");
		for(int i=0; i<FLOOR; i++) { //i, 0 ~ FLOOR-1, FLOOR 회
			System.out.print(" >> " + (i+1) + "층의 인원은? ");
			people[i] = sc.nextInt();

			//총 인원 수 합계 계산
			people[FLOOR] += people[i];
		}

		//총인원 계산(반복적으로 계산되지 않고 있음)
		//		people[5] = people[0] + people[1] + people[2] + people[3] + people[4];
		//-------------------------



		//TEST: 층별 인원 + 총인원 전체 출력 테스트
		//		for(int i=0; i<people.length; i++) {
		//			System.out.println( people[i] );
		//		}



		//--- 관리비 계산 ---
		for(int i=0; i<expense.length; i++) {
			expense[i] = people[i] * FEE;
		}
		//-------------------



		//TEST: 층별 관리비 + 총관리비 전체 출력 테스트
		//		for(int i=0; i<expense.length; i++) {
		//			System.out.println( expense[i] );
		//		}



		//--- 최종 출력 ---
		System.out.println();
		System.out.println("- - - 각 층의 관리비 출력 - - -");
		for(int i=0; i<FLOOR; i++) {
			System.out.println(" >> " + (i+1) +"층의 관리비는 " + expense[i] + "원 입니다");
		}

		System.out.println();
		System.out.println("총 인원은 " + people[FLOOR] + "명 입니다");
		System.out.println("총 관리비는 " + expense[FLOOR] + "원 입니다");
		//-----------------

	}//main

}//class







