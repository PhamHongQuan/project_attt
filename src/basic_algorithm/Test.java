package basic_algorithm;

public class Test {
	public static void main(String[] args) {
		String plainText = "anh yeu em z";
		
//		int[] arr = new int[2];
//        arr[0] = 5;
//        arr[1] = 3;
//		Affine affine = new Affine(plainText, arr, "EN");
//		System.out.println(affine.encrypt());
//		System.out.println(affine.decrypt());
		
//		Substitution substitution = new Substitution(plainText, null, "EN");
//		System.out.println("Mã hóa: "+substitution.encrypt());
//		System.out.println("Giải mã: "+substitution.decrypt());
//		System.out.println();
		Caesar caesar = new Caesar(plainText, null, "EN");
		caesar.loadKey(3);
		System.out.println("Mã hóa: "+caesar.encrypt());
		System.out.println("Giải mã: "+caesar.decrypt());
	}
}
