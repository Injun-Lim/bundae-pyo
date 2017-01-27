package fileIO;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.StringTokenizer;
 
class fileIO1 {
    public static ArrayList readFile(String fileName) throws Exception {
        // ���Ͽ��� ���� �о� ���̱� ���� ���� ��Ʈ�� ����
        BufferedReader bufReader = new BufferedReader(new FileReader(fileName));
        // �� �� ������ �о� �� ���ڿ����� ���� ������ ������ ���� ��ū ������ ����
        StringTokenizer strToken = null;
        // ������ �迭���� ���� ��ü ����
        ArrayList iList = new ArrayList();
        // ������ ������ ������ ��� ���� ������ �迭 
        String[] iArray = null;
        // ���Ͽ��� �о� �帰 �� ��..
        String line = null;
        
        // �迭�� ������ �о� ���δ�.
        while((line = bufReader.readLine()) != null) {
            // ��������� ������ ���� ��ū ����
            strToken = new StringTokenizer(line);
            // ������ ũ�⿡ �´� �迭�� ũ��� ����
            iArray = new String[strToken.countTokens()];
            // �迭�� ������ �ε��� �ѹ�
            int idx = 0;
            
            // ��ū�� �о� ����..
            while(strToken.hasMoreTokens()) {
                // ������ �迭�� ����
                iArray[idx++] = strToken.nextToken();
            }
            // ��� ����Ʈ�� ����
            iList.add(iArray);
        }
        
        // ��� ����Ʈ ��ȯ
        return iList;
    }
 
    public static void main(String[] args) throws Exception {
        // ���Ͽ��� �������� �о� ���δ�.
        ArrayList iList = readFile("data.txt");
        
        // ���
        System.out.println("< ������ ���� >");
        
        // �� �������� ����Ѵ�.
        for(int i=0; i<iList.size(); i++) {
        	String[] iArray = (String[])iList.get(i);
            for(int j=0; j<iArray.length; j++) {
                System.out.print(iArray[j] + " ");
            }
            System.out.println();
        }
    }
}
