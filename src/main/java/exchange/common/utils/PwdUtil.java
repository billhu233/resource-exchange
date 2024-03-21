package exchange.common.utils;

import groovy.lang.Tuple2;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.DigestUtils;

import java.util.Random;

/**
 * 密码工具类
 *
 * @author tl
 * @since 2023-11-02
 */
public class PwdUtil {
    private static final String SALT_ONE = "61A98CF8";
    private static final String SALT_TWO = "E71E5ADB";

    /**
     * 获取密码串：从[原始密码]到[数据库密码]
     * <p>
     * 一次加密：前端将原始密码加盐（如：Land2009）md5后传输到后端
     * 二次加密：后端将前端密码再次加盐（如：lhp@%*）md5后存储到数据库
     */
    public static String getPWDStrFromRaw2Db(String password) {
        if (StringUtils.isEmpty(password)) {
            return null;
        }
        return saltAndMd5(saltAndMd5(password, SALT_ONE), SALT_TWO);
    }

    /**
     * 获取密码串：从[一次加密后密码]到[数据库密码]
     * <p>
     * 一次加密：前端将原始密码加盐（如：Land2009）md5后传输到后端
     * 二次加密：后端将前端密码再次加盐（如：lhp@%*）md5后存储到数据库
     */
    public static String getPWDStrFromOne2Db(String password) {
        if (StringUtils.isEmpty(password)) {
            return null;
        }
        return saltAndMd5(password, SALT_TWO);
    }

    /**
     * 获取密码串：从[原始密码]到[一次加密后密码]
     * <p>
     * 一次加密：前端将原始密码加盐（如：Land2009）md5后传输到后端
     * 二次加密：后端将前端密码再次加盐（如：lhp@%*）md5后存储到数据库
     */
    public static String getPWDStrFromRaw2One(String password) {
        if (StringUtils.isEmpty(password)) {
            return null;
        }
        return saltAndMd5(password, SALT_ONE);
    }

    public static void main(String[] args) {
        System.out.println(getPWDStrFromRaw2One("a1234567"));
    }

    /**
     * 加盐、MD5
     */
    private static String saltAndMd5(String str, String salt) {
        String strWithSalt = str.concat(salt);
        return DigestUtils.md5DigestAsHex(strWithSalt.getBytes());
    }

    /**
     * 生成指定位数随机数字串
     */
    public static String getGiveLenRandomNum(int len) {
        StringBuilder str = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < len; i++) {
            str.append(random.nextInt(9));
        }
        return str.toString();
    }

    /**
     * 获取初始密码
     */
    public static Tuple2<String, String> getInitPwd() {
        String raw = getGiveLenRandomNum(6);
        return new Tuple2<>(raw, getPWDStrFromRaw2Db(raw));
    }

}

