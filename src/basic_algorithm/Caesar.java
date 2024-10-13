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
	 * Nếu người dùng có key thì nhập key vào,
	 * nhập cả bảng chữ cái để biết số lượng tối đa của key nhập vào
	 * (0 -> 25: đối với EN)
	 * (0 -> 88: đối với VI)
	 */
	@Override
	public void loadKey(String alphabet, Integer key) {
		if(alphabet.equalsIgnoreCase("EN")) {
			// độ dài của key <= 25
			if(key >= 0 && key <= 25) {
				this.key = key;
			} else {
				throw new IllegalArgumentException("Key phải là số nhỏ hơn 26 trong bảng chữ cái tiếng Anh!!!");
			}
			
		} else if (alphabet.equalsIgnoreCase("VI")) {
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

	@Override
	public String encrypt() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String decrypt() {
		// TODO Auto-generated method stub
		return null;
	}

}
