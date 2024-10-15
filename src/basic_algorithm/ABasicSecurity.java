package basic_algorithm;

public abstract class ABasicSecurity<T> {
    protected String plainText, cipherText, selectedAlphabetStr;
    protected T key;
    protected char[] alphabetEn, alphabetVi, selectedAlphabet;

    public ABasicSecurity(String plainText, T keyInput, String alphabet) {
        plainText = formatPlainText(plainText);
        key = keyInput;
        initializeAlphabets();
        selectAlphabet(alphabet);
        validatePlainText();
    }

    // Đảm bảo plainText nhập vào phải là chữ IN HOA và xóa khoảng trắng dư thừa
    private String formatPlainText(String plainText) {
        return plainText.toUpperCase().trim();
    }
    
    // Khởi tạo 2 bảng chữ cái ANH - VIET
    private void initializeAlphabets() {
        // bảng chữ cái tiếng Anh
        alphabetEn = new char[] { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q',
                'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z' };

        // bảng chữ cái tiếng Việt có dấu
        alphabetVi = new char[] { 'A', 'Á', 'À', 'Ả', 'Ã', 'Ạ', 'Ă', 'Ắ', 'Ằ', 'Ẳ', 'Ẵ', 'Ặ', 'Â', 'Ấ', 'Ầ', 'Ẩ', 'Ẫ',
                'Ậ', 'B', 'C', 'D', 'Đ', 'E', 'É', 'È', 'Ẻ', 'Ẽ', 'Ẹ', 'Ê', 'Ế', 'Ề', 'Ể', 'Ễ', 'Ệ', 'G', 'H', 'I', 'Í',
                'Ì', 'Ỉ', 'Ĩ', 'Ị', 'K', 'L', 'M', 'N', 'O', 'Ó', 'Ò', 'Ỏ', 'Õ', 'Ọ', 'Ô', 'Ố', 'Ồ', 'Ổ', 'Ỗ', 'Ộ', 'Ơ',
                'Ớ', 'Ờ', 'Ở', 'Ỡ', 'Ợ', 'P', 'Q', 'R', 'S', 'T', 'U', 'Ú', 'Ù', 'Ủ', 'Ũ', 'Ụ', 'Ư', 'Ứ', 'Ừ', 'Ử', 'Ữ',
                'Ự', 'V', 'X', 'Y', 'Ý', 'Ỳ', 'Ỷ', 'Ỹ', 'Ỵ' };
    }

    // Kiểm tra bảng chữ cái người dùng chọn 
    private void selectAlphabet(String alphabet) {
        if (alphabet.trim().equalsIgnoreCase("EN")) {
            selectedAlphabet = alphabetEn;
            selectedAlphabetStr = "EN";
        } else if (alphabet.trim().equalsIgnoreCase("VI")) {
            selectedAlphabet = alphabetVi;
            selectedAlphabetStr = "VI";
        } else {
            // mặc định là EN
            selectedAlphabet = alphabetEn;
            selectedAlphabetStr = "EN";
        }
    }

    // Kiểm tra từng kí tự (trừ khoảng trắng) thì còn lại có hợp lệ với bảng chữ cái đã chọn không
    private void validatePlainText() {
        for (char c : plainText.toCharArray()) {
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
                throw new IllegalArgumentException("Ký tự '" + c + "' không hợp lệ với bảng chữ cái " + selectedAlphabetStr);
            }
        }
    }

    public abstract void genKey(); 

    public abstract void loadKey(T key); 

    public abstract String encrypt();

    public abstract String decrypt();
}
