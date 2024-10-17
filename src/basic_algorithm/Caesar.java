package basic_algorithm;

import java.util.Random;

public class Caesar extends ABasicSecurity<Integer>{

	public Caesar(String plainText, Integer key, String alphabet) {
		super(plainText, key, alphabet);
	}

	/**
	 * Mục tiêu: tạo ra 1 con số từ 
	 * 0 -> 25 (đối với EN)
	 * 0 -> 88 (đối với VI), sau đó gán cho biến key.
	 * key này là số vị trí dịch chuyển đi
	 */
	@Override
	public void genKey() {
		Random random = new Random();
		key = random.nextInt(0, selectedAlphabet.length);
	}

	/**
	 * Nếu người dùng có key thì nhập key vào.
	 * Key là 1 con số tương ứng với từng bảng chữ cái
	 * Đối với EN: key phải là con số nằm trong (0, 25)
	 * Đối với VI: key phải là con số nằm trong (0, 88)
	 */
	@Override
	public void loadKey(Integer keyInput) {
		if(selectedAlphabetStr.equalsIgnoreCase("EN")) {
			// độ dài của key <= 25
			if(keyInput >= 0 && keyInput <= 25) {
				key = keyInput;
			} else {
				throw new IllegalArgumentException("Key phải là số nhỏ hơn 26 trong bảng chữ cái tiếng Anh!!!");
			}
			
		} else if (selectedAlphabetStr.equalsIgnoreCase("VI")) {
			// độ dài của key <= 88
			if(keyInput >= 0 && keyInput <= 88) {
				key = keyInput;
			} else {
				throw new IllegalArgumentException("Key phải là số nhỏ hơn 89 trong bảng chữ cái tiếng Việt!!!");
			}
		} else {
			// mặc định là EN (key <= 25)
			if(keyInput >= 0 && keyInput <= 25) {
				key = keyInput;
			} else {
				throw new IllegalArgumentException("Key phải là số nhỏ hơn 26 trong bảng chữ cái tiếng Anh!!!");
			}
		}
	}

	/**
	 * Mục tiêu: dựa vào khóa k và áp dụng công thức để chuyển plainText thành cipherText
	 * Công thức: E(x) = (x + k) % n
	 * Trong đó: 
	 * 		x là thứ tự từ A - Z (bảng chữ cái EN_n=26) và từ A - Ỵ (bảng chữ cái VI_n=89) bắt đầu từ 0.
	 * 		n là số lượng kí tự trong từng bảng chữ cái (EN -> n=26) (VI -> n=89)
	 * Ví dụ: Trên bảng chữ cái tiếng Anh
	 * 		A B C D E F G H I J K ....
	 * x =  0 1 2 3 4 5 6 7 8 9 ...
	 * k = 3
	 * 		plainText:  ABC
	 * 	=> cipherText:  DEF (dịch chuyển từ A về sau 3 kí tự)
	 */
	@Override
	public String encrypt() {
		super.checkKeyValid();
		
		char[] cipherTextArray = new char[plainText.length()];

	    // chuyển plainText thành mảng kí tự
	    char[] plainTextArray = plainText.toCharArray();
	    
	    // độ dài của bảng chữ cái
	    int alphabetLength = selectedAlphabet.length; 
	    
	    for(int i = 0; i < plainTextArray.length; i++) {
	    	// tìm vị trí của kí tự trong mảng selectedAlphabet
	        int x = super.findCharacterIndex(plainTextArray[i]);
	        
	        if(x != -1) {
	        	// áp dụng công thức E(x) = (x + k) % n và gán nó vào cho mảng cipherText
	        	int position = (x + key) % alphabetLength;
	            cipherTextArray[i] = selectedAlphabet[position];
	        } else {
	        	// ngược lại thì giữ nguyên
	        	cipherTextArray[i] = plainTextArray[i];
	        }
	    }
	    
	    String cipherText = new String(cipherTextArray);
		return cipherText;
	}

	
	/**
	 * Mục tiêu: dựa vào kết quả của hàm encrypt(), chuyển nó thành plainText trên bảng chữ cái (EN/ VI).
	 * Công thức: D(y) = (y - k) % n
	 * Trong đó: 
	 * 		y là vị trí của kí tự đã được mã hóa trong bảng chữ cái.
	 * Ví dụ: Trên bảng chữ cái tiếng Anh
	 * 		A B C D E F G H I J K L M N O ....
	 * key:	3
	 * 		cipherText: 	JKL
	 * => 	plainText:		GHI (lùi về trước 3 kí tự)
	 */
	@Override
	public String decrypt() {
	    // cipherText là kết quả của hàm encrypt()
	    cipherText = encrypt();
	    
	    char[] plainTextArray = new char[cipherText.length()];

	    // chuyển cipherText thành mảng ký tự
	    char[] cipherTextArray = cipherText.toCharArray();
	    
	    // độ dài của bảng chữ cái
	    int alphabetLength = selectedAlphabet.length; 

	    for (int i = 0; i < cipherTextArray.length; i++) {
	        // tìm vị trí của ký tự trong mảng selectedAlphabet
	        int y = super.findCharacterIndex(cipherTextArray[i]);
	        
	        if (y != -1) {
	            // áp dụng công thức D(y) = (y - k) % n
	            int position = (y - key) % alphabetLength;
	            
	            // kiểm tra nếu position âm thì cộng thêm độ dài của bảng chữ cái
	            if (position < 0) {
	                position += alphabetLength;
	            }
	            
	            plainTextArray[i] = selectedAlphabet[position];
	        } else {
	        	// giữ nguyên kí tự không phải trong bảng chữ cái (là khoảng trắng)
	            plainTextArray[i] = cipherTextArray[i]; 
	        }
	    }
	    
	    String plainText = new String(plainTextArray);
	    return plainText;
	}



}
