package test;

public class CalendarTest {
	public static void main(String[] args){
		for(int i = 1; i <= 10; i++){ 
		 	Boolean chkDateFlag = false;
			
			for(int n = 1; n <= 3; n++){
				if(n == i){
					System.out.print(i);
					System.out.println("ボタン");
					chkDateFlag = true;
					break;
				}
			}
			
			if(!chkDateFlag) {
				System.out.print(i);
				System.out.println("ボタン");
			}
		} 
	}

}
