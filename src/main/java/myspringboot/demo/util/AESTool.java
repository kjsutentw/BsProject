package myspringboot.demo.util;



import java.util.Base64;
import java.util.Base64.Encoder;
import java.util.Base64.Decoder;


import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/**
 * Created by Dell on 2017/9/28.
 */
public class AESTool {
    private String ivParameter = "0231345874954435";// 偏移量,可自行修改
    private static AESTool instance = null;

    public AESTool() {}

    public static AESTool getInstance() {
        if (instance == null)
            instance = new AESTool();
        return instance;
    }

    public static String Encrypt(String encData, String secretKey, String vector) throws Exception {

        if (secretKey == null) {
            return null;
        }
        if (secretKey.length() != 16) {
            return null;
        }
        byte[] raw = secretKey.getBytes();
        SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
        IvParameterSpec iv = new IvParameterSpec(vector.getBytes());// 使用CBC模式，需要一个向量iv，可增加加密算法的强度
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv);
        byte[] encrypted = cipher.doFinal(encData.getBytes("utf-8"));

        Encoder encoder = Base64.getEncoder();
        String result = encoder.encodeToString(encrypted);


        //byte[] encrypted = cipher.doFinal(sSrc.getBytes("utf-8"));
        //        return new BASE64Encoder().encode(encrypted);// 此处使用BASE64做转码。

        return result;// 此处使用BASE64做转码。
    }

    /**
     * 加密
     * @param sSrc 加密内容
     * @param encodingAESKey 加解密密钥
     * @return
     * @throws Exception
     */
    public String encrypt(String sSrc,String encodingAESKey) throws Exception {
        byte[] raw = encodingAESKey.getBytes();
        SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
        IvParameterSpec iv = new IvParameterSpec(ivParameter.getBytes());// 使用CBC模式，需要一个向量iv，可增加加密算法的强度
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv);
        byte[] encrypted = cipher.doFinal(sSrc.getBytes("utf-8"));
        Encoder encoder = Base64.getEncoder();
        String result = encoder.encodeToString(encrypted);

        return result;// 此处使用BASE64做转码。
    }

    /**
     * 解密
     * @param sSrc 解密内容
     * @param encodingAESKey 加解密密钥
     * @return
     * @throws Exception
     */
    public String decrypt(String sSrc,String encodingAESKey) throws Exception {
        try {
            byte[] raw = encodingAESKey.getBytes("ASCII");
            SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
            IvParameterSpec iv = new IvParameterSpec(ivParameter.getBytes());
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            cipher.init(Cipher.DECRYPT_MODE, skeySpec, iv);
            Decoder decoder = Base64.getDecoder();
            byte[] encrypted1 = decoder.decode(sSrc);// 先用base64解密

            byte[] original = cipher.doFinal(encrypted1);
            String originalString = new String(original, "utf-8");
            return originalString;
        } catch (Exception ex) {
            return null;
        }
    }

    public String decrypt(String sSrc, String key, String ivs) throws Exception {
        try {
            byte[] raw = key.getBytes("ASCII");
            SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
            IvParameterSpec iv = new IvParameterSpec(ivs.getBytes());
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            cipher.init(Cipher.DECRYPT_MODE, skeySpec, iv);

            Decoder decoder = Base64.getDecoder();
            byte[] encrypted1 = decoder.decode(sSrc);// 先用base64解密

            byte[] original = cipher.doFinal(encrypted1);
            String originalString = new String(original, "utf-8");
            return originalString;
        } catch (Exception ex) {
            return null;
        }
    }

    public static String encodeBytes(byte[] bytes) {
        StringBuffer strBuf = new StringBuffer();

        for (int i = 0; i < bytes.length; i++) {
            strBuf.append((char) (((bytes[i] >> 4) & 0xF) + ((int) 'a')));
            strBuf.append((char) (((bytes[i]) & 0xF) + ((int) 'a')));
        }

        return strBuf.toString();
    }

    public static void main(String[] args) throws Exception {
        // 需要加密的字串
        String cSrc = "{\"telphone\":\"手机号码\"}";
        // 加密用的Key 可以用26个字母和数字组成 此处使用AES-128-CBC加密模式，key需要为16位。
        String encodingAESKey = "20200129!#xm@837";// key，可自行修改

        // 加密
        long lStart = System.currentTimeMillis();
        String enString = AESTool.getInstance().encrypt(cSrc,encodingAESKey);
        System.out.println("加密后的字串是：" + enString);

        long lUseTime = System.currentTimeMillis() - lStart;
        System.out.println("加密耗时：" + lUseTime + "毫秒");
        // 解密
        lStart = System.currentTimeMillis();
        String DeString = AESTool.getInstance().decrypt(enString,encodingAESKey);
        System.out.println("解密后的字串是：" + DeString);
        lUseTime = System.currentTimeMillis() - lStart;
        System.out.println("解密耗时：" + lUseTime + "毫秒");
    }
}
