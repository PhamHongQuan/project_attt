package basic_algorithm;

import java.util.Random;

public class Affine extends ABasicSecurity<int[]> {
	public Affine(String plainText, int[] key, String alphabet) {
		super(plainText, key, alphabet);
		
		// kiểm tra xem mảng key có tồn tại không
		if(key == null) {
			// nếu không thì tạo mới 1 mảng gồm 2 phần tử tương ứng cho a và b
			key = new int[2];
		} else {
			// nếu có thì kiểm tra key đó có đúng 2 phần tử hay không
			if(key.length != 2) {
				throw new IllegalArgumentException("Key chỉ chứa 2 con số !!!");
			} else {
				// kiểm tra a, b trong mảng đó có nằm trong khoảng độ dài cho phép của bảng chữ cái được chọn hay không
				if(!(key[0] >= 0 && key[0] <= selectedAlphabet.length 
				&& key[1] >= 0 && key[1] <= selectedAlphabet.length)) {
					throw new IllegalArgumentException("Key phải là những số nằm trong độ dài của bảng chữ cái "+ selectedAlphabetStr);
				} 
			}
		}
		
	}

	
	/**
	 * Mục tiêu: tạo ra 1 mảng chứa 2 phần tử a và b ngẫu nhiên.
	 * Sau đó gán từng giá trị cho mảng key
	 */
	@Override
	public void genKey() {
        key = new int[2];
        Random random = new Random();
        int a = random.nextInt(selectedAlphabet.length); 
        int b = random.nextInt(selectedAlphabet.length);
        key[0] = a;
        key[1] = b;
    }


	/**
	 * Mục tiêu: gán giá trị của các key mà người dùng nhập vào mảng key.
	 * Kiểm tra giá trị nhập vào có hợp lệ hay không trước khi gán giá trị.
	 */
	@Override
	public void loadKey(int[] keyInput) {
		if(keyInput[0] >= 0 && keyInput[0] <= selectedAlphabet.length 
		&& keyInput[1] >= 0 && keyInput[1] <= selectedAlphabet.length) {
			key[0] = keyInput[0];
			key[1] = keyInput[1];
		} else {
			throw new IllegalArgumentException("Key phải là những số nằm trong độ dài của bảng chữ cái "+selectedAlphabetStr);
		}
	}
	

	
	/**
	 * Mục tiêu: áp dụng công thức để tìm E(x.
	 * Ta dùng công thức: E(x) =  (ax + b) % n
	 * Trong đó: 
	 * 			x là thứ tự từ A - Z (bảng chữ cái EN_n=26) và từ A - Ỵ (bảng chữ cái VI_n=89) bắt đầu từ 0.
	 * 			a, b là giá trị của mảng key.
	 * Ví dụ: trong bảng chữ cái EN (n = 26) ta có: 
	 * 				A B C D E F G H I J K L M N ...
	 * plainText:	A C H D
	 * 		   x:	0 1 2 3 4 5 6 7 8 9 ...
	 * a = 5
	 * b = 3
	 *=> cipherText:D N M S 
	 */
	@Override
	public String encrypt() {
		super.checkKeyValid();
		
		char[] cipherTextArray = new char[plainText.length()];

	    // chuyển plainText thành mảng kí tự
	    char[] plainTextArray = plainText.toCharArray();
	    
	    // độ dài của bảng chữ cái
	    int alphabetLength = selectedAlphabet.length; 
	    
	    int a = key[0];
	    int b = key[1];
	    for(int i = 0; i < plainTextArray.length; i++) {
	    	// tìm vị trí của kí tự trong mảng selectedAlphabet
	    	int x = findCharacterIndex(plainTextArray[i]);
	    	if(x != -1) {
	        	// áp dụng công thức E(x) = (ax + b) % n và gán nó vào cho mảng cipherText
	    		int position = (a * x + b) % alphabetLength;
	            cipherTextArray[i] = selectedAlphabet[position];
	        } else {
	        	cipherTextArray[i] = plainTextArray[i];
	        }
	    }
	    
		String cipherText = new String(cipherTextArray);
		return cipherText;
	}

	
	/**
	 * Tương tự như hàm mã hóa trên, chỉ khác cái công thức tính D(x)
	 * D(x) = (a^-1 * (x-b)) % n
	 * Trong đó: để tìm a^-1 thì áp dụng công thức: a * (a^-1) % n = 1
	 * Ví dụ: trong bảng chữ cái EN (n = 26) ta có: 
	 * 				A B C D E F G H I J K L M N ...
	 * cipherText:	D N M S
	 * 		   	x:	0 1 2 3 4 5 6 7 8 9 ...
	 * a = 5
	 * b = 3
	 *=> plainText: A C H D
	 */
	@Override
	public String decrypt() {
		// cipherText là kết quả của hàm encrypt()
		cipherText = encrypt();
		
	    char[] plainTextArray = new char[cipherText.length()];
		
	    // chuyển cipherText thành mảng kí tự
	    char[] cipherTextArray = cipherText.toCharArray();
	    
	    // độ dài của bảng chữ cái
	    int alphabetLength = selectedAlphabet.length; 
	    
	    int a = key[0];
	    int b = key[1];
	    
	    // Tính nghịch đảo của a
	    int aInverse = modInverse(a, alphabetLength);
	    
	    for (int i = 0; i < cipherTextArray.length; i++) {
	        // tìm vị trí của kí tự trong mảng selectedAlphabet
	        int mark = super.findCharacterIndex(cipherTextArray[i]);
	        
	        if (mark != -1) {
	        	// áp dụng công thức D(x) = (a^-1 (x-b)) % n và gán nó vào cho mảng plainTextArray
	        	int position = (aInverse * (mark - b)) % alphabetLength;
	        	if (position < 0) {
	        	    position += alphabetLength;
	        	}
	        	plainTextArray[i] = selectedAlphabet[position];
	        } else {
	        	plainTextArray[i] = cipherTextArray[i];
	        }
	    }
	    
		String plainText = new String(plainTextArray);
	    return plainText;
	}
	
	// hàm tính nghịch đảo 
	private int modInverse(int a, int m) {
	    a = a % m;
	    for (int x = 1; x < m; x++) {
	        if ((a * x) % m == 1) {
	            return x;
	        }
	    }
	    throw new IllegalArgumentException("Không tồn tại nghịch đảo của a theo modulo m");
	}

   
}
