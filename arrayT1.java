import java.util.Random;

public class arrayT1 {

	
	
	public static void main(String[] args) {
		
//		String[][] name = new String[20][5]; 
		String name[][] = { // x���� �����, y���� ���� ��ȣ
				{"������","���ǿ�","������"},
				{"��ȿ��","������",},
				{"����ȯ","����ȣ"},
				{"�ȱ⼮"},
				{"������"}
		};
		Random r = new Random(); //�������� ���
		String temp = null;
		for(int i = 0 ; i < name.length ; i++){  //���� i,0���ε������� i,j������ ���� ġȯ
			for(int j = 0; j<name[i].length ; j++){
				int d =r.nextInt(name[i].length);
				temp = name[i][j];
				name[i][j]=name[i][d];
				name[i][d]=temp;	
			}
		}
		
		for(int i = 0 ; i < name.length ; i++){ // ���
			for(int j = 0; j<name[i].length ; j++){
				System.out.print(name[i][j] + " ");
			}
			System.out.println();
		}
	}
}

// http://mainia.tistory.com/2323   
// List ���� ����Ʈ
// http://arabiannight.tistory.com/entry/%EC%9E%90%EB%B0%94Java-String%EC%9D%84-List-List%EB%A5%BC-String-%EB%B0%B0%EC%97%B4%EB%A1%9C-%EB%B3%80%ED%99%98
// Array -> List
// http://debong.tistory.com/entry/%EB%AA%85%ED%92%88Java38-2%EC%B0%A8%EC%9B%90%EB%B0%B0%EC%97%B4%EC%97%90-%EB%9E%9C%EB%8D%A4%EC%B6%9C%EB%A0%A5
// 2�����迭 �������