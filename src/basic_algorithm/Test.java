package basic_algorithm;

public class Test {
	public static void main(String[] args) {
		String plainText = "ANH YEU EM";
//		Substitution substitution = new Substitution(plainText, null, "EN");
//		System.out.println("Mã hóa: "+substitution.encrypt());
//		System.out.println("Giải mã: "+substitution.decrypt());
		
		Caesar caesar = new Caesar(plainText, null, plainText);
//		caesar.genKey();
//		System.out.println(caesar.key);
		
		caesar.loadKey("", 21);
		System.out.println(caesar.key);
	}
}
