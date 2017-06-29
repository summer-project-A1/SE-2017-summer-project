package common.utils;

public class PasswordUtil {
    static public String getEncryptedPassword(String plainPassword) {
        return MD5Util.encoderByMd5(plainPassword).toLowerCase();
    }
    static public boolean checkPassword(String plainPassword, String encryptedPassword) {
        return MD5Util.encoderByMd5(plainPassword).toLowerCase().equals(encryptedPassword.toLowerCase());
    }
}