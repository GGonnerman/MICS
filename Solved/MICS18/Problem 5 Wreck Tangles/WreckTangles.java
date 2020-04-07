import java.util.ArrayList;

public class WreckTangles {
	public static void main(String[] args) {
		String input = "2\ndragon\nantelope\neagle\nbadger\nrabbit\ntiget\nt-rex\nant";
		ArrayList<ArrayList<String>> wordSets = WreckTangles.processInput(input);
		countCombinations(wordSets.get(0), -1, 0);
		//countCombinations(wordSets.get(1), -1, 0);
	}

	public static ArrayList<ArrayList<String>> processInput(String input) {
		ArrayList<ArrayList<String>> output = new ArrayList<ArrayList<String>>();
		String[] individualInput = input.split("\n"); // Cut at newlines
		int setCount = 0;
		ArrayList<String> set = null;
		for(int i = 1; i < individualInput.length; i++) {
			if((i-1) % 4 == 0) { // Start a new 'set' every 4 words
				set = new ArrayList<String>();
				output.add(set);
			}
			set.add(individualInput[i]);
		}

		for(ArrayList<String> arr : output) {
			for(String s : arr) {
				System.out.printf("%s ", s);
			}
			System.out.println("");
		}

		return output;
	}

	public static void countCombinations(ArrayList<String> input, int offset, int count) {

		for(String s : input) System.out.println(s);

		ArrayList<String> inputCopy = (ArrayList) input.clone();
		// On first instance, clone first vlaue to end
		if(offset == -1) {
			input.add(input.get(0));
			offset = 0;
		}
		// If first 2 words, cut off start else cut off end
		String portion = input.get(0);
		if(input.size() > 3) {
			portion = portion.substring(offset, portion.length());
		} else {
			portion = portion.substring(0, offset);
		}

		if(input.size() == 1) { return; }

		for(int i = 0; i < portion.length(); i++) { // Loop through all characters in first word portion
			char mainChar = portion.charAt(i);
			for(int j = 0; j < input.get(1).length(); j++) { // Loop through all characters in next word
				char altChar = input.get(1).charAt(j);
				System.out.println("S:"+input.size()+" Port:" + portion + " Main:" + mainChar + " In:" + input.get(1) + " Alt:" + altChar);
				if(mainChar == altChar) {
					if(input.size() == 5) {
						input.set(input.size() - 1, input.get(0).substring(0, j+1));
					}
					if(input.size() == 2) {
						System.out.println("Not recusing");
						count++;
						System.out.println(count + "---------------------------------------------------------------------------------------");
					} else {
						System.out.println("Recusing");
						countCombinations(new ArrayList<String>(input.subList(1, input.size())), j, count);
					}
				}
			}
		}
		System.out.println("Failed");
	}
}
