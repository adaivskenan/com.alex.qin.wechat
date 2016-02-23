package com.alex.qin.Service;

import java.security.MessageDigest;
import java.util.Arrays;

/**
 * VldtSgntrService(ValidateSignatureService) 验证服务器地址的有效性
 */
public class VldtSgntrService {

	private static final String token = "adaivskenan";

	/**
	 * <p>
	 * <h6>加密/校验流程如下：</h6>
	 * <p>
	 * <ol>
	 * <li>将token、timestamp、nonce三个参数进行字典序排序</li>
	 * <li>将三个参数字符串拼接成一个字符串进行sha1加密</li>
	 * <li>开发者获得加密后的字符串可与signature对比，标识该请求来源于微信</li>
	 */
	public boolean ValidateSignature(String signature, String timestamp, String nonce) {

		String[] arr = { token, timestamp, nonce };
		// 字典序排序
		Arrays.sort(arr);
		// 生成字符串
		StringBuffer content = new StringBuffer();
		for (int i = 0; i < arr.length; i++) {
			content.append(arr[i]);
		}
		// sha1加密
		String temp = getSha1(content.toString());

		if (temp != null) {
			return temp.equals(signature);
		} else {
			return false;
		}

	}

	/**
	 * sha1加密
	 */
	public String getSha1(String str) {
		if (str == null || str.length() == 0) {
			return null;
		}
		char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };
		try {
			MessageDigest mdTemp = MessageDigest.getInstance("SHA1");
			mdTemp.update(str.getBytes("UTF-8"));

			byte[] md = mdTemp.digest();
			int j = md.length;
			char buf[] = new char[j * 2];
			int k = 0;
			for (int i = 0; i < j; i++) {
				byte byte0 = md[i];
				buf[k++] = hexDigits[byte0 >>> 4 & 0xf];
				buf[k++] = hexDigits[byte0 & 0xf];
			}
			return new String(buf);
		} catch (Exception e) {
			return null;
		}
	}

}
