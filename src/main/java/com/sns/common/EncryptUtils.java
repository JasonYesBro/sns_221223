package com.sns.common;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class EncryptUtils {
	// 스프링의 bean을 따로 사용하지 않기 때문에 일반 자바 객체로 생성할것임
	public static String md5(String message) {
		String encData = "";
		try {
			// MessageDigest 객체 생성 getInstance
			MessageDigest md = MessageDigest.getInstance("MD5");
			
			// bytes 단위로 변환
			byte[] bytes = message.getBytes();
			md.update(bytes);
			// bytes -> hash값으로 반환
			byte[] digest = md.digest();
			
			// digest의 길이만큼 for문 돈다.
			for (int i = 0; i < digest.length; i++) {
				// 각 자리를 돌면서 16진수로 변환하는 과정을 거쳐 encData에 append 해준다.
				encData += Integer.toHexString(digest[i] & 0xff); // 16진수로 변환하는 과정
			}
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		// encData 반환
		return encData;
	}
}
