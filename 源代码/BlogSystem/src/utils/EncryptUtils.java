package utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.commons.codec.binary.Hex;


public class EncryptUtils {

	/**
	 * 针对于密码字段的加密处理。通过md5哈希算法实现非对称加密实现。
	 * @param raw  待加密的密码字段内容。32位
	 * @return  加密完成后的哈希值，长度固定为：
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
