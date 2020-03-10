package myspringboot.demo.util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import java.security.SecureRandom;

/**
 * @author hyimin
 * @description AES加解密工具
 * @date 2019-12-06
 **/
public class AESUtils {
       private static Logger logger = LoggerFactory.getLogger(AESUtils.class);
        /**
         * 编码格式
         */
        private static final String ENCODING = "UTF-8";
        /**
         * 加密算法
         */
        public static final String KEY_ALGORITHM = "AES";
        /**
         * 签名算法
         */
        public static final String SIGN_ALGORITHMS = "SHA1PRNG";

        /**
         * 加密
         *
         * @param content 待加密内容
         * @param key     加密的密钥
         * @return
         */
        public static String encrypt(String content, String key) {
            try {
                KeyGenerator kgen = KeyGenerator.getInstance(KEY_ALGORITHM);
                SecureRandom random = SecureRandom.getInstance(SIGN_ALGORITHMS);
                random.setSeed(key.getBytes(ENCODING));
                kgen.init(128, random);
                SecretKey secretKey = kgen.generateKey();
                byte[] enCodeFormat = secretKey.getEncoded();
                SecretKeySpec secretKeySpec = new SecretKeySpec(enCodeFormat, KEY_ALGORITHM);
                Cipher cipher = Cipher.getInstance(KEY_ALGORITHM);
                byte[] byteContent = content.getBytes(ENCODING);
                cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec);
                byte[] byteRresult = cipher.doFinal(byteContent);
                StringBuffer sb = new StringBuffer();
                for (int i = 0; i < byteRresult.length; i++) {
                    String hex = Integer.toHexString(byteRresult[i] & 0xFF);
                    if (hex.length() == 1) hex = '0' + hex;
                    sb.append(hex.toUpperCase());
                }
                return sb.toString();
            } catch (Exception e) {
                e.toString();
            }
            return null;
        }

        /**
         * 解密
         *
         * @param content 待解密内容
         * @param key     解密的密钥
         * @return
         */
        public static String decrypt(String content, String key) {
            if (content.length() < 1) return null;
            byte[] byteRresult = new byte[content.length() / 2];
            for (int i = 0; i < content.length() / 2; i++) {
                int high = Integer.parseInt(content.substring(i * 2, i * 2 + 1), 16);
                int low = Integer.parseInt(content.substring(i * 2 + 1, i * 2 + 2), 16);
                byteRresult[i] = (byte) (high * 16 + low);
            }
            try {
                KeyGenerator kgen = KeyGenerator.getInstance(KEY_ALGORITHM);
                SecureRandom random = SecureRandom.getInstance(SIGN_ALGORITHMS);
                random.setSeed(key.getBytes(ENCODING));
                kgen.init(128, random);
                SecretKey secretKey = kgen.generateKey();
                byte[] enCodeFormat = secretKey.getEncoded();
                SecretKeySpec secretKeySpec = new SecretKeySpec(enCodeFormat, KEY_ALGORITHM);
                Cipher cipher = Cipher.getInstance(KEY_ALGORITHM);
                cipher.init(Cipher.DECRYPT_MODE, secretKeySpec);
                byte[] result = cipher.doFinal(byteRresult);
                return new String(result, ENCODING);
            } catch (Exception e) {
                //e.toString();
            }
            return null;
        }




    public static void main(String[] args) {
        String s = "谭国猛";
        String s1="452226199109242718";
        String key = "8a6c92117f424a7295a8adbbd0b45797";
        String str = "ekKYOP3LrT8WmON0lpFA7Q==";
        String json= "{\"IDCardValidEnd\":\"\",\"IDCardValidFrom\":\"\",\"attributes\":null,\"authTime\":1574232983306,\"authenType\":\"UNPUBLISH\",\"beanFlag\":null,\"caUserId\":\"UNPUBLISH\",\"cardAuthenType\":\"PASS\",\"cardID\":\"452226199109242719\",\"city\":\"\",\"country\":\"\",\"county\":\"\",\"createTime\":1530061200000,\"creator\":\"\",\"deleteFlag\":false,\"email\":\"985145979@qq.com\",\"errorLoginTimes\":0,\"faceAuthenType\":\"PASS\",\"gradeType\":\"ADVANCED\",\"homePlace\":\"123号\",\"identityType\":\"IdentityCard\",\"lastChgPwdTime\":0,\"loginName\":\"ceshi22\",\"modifier\":\"\",\"modifyTime\":1576468275726,\"name\":\"谭国猛\",\"nation\":\"\",\"password\":\"96E79218965EB72C92A549DD5A330112\",\"province\":\"广西壮族自治区\",\"resetPassword\":false,\"sex\":\"MAN\",\"telephone\":\"13044591211\",\"unid\":\"401333ed23d045b4be7e4c0e6463177d\",\"unitUnid\":\"\",\"userStatus\":\"NORMAL\",\"wxId\":\"\",\"zfbId\":\"\"}";
        System.out.println(AESUtils.encrypt(s,key));
        System.out.println(AESUtils.encrypt(s1,key));
        System.out.println(AESUtils.decrypt(AESUtils.encrypt(s1,key),key));

    }
}
