/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package veterinaria;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.SecureRandom;
import java.util.Base64;
import java.nio.charset.StandardCharsets;

public class PasswordManager {
    
    public static String convertToBase64(String hexadecimalString) {
        // Eliminar los dos puntos y convertir la cadena hexadecimal en Base64
        byte[] cadenaBytes = hexStringToByteArray(hexadecimalString.replace(":", ""));
        String cadenaBase64 = Base64.getEncoder().encodeToString(cadenaBytes);
        return cadenaBase64;
    }
    public static String hashPassword(String password) throws NoSuchAlgorithmException, InvalidKeySpecException {
        // Número de iteraciones para aplicar el algoritmo de hash
        int iterations = 10000;

        // Convierte la contraseña en un arreglo de caracteres
        char[] chars = password.toCharArray();

        // Genera una sal aleatoria
        byte[] salt = getSalt();

        // Configura los parámetros para el algoritmo de derivación de clave PBKDF2
        PBEKeySpec spec = new PBEKeySpec(chars, salt, iterations, 64 * 8);
        SecretKeyFactory skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");

        // Calcula el hash de la contraseña
        byte[] hash = skf.generateSecret(spec).getEncoded();

        // Retorna el hash de la contraseña con información adicional
        return iterations + ":" + toHex(salt) + ":" + toHex(hash);
    }

    public static boolean verifyPassword(String storedPassword, String enteredPassword)
            throws NoSuchAlgorithmException, InvalidKeySpecException {
        // Divide el hash de contraseña almacenado en sus componentes
        String[] parts = storedPassword.split(":");
        int iterations = Integer.parseInt(parts[0]);
        byte[] salt = fromHex(parts[1]);
        byte[] hash = fromHex(parts[2]);

        // Configura los parámetros para el algoritmo de derivación de clave PBKDF2
        PBEKeySpec spec = new PBEKeySpec(enteredPassword.toCharArray(), salt, iterations, hash.length * 8);
        SecretKeyFactory skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");

        // Calcula el hash de la contraseña ingresada
        byte[] testHash = skf.generateSecret(spec).getEncoded();

        // Compara los hashes para verificar la contraseña
        int diff = hash.length ^ testHash.length;
        for (int i = 0; i < hash.length && i < testHash.length; i++) {
            diff |= hash[i] ^ testHash[i];
        }

        // Si los hashes coinciden, la contraseña es válida
        return diff == 0;
    }

    private static byte[] getSalt() throws NoSuchAlgorithmException {
        // Genera una sal aleatoria
        SecureRandom sr = SecureRandom.getInstance("SHA1PRNG");
        byte[] salt = new byte[16];
        sr.nextBytes(salt);
        return salt;
    }

    private static String toHex(byte[] array) {
        // Convierte un arreglo de bytes en una representación hexadecimal
        StringBuilder sb = new StringBuilder(array.length * 2);
        for (byte b : array) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }

    private static byte[] fromHex(String hex) {
        // Convierte una cadena hexadecimal en un arreglo de bytes
        byte[] bytes = new byte[hex.length() / 2];
        for (int i = 0; i < bytes.length; i++) {
            bytes[i] = (byte) Integer.parseInt(hex.substring(2 * i, 2 * i + 2), 16);
        }
        return bytes;
    }
    
       private static byte[] hexStringToByteArray(String s) {
        int len = s.length();
        byte[] data = new byte[len / 2];
        for (int i = 0; i < len; i += 2) {
            data[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4) + Character.digit(s.charAt(i + 1), 16));
        }
        return data;
    }
}
