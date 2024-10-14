package basic_algorithm;

import java.util.Random;

public class Caesar extends ABasicSecurity<Integer> implements IBasicSecurity<Integer>{

	public Caesar(String plainText, Integer key, String alphabet) {
		super(plainText, key, alphabet);
	}

	/**
	 * Mục tiêu: tạo ra 1 con số từ 0 -> 25 (đối với EN)
	 * và từ 0 -> 88 (đối với VI), sau đó gán cho biến key.
	 * key này là số vị trí dịch chuyển đi
	 */
	@Override
	public void genKey() {
		Random random = new Random();
		this.key = random.nextInt(0, selectedAlphabet.length);
	}

	/**
	 * Nếu người dùng có key thì nhập key vào.
	 * Key là 1 con số tương ứng với từng bảng chữ cái
	 * Đối với EN: key phải là con số nằm trong (0, 25)
	 * Đối với VI: key phải là con số nằm trong (0, 88)
	 */
	@Override
	public void loadKey(Integer key) {
		if(this.selectedAlphabetStr.equalsIgnoreCase("EN")) {
			// độ dài của key <= 25
			if(key >= 0 && key <= 25) {
				this.key = key;
			} else {
				throw new IllegalArgumentException("Key phải là số nhỏ hơn 26 trong bảng chữ cái tiếng Anh!!!");
			}
			
		} else if (this.selectedAlphabetStr.equalsIgnoreCase("VI")) {
			// độ dài của key <= 88
			if(key >= 0 && key <= 88) {
				this.key = key;
			} else {
				throw new IllegalArgumentException("Key phải là số nhỏ hơn 89 trong bảng chữ cái tiếng Việt!!!");
			}
		} else {
			// mặc định là EN (key <= 25)
			if(key >= 0 && key <= 25) {
				this.key = key;
			} else {
				throw new IllegalArgumentException("Key phải là số nhỏ hơn 26 trong bảng chữ cái tiếng Anh!!!");
			}
		}
	}

	/**
	 * Mục tiêu: dựa vào khóa k để chuyển plainText thành cipherText
	 * Ví dụ: Trên bảng chữ cái tiếng Anh
	 * 		A B C D E F G H I J K ....
	 * k = 3
	 * 		plainText:  ABC
	 * 	=> cipherText:  DEF (dịch chuyển từ A về sau 3 kí tự)
	 */
	@Override
	public String encrypt() {
		char[] cipherTextArray = new char[plainText.length()];

		// kiểm tra coi người dùng có nhập key không
	    if (this.key == null) {
	        //nếu không thì gọi hàm genKey() để tạo key
	        genKey();
	    } else {
	        // nếu có key thì gọi hàm loadKey() để load key
	        loadKey(key);
	    }
	    
	    // chuyển plainText thành mảng kí tự
	    char[] plainTextArray = this.plainText.toCharArray();
	    
	    // độ dài của bảng chữ cái
	    int alphabetLength = this.selectedAlphabet.length; 
	    
	    for(int i = 0; i < plainTextArray.length; i++) {
	    	// tìm vị trí của kí tự trong mảng selectedAlphabet
	        int mark = findCharacterIndex(plainTextArray[i]);
	        
	        if(mark != -1) {
	        	// dịch chuyển kí tự và sử dụng phép chia lấy dư để quay lại 'A' sau 'Z'
	            cipherTextArray[i] = this.selectedAlphabet[(mark + key) % alphabetLength];
	        } else {
	        	cipherTextArray[i] = plainTextArray[i];
	        }
	    }
	    
	    String cipherText = new String(cipherTextArray);
		return cipherText;
	}

	private int findCharacterIndex(char c) {
	    for (int j = 0; j < selectedAlphabet.length; j++) {
	    	// kiểm tra nếu kí tự hiện tại bằng kí tự trong slectedAlphabet => return vị trí của kí tự đó
	        if (c == selectedAlphabet[j]) {
	            return j; 
	        }
	    }
	    return -1; 
	}
	
	/**
	 * Mục tiêu: dựa vào kết quả của hàm encrypt(), chuyển nó thành plainText trên bảng chữ cái (EN/ VI).
	 * Ví dụ: Trên bảng chữ cái tiếng Anh
	 * 		A B C D E F G H I J K L M N O ....
	 * key:	3
	 * 		cipherText: 	JKL
	 * => 	plainText:		GHI (lùi về trước 3 kí tự)
	 */
	@Override
	public String decrypt() {
		// cipherText là kết quả của hàm encrypt()
		this.cipherText = encrypt();
		
	    char[] decryptedTextArray = new char[cipherText.length()];

	    // chuyển cipherText thành mảng kí tự
	    char[] cipherTextArray = this.cipherText.toCharArray();
	    
	    // độ dài của bảng chữ cái
	    int alphabetLength = this.selectedAlphabet.length; 

	    for (int i = 0; i < cipherTextArray.length; i++) {
	        // tìm vị trí của kí tự trong mảng selectedAlphabet
	        int mark = findCharacterIndex(cipherTextArray[i]);
	        
	        if (mark != -1) {
	            // dịch chuyển kí tự ngược lại và sử dụng phép chia lấy dư để quay lại 'Z' nếu vượt quá 'A'
	            decryptedTextArray[i] = this.selectedAlphabet[(mark - key + alphabetLength) % alphabetLength];
	        } else {
	            decryptedTextArray[i] = cipherTextArray[i];
	        }
	    }
	    
	    String decryptedText = new String(decryptedTextArray);
	    return decryptedText;
	}


}
