import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;

public class Summit {
	public static void main(String[] args) {
		String input = "3\n3 27 13 15 6 7 5 9\n4 21 9 10 4 5 4 6 2 2 1 4 1 3 2 4\n3 29 13 16 5 8 9 1";
		ArrayList<ArrayList<Integer>> summits = Summit.processInput(input);

		for(int i = 0; i < summits.size(); i++) {
			ArrayList<Integer> verificationTree = Summit.buildSummit(summits.get(i));
			int[] error = Summit.getDifference(summits.get(i), verificationTree);
			System.out.printf("Case %d: %d %d\n", i+1, error[0], error[1]);
		}
	}

	public static ArrayList<ArrayList<Integer>> processInput(String input) {
		ArrayList<ArrayList<Integer>> summits = new ArrayList<ArrayList<Integer>>();
		// Remove first line, indicating number of summits
		input = input.substring(input.indexOf("\n")+1);
		for(String summit : input.split("\n")) {
			// Remove first num, indicated number of levels
			summit = summit.substring(summit.indexOf(" ")+1);
			// Add all points as numbers into 'summit' lists
			summits.add(Arrays.stream(summit.split(" "))
					.map(Integer::parseInt)
					.collect(Collectors.toCollection(ArrayList::new)));
		}
		return summits;
	}

	public static ArrayList<Integer> buildSummit(ArrayList<Integer> inputTree) {
		// Copy the "bottom level" of the summit into a new list
		ArrayList<Integer> newSummit = new ArrayList<Integer> ( inputTree.subList( (inputTree.size() - 1) / 2, inputTree.size()) );

		// Build the full tree
		for(int i = newSummit.size() - 1; i > 0; i--) {
			newSummit.add(0, newSummit.get(i) + newSummit.get(i - 1));
		}

		return newSummit;
	}

	public static int[] getDifference(ArrayList<Integer> originalSummit, ArrayList<Integer> generatedSummit) {
		int[] error = new int[2];
		int errorCount = 0;
		for(int i = 0; i < originalSummit.size(); i++) {
			if( originalSummit.get(i) != generatedSummit.get(i) ) {
				error[0] = i; // Add index of error
				error[1] = generatedSummit.get(i); // Add corrected value
				errorCount++;
			}
		}

		if(errorCount > 1) {
			// Must be on a leaf-node, select right side
			int slowWalker = 0;
			int fastWalker = 0;
			while(fastWalker < originalSummit.size()) {
				if(originalSummit.get(slowWalker++) != originalSummit.get(++fastWalker) + originalSummit.get(++fastWalker)) {
					error[0] = fastWalker;
					error[1] = originalSummit.get(slowWalker - 1) - originalSummit.get(fastWalker - 1);
					break;
				}
			}
		}

		return error;
	}
}
