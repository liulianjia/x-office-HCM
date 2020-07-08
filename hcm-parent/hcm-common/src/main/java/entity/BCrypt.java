package entity;

import org.apache.commons.lang3.RandomUtils;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
/**
 * BCrypt 加密测试
 */
public class BCrypt {
    //每次计算得到的散列值不同
    @Test
    public void test() {
        String password = "BCrypt Test" + RandomUtils.nextInt(0, 999);
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        for (int i = 0, length = RandomUtils.nextInt(1, 6); i <= length; i++) {
            String encodeResult = bCryptPasswordEncoder.encode(password);
            System.out.println(encodeResult);
            Assert.assertTrue(bCryptPasswordEncoder.matches(password, encodeResult));
        }
        bCryptPasswordEncoder = new BCryptPasswordEncoder(BCryptPasswordEncoder.BCryptVersion.$2B);
        for (int i = 0, length = RandomUtils.nextInt(1, 6); i <= length; i++) {
            String encodeResult = bCryptPasswordEncoder.encode(password);
            System.out.println(encodeResult);
            Assert.assertTrue(bCryptPasswordEncoder.matches(password, encodeResult));
        }
        bCryptPasswordEncoder = new BCryptPasswordEncoder(BCryptPasswordEncoder.BCryptVersion.$2Y);
        for (int i = 0, length = RandomUtils.nextInt(1, 6); i <= length; i++) {
            String encodeResult = bCryptPasswordEncoder.encode(password);
            System.out.println(encodeResult);
            Assert.assertTrue(bCryptPasswordEncoder.matches(password, encodeResult));
        }
    }
}
