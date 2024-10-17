package basic_algorithm;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Vigenere extends ABasicSecurity<String>{

	public Vigenere(String plainTextInput, String keyInput, String alphabet) {
		super(plainTextInput, keyInput, alphabet);
	}

	/**
	 * Mục tiêu: tạo ra 1 (ít nhất là 1 kí tự) chuỗi các chữ cái trong bảng chữ cái (được chọn) 
	 * với số lượng từ 1 đến 25 (đối với EN) hoặc 1 đến 88 (đối với VI).
	 * Sau đó gán chuỗi đó cho chuỗi key
	 */
	@Override
	public void genKey() {
		// tạo list các kí tự từ bảng chữ cái được chọn
	    List<Character> letters = new ArrayList<>();
	    for (char c : selectedAlphabet) {
	    	letters.add(c);
	    }
	    
	    // làm lộn xộn các kí tự trong list đó
	    Collections.shuffle(letters);
	    
	    // tạo ra số lượng kí tự
	    Random random = new Random();
	    int keyLen = random.nextInt(1, selectedAlphabet.length);
	    
	    // tạo chuỗi từ list (đã được làm lộn xộn) với số lượng kí tự random 
	    StringBuilder key = new StringBuilder();
	    for (int i = 0; i < keyLen; i++) {
	        key.append(letters.get(i));
	    }
	    
	    this.key = key.toString();
	}

	/**
	 * Mục tiêu: kiểm tra key người dùng nhập có hợp lệ không.
	 * Kiểm tra chiều dài của key và từng kí tự trong key đó có phù hợp với bảng chữ cái được chọn hay không.
	 * Sau đó gán cho biến key
	 */
	@Override
	public void loadKey(String keyInput) {
		if(keyInput.length() <= 0 || keyInput.length() >= selectedAlphabet.length) {
			throw new IllegalArgumentException("Key có chiều dài nhỏ hơn hoặc vượt quá số lượng kí tự trong bảng chữ cái "+selectedAlphabetStr);
		} else {
			for (char c : keyInput.toCharArray()) {
	            // nếu là khoảng trắng thì bỏ qua
	            if (c == ' ') {
	                continue;
	            }
	            boolean isValid = false;
	            for (char validChar : selectedAlphabet) {
	                if (validChar == Character.toUpperCase(c)) {
	                    isValid = true;
	                    break;
	                }
	            }
	            if (!isValid) {
	                throw new IllegalArgumentException("Kí tự '" + c + "' không hợp lệ với bảng chữ cái " + selectedAlphabetStr);
	            }
	        }
			key = keyInput.toUpperCase();
		}
	}

	
	/**
	 * Mục tiêu: Sử dụng phương pháp Vigenere để biến plainText thành cipherText dựa trên key.
	 * Công thức: E(x) = (x + k) % n
	 * Trong đó: 
	 * 		x là vị trí của kí tự trong plainText.
	 * 		k là vị trí tương ứng của kí tự trong key.
	 * Ví dụ: Trên bảng chữ cái tiếng Anh
	 * 			A B C D E F G H I J K L M N O ....
	 * key: LEMONLEMONLE
	 * 	   plainText:	 ATTACKATDAWN
	 * => cipherText:	 LXFOPVEFRNHR (sử dụng key để dịch chuyển các kí tự tương ứng)
	 */

	@Override
	public String encrypt() {
	    super.checkKeyValid();

	    char[] cipherTextArray = new char[plainText.length()];

	    // chuyển plainText thành mảng kí tự
	    char[] plainTextArray = plainText.toCharArray();

	    // độ dài của bảng chữ cái
	    int alphabetLength = selectedAlphabet.length; 
	    
	    for (int i = 0; i < plainTextArray.length; i++) {
	    	// tìm vị trí của kí tự trong mảng selectedAlphabet
	        int x = super.findCharacterIndex(plainTextArray[i]);
	        
	        if (x != -1) {
	            // tìm vị trí tương ứng của kí tự trong khóa
	            int keyIndex = super.findCharacterIndex(key.charAt(i % key.length()));

	            // áp dụng công thức E(x) = (x + k) % n (n là độ dài của bảng chữ cái được chọn)
	            int position = (x + keyIndex) % alphabetLength;
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
	 * Mục tiêu: dựa vào kết quả của hàm encrypt(), chuyển nó thành plainText trên bảng chữ cái (EN/VI).
	 * Công thức: D(y) = (y - k) % n
	 * Trong đó: 
	 * 		y là vị trí của kí tự đã được mã hóa trong bảng chữ cái.
	 * 		k là vị trí tương ứng của kí tự trong key.
	 * Ví dụ: Trên bảng chữ cái tiếng Anh
	 * 			A B C D E F G H I J K L M N O ....
	 * key: LEMONLEMONLE
	 *  	   cipherText:	 LXFOPVEFRNHR
	 * 	=>  	plainText:	 ATTACKATDAWN (sử dụng key để lùi về trước các kí tự tương ứng)
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

	    int keyIndex = 0;
	    int keyLength = key.length();

	    for (int i = 0; i < cipherTextArray.length; i++) {
	        // tìm vị trí của kí tự trong mảng selectedAlphabet
	        int y = super.findCharacterIndex(cipherTextArray[i]);
	        
	        if (y != -1) {
	            // tìm vị trí của kí tự trong khóa
	            int k = super.findCharacterIndex(key.charAt(keyIndex % keyLength));

	            // áp dụng công thức D(y) = (y - k) % n
	            int position = (y - k) % alphabetLength; 
	            
	            // kiểm tra nếu position âm thì cộng thêm độ dài của bảng chữ cái
	            if (position < 0) {
	                position += alphabetLength;
	            }
	            
	            plainTextArray[i] = selectedAlphabet[position];
	            
	            // tăng chỉ số cho khóa chỉ khi kí tự hiện tại là một kí tự chữ cái
	            keyIndex++;
	        } else {
	            // giữ nguyên kí tự không phải trong bảng chữ cái (là khoảng trắng)
	            plainTextArray[i] = cipherTextArray[i]; 
	        }
	    }
	    
	    String plainText = new String(plainTextArray);
	    return plainText;
	}


}
