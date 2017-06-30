package utils;

import org.junit.Test;

public class EncryptUtilTest {

	@Test
	public void testEncode() throws Exception {
		String raw = "root";
		String encoded = EncryptUtils.encode(raw);
		System.out.println(encoded);
		raw = "marksinoberg";
		encoded = EncryptUtils.encode(raw);
		System.out.println(encoded);
	}
	
	
	
}
