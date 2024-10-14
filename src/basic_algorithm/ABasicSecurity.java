package basic_algorithm;

public abstract class ABasicSecurity<T> {
	protected String plainText, cipherText, selectedAlphabetStr;
	protected T key;
	protected char[] alphabetEn, alphabetVi, selectedAlphabet;
	
	public ABasicSecurity(String plainText, T key, String alphabet) {
		this.plainText = plainText.toUpperCase().trim();
		this.key = key;

		// bảng chữ cái tiếng Anh
		alphabetEn = new char[] { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q',
				'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z' };

		// bảng chữ cái tiếng Việt có dấu
		alphabetVi = new char[] { 'A', 'Á', 'À', 'Ả', 'Ã', 'Ạ', 'Ă', 'Ắ', 'Ằ', 'Ẳ', 'Ẵ', 'Ặ', 'Â', 'Ấ', 'Ầ', 'Ẩ', 'Ẫ',
				'Ậ', 'B', 'C', 'D', 'Đ', 'E', 'É', 'È', 'Ẻ', 'Ẽ', 'Ẹ', 'Ê', 'Ế', 'Ề', 'Ể', 'Ễ', 'Ệ', 'G', 'H', 'I', 'Í',
				'Ì', 'Ỉ', 'Ĩ', 'Ị', 'K', 'L', 'M', 'N', 'O', 'Ó', 'Ò', 'Ỏ', 'Õ', 'Ọ', 'Ô', 'Ố', 'Ồ', 'Ổ', 'Ỗ', 'Ộ', 'Ơ',
				'Ớ', 'Ờ', 'Ở', 'Ỡ', 'Ợ', 'P', 'Q', 'R', 'S', 'T', 'U', 'Ú', 'Ù', 'Ủ', 'Ũ', 'Ụ', 'Ư', 'Ứ', 'Ừ', 'Ử', 'Ữ',
				'Ự', 'V', 'X', 'Y', 'Ý', 'Ỳ', 'Ỷ', 'Ỹ', 'Ỵ' };
		
		// kiểm tra người dùng chọn bảng ngôn ngữ nào để giải mã - mã hóa
		if (alphabet.trim().equalsIgnoreCase("EN")) {
			selectedAlphabet = alphabetEn;
			selectedAlphabetStr = "EN";
		} else if (alphabet.trim().equalsIgnoreCase("VI")) {
			selectedAlphabet = alphabetVi;
			selectedAlphabetStr = "VI";
		} else {
			// mặc định là En
			selectedAlphabet = alphabetEn; 
			selectedAlphabetStr = "EN";
		}
		
		// kiểm tra plainText có đúng với bảng chữ cái đã chọn hay không
		for (char c : plainText.toCharArray()) {
			// nếu là khoảng trắng thì bỏ qua
	        if (c == ' ') {
	        	continue;
	        }
	        boolean isValid = false;
	        for (char validChar : selectedAlphabet) {
	            if (Character.toUpperCase(c) == validChar) {
	                isValid = true;
	                break;
	            }
	        }
	        if (!isValid) {
	            throw new IllegalArgumentException("Ký tự '" + c + "' không hợp lệ với bảng chữ cái " + selectedAlphabetStr);
	        }
		}
	}
	
}
