package com.alex.qin.Service;

import java.security.MessageDigest;
import java.util.Arrays;

/**
 * VldtSgntrService(ValidateSignatureService) ��֤��������ַ����Ч��
 */
public class VldtSgntrService {

	private static final String token = "adaivskenan";

	/**
	 * <p>
	 * <h6>����/У���������£�</h6>
	 * <p>
	 * <ol>
	 * <li>��token��timestamp��nonce�������������ֵ�������</li>
	 * <li>�����������ַ���ƴ�ӳ�һ���ַ�������sha1����</li>
	 * <li>�����߻�ü��ܺ���ַ�������signature�Աȣ���ʶ��������Դ��΢��</li>
	 */
	public boolean ValidateSignature(String signature, String timestamp, String nonce) {

		String[] arr = { token, timestamp, nonce };
		// �ֵ�������
		Arrays.sort(arr);
		// �����ַ���
		StringBuffer content = new StringBuffer();
		for (int i = 0; i < arr.length; i++) {
			content.append(arr[i]);
		}
		// sha1����
		String temp = getSha1(content.toString());

		if (temp != null) {
			return temp.equals(signature);
		} else {
			return false;
		}

	}

	/**
	 * sha1����
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
