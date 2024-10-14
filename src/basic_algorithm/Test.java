package basic_algorithm;

public class Test {
	public static void main(String[] args) {
		String plainText = "i love you";
		Substitution substitution = new Substitution(plainText, null, "EN");
		System.out.println("Mã hóa: "+substitution.encrypt());
		System.out.println("Giải mã: "+substitution.decrypt());
		System.out.println();
		Caesar caesar = new Caesar(plainText, null, "EN");
		caesar.loadKey(3);
		System.out.println("Mã hóa: "+caesar.encrypt());
		System.out.println("Giải mã: "+caesar.decrypt());
	}
}
