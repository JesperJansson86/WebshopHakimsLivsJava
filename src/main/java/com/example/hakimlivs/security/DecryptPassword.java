package com.example.hakimlivs.security;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

public class DecryptPassword {

    public static void main(String[] args) throws NoSuchAlgorithmException, InvalidKeySpecException
    {
        SecurityController securityController = new SecurityController();
        String  originalPassword = "password";
        String generatedSecuredPasswordHash = securityController.generateStrongPasswordHash(originalPassword);
        System.out.println(generatedSecuredPasswordHash);

        boolean matched = validatePassword("password", generatedSecuredPasswordHash);
        System.out.println(matched);

        matched = validatePassword("passwörd", generatedSecuredPasswordHash);
        System.out.println(matched);
    }

    private static boolean validatePassword(String originalPassword, String storedPassword) throws NoSuchAlgorithmException, InvalidKeySpecException
    {
        String[] parts = storedPassword.split(":");
        int iterations = Integer.parseInt(parts[0]);
        byte[] salt = fromHex(parts[1]);
        byte[] hash = fromHex(parts[2]);

        PBEKeySpec spec = new PBEKeySpec(originalPassword.toCharArray(), salt, iterations, hash.length * 8);
        SecretKeyFactory skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
        byte[] testHash = skf.generateSecret(spec).getEncoded();

        int difference = hash.length ^ testHash.length;
        for(int i = 0; i < hash.length && i < testHash.length; i++)
        {
            //Båda hasharna jåmnförs tecken för tecken, om det inte stämmer överens hela vägen kommer difference inte att vara
            // == 0  Det är bitwise operatorerna | och ^ som används.
            difference |= hash[i] ^ testHash[i];
        }
        return difference == 0;
    }

    //Metod för att konvertera hexadecimalt till binärt
    private static byte[] fromHex(String hex) throws NoSuchAlgorithmException
    {
        byte[] bytes = new byte[hex.length() / 2];
        for(int i = 0; i<bytes.length ;i++)
        {
            bytes[i] = (byte)Integer.parseInt(hex.substring(2 * i, 2 * i + 2), 16);
        }
        return bytes;
    }
}
