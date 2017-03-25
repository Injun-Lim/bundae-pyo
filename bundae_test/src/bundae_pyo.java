import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.StringTokenizer;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

public class bundae_pyo {

	public static void main(String[] args) throws IOException{
		String name[][] = null; // x값은 기수별, y값은 동기 번호
		try {
			BufferedReader xReader = new BufferedReader(new FileReader("./이름목록.txt"));
			String data = "";
			int ti = 0, tj = 0, x = 0, y = 0;
			while ((data = xReader.readLine()) != null) {
				x++; // name배열의 동적할당을 위한 x값 (기수의 종류 수 (몇 기수나 있나))
			}
			xReader.close();
			name = new String[x][];
			
			// readLine 사용해 한 라인씩 읽어들인다
			BufferedReader reader = new BufferedReader(new FileReader("./이름목록.txt"));
			while ((data = reader.readLine()) != null) {
				StringTokenizer st = new StringTokenizer(data, " ");
				y = st.countTokens();
				name[ti] = new String[y];
				// System.out.println(st.countTokens());
				while (st.hasMoreTokens()) {
//					System.out.print(st.nextToken() + " ");
					name[ti][tj] = st.nextToken();
					tj++;
				}
				ti++;
				tj=0;
			}
			reader.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		Random r = new Random(); //랜덤으로 섞어서
		String temp = null;
		List<String> nameList = new ArrayList<String>(); //대원들 이름을 차례로 넣어줄 List
		
		
		for(int i = 0 ; i < name.length ; i++){  //값을 0,0번인덱스부터 i,j값까지 서로 치환(랜덤섞기)
			for(int j = 0; j<name[i].length ; j++){
				int d =r.nextInt(name[i].length);
				temp = name[i][j];
				name[i][j]=name[i][d];
				name[i][d]=temp;	
			}
		}
		
		for(int i = 0 ; i < name.length ; i++){ // 기수별 배열 출력
			for(int j = 0; j<name[i].length ; j++){
				System.out.print(name[i][j] + " ");
			}
			System.out.println();
		}
		
		for(int i = 0 ; i < name.length ; i++){  //name(2차원 배열)값을 List로 옮김
			for(int j = 0; j<name[i].length ; j++){
				nameList.add(name[i][j]);
			}
		}
		
		List<String> otherList = new ArrayList<String>(); //열외자들 입력 (0:소경 1:항해사 2:채증 3:채증보조)
		Scanner scan = new Scanner(System.in);
		System.out.print("소경 이름 : ");
		otherList.add(scan.next());
		System.out.print("항해사 이름 : ");
		otherList.add(scan.next());
		System.out.print("채증 이름 : ");
		otherList.add(scan.next());
		System.out.print("채증 보조 이름 : ");
		otherList.add(scan.next());
		for(; ;){ //무한루프로 기타 열외자 이름 입력받음 ( '끝' 입력시 루프 탈출 )
			System.out.print("기타 영외활동 등 열외자 이름('끝'이라고 입력시 열외자 입력 종료) : ");
			otherList.add(scan.next());
			if(otherList.contains("끝")){
				otherList.remove("끝");
				break;
			}
		}
		
		for(int i = 0; i < otherList.size(); i++)
			nameList.remove(nameList.indexOf(otherList.get(i))); // 열외자 인덱스 삭제
		
		
		int n = nameList.size()+(4-nameList.size()%4); // \t가 들어갈 부분을 확보한 resultList의 size값이 됨
		int a = 0, b = 3;
		List<String> result = new ArrayList<String>(){ // 결과물이 들어갈 list
			{
				for(int i = 0 ; i <= n ; i ++)
					add(" ");
			}
		};
		
		result.set(0 , nameList.get(2)); // 분대장
		result.set(1 , nameList.get(0));
		result.set(2 , nameList.get(1));
		result.set(3 , nameList.get(3));
		
		result.set(4 , nameList.get(nameList.size()-3)); // 최저짬 (2번줄)
		result.set(5 , nameList.get(nameList.size()-1));
		result.set(6 , nameList.get(nameList.size()-2));
		result.set(7 , nameList.get(nameList.size()-4));
		
		result.set(8 , nameList.get(10)); // 부분 다음짬 (3번줄)
		result.set(9 , nameList.get(8));
		result.set(10 , nameList.get(9));
		result.set(11 , nameList.get(11));
		
		if(nameList.size() >= 17){				// 5개의 줄이 생겨나는 최소 인원
			if(nameList.size()%4 == 0){ 		// 부분대장 윗 줄 
				result.set(n-8 , nameList.get(14)); 
				result.set(n-7 , nameList.get(12));
				result.set(n-6 , nameList.get(13));
				result.set(n-5 , nameList.get(15));
				a=16;
			}
			else if(nameList.size()%4 == 1){
				result.set(n-8 , " "); 
				result.set(n-7 , " ");
				result.set(n-6 , " ");
				result.set(n-5 , nameList.get(12));
				a=13;
			}
			else if(nameList.size()%4 == 2){
				result.set(n-8 , nameList.get(12)); 
				result.set(n-7 , " ");
				result.set(n-6 , " ");
				result.set(n-5 , nameList.get(13));
				a=14;
			}
			else if(nameList.size()%4 == 3){
				result.set(n-8 , nameList.get(13)); 
				result.set(n-7 , " ");
				result.set(n-6 , nameList.get(12));
				result.set(n-5 , nameList.get(14));
				a=15;
			}
			
			if(nameList.size() > 20){ // 6번째 줄이 생기는 최소 인원
				for(int i = a ; i < nameList.size()-4 ; i+=4){ // 부분 윗윗줄부터 for문
					result.set(n-(b*4) , nameList.get(a+2)); 
					result.set(n-(b*4)+1 , nameList.get(a));
					result.set(n-(b*4)+2 , nameList.get(a+1));
					result.set(n-(b*4)+3 , nameList.get(a+3));
				}
			}
		}else if (nameList.size() <= 15){
			System.out.println("열외자가 너무 많습니다. 소경, 항해사, 채증, 채보 제외 8명까지 가능합니다. 프로그램을 종료합니다.");
			System.exit(0);
		}
		
		
		
		result.set(n-4 , nameList.get(6)); // 부분대장
		result.set(n-3 , nameList.get(4));
		result.set(n-2 , nameList.get(5));
		result.set(n-1 , nameList.get(7));
		
		PrintWriter pw = new PrintWriter("./분대표.txt"); // 괄호 안의 경로로 파일이 생성
		pw.println("4분대 \t  |\t3분대 \t  |\t2분대 \t  |\t1분대");
		for( int i = 0 ; i < n ; i++){ // 전체 출력
			if(i % 4 == 0){       // 비어있는 줄 삭제
				if(result.get(i) == " "){
					if(result.get(i+3) == " "){
						i+=4;
					}
				}
			}
			pw.print(result.get(i) + " \t");
			if(i%4 == 3)
				pw.println();
			else
				pw.print("  |\t");
		}
		
		pw.println();pw.println();  // 열외자들 목록 뿌려주기 위함
		pw.println("소경 : " + otherList.get(0) + "\t항해사 : " + otherList.get(1));
		pw.println("채증 : " + otherList.get(2) + "\t채증보조 : " + otherList.get(3));
		pw.println("현원 : " + (nameList.size()+4) + "분지 1이슬");
		pw.flush();  //flush = write를 쓴 데이터를 뿌려주고 데이터 삭제
		pw.close();  //close = 스트림 닫기
		
//		HSSFWorkbook workbook = new HSSFWorkbook(); // 새 엑셀 생성
//        HSSFSheet sheet = workbook.createSheet("시트명"); // 새 시트(Sheet) 생성
//        HSSFRow row = sheet.createRow(0); // 엑셀의 행은 0번부터 시작
//        HSSFCell cell = row.createCell(0); // 행의 셀은 0번부터 시작
//        cell.setCellValue("테스트 데이터"); //생성한 셀에 데이터 삽입
//        try {
//            FileOutputStream fileoutputstream = new FileOutputStream("저장할 경로/이름.xlsx");
//            workbook.write(fileoutputstream);
//            fileoutputstream.close();
//            System.out.println("엑셀파일생성성공");
//        } catch (IOException e) {
//            e.printStackTrace();
//            System.out.println("엑셀파일생성실패");
//        }

		
		try { // 분대표.txt파일 콘솔에 출력
			// readLine 사용해 한 라인씩 읽어들인다
			BufferedReader reader = new BufferedReader(new FileReader("./분대표.txt"));
			String data = "";
			while ((data = reader.readLine()) != null) {
				StringTokenizer st = new StringTokenizer(data, "\n");
				while (st.hasMoreTokens()) {
					System.out.print(st.nextToken() + " ");
				}
				System.out.println();
			}
			reader.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

// http://mainia.tistory.com/2323   
// List 참고 사이트
// http://arabiannight.tistory.com/entry/%EC%9E%90%EB%B0%94Java-String%EC%9D%84-List-List%EB%A5%BC-String-%EB%B0%B0%EC%97%B4%EB%A1%9C-%EB%B3%80%ED%99%98
// Array -> List
// http://debong.tistory.com/entry/%EB%AA%85%ED%92%88Java38-2%EC%B0%A8%EC%9B%90%EB%B0%B0%EC%97%B4%EC%97%90-%EB%9E%9C%EB%8D%A4%EC%B6%9C%EB%A0%A5
// 2차원배열 랜덤출력
// http://blog.naver.com/highkrs/220855155455
// output관련
// http://arer.tistory.com/48
// stringTokenizer 관련
// http://boxfoxs.tistory.com/304
// 엑셀 POI 관련