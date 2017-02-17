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
//            /** 파일 쓰기 */
//            BufferedWriter writer = new BufferedWriter(new FileWriter("C:/Users/user/Desktop/eclipse/test.txt"));
//            writer.write("파일쓰기 테스트\r\n");
//            writer.write("다음라인");
//            writer.newLine(); // \r\n 써도 되고 newLine 함수를 사용해 다음라인으로 내려도 된다
// 
//            writer.write("newLine 사용해서 내림\r\n");
//            writer.close();
 
            /** 위에서 쓴 파일을 읽어들인다 */
            BufferedReader reader = new BufferedReader(new FileReader("C:/Users/user/Desktop/eclipse/이름목록.txt"));
            String data = "";
            // readLine 사용해 한 라인씩 읽어들인다
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
