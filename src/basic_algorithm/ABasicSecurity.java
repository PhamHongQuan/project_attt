package basic_algorithm;

public abstract class ABasicSecurity<T> {
	protected String plainText, cipherText, selectedAlphabetStr;
	protected T key;
	protected char[] alphabetEn, alphabetVi, selectedAlphabet;
	
	public ABasicSecurity(String plainText, T key, String alphabet) {
		this.plainText = plainText;
		this.key = key;

		// Bảng chữ cái tiếng Anh
		alphabetEn = new char[] { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q',
				'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z' };

		// Bảng chữ cái tiếng Việt có dấu
		alphabetVi = new char[] { 'A', 'Á', 'À', 'Ả', 'Ã', 'Ạ', 'Ă', 'Ắ', 'Ằ', 'Ẳ', 'Ẵ', 'Ặ', 'Â', 'Ấ', 'Ầ', 'Ẩ', 'Ẫ',
				'Ậ', 'B', 'C', 'D', 'Đ', 'E', 'É', 'È', 'Ẻ', 'Ẽ', 'Ẹ', 'Ê', 'Ế', 'Ề', 'Ể', 'Ễ', 'Ệ', 'G', 'H', 'I', 'Í',
				'Ì', 'Ỉ', 'Ĩ', 'Ị', 'K', 'L', 'M', 'N', 'O', 'Ó', 'Ò', 'Ỏ', 'Õ', 'Ọ', 'Ô', 'Ố', 'Ồ', 'Ổ', 'Ỗ', 'Ộ', 'Ơ',
				'Ớ', 'Ờ', 'Ở', 'Ỡ', 'Ợ', 'P', 'Q', 'R', 'S', 'T', 'U', 'Ú', 'Ù', 'Ủ', 'Ũ', 'Ụ', 'Ư', 'Ứ', 'Ừ', 'Ử', 'Ữ',
				'Ự', 'V', 'X', 'Y', 'Ý', 'Ỳ', 'Ỷ', 'Ỹ', 'Ỵ' };
		
		// Kiểm tra người dùng chọn bảng ngôn ngữ nào để giải mã - mã hóa
		if (alphabet.equalsIgnoreCase("EN")) {
			selectedAlphabet = alphabetEn;
			selectedAlphabetStr = "EN";
		} else if (alphabet.equalsIgnoreCase("VI")) {
			selectedAlphabet = alphabetVi;
			selectedAlphabetStr = "VI";
		} else {
			// Mặc định là En
			selectedAlphabet = alphabetEn; 
			selectedAlphabetStr = "EN";
		}
	}
	
}
