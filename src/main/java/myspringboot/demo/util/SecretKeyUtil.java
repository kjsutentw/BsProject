package myspringboot.demo.util;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.Key;

public class SecretKeyUtil {

    public static void main(String[] args) throws Exception {
        String content="这是加密内容。。。。";
        // 字符串必须16位
        String key="qnloft.com/blog/";

        System.out.println(encrypt(content , key));
    }
    public static String encrypt(String content, String key) throws Exception {
        try {

            Key keySpec = new SecretKeySpec(key.getBytes(), "AES");    //两个参数，第一个为私钥字节数组， 第二个为加密方式 AES或者DES

            String iv   = "0231345874954435";//必须为16位
            IvParameterSpec ivSpec = new IvParameterSpec(iv.getBytes());

            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, keySpec,ivSpec);

            byte[] byteResult = cipher.doFinal(content.getBytes("utf-8"));

            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < byteResult.length; i++) {
                String hex = Integer.toHexString(byteResult[i] & 0xFF);
                if (hex.length() == 1) {
                    hex = '0' + hex;
                }
                sb.append(hex.toUpperCase());
            }
            return sb.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
