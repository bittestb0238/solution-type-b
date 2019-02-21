package problem04;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.Random;
import java.util.StringTokenizer;

public class Main {
	private static char[] list = new char[20];
	private static Random r = new Random();
	private static final String fileName = "./Reusult.txt";
	
	public static void main(String[] args) {
		File file = new File(fileName);

		if (!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		try {
			boolean finish = true;
			int count = 0;//도전횟수 = 엔터를 누른 횟수
			int reCount = 1;//재시도횟수 = 도착에 실패한 횟수
			int acc;// 걸음거리 누적
			long start = System.currentTimeMillis(); //시작하는 시점 계산
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			while (finish) {
				createList();
				acc = 0;// 걸음거리 누적
				
				while (br.readLine() != null) {
					count++;
					acc += r.nextInt(4) + 1;
					if (acc > (list.length-1)) {// 도달한 경우
						finish = false;
						break;
					} else if (list[acc] == '#') {// 가지 못하는 경우
						break;
					}
					printList(acc);
				}

				if (!finish) {// 도착한 경우
					//파일에 기록 후 종료
					br.close();
					long end = System.currentTimeMillis();
					writeHistory(file, count, reCount, end - start);
					System.out.println("종료");
				}else {//도착하지 못한 경우
					reCount++;
				}
			}
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private static void swap(int a, int b) {
		char temp = list[a];
		list[a] = list[b];
		list[b] = temp;
	}

	private static void createList() {

		int unableCount = r.nextInt(20) + 1;
		unableCount = 1;
		for (int i = 0; i < 20; i++) {
			if (i < unableCount) {
				list[i] = '#';
			} else {
				list[i] = 'O';
			}
		}
		for (int i = 0; i < 200; i++) {
			swap(r.nextInt(20), r.nextInt(20));
		}
		printList(0);
	}
	private static void printList(int nowLocation) {
		char[] a = new char[list.length];
		for(int i = 0; i<list.length; i++) {
			if(nowLocation == i) {
				a[i] = '^';
			}else {
				a[i] = ' ';
			}
			System.out.print(list[i]);
		}
		System.out.println();
		for(char i : a) {
			System.out.print(i);
		}
		System.out.println();
	}

	private static void writeHistory(File file, int info, int info2, long info3) {

		try {
			BufferedReader br = new BufferedReader(new FileReader(file));
			PriorityQueue<Player> history = new PriorityQueue<Player>();
			String data;
			StringTokenizer stringTokenizer;
			int count,reCount;
			long time;
			history.add(new Player(info, info2, info3));
			while ((data = br.readLine()) != null) {
				stringTokenizer = new StringTokenizer(data, "/");
				count = Integer.parseInt(stringTokenizer.nextToken());
				reCount = Integer.parseInt(stringTokenizer.nextToken());
				time = Long.parseLong(stringTokenizer.nextToken());
				history.add(new Player(count,reCount,time));
			}
			
			br.close();
			
			BufferedWriter bw = new BufferedWriter(new FileWriter(file));
			
			while(!history.isEmpty()) {
				Player p = history.poll();
				bw.write(p.getStart()+"/"+p.count+"/"+p.time+System.lineSeparator());
				bw.flush();
			}
			
			bw.close();

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}

class Player implements Comparable<Player> {
	int start;// 도전 횟수;
	int count;//
	long time;
	public Player(int start, int reCount, long time) {
		this.start = start;
		this.count = reCount;
		this.time = time;
	}
	public int getStart() {
		return start;
	}

	public int getCount() {
		return count;
	}
	public long getTime() {
		return time;
	}
	@Override
	public int compareTo(Player o) {
		// TODO Auto-generated method stub
		if (start > o.getStart()) {//도전횟수 비교
			return 1;
		} else if(start == o.getStart()){
			if(count > o.getCount()) {//재시도횟수 비교
				return 1;
			}else if(count == o.getStart()) {
				if(time > o.getTime()) {//시간을 비교
					return 1;
				}else {
					return 0;
				}
			}else {
				return -1;
			}
		}else {
			return -1;
		}
	}

}
