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
	public static String name[][] = null; // x���� �����, y���� ���� ��ȣ
	public static List<String> nameList = new ArrayList<String>(); //����� �̸��� ���ʷ� �־��� List
	public static List<String> otherList = new ArrayList<String>(); //������ List
	public static List<String> result; //��µ� �д�ǥ List
	public static int n = 0 ;
	
	public static int bdOrder[]; // �д� ���������� ����
	public static List<String> workOrder = new ArrayList<String>(); // �ٹ� ���� (�д� ������ «�� ����)
	
	public static void readAndSet() { //txt���� �ҷ�������� ����� �����迭, list�� ��ȯ����
		try {
			BufferedReader xReader = new BufferedReader(new FileReader("./�̸����.txt"));
			String data = "";
			int ti = 0, tj = 0, x = 0, y = 0;
			while ((data = xReader.readLine()) != null) {
				x++; // name�迭�� �����Ҵ��� ���� x�� (����� ���� �� (�� ����� �ֳ�))
			}
			xReader.close();
			name = new String[x][];
			
			// readLine ����� �� ���ξ� �о���δ�
			BufferedReader reader = new BufferedReader(new FileReader("./�̸����.txt"));
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
		Random r = new Random(); //�������� ���
		String temp = null;
		
		for(int i = 0 ; i < name.length ; i++){  //���� 0,0���ε������� i,j������ ���� ġȯ(��������)
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
	}
	
	public static void except() { //�����ڵ� list ����
		Scanner scan = new Scanner(System.in); //�����ڵ� �Է� (0:�Ұ� 1:���ػ� 2:ä�� 3:ä������)
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
	}
	
	public static void bundaeSet(){
		n = nameList.size()+(4-nameList.size()%4); // \t�� �� �κ��� Ȯ���� resultList�� size���� ��
		int a = 0, b = 3;
		result = new ArrayList<String>(){ // ������� �� list
			{
				for(int i = 0 ; i < n ; i ++)
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
			
			if(nameList.size() > 20){ // 6��° ���� ����� �ּ� �ο�
				for(int i = a ; i < nameList.size()-4 ; i+=4){ // �κ� �����ٺ��� for��
					result.set(n-(b*4) , nameList.get(a+2)); 
					result.set(n-(b*4)+1 , nameList.get(a));
					result.set(n-(b*4)+2 , nameList.get(a+1));
					result.set(n-(b*4)+3 , nameList.get(a+3));
				}
			}
		}else if (nameList.size() <= 15){
			System.out.println("�����ڰ� �ʹ� �����ϴ�. �Ұ�, ���ػ�, ä��, ä�� ���� 8����� �����մϴ�. ���α׷��� �����մϴ�.");
			System.exit(0);
		}
		
		result.set(n-4 , nameList.get(6)); // �κд���
		result.set(n-3 , nameList.get(4));
		result.set(n-2 , nameList.get(5));
		result.set(n-1 , nameList.get(7));
	}
	
	public static void printBundae() throws FileNotFoundException{ //�ܼ�, ���� ��º�
		PrintWriter pw = new PrintWriter("./�д�ǥ.txt"); // ��ȣ ���� ��η� ������ ����
		pw.println("4�д� \t  |\t3�д� \t  |\t2�д� \t  |\t1�д�");
		for( int i = 0 ; i < n ; i++){ // ��ü ���
			if(i % 4 == 0){       // ����ִ� �� ����
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
		
		pw.println();pw.println();  // �����ڵ� ��� �ѷ��ֱ� ����
		pw.println("�Ұ� : " + otherList.get(0) + "\t���ػ� : " + otherList.get(1));
		pw.println("ä�� : " + otherList.get(2) + "\tä������ : " + otherList.get(3));
		pw.println("���� : " + (nameList.size()+4) + "���� 1�̽�");
		pw.flush();  //flush = write�� �� �����͸� �ѷ��ְ� ������ ����
		pw.close();  //close = ��Ʈ�� �ݱ�
		
		try { // �д�ǥ.txt���� �ֿܼ� ���
			// readLine ����� �� ���ξ� �о���δ�
			BufferedReader reader = new BufferedReader(new FileReader("./�д�ǥ.txt"));
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
//		HSSFWorkbook workbook = new HSSFWorkbook(); // �� ���� ����
//        HSSFSheet sheet = workbook.createSheet("��Ʈ��"); // �� ��Ʈ(Sheet) ����
//        HSSFRow row = sheet.createRow(0); // ������ ���� 0������ ����
//        HSSFCell cell = row.createCell(0); // ���� ���� 0������ ����
//        cell.setCellValue("�׽�Ʈ ������"); //������ ���� ������ ����
//        try {
//            FileOutputStream fileoutputstream = new FileOutputStream("������ ���/�̸�.xlsx");
//            workbook.write(fileoutputstream);
//            fileoutputstream.close();
//            System.out.println("�������ϻ�������");
//        } catch (IOException e) {
//            e.printStackTrace();
//            System.out.println("�������ϻ�������");
//        }
	}

	public static void bundaeOrder(){ //bdOrder ����Ʈ�� �д� ���������� ��� ���� �ֱ�
		System.out.println("=============================================");
		System.out.println(result);
		Random r = new Random();
		bdOrder = new int[4];
		int temp = 0;
		for( int i = 0 ; i < 4 ; i++)
			bdOrder[i] = i; // �迭 0,1,2,3���� �ʱ�ȭ
		for( int i = 0 ; i < 4 ; i++){
			int d =r.nextInt(4); // �д� ���� ���� ����
			temp = bdOrder[i];
			bdOrder[i]=bdOrder[d];
			bdOrder[d]=temp;
		}
		System.out.println("������ �ٹ� ����");
		for( int i = 0 ; i < 3 ; i++)
			System.out.print(bdOrder[i]+1 + "�д� -> ");
		System.out.print(bdOrder[3]+1 + "�д�");
		System.out.println();
	}
	
	public static void workOrder(){ //�д���� �����Ͽ� 2-�κ���~3-�κ�-�д��� �� ���� �ε��� ����
		for(int i = 0 ; i < 4 ; i++){ //(�д�%4) 1�д� = 0 2�д� = 1 3�д� = 2 4�д� = 3
			for(int j = 0; j < result.size() ; j++){
				if(j%4 == bdOrder[i]){
					if(result.get(j) != " ")
						workOrder.add(result.get(j)); ///////�� �κк��� ���� �ʿ�
				}
			}
		}
	}
	public static void workTime(){ //����� �� �Է�, ����� �̸�, ���, �ٹ� ���۽ð�, �ٹ��ð� �Է� �ް� �ε��� ������ ����
		
	}
	public static void workPrint(){
		for(int i = 0 ; i < workOrder.size() ; i ++){
			System.out.print(workOrder.get(i) + " ");
		}
	}
	
	public static void main(String[] args) throws IOException{
		
		readAndSet(); //�ʱ� �迭 ����, list�� �ű��
		except(); //������ ����
		bundaeSet(); //��µ� �д�ǥ(result) ����
		printBundae(); //�ܼ�, ���� ��º�
		bundaeOrder();
		workOrder();
		workPrint();
	}
}

// http://mainia.tistory.com/2323   
// List ���� ����Ʈ
// http://arabiannight.tistory.com/entry/%EC%9E%90%EB%B0%94Java-String%EC%9D%84-List-List%EB%A5%BC-String-%EB%B0%B0%EC%97%B4%EB%A1%9C-%EB%B3%80%ED%99%98
// Array -> List
// http://debong.tistory.com/entry/%EB%AA%85%ED%92%88Java38-2%EC%B0%A8%EC%9B%90%EB%B0%B0%EC%97%B4%EC%97%90-%EB%9E%9C%EB%8D%A4%EC%B6%9C%EB%A0%A5
// 2�����迭 �������
// http://blog.naver.com/highkrs/220855155455
// output����
// http://arer.tistory.com/48
// stringTokenizer ����
// http://boxfoxs.tistory.com/304
// ���� POI ����


/*������
������
�̿���
���ǿ�
�̰���
������
������
����ȯ
������
��*/