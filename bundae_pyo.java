import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class arrayT1 {

	
	
	public static void main(String[] args) throws IOException{
		
//		String[][] name = new String[20][5]; 
		String name[][] = { // x값은 기수별, y값은 동기 번호
				{"전호영"},
				{"김창모"},
				{"고현기","양동빈"},
				{"김진혁","이강희","이준행"},
				{"정순상","한재혁"},
				{"이우진","정선우"},
				{"김정수"},
				{"엄기수","조규섭"},
				{"김효준"},
				{"김유성","조권영","임인준","변규석"},
				{"강효찬","배진형",},
				{"정길환","곽준호"},
				{"안기석"},
				{"강성욱"},
				{"이원석"},
				{"심종현"},
				{"여동근","김석우"}
		};
		Random r = new Random(); //랜덤으로 섞어서
		String temp = null;
		List<String> nameList = new ArrayList<String>(); //대원들 이름을 차례로 넣어줄 List
		
		
		for(int i = 0 ; i < name.length ; i++){  //값을 i,0번인덱스부터 i,j값까지 서로 치환
			for(int j = 0; j<name[i].length ; j++){
				int d =r.nextInt(name[i].length);
				temp = name[i][j];
				name[i][j]=name[i][d];
				name[i][d]=temp;	
			}
		}
		
//		for(int i = 0 ; i < name.length ; i++){ // 기수별 배열 출력
//			for(int j = 0; j<name[i].length ; j++){
//				System.out.print(name[i][j] + " ");
//			}
//			System.out.println();
//		}
		
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
		
		try{
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
		}else{
			throw new Exception();
		}
		}catch(Exception e){
			// 예외처리 ( 열외자들 제외 인원수가 16명 이하인 경우)
		}
		
		for(int i = a ; i < nameList.size() ; i+=4){ // 부분 윗윗줄부터 for문
			result.set(n-(b*4) , nameList.get(a+2)); 
			result.set(n-(b*4)+1 , nameList.get(a));
			result.set(n-(b*4)+2 , nameList.get(a+1));
			result.set(n-(b*4)+3 , nameList.get(a+3));
		}
		
		
		result.set(n-4 , nameList.get(6)); // 부분대장
		result.set(n-3 , nameList.get(4));
		result.set(n-2 , nameList.get(5));
		result.set(n-1 , nameList.get(7));
		
		PrintWriter pw = new PrintWriter("C:/Users/user/Desktop/eclipse/out.txt"); // 괄호 안의 경로로 파일이 생성
		pw.println("4분대\t3분대\t2분대\t1분대");
		for( int i = 0 ; i < n ; i++){ // 전체 출력
			if(i % 4 == 0){       // 비어있는 줄 삭제
				if(result.get(i) == " "){
					if(result.get(i+3) == " "){
						i+=4;
					}
				}
			}
			pw.print(result.get(i) + "\t");
			if(i%4 == 3)
				pw.println();
		}
		
		pw.println();pw.println();  // 열외자들 목록 뿌려주기 위함
		pw.println("현원 (+소경,항해사,채증,채증보조): [" + nameList.size() + "+4]");
		pw.println("소경 : " + otherList.get(0) + "\t항해사 : " + otherList.get(1));
		pw.println("채증 : " + otherList.get(2) + "\t채증보조 : " + otherList.get(3));
		pw.flush();  //flush = write를 쓴 데이터를 뿌려주고 데이터 삭제
		pw.close();  //close = 스트림 닫기
	}
}

// http://mainia.tistory.com/2323   
// List 참고 사이트
// http://arabiannight.tistory.com/entry/%EC%9E%90%EB%B0%94Java-String%EC%9D%84-List-List%EB%A5%BC-String-%EB%B0%B0%EC%97%B4%EB%A1%9C-%EB%B3%80%ED%99%98
// Array -> List
// http://debong.tistory.com/entry/%EB%AA%85%ED%92%88Java38-2%EC%B0%A8%EC%9B%90%EB%B0%B0%EC%97%B4%EC%97%90-%EB%9E%9C%EB%8D%A4%EC%B6%9C%EB%A0%A5
// 2차원배열 랜덤출력