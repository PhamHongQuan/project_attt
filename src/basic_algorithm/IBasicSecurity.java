package basic_algorithm;

/**
 * @param <T>: kiểu dữ liệu động của biến key. Tương tự như trên key có thể là mảng, chuỗi, con số,... tùy vào từng thuật toán
 */
public interface IBasicSecurity<T> {
	void genKey();

	void loadKey(String alphabet, T key);

	String encrypt();

	String decrypt();
}
