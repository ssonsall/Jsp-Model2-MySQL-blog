package blog.util;

import java.security.MessageDigest;

//256bit 길이 암호, 해시라서 복호화 불가능
public class SHA256 {
//password를 암호화해서 return
	public static String getEncrypt(String rawPassword, String salt) {

		// byte화
		// ex) rawpassword = "qw5000qw"
		// salt = "cos"
		String result = "";
		byte[] b = (rawPassword + salt).getBytes();

		try {
			MessageDigest md = MessageDigest.getInstance("SHA-256");
			md.update(b); // MessageDigest가 SHA-256으로 암호화된 값을 들고 있음

			byte[] bResult = md.digest(); // 암호화된 값을 받을 수 있음
			
//			for (byte data : bResult) {
//				System.out.print(data + " ");
//			}
			
			StringBuffer sb = new StringBuffer();
			
			for (byte data : bResult) {
				sb.append(Integer.toString(data & 0xff, 16));
			}
			
			result = sb.toString();
			System.out.println(result);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public static void main(String[] args) {
		getEncrypt("qw5678qw", "cos");
	}
}
