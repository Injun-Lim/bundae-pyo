package fileIO;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.StringTokenizer;
 
class fileIO1 {
    public static ArrayList readFile(String fileName) throws Exception {
        // 파일에서 값을 읽어 들이기 위한 파일 스트림 생성
        BufferedReader bufReader = new BufferedReader(new FileReader(fileName));
        // 한 줄 단위로 읽어 온 문자열들을 공백 단위로 나누기 위한 토큰 나이저 생성
        StringTokenizer strToken = null;
        // 정수형 배열들을 담을 객체 생성
        ArrayList iList = new ArrayList();
        // 파일의 한줄의 정수를 담기 위한 정수형 배열 
        String[] iArray = null;
        // 파일에서 읽어 드린 한 줄..
        String line = null;
        
        // 배열의 끝까지 읽어 들인다.
        while((line = bufReader.readLine()) != null) {
            // 공백단위로 나누기 위해 토큰 생성
            strToken = new StringTokenizer(line);
            // 한줄의 크기에 맞는 배열의 크기로 생성
            iArray = new String[strToken.countTokens()];
            // 배열에 저장할 인덱스 넘버
            int idx = 0;
            
            // 토큰을 읽어 들임..
            while(strToken.hasMoreTokens()) {
                // 정수를 배열에 삽입
                iArray[idx++] = strToken.nextToken();
            }
            // 어레이 리스트에 삽입
            iList.add(iArray);
        }
        
        // 어레이 리스트 반환
        return iList;
    }
 
    public static void main(String[] args) throws Exception {
        // 파일에서 정수들을 읽어 들인다.
        ArrayList iList = readFile("data.txt");
        
        // 출력
        System.out.println("< 파일의 내용 >");
        
        // 각 정수들을 출력한다.
        for(int i=0; i<iList.size(); i++) {
        	String[] iArray = (String[])iList.get(i);
            for(int j=0; j<iArray.length; j++) {
                System.out.print(iArray[j] + " ");
            }
            System.out.println();
        }
    }
}
