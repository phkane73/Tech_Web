package com.ct271.encrypt;
//Mã hóa mật khẩu
import java.security.MessageDigest;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.stereotype.Component;
@Component
public class Encrypt {

	public static String toSHA512(String str) {
		String salt = "dfhash9qkhjasohdfsa@abd";
		String result = null;
		str = str + salt;
		try {
			byte[] dataBytes = str.getBytes("UTF-8");
			MessageDigest md = MessageDigest.getInstance("SHA-512");
			result = Base64.encodeBase64String(md.digest(dataBytes));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
}
