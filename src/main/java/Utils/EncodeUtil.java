package Utils;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.UUID;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.DigestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 编码与解码操作工具类
 *
 * @author shenxiangwei
 * @since 1.0
 */
public class EncodeUtil {

    private static final Logger logger = LoggerFactory.getLogger(EncodeUtil.class);

    private String  UTF_8 = "UTF-8";

    /**
     * 将 URL 编码
     */
    public static String encodeURL(String str) {
        String target;
        try {
            target = URLEncoder.encode(str, "UTF-8");
        } catch (Exception e) {
            logger.error("编码出错！", e);
            throw new RuntimeException(e);
        }
        return target;
    }

    /**
     * 将 URL 解码
     */
    public static String decodeURL(String str) {
        String target;
        try {
            target = URLDecoder.decode(str, "UTF-8");
        } catch (Exception e) {
            logger.error("解码出错！", e);
            throw new RuntimeException(e);
        }
        return target;
    }

    /**
     * 将字符串 Base64 编码
     */
    public static String encodeBASE64(String str) {
        String target;
        try {
            target = Base64.encodeBase64URLSafeString(str.getBytes("UTF-8"));
        } catch (UnsupportedEncodingException e) {
            logger.error("编码出错！", e);
            throw new RuntimeException(e);
        }
        return target;
    }

    /**
     * 将字符串 Base64 解码
     */
    public static String decodeBASE64(String str) {
        String target;
        try {
            target = new String(Base64.decodeBase64(str), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            logger.error("解码出错！", e);
            throw new RuntimeException(e);
        }
        return target;
    }

    /**
     * 将字符串 MD5 加密
     */
    public static String encryptMD5(String str) {
        return DigestUtils.md5Hex(str);
    }

    /**
     * 将字符串 SHA 加密
     */
    public static String encryptSHA(String str) {
        return DigestUtils.sha1Hex(str);
    }

    /**
     * 获取 UUID（32位）
     */
    public static String createUUID() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }

    public static void main(String[] args) {

        String testStr = "钢铁侠";
        logger.info("测试字符串："+testStr);

        String urlEncodeStr = encodeURL(testStr);
        logger.info("URL 编码："+urlEncodeStr);
        logger.info("URL 解码："+decodeURL(urlEncodeStr));

        String base64EncodeStr = encodeBASE64(testStr);
        logger.info("Base64编码："+base64EncodeStr);
        logger.info("Base64解码："+decodeBASE64(base64EncodeStr));

        logger.info("md5加密："+encryptMD5(testStr));
        logger.info("SHA加密："+encryptSHA(testStr));
        logger.info("获取UUID："+createUUID());
    }
}