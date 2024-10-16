package basic_algorithm;

import java.util.Random;

public class Substitution extends ABasicSecurity<char[]> {

	public Substitution(String plainText, char[] key, String alphabet) {
		super(plainText, key, alphabet);
	}

	/**
	 * Mục tiêu: tạo 1 mảng các kí tự được sắp xếp lại theo bảng chữ cái ban đầu (EN/ VI). 
	 * Sau đó gán cho biến key. 
	 * Ví dụ: bảng chữ cái EN: 		A B C D .... 
	 * => kết quả sau khi genKey:	M D O S....
	 */
	@Override
	public void genKey() {
		char[] keyChar = new char[selectedAlphabet.length];

		// copy kí tự từ bảng đã chọn sang mảng keyChar
		for (int i = 0; i < selectedAlphabet.length; i++) {
			keyChar[i] = selectedAlphabet[i];
		}

		Random random = new Random();
		for (int i = 0; i < keyChar.length; i++) {
			// j là biến random từ 0 -> len - 1
			int j = random.nextInt(keyChar.length);

			// hàm swap để đổi chỗ keyChar[i] và keyChar[j] trong mảng keyChar
			swap(keyChar, i, j);
		}

		key = keyChar;
	}

	private void swap(char[] array, int i, int j) {
		char temp = array[i];
		array[i] = array[j];
		array[j] = temp;
	}

	/**
	 * Nếu người dùng có key thì nhập key vào.
	 * Key là 1 mảng kí tự có chiều dài bằng với chiều dài của mảng kí tự được chọn
	 * Đối với EN: mảng key phải có 26 kí tự
	 * Đối với VI: mảng key phải có 89 kí tự
	 */
	@Override
	public void loadKey(char[] keyInput) {
		// key thuộc bảng chữ cái tiếng Anh
		if (selectedAlphabetStr.equalsIgnoreCase("EN")) {
			// nếu độ dài bằng độ dài của bảng kí tự được chọn thì gán cho biến key
			if (keyInput.length == selectedAlphabet.length) {
				key = keyInput;
			} else {
				throw new IllegalArgumentException("Key không đủ 26 kí tự trong bảng chữ cái tiếng Anh!!!");
			}
		}
		// key thuộc bảng chữ cái tiếng Việt
		else if (selectedAlphabetStr.equalsIgnoreCase("VI")) {
			if (keyInput.length == selectedAlphabet.length) {
				key = keyInput;
			} else {
				throw new IllegalArgumentException("Key không đủ 89 kí tự trong bảng chữ cái tiếng Anh!!!");
			}
		}
		// mặc định là tiếng Anh
		else {
			if (keyInput.length == selectedAlphabet.length) {
				key = keyInput;
			} else {
				throw new IllegalArgumentException("Key không đủ 26 kí tự trong bảng chữ cái tiếng Anh!!!");
			}
		}
	}

	/**
	 * Mục tiêu: dựa vào mảng key để biến plainText thành cipherText trên bảng chữ cái (EN/ VI).
	 * Ví dụ: Trên bảng chữ cái tiếng Anh
	 * 		A B C D E F G H I J L M N O ....
	 * key: K O U H D M N S D C A Q T Y ....
	 * STT: 0 1 2 3 4 5 6 7 8 9 ....
	 * 	  plainText:  DA CO ANH
	 * => cipherText: HK UY KTS
	 */
	@Override
	public String encrypt() {
		super.checkKeyValid();
		
	    char[] cipherTextArray = new char[plainText.length()];

	    // chuyển plainText thành mảng kí tự
	    char[] plainTextArray = plainText.toCharArray();

	    for (int i = 0; i < plainTextArray.length; i++) {
	        // tìm vị trí của kí tự trong mảng selectedAlphabet
	        int mark = super.findCharacterIndex(plainTextArray[i]);

	        if (mark != -1) {
	            // thay thế kí tự và lưu vào mảng cipherText
	        	cipherTextArray[i] = key[mark];
	        } else {
	            // nếu không thì giữ nguyên kí tự gốc
	            cipherTextArray[i] = plainTextArray[i];
	        }
	    }

	    String cipherText = new String(cipherTextArray);
	    return cipherText; 
	}


	/**
	 * Mục tiêu: dựa vào kết quả của hàm encrypt(), chuyển nó thành plainText trên bảng chữ cái (EN/ VI).
	 * Ví dụ: Trên bảng chữ cái tiếng Anh
	 * 		A B C D E F G H I J K L N O ....
	 * key:	K O U H D M N S D C A Q T Y ....
	 * 		cipherText: 	HK UY KTS
	 * => 	plainText:		DA CO ANH 
	 */
	@Override
	public String decrypt() {
		// cipherText là kết quả của hàm encrypt()
		cipherText = encrypt();
		
	    char[] plainTextArray = new char[cipherText.length()];

	    // chuyển cipherText thành mảng kí tự
	    char[] cipherTextArray = cipherText.toCharArray();

	    for (int i = 0; i < cipherTextArray.length; i++) {
	        // tìm vị trí của kí tự đã mã hóa trong mảng key
	        int mark = findKeyCharacterIndex(cipherTextArray[i]);

	        if (mark != -1) {
	            // thay thế kí tự và lưu vào mảng plainText
	            plainTextArray[i] = selectedAlphabet[mark];
	        } else {
	            // giữ nguyên kí tự đã mã hóa
	            plainTextArray[i] = cipherTextArray[i];
	        }
	    }

	    String plainText = new String(plainTextArray);
	    return plainText; 
	}

	private int findKeyCharacterIndex(char c) {
	    for (int j = 0; j < key.length; j++) {
	        // kiểm tra nếu kí tự hiện tại bằng kí tự trong key => trả về vị trí của kí tự đó
	        if (c == key[j]) {
	            return j; 
	        }
	    }
	    return -1; 
	}

}
