import java.util.ArrayList;
import java.util.Arrays;

public class Summit {
	public static void main(String[] args) {
		String input = "3\n3 27 13 15 6 7 5 9\n4 21 9 10 4 5 4 6 2 2 1 4 1 3 2 4\n3 29 13 16 5 8 9 1";

		ArrayList<ArrayList<int>> summits = Summit.processInput(input);

		for(int i = 0; i < summits.size(); i++) {
			int[] error = Summit.getError(summits.get(i));
			System.out.printf("Case %d: %d %d\n", i, error[0], error[1]);
		}
	}

	public static ArrayList<ArrayList<int>> processInput(String input) {
		ArrayList<ArrayList<int>> summits = new ArrayList<ArrayList<int>>();
		// Remove first line, indicating number of summits
		input = input.substring(input.indexOf("\n")+1);
		for(String summit : input.split("\n")) {
			// Remove first num, indicated number of levels
			summit = summit.substring(summit.indexOf(" ")+1);
			// Add all points as numbers into 'summit' lists
			summits.add(Arrays.stream(summit.split(" "))
					.mapToInt(Integer::parseInt)
					.toArray());
		}
		return summits;
	}
		
	public static generateTree(ArrayList<ArrayList<int>> inputTree) {

	/*
	public static int[] getError(int[] input) {
		int[] error = new int[2];
		
		int slowWalker = 0;
		int fastWalker = 0;
		while(fastWalker < input.length) {
			if(input[slowWalker++] != input[++fastWalker] + input[++fastWalker]) {
				System.out.printf("s:%d f1:%d f2:%d\n",input[slowWalker-1],input[fastWalker-1],input[fastWalker]);
				error[0] = fastWalker;
				error[1] = input[slowWalker - 1] - input[fastWalker - 1];
				break;
			}
		}
	return error;
	}
	*/
}
