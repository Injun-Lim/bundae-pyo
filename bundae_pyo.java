import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class arrayT1 {

	
	
	public static void main(String[] args) throws IOException{
		
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
		
//		for(int i = 0 ; i < name.length ; i++){ // ����� �迭 ���
//			for(int j = 0; j<name[i].length ; j++){
//				System.out.print(name[i][j] + " ");
//			}
//			System.out.println();
//		}
		
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
		
		
		int n = nameList.size()+(4-nameList.size()%4); // \t�� �� �κ��� Ȯ���� resultList�� size���� ��
		int a = 0, b = 3;
		List<String> result = new ArrayList<String>(){ // ������� �� list
			{
				for(int i = 0 ; i <= n ; i ++)
					add(" ");
			}
		};
		
		result.set(0 , nameList.get(2)); // �д���
		result.set(1 , nameList.get(0));
		result.set(2 , nameList.get(1));
		result.set(3 , nameList.get(3));
		
		result.set(4 , nameList.get(nameList.size()-3)); // ����« (2����)
		result.set(5 , nameList.get(nameList.size()-1));
		result.set(6 , nameList.get(nameList.size()-2));
		result.set(7 , nameList.get(nameList.size()-4));
		
		result.set(8 , nameList.get(10)); // �κ� ����« (3����)
		result.set(9 , nameList.get(8));
		result.set(10 , nameList.get(9));
		result.set(11 , nameList.get(11));
		
		try{
		if(nameList.size() >= 17){				// 5���� ���� ���ܳ��� �ּ� �ο�
			if(nameList.size()%4 == 0){ 		// �κд��� �� �� 
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
			// ����ó�� ( �����ڵ� ���� �ο����� 16�� ������ ���)
		}
		
		for(int i = a ; i < nameList.size() ; i+=4){ // �κ� �����ٺ��� for��
			result.set(n-(b*4) , nameList.get(a+2)); 
			result.set(n-(b*4)+1 , nameList.get(a));
			result.set(n-(b*4)+2 , nameList.get(a+1));
			result.set(n-(b*4)+3 , nameList.get(a+3));
		}
		
		
		result.set(n-4 , nameList.get(6)); // �κд���
		result.set(n-3 , nameList.get(4));
		result.set(n-2 , nameList.get(5));
		result.set(n-1 , nameList.get(7));
		
		PrintWriter pw = new PrintWriter("C:/Users/user/Desktop/eclipse/out.txt"); // ��ȣ ���� ��η� ������ ����
		pw.println("4�д�\t3�д�\t2�д�\t1�д�");
		for( int i = 0 ; i < n ; i++){ // ��ü ���
			if(i % 4 == 0){       // ����ִ� �� ����
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
		
		pw.println();pw.println();  // �����ڵ� ��� �ѷ��ֱ� ����
		pw.println("���� (+�Ұ�,���ػ�,ä��,ä������): [" + nameList.size() + "+4]");
		pw.println("�Ұ� : " + otherList.get(0) + "\t���ػ� : " + otherList.get(1));
		pw.println("ä�� : " + otherList.get(2) + "\tä������ : " + otherList.get(3));
		pw.flush();  //flush = write�� �� �����͸� �ѷ��ְ� ������ ����
		pw.close();  //close = ��Ʈ�� �ݱ�
	}
}

// http://mainia.tistory.com/2323   
// List ���� ����Ʈ
// http://arabiannight.tistory.com/entry/%EC%9E%90%EB%B0%94Java-String%EC%9D%84-List-List%EB%A5%BC-String-%EB%B0%B0%EC%97%B4%EB%A1%9C-%EB%B3%80%ED%99%98
// Array -> List
// http://debong.tistory.com/entry/%EB%AA%85%ED%92%88Java38-2%EC%B0%A8%EC%9B%90%EB%B0%B0%EC%97%B4%EC%97%90-%EB%9E%9C%EB%8D%A4%EC%B6%9C%EB%A0%A5
// 2�����迭 �������