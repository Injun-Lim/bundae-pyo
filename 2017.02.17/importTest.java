import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.StringTokenizer;

public class importTest {

	public static void main(String[] args) {
		
		try {
//            /** ���� ���� */
//            BufferedWriter writer = new BufferedWriter(new FileWriter("C:/Users/user/Desktop/eclipse/test.txt"));
//            writer.write("���Ͼ��� �׽�Ʈ\r\n");
//            writer.write("��������");
//            writer.newLine(); // \r\n �ᵵ �ǰ� newLine �Լ��� ����� ������������ ������ �ȴ�
// 
//            writer.write("newLine ����ؼ� ����\r\n");
//            writer.close();
 
            /** ������ �� ������ �о���δ� */
            BufferedReader reader = new BufferedReader(new FileReader("C:/Users/user/Desktop/eclipse/�̸����.txt"));
            String data = "";
            // readLine ����� �� ���ξ� �о���δ�
            while ((data = reader.readLine()) != null) {
            	StringTokenizer st = new StringTokenizer(data);
//            	System.out.println(st.countTokens());
            	while(st.hasMoreTokens()) {
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
