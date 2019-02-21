package problem02;

import java.util.StringTokenizer;

import com.sun.org.apache.xml.internal.serializer.utils.StringToIntTable;

import sun.util.locale.StringTokenIterator;

public class Main {

	public static void main(String[] args) {
		String[] expressions = {"3 + 4", "12 * 4", "8 / 2", "10 - 2"};
		
		for(String expression : expressions) {
			String[] tokens = expression.split(" ");
			
			String arith = tokens[1];
			int num1 = Integer.parseInt(tokens[0]);
			int num2 = Integer.parseInt(tokens[2]);

			
			// num1, num2, arith 세 개의 변수와 getInstance 메소드를 사용하여
			// 문제의 결과화면에 맞게 출력하는 계산기 나머지 코드를 아래에 완성 하십시오.
			// 주어진 코드는 수정하지 않습니다.
			Arithmetic arithmetic = getInstance(arith);
			if(arithmetic == null) {
				System.out.println("ERROR");
			}else {
				System.out.println(arithmetic.calculate(num1, num2));
			}
			
		
		
		}
	}
	
	public static Arithmetic getInstance(String arith) {
		// 메소드 구현을 완성 하십시오.
		
		switch(arith) {
		case "+":
			return new Add();
		case "-":
			return new Sub();
		case "*":
			return new Mul();
		case "/":
			return new Div();
			default:
				return null;
		}
	}
}

class Add implements Arithmetic{

	@Override
	public int calculate(int a, int b) {
		// TODO Auto-generated method stub
		return a+b;
	}
}

class Sub implements Arithmetic{

	@Override
	public int calculate(int a, int b) {
		// TODO Auto-generated method stub
		return a-b;
	}
}

class Mul implements Arithmetic{

	@Override
	public int calculate(int a, int b) {
		// TODO Auto-generated method stub
		return a*b;
	}
}

class Div implements Arithmetic{

	@Override
	public int calculate(int a, int b) {
		// TODO Auto-generated method stub
		return a/b;
	}
}
