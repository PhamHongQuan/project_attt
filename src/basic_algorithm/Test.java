package basic_algorithm;

public class Test {
	public static void main(String[] args) {
		String plainText = "ATTACKATDAWN";
		
//		Substitution substitution = new Substitution(plainText, null, "EN");
//		System.out.println("Thay the: ");
//		System.out.println("Mã hóa: "+substitution.encrypt());
//		System.out.println("Giải mã: "+substitution.decrypt());
//		System.out.println();
//		Caesar caesar = new Caesar(plainText, null, "en");
//		caesar.loadKey(3);
//		System.out.println("dich chuyen:");
//		System.out.println("Mã hóa: "+caesar.encrypt());
//		System.out.println("Giải mã: "+caesar.decrypt());
//		System.out.println();
//		
//		int[] arr = new int[2];
//        arr[0] = 5;
//        arr[1] = 3;
//		Affine affine = new Affine(plainText, arr, "EN");
//		System.out.println("Affine:");
//		System.out.println("Mã hóa: "+affine.encrypt());
//		System.out.println("Giải mã: "+affine.decrypt());
//		System.out.println();
		
		System.out.println("Vigenere: ");
		Vigenere vigenere = new Vigenere(plainText, null, "EN");
		System.out.println("Mã hóa: "+vigenere.encrypt());
		System.out.println("Giải mã: "+vigenere.decrypt());
		 
	}
}
