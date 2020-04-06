import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

/**
 * Takes a string input, number of points followed by a space seperated list of floats
 * indicates points on gives circles
 * 
 * Constraints
 * 1. The rings don't overlap
 * 2. The ring centers are on the x-axis
 * 3. The set of points include all points where the rings intersect the x-axis
 * 
 * Executions walkthrough
 * 1. Processes the input into an arraylist of 2d floats [x, y]
 * 2. Sorts that list ascending based on x values
 * 3. Creates a list of circles (sorted clockwise from leftmost point)
 * 4. Prints that list in set format
 */
public class CircusSort {
	public static void main(String[] args) {
		String input = "9\n4.0 0.0 -3.0 0.0 -7.0 0.0 2.0 2.0 5.0 0.0 0.0 0.0 7.0 0.0 0.5 -1.3 6.5 0.9";

		ArrayList<float[]> coordList = CircusSort.proccessInput(input);
		CircusSort.sortLinearly(coordList);
		
		ArrayList<ArrayList<float[]>> circleList = CircusSort.convertToCircles(coordList);
		
		CircusSort.printCircles(circleList);
	}

	/**
	 * Processed the input into a list of coordinates
	 */
	public static ArrayList<float[]> proccessInput(String textInput) {

		int coordCount = Integer.parseInt(textInput.split("\n")[0]);
		String[] individualPointList = textInput.split("\n")[1].split(" ");

		ArrayList<float[]> pointList = new ArrayList<float[]>();

		for (int i = 0; i < coordCount; i++) {
			float[] coord = new float[2];
			coord[0] = Float.parseFloat(individualPointList[i * 2]);
			coord[1] = Float.parseFloat(individualPointList[i * 2 + 1]);
			pointList.add(coord);
		}

		return pointList;
	}

	public static void sortLinearly(ArrayList<float[]> coordList) {
		coordList.sort(new Comparator<float[]>() {
			public int compare(float[] a, float[] b) {
				if (a[0] > b[0]) {
					return 1;
				}
				if (a[0] < b[0]) {
					return -1;
				}
				return 0;
			}
		});
	}

	public static ArrayList<ArrayList<float[]>> convertToCircles(ArrayList<float[]> coordList) {
		ArrayList<ArrayList<float[]>> circleList = new ArrayList<ArrayList<float[]>>();

		int intersectionCount = 0;
		ArrayList<float[]> currentCircle = new ArrayList<float[]>();
		for (float[] coord : coordList) {
			// If y is 0, add 1 to intersection count
			if (coord[1] == 0) {
				intersectionCount++;
			}

			currentCircle.add(coord);

			// If on a new circle: add to listreset circle list
			if (intersectionCount % 2 == 0) {
				circleList.add(CircusSort.sortCircle(currentCircle));
				currentCircle = new ArrayList<float[]>(); // Reset
			}
		}
		return circleList;
	}

	public static ArrayList<float[]> sortCircle(ArrayList<float[]> circle) {
		circle.sort(new Comparator<float[]>() {
			public int compare(float[] a, float[] b) {
				// If one value if on/above the x axis and the other is below, put above first
				if (a[1] >= 0 && b[1] < 0) {
					return -1;
				}
				if (b[1] >= 0 && a[1] < 0) {
					return 1;
				}
				
				// If above line sort ascending, else descending
				if (a[1] >= 0 || b[1] >= 0) {
					return Float.compare(a[0], b[0]);
				} else {
					return Float.compare(b[0], a[0]);
				}
			}
		});
		return circle;
	}
	
	public static void printCircles(ArrayList<ArrayList<float[]>> circleList) {
		for(int i = 0; i < circleList.size(); i++) {
			System.out.printf("Ring %d:", i+1);
			ArrayList<float[]> circle = circleList.get(i);
			for(int pointNum = 0; pointNum < circle.size(); pointNum++) {
				float[] point = circle.get(pointNum);
				System.out.printf(" (%1.1f,%1.1f)", point[0], point[1]);
			}
			System.out.println("");
		}
	}
}