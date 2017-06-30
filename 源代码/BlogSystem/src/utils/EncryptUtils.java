package utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.commons.codec.binary.Hex;


public class EncryptUtils {

	/**
	 * ����������ֶεļ��ܴ���ͨ��md5��ϣ�㷨ʵ�ַǶԳƼ���ʵ�֡�
	 * @param raw  �����ܵ������ֶ����ݡ�32λ
	 * @return  ������ɺ�Ĺ�ϣֵ�����ȹ̶�Ϊ��
	 */
	public static String encode(String raw) {
		MessageDigest md = null;
		try {
			md = MessageDigest.getInstance("md5");
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		byte[] bytes = md.digest(raw.getBytes());
		
		return Hex.encodeHexString(bytes);
	}
	
}
