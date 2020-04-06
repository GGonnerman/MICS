public class InvisibleInk {
	public static void main(String[] args) {
		String input = "tsstsssttstsststssssstsssststtsttttttssstsstsssst";
		String binary = InvisibleInk.convertToBinary(input);
		String output = processAsBinary(binary);
		System.out.println(output);
	}
	
	public static String convertToBinary(String input) {
		input = input.replace((char) 115, '0'); // 's' replace with 32 (Space)
		input = input.replace((char) 116, '1'); // 't' replace with 9 (tab)
		return input;
	}
	
	public static String processAsBinary(String binary) {
		String result = "";
		for(int i = 0; i < binary.length(); i += 7) {
			String bit = binary.substring(i, i+7);
			result += Character.toString(Integer.valueOf(bit, 2));
		}
		return result;
	}
}