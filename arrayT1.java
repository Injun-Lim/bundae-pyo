import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class arrayT1 {

	
	
	public static void main(String[] args) {
		
//		String[][] name = new String[20][5]; 
		String name[][] = { // x���� �����, y���� ���� ��ȣ
				{"��ȣ��"},
				{"��â��"},
				{"������","�絿��"},
				{"������","�̰���","������"},
				{"������","������"},
				{"�̿���","������"},
				{"������"},
				{"�����","���Լ�"},
				{"��ȿ��"},
				{"������","���ǿ�","������","���Լ�"},
				{"��ȿ��","������",},
				{"����ȯ","����ȣ"},
				{"�ȱ⼮"},
				{"������"},
				{"�̿���"},
				{"������"},
				{"������","�̼���"}
		};
		Random r = new Random(); //�������� ���
		String temp = null;
		List<String> nameList = new ArrayList<String>();
		
		
		for(int i = 0 ; i < name.length ; i++){  //���� i,0���ε������� i,j������ ���� ġȯ
			for(int j = 0; j<name[i].length ; j++){
				int d =r.nextInt(name[i].length);
				temp = name[i][j];
				name[i][j]=name[i][d];
				name[i][d]=temp;	
			}
		}
		
		for(int i = 0 ; i < name.length ; i++){ // ����� �迭 ���
			for(int j = 0; j<name[i].length ; j++){
				System.out.print(name[i][j] + " ");
			}
			System.out.println();
		}
		
		for(int i = 0 ; i < name.length ; i++){  //name(2���� �迭)���� List�� �ű�
			for(int j = 0; j<name[i].length ; j++){
				nameList.add(name[i][j]);
			}
		}
		
		List<String> otherList = new ArrayList<String>(); //�����ڵ� �Է� (0:�Ұ� 1:���ػ� 2:ä�� 3:ä������)
		Scanner scan = new Scanner(System.in);
		System.out.print("�Ұ� �̸� : ");
		otherList.add(scan.next());
		System.out.print("���ػ� �̸� : ");
		otherList.add(scan.next());
		System.out.print("ä�� �̸� : ");
		otherList.add(scan.next());
		System.out.print("ä�� ���� �̸� : ");
		otherList.add(scan.next());
		for(; ;){ //���ѷ����� ��Ÿ ������ �̸� �Է¹��� ( '��' �Է½� ���� Ż�� )
			System.out.print("��Ÿ ����Ȱ�� �� ������ �̸�('��'�̶�� �Է½� ������ �Է� ����) : ");
			otherList.add(scan.next());
			if(otherList.contains("��")){
				System.out.println(otherList.get(otherList.size()-1));
				otherList.remove("��");
				break;
			}
		}
		
		for(int i = 0; i < nameList.size(); i++){ // List ��ü ���
			System.out.print(nameList.get(i) + " ");
			System.out.println();
		}
		
		for(int i = 0; i < otherList.size(); i++)
			System.out.println(nameList.remove(nameList.indexOf(otherList.get(i)))); // ������ �ε��� ����
		
		for(int i = 0; i < nameList.size(); i++) // ���� ���� �� ��ü ���
			System.out.print(nameList.get(i) + " ");
	}
}

// http://mainia.tistory.com/2323   
// List ���� ����Ʈ
// http://arabiannight.tistory.com/entry/%EC%9E%90%EB%B0%94Java-String%EC%9D%84-List-List%EB%A5%BC-String-%EB%B0%B0%EC%97%B4%EB%A1%9C-%EB%B3%80%ED%99%98
// Array -> List
// http://debong.tistory.com/entry/%EB%AA%85%ED%92%88Java38-2%EC%B0%A8%EC%9B%90%EB%B0%B0%EC%97%B4%EC%97%90-%EB%9E%9C%EB%8D%A4%EC%B6%9C%EB%A0%A5
// 2�����迭 �������