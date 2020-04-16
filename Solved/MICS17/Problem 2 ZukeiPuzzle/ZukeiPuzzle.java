import java.util.ArrayList;
import java.util.Arrays;

public class ZukeiPuzzle {
	public static void main(String[] args) {
		String input = "2\n" + 
				"7 1 1 7 1 7 3 1 3 3 9 3 2 8 5\n" + 
				"8 4 7 8 4 7 4 4 1 5 9 2 8 5 4 8 9";
		ArrayList<ArrayList<Point>> processedInput = ZukeiPuzzle.processInput(input);

		for(int i = 0; i < processedInput.size(); i++) {
			ArrayList<Point> pointList = processedInput.get(i);
			System.out.printf("Case %d: ", i+1);
			pointList	
				.stream()
				.filter(point -> pointList.stream().filter(p2 -> p2.getX() == point.getX()).count() > 1)
				.filter(point -> pointList.stream().filter(p2 -> p2.getY() == point.getY()).count() > 1)
				.filter(point -> pointList.stream().filter(p2 -> p2.getX() == point.getX()).count() > 1)
				.filter(point -> pointList.stream().filter(p2 -> p2.getY() == point.getY()).count() > 1)
				.sorted((p1, p2)->p1.getY().compareTo(p2.getY())==0?p1.getX().compareTo(p2.getX()):p1.getY().compareTo(p2.getY()))
				.forEach(point -> System.out.print(point+" "));
			System.out.println("");
			}
	}
	
	public static ArrayList<ArrayList<Point>> processInput(String input) {
		String[] splitInput = input.split("\n");
		ArrayList<ArrayList<Point>> processedInput = new ArrayList<ArrayList<Point>>();
		for(int i = 1; i < splitInput.length; i++) {
			String[] splitLine = splitInput[i].split(" ");
			ArrayList<Point> processedLine = new ArrayList<Point>();
			for(int j = 1; j < splitLine.length; j += 2) {
				processedLine.add(
					new Point(Integer.parseInt(splitLine[j]), Integer.parseInt(splitLine[j+1])));
			}
			processedInput.add(processedLine);
		}
		return processedInput;
	}
}

class Point {
	private int x;
	private int y;

	Point(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public String toString() { return x+" "+y; }

	public Integer getX() { return x; }
	public Integer getY() { return y; }
}
