package com.example.demo.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

//依存関係にはBcryptだけ入れています
public class PassEncoderUtil {
	private static final PasswordEncoder ENCODER = new BCryptPasswordEncoder();
	
	private PassEncoderUtil() {}
	
//	ハッシュ化する
	public static String encode(String rawPassword) {
		return ENCODER.encode(rawPassword);
	}
	
//	入力とハッシュ値の比較
	public static boolean matches(
			String rawPassword,
			String encodedPassword) {
		return ENCODER.matches(rawPassword,encodedPassword);
	}

}
