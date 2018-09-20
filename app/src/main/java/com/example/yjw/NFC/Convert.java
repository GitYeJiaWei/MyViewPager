package com.example.yjw.NFC;

import java.io.ByteArrayOutputStream;

public class Convert {

	public static String bytesToHexString(byte[] src) {
		StringBuilder stringBuilder = new StringBuilder("");
		if (src == null || src.length <= 0) {
			return null;
		}



		for (int i = 1; i < src.length; i++) {
			int v = src[i] & 0xFF;
			String hv = Integer.toHexString(v);
			if (hv.length() < 2) {
				stringBuilder.append(0);
			}
			stringBuilder.append(hv);
		}
		return stringBuilder.toString().trim();

	}


	/**
	 * Convert hex string to byte[]
	 * @param hexString the hex string
	 * @return byte[]
	 */

	public static byte[] hexStringToBytes(String hexString) {

		if (hexString == null || hexString.equals("")) {
			return null;
		}
		hexString = hexString.toUpperCase();

		int length = hexString.length() / 2;

		char[] hexChars = hexString.toCharArray();

		byte[] d = new byte[length];

		for (int i = 0; i < length; i++) {

			int pos = i * 2;

			d[i] = (byte) (charToByte(hexChars[pos]) << 4 | charToByte(hexChars[pos + 1]));

		}
		return d;
	}

	/**

	 * Convert char to byte
	 * @param c char
	 * @return byte
	 */
	private static byte charToByte(char c) {

		return (byte) "0123456789ABCDEF".indexOf(c);

	}

	public static String toHexString(String s)
	{
		String str="";
		for (int i=0;i<s.length();i++)
		{
			int ch = (int)s.charAt(i);
			String s4 = Integer.toHexString(ch);
			str = str + s4;
		}
		return str;
	}

	/*
	 * 16进制数字字符集
	 */
	private static String hexString="0123456789ABCDEF";
	/*
	 * 将字符串编码成16进制数字,适用于所有字符（包括中文）
	 */
	public static String encode(String str)
	{
		//根据默认编码获取字节数组
		byte[] bytes=str.getBytes();
		StringBuilder sb=new StringBuilder(bytes.length*2);
		//将字节数组中每个字节拆解成2位16进制整数
		for(int i=0;i<bytes.length;i++)
		{
			sb.append(hexString.charAt((bytes[i]&0xf0)>>4));
			sb.append(hexString.charAt((bytes[i]&0x0f)>>0));
		}
		return sb.toString();
	}
	/*
	 * 将16进制数字解码成字符串,适用于所有字符（包括中文）
	 */
	public static String decode(String bytes)
	{
		ByteArrayOutputStream baos=new ByteArrayOutputStream(bytes.length()/2);
		//将每2位16进制整数组装成一个字节
		for(int i=0;i<bytes.length();i+=2)
			baos.write((hexString.indexOf(bytes.charAt(i))<<4 |hexString.indexOf(bytes.charAt(i+1))));
		return new String(baos.toByteArray());
	}


	public static String getparseHumidity(String humiditystr)
	{
		int humidity=Integer.parseInt(humiditystr, 16);
		if(humidity>=Integer.parseInt("0014", 16) && humidity<=Integer.parseInt("0016", 16))
		{
			return "30%";
		}
		else if(humidity>=Integer.parseInt("0004", 16) && humidity<=Integer.parseInt("0007", 16))
		{
			return "35%";
		}
		else if(humidity>=Integer.parseInt("0007", 16) && humidity<=Integer.parseInt("0009", 16))
		{
			return "40%";
		}
		else if(humidity>=Integer.parseInt("0000", 16) && humidity<=Integer.parseInt("0000", 16))
		{
			return "45%";
		}
		else if(humidity>=Integer.parseInt("0000", 16) && humidity<=Integer.parseInt("0000", 16))
		{
			return "50%";
		}
		else if(humidity>=Integer.parseInt("0000", 16) && humidity<=Integer.parseInt("0000", 16))
		{
			return "55%";
		}
		else if(humidity>=Integer.parseInt("0000", 16) && humidity<=Integer.parseInt("0003", 16))
		{
			return "60%";
		}
		else if(humidity>=Integer.parseInt("0011", 16) && humidity<=Integer.parseInt("0022", 16))
		{
			return "65%";
		}
		else if(humidity>=Integer.parseInt("0023", 16) && humidity<=Integer.parseInt("0036", 16))
		{
			return "70%";
		}
		else if(humidity>=Integer.parseInt("0037", 16) && humidity<=Integer.parseInt("004d", 16))
		{
			return "75%";
		}
		else if(humidity>=Integer.parseInt("004e", 16) && humidity<=Integer.parseInt("00a2", 16))
		{
			return "80%";
		}
		else if(humidity>=Integer.parseInt("00a3", 16) && humidity<=Integer.parseInt("012a", 16))
		{
			return "85%";
		}
		else if(humidity>=Integer.parseInt("012b", 16) && humidity<=Integer.parseInt("01e7", 16))
		{
			return "90%";
		}
		else if(humidity>=Integer.parseInt("01e8", 16) && humidity<=Integer.parseInt("0256", 16))
		{
			return "95%";
		}
		else if(humidity>=Integer.parseInt("0257", 16) && humidity<=Integer.parseInt("0279", 16))
		{
			return "99%";
		}
		else
		{
			return "0%";
		}
	}

}
