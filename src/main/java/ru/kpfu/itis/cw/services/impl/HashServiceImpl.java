package ru.kpfu.itis.cw.services.impl;

import ru.kpfu.itis.cw.services.HashService;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class HashServiceImpl implements HashService {
    private static final String ALGORITHM = "MD5";

    @Override
    public String hashPassword(String password) {
        String hashedPassword = null;

        try {
            MessageDigest md = MessageDigest.getInstance(ALGORITHM);
            byte[] messageDigest = md.digest(password.getBytes());
            hashedPassword = convertToHex(messageDigest);

        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }

        return hashedPassword;
    }

    private String convertToHex(final byte[] messageDigest) {
        BigInteger bigint = new BigInteger(1, messageDigest);
        String hexText = bigint.toString(16);
        while (hexText.length() < 32) {
            hexText = "0".concat(hexText);
        }
        return hexText;
    }

    @Override
    public Boolean matches(String inputPassword, String databasePassword) {
        return hashPassword(inputPassword).equals(databasePassword);
    }
}
