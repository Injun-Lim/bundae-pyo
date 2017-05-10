import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.StringTokenizer;

public class bundae_pyo_function {
	public static String name[][] = null; // x값은 기수별, y값은 동기 번호
	public static List<String> nameList = new ArrayList<String>(); //대원들 이름을 차례로 넣어줄 List
	public static List<String> otherList = new ArrayList<String>(); //열외자 List
	public static List<String> result; //출력될 분대표 List
	public static int n = 0 ;
	public static int nope = 4; //없음 인원만큼 열외자 목록에서 갯수 제외하기 위한 변수
	
	public static int bdOrder[]; // 분대 가위바위보 순서
	public static List<String> workOrder = new ArrayList<String>(); // 근무 순서 (분대 순위별 짬순 적용)
	public static List<String> workName = new ArrayList<String>(); // 사오지 이름
	public static List<String> workFinal = new ArrayList<String>(); // 최종 근무 출력물
	
	public static void readAndSet() { //txt파일 불러오기부터 기수별 랜덤배열, list로 변환까지
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
	}
	
	public static void except() { //열외자들 list 삭제
		Scanner scan = new Scanner(System.in); //열외자들 입력 (0:소경 1:항해사 2:채증 3:채증보조)
		System.out.print("소경 이름 ('없음' 입력가능): ");
		otherList.add(scan.next());
		System.out.print("항해사 이름 ('없음' 입력가능): ");
		otherList.add(scan.next());
		System.out.print("채증 이름 ('없음' 입력가능): ");
		otherList.add(scan.next());
		System.out.print("채증 보조 이름 ('없음' 입력가능): ");
		otherList.add(scan.next());

		
		for(; ;){ //무한루프로 기타 열외자 이름 입력받음 ( '끝' 입력시 루프 탈출 )
			System.out.print("기타 영외활동 등 열외자 이름('끝'이라고 입력시 열외자 입력 종료) : ");
			otherList.add(scan.next());
			if(otherList.contains("끝")){
				otherList.remove("끝");
				break;
			}
		}
		for(int i = 0; i < otherList.size(); i++){
			if(otherList.get(i).equals("없음")){// 없음 입력시 인덱스 삭제 x
				nope--;	//없음 인원만큼 열외자 목록에서 갯수 제외하기 위한 변수
				continue;
			}
			nameList.remove(nameList.indexOf(otherList.get(i))); // 열외자 인덱스 삭제
		}
	}
	
	public static void bundaeSet(){
		n = nameList.size()+(4-nameList.size()%4); // \t가 들어갈 부분을 확보한 resultList의 size값이 됨
		int a = 0, b = 3;
		result = new ArrayList<String>(){ // 결과물이 들어갈 list
			{
				for(int i = 0 ; i < n ; i ++)
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
	}
	
	public static void printBundae() throws FileNotFoundException{ //콘솔, 파일 출력부
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
		pw.println("현원 : " + (nameList.size()+nope) + "분지 1이슬");
		pw.flush();  //flush = write를 쓴 데이터를 뿌려주고 데이터 삭제
		pw.close();  //close = 스트림 닫기
		
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
	}

	public static void bundaeOrder(){ //bdOrder 리스트에 분대 가위바위보 등수 넣기
		bdOrder = new int[4];
		System.out.println("=============================================");
		Scanner scan = new Scanner(System.in);
		System.out.print("분대 근무 순서('1' : 랜덤으로, '2' : 입력하기): ");
		int select = scan.nextInt();
		if(select == 1){
			Random r = new Random();
			int temp = 0;
			for( int i = 0 ; i < 4 ; i++)
				bdOrder[i] = i; // 배열 0,1,2,3으로 초기화
			for( int i = 0 ; i < 4 ; i++){
				int d =r.nextInt(4); // 분대 순서 랜덤 섞기
				temp = bdOrder[i];
				bdOrder[i]=bdOrder[d];
				bdOrder[d]=temp;
			}
		}else if (select == 2){
			for( int i = 0 ; i<4; i++){
				System.out.print(i+1 + "등으로 근무를 설 분대(가위바위보 꼴찌부터 차례로 숫자만 입력) : ");
				bdOrder[i] = scan.nextInt()-1;
			}
		}
		System.out.println("오늘의 근무 순서");
		for( int i = 0 ; i < 3 ; i++)
			System.out.print(bdOrder[i]+1 + "분대 -> ");
		System.out.print(bdOrder[3]+1 + "분대");
		System.out.println();
	}
	
	public static void workOrder(){ //분대순서 적용하여 2-부분위~3-부분-분대장 순 적용 인덱스 생성
		for(int i = 0 ; i < 4 ; i++){ //(분대%4) 4분대 = 0 3분대 = 1 2분대 = 2 1분대 = 3
			for(int j = 3-bdOrder[i]; j < result.size() ; j+=4){ //3-분대순 한 이유는 4-3-2-1순으로 분대 나열돼서
				if(j/4 == 1){ //제일 막내 (2번째 줄)
					workOrder.add(result.get(j));
					if(i==2 && otherList.get(3).equals("없음")==false)
						workOrder.add(otherList.get(3)); //2등분대에 채증보조 추가
					else if(i==3 && otherList.get(2).equals("없음")==false)
						workOrder.add(otherList.get(2)); //1등분대에 채증 추가
				}
				else if(j/4 == (result.size()/4 )-2){ //부분대장 윗줄 선택해서 (부분 윗줄 ~ 3번째 줄)
					for(int row = 0; ; row +=4){ //한 줄씩 올려서 비교
						if(j-row <= 7) // 이미 추가한 2번째줄 선택되면 break
							break;
						else if(result.get(j-row) != " ") 
							workOrder.add(result.get(j-row)); //근무자로 추가 
					}
				}
				else if(j/4 == (result.size()/4 )-1){ //부분대장 , 분대장
					workOrder.add(result.get(j));
					workOrder.add(result.get(j%4));
				}
			}
		}
	}
	public static void workTime(){ //사오지 수 입력, 사오지 이름, 명수, 근무 시작시간, 근무시간 입력 받고 인덱스 명수대로 끊기
		Scanner scan = new Scanner(System.in);
		System.out.print("근무지 수 입력 : ");
		int workN = scan.nextInt(); //사오지 갯수
		System.out.print("근무 시작 시간 입력 ex(8시30분=830, 13시=1300) : ");
		int workSTime = scan.nextInt(); //근무시작시간
		System.out.print("근무 시간 분단위로 입력 ex(1시간=60, 1시간 30분 =90, 30분=30) : ");
		int workTime = scan.nextInt(); //근무시간
		System.out.print("타소대와 격대 여부(y/n) : ");
		String chanYN = scan.next(); //타소대와 격대 여부 y/n으로 입력받음
		int workerN[] = new int[workN]; //사오지 갯수만큼 사오지별 인원수 인덱스 추가
		workFinal.add("근무시간\t\t"); //사오지 이름별 탭간격
		for ( int i = 0 ; i < workN ; i++){
			System.out.print(i+1 + "번째 근무 이름 입력 ex(고정1) : ");
			workFinal.add(scan.next()); //사오지 이름
			System.out.print(i+1 + "번째 근무지 근무인원 입력 : ");
			workerN[i] = scan.nextInt(); //사오지별 인원수
			for(int l = 0 ; l<workerN[i] ; l++) //근무자 수만큼 근무지(맨윗줄) 공백 추가
				workFinal.add("\t");
		}
		workFinal.add("\n"); //사오지 이름입력다되면 줄바꿈
		
		int index = 0, stHour = workSTime/100, stMin = workSTime%100;  //다음 근무자 지정 인덱스, 근무 시,분 저장 변수
		for(int howMany =  0; howMany < 24 ; howMany++){//howmany는 몇 번째 근무까지 계산할지
			if(chanYN.equals("y") || chanYN.equals("Y")){
				stMin += workTime*2; //stMin에 타소대 근무시간 더해주기
				if(stMin>=60){
					stHour+=(stMin/60); //60분 넘으면 '시'에 옮기기 
					stMin-=(stMin/60)*60; //넘긴만큼 빼기
				}
				if(stHour >= 24) //24시 = 00시
					stHour -= 24;
			}
			workFinal.add(String.valueOf(String.format("%02d", stHour)+":"+ String.format("%02d", stMin))+ " ~"); //format을 사용한 부분은 숫자를 2자리로 표현하기 위함
			stMin += workTime; //stMin에 근무시간 더해주기
			if(stMin>=60){
				stHour+=(stMin/60); //60분 넘으면 '시'에 옮기기 
				stMin-=(stMin/60)*60; //넘긴만큼 빼기
			}
			if(stHour >= 24) //24시 = 00시
				stHour -= 24;

			
			workFinal.add(String.valueOf(String.format("%02d", stHour)+":"+ String.format("%02d", stMin))+ "\t"); //format을 사용한 부분은 숫자를 2자리로 표현하기 위함
			for(int i = 0 ; i < workN ; i ++){
				for(int j = 0 ; j < workerN[i] ; j++){
					if(index >= workOrder.size())
						index=0;
					workFinal.add(workOrder.get(index));
					index++;
				}
				workFinal.add("\t"); //근무지별 탭 간격
			}
			workFinal.add("\n"); //근무시간별 엔터 간격
			
		}
		
	}
	public static void workPrint() throws FileNotFoundException{
		
		PrintWriter pw = new PrintWriter("./근무표.txt"); // 괄호 안의 경로로 파일이 생성
		pw.print("근무 순서 : ");
		for(int i = 0 ; i < workOrder.size() ; i ++){
			pw.print(workOrder.get(i) + " ");
		}
		pw.println("");
		for(int i = 0 ; i < workFinal.size() ; i ++){
			if(workFinal.get(i).equals("\n")){ //\n 출력시 깨져보이는 문제 해결용
				pw.println("");
			}else
				pw.print(workFinal.get(i) + " ");
		}
		pw.flush();  //flush = write를 쓴 데이터를 뿌려주고 데이터 삭제
		pw.close();  //close = 스트림 닫기
		
		try { // 분대표.txt파일 콘솔에 출력
			// readLine 사용해 한 라인씩 읽어들인다
			BufferedReader reader = new BufferedReader(new FileReader("./근무표.txt"));
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
	
	public static void main(String[] args) throws IOException{
		
		readAndSet(); //초기 배열 세팅, list로 옮기기
		except(); //열외자 세팅
		bundaeSet(); //출력될 분대표(result) 세팅
		printBundae(); //콘솔, 파일 출력부
		
		Scanner scan = new Scanner(System.in);
		System.out.print("근무표를 짜시겠습니까?(y/n) : ");
		String yn = scan.nextLine();
		if(yn.equals("Y") || yn.equals("y")){
			bundaeOrder(); //분대 가위바위보 순서 적용
			workOrder(); //근무자들 근무 순서 적용
			workTime(); //근무 조건별 적용
			workPrint(); //출력
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


/*
김진혁
정순상
이우진
조권영
이강희
이준행
김유성
정길환
강성욱
이준휘
변규석
강효찬
끝
1
2
830
150
고정1
2
고정2
2
*/