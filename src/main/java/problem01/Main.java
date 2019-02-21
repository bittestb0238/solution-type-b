package problem01;

public class Main {
	public static void main(String[] args) {
		int[] arr = { -10, -3, 5, 6, -20 };
		findMaxProduct(arr);
	}

	public static void findMaxProduct(int[] arr) {
		int first, second, max = Integer.MIN_VALUE, temp;
		int[] answer = new int[2];
		for(int i = 0; i < arr.length; i++) {
			first = arr[i];
			for(int j = 0; j < arr.length; j++) {
				second = arr[j];
				if(i == j)
					continue;
				
				if((temp = first * second) > max) {
					max = temp;
					answer[0] = first;
					answer[1] = second;
				}
			}
		}
		System.out.println("["+answer[0]+","+answer[1]+"]");
		//
		// 코드를 완성 하십시오.
		//
		
	}
}
