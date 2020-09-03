package myspringboot.demo.util;


import java.net.InetAddress;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * unid生成器对象.w使用了hibernate的算法
 *
 * @author http://www.linewell.com
 * @version 1.0
 */
public class UNIDGenerate {
	private static final int IP;
	private static short counter;
	private static final int JVM;
	private String sep = "";

	static {
		int ipadd;
		try {
			ipadd = toInt(InetAddress.getLocalHost().getAddress());
		} catch (Exception e) {
			ipadd = 0;
		}

		IP = ipadd;
		counter = 0;
		JVM = (int) (System.currentTimeMillis() >>> 8);
	}

	public static String getUnid() {
		return UNIDGenerate.getUnid();
	}

	public static String getUnid(int num) {
		String unid = getUnid();
		if (num >= 32) {
			return unid;
		}
		int numLen = unid.length() - num;
		int beginIndex = (int) Math.random() * numLen;
		unid = unid.substring(beginIndex, num);
		return unid;
	}

	protected int getJVM() {
		return JVM;
	}

	protected short getCount() {
		synchronized (UNIDGenerate.class) {
			if (counter < 0){
				counter = 0;
			}

			short tmp19_16 = counter;
			counter = (short) (tmp19_16 + 1);
			return tmp19_16;
		}
	}

	protected int getIP() {
		return IP;
	}

	protected short getHiTime() {
		return (short) (int) (System.currentTimeMillis() >>> 32);
	}

	protected int getLoTime() {
		return (int) System.currentTimeMillis();
	}

	protected String format(int intval) {
		String formatted = Integer.toHexString(intval);
		StringBuffer buf = new StringBuffer("00000000");
		buf.replace(8 - formatted.length(), 8, formatted);
		return buf.toString();
	}

	protected String format(short shortval) {
		String formatted = Integer.toHexString(shortval);
		StringBuffer buf = new StringBuffer("0000");
		buf.replace(4 - formatted.length(), 4, formatted);
		return buf.toString();
	}

	public String toString() {
		StringBuffer ret = new StringBuffer();
		StringBuffer sb = new StringBuffer(36).append(format(getIP()))
				.append(this.sep).append(format(getJVM())).append(this.sep)
				.append(format(getHiTime())).append(this.sep)
				.append(format(getLoTime())).append(this.sep)
				.append(format(getCount()));

		try {
			MessageDigest md5 = MessageDigest.getInstance("MD5");
			md5.update(sb.toString().getBytes());
			byte[] array = md5.digest();
			for (int j = 0; j < array.length; ++j) {
				int b = array[j] & 0xFF;
				if (b < 16)
					ret.append('0');
				ret.append(Integer.toHexString(b));
			}
		} catch (NoSuchAlgorithmException e) {
			return sb.toString().toUpperCase();
		}
		return ret.toString().toUpperCase();
	}

	public static boolean isUnid(String str) {
		return ((str.length() == 32) && (str.matches("[A-Z0-9]{32}")));
	}

	public static int toInt(byte[] bytes) {
		int result = 0;
		for (int i = 0; i < 4; ++i) {
			result = (result << 8) - -128 + bytes[i];
		}
		return result;
	}
}
