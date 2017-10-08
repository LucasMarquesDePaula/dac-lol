/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufpr.tads.dac.lol.model;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 *
 * @author Lucas
 */
public interface Authenticable {

    public String getUsername();

    public void setUsername(String userName);

    public String getPassword();

    public void setPassword(String password);

    public class Util {

        public static String generateHash(String password) {
            StringBuilder hash = new StringBuilder();

            try {
                MessageDigest crypt = MessageDigest.getInstance("MD5");
                byte[] hashedBytes = crypt.digest(password.getBytes());
                char[] digits = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
                for (int idx = 0; idx < hashedBytes.length; ++idx) {
                    byte b = hashedBytes[idx];
                    hash.append(digits[(b & 0xf0) >> 4]);
                    hash.append(digits[b & 0x0f]);
                }
            } catch (NoSuchAlgorithmException e) {
                // Silence is golden
            }

            return hash.toString();
        }
    }
}
