package common.utils;

public class PasswordUtil {
    public static Boolean checkPassword(String plainPassword, String encryptedPassword) {
        return MD5Util.encoderByMd5(plainPassword).toLowerCase().equals(encryptedPassword.toLowerCase());
    }
}