import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class arrayT1 {

	
	
	public static void main(String[] args) {
		
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
				{"여동근","이석우"}
		};
		Random r = new Random(); //랜덤으로 섞어서
		String temp = null;
		List<String> nameList = new ArrayList<String>();
		
		
		for(int i = 0 ; i < name.length ; i++){  //값을 i,0번인덱스부터 i,j값까지 서로 치환
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
				System.out.println(otherList.get(otherList.size()-1));
				otherList.remove("끝");
				break;
			}
		}
		
		for(int i = 0; i < nameList.size(); i++){ // List 전체 출력
			System.out.print(nameList.get(i) + " ");
			System.out.println();
		}
		
		for(int i = 0; i < otherList.size(); i++)
			System.out.println(nameList.remove(nameList.indexOf(otherList.get(i)))); // 열외자 인덱스 삭제
		
		for(int i = 0; i < nameList.size(); i++) // 열외 삭제 후 전체 출력
			System.out.print(nameList.get(i) + " ");
	}
}

// http://mainia.tistory.com/2323   
// List 참고 사이트
// http://arabiannight.tistory.com/entry/%EC%9E%90%EB%B0%94Java-String%EC%9D%84-List-List%EB%A5%BC-String-%EB%B0%B0%EC%97%B4%EB%A1%9C-%EB%B3%80%ED%99%98
// Array -> List
// http://debong.tistory.com/entry/%EB%AA%85%ED%92%88Java38-2%EC%B0%A8%EC%9B%90%EB%B0%B0%EC%97%B4%EC%97%90-%EB%9E%9C%EB%8D%A4%EC%B6%9C%EB%A0%A5
// 2차원배열 랜덤출력