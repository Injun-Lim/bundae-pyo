package fileIO;

public class arrayT1 {

	
	
	public static void main(String[] args) {
		
//		String[][] name = new String[20][5]; 
		String name[][] = { // x값은 기수별, y값은 동기 번호
				{"김유성","조권영","임인준"},
				{"강효찬","배진형"},
				{"정길환","곽준호"},
				{"안기석"},
				{"강성욱"}
		};
		
		for(int i = 0 ; i < name.length ; i++){
			int randTemp[] = new int[name[i].length];
			int temp = 0;
			for(int j = 0; j<name[i].length ; j++){
//				System.out.print(name[i][j] + " ");
				for( int k = 0; k<name[i].length;){
					temp = (int)(Math.random()*name[i].length);
					if(temp != randTemp[k]){
						randTemp[j] = temp;
						System.out.println(name[i][j] +" : " +randTemp[j]);
						break;
					}
				}
			}
			System.out.println();
//			
//			System.out.println( (int) (Math.random()*name[i].length));
			
		}

	}

}
// http://mainia.tistory.com/2323   
// List 참고 사이트
// http://arabiannight.tistory.com/entry/%EC%9E%90%EB%B0%94Java-String%EC%9D%84-List-List%EB%A5%BC-String-%EB%B0%B0%EC%97%B4%EB%A1%9C-%EB%B3%80%ED%99%98
// Array -> List
// http://debong.tistory.com/entry/%EB%AA%85%ED%92%88Java38-2%EC%B0%A8%EC%9B%90%EB%B0%B0%EC%97%B4%EC%97%90-%EB%9E%9C%EB%8D%A4%EC%B6%9C%EB%A0%A5
// 2차원배열 랜덤출력