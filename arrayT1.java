import java.util.ArrayList;
import java.util.Collections;
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
				{"������","�輮��"}
		};
		Random r = new Random(); //�������� ���
		String temp = null;
		List<String> nameList = new ArrayList<String>(); //����� �̸��� ���ʷ� �־��� List
		
		
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
				otherList.remove("��");
				break;
			}
		}
		
		for(int i = 0; i < otherList.size(); i++)
			nameList.remove(nameList.indexOf(otherList.get(i))); // ������ �ε��� ����
		
//		for(int i = 0; i < nameList.size(); i++) // ���� ���� �� ��ü ���
//			System.out.print(nameList.get(i) + " ");
		
		for(int i = 0; i < 8; i+=4){
			Collections.swap(nameList, i, i+1);
			Collections.swap(nameList, i, i+2);
		}
		for(int i = nameList.size()-1; i>=11; i-=4){
			Collections.swap(nameList, i, i-3);
			Collections.swap(nameList, i, i-1);
		}
		
		System.out.println();
		System.out.println("\t4�д�\t3�д�\t2�д�\t1�д�");
		
		System.out.print("�д���\t");
		for(int i = 0; i < 4; i++)
			System.out.print(nameList.get(i) + "\t");
		
		for(int i = nameList.size()-1 ; i >= 8 ; i--){
			if(i%4 == (nameList.size()-1)%4){
				System.out.println();
				System.out.print((nameList.size()-1)/4-i/4+2 + "�� ��\t");
				if(i == 7+(nameList.size())%4){ //�κд��� ���� 4������ ���� �ο� (�������� �迭)
					if((nameList.size())%4 == 1)
						System.out.print("\t\t\t" + nameList.get(8) + "\t");
					if((nameList.size())%4 == 2)
						System.out.print(nameList.get(8) + "\t\t\t" + nameList.get(9));
					if((nameList.size())%4 == 3)
						System.out.print(nameList.get(9) + "\t\t" + nameList.get(8)+ "\t" + nameList.get(10));
					break;
				}
			}
			System.out.print(nameList.get(i) + "\t");
		}
		
		System.out.println();
		System.out.print("�κд���\t");
		for(int i = 4; i < 8; i++)
			System.out.print(nameList.get(i) + "\t");
		
		System.out.println();System.out.println();
		System.out.println("���� (+�Ұ�,���ػ�,ä��,ä������): [" + nameList.size() + "+4]");
		System.out.println("�Ұ� : " + otherList.get(0) + "\t���ػ� : " + otherList.get(1));
		System.out.println("ä�� : " + otherList.get(2) + "\tä������ : " + otherList.get(3));
	}
}

// http://mainia.tistory.com/2323   
// List ���� ����Ʈ
// http://arabiannight.tistory.com/entry/%EC%9E%90%EB%B0%94Java-String%EC%9D%84-List-List%EB%A5%BC-String-%EB%B0%B0%EC%97%B4%EB%A1%9C-%EB%B3%80%ED%99%98
// Array -> List
// http://debong.tistory.com/entry/%EB%AA%85%ED%92%88Java38-2%EC%B0%A8%EC%9B%90%EB%B0%B0%EC%97%B4%EC%97%90-%EB%9E%9C%EB%8D%A4%EC%B6%9C%EB%A0%A5
// 2�����迭 �������