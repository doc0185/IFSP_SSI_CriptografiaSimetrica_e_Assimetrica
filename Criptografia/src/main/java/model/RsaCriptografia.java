package model;

import java.nio.charset.StandardCharsets;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.MessageDigest;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Arrays;

import javax.crypto.Cipher;

public class RsaCriptografia {

	public static KeyPair geraRSAKeyPair() throws Exception {
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        keyPairGenerator.initialize(3072);
        return keyPairGenerator.generateKeyPair();
    }
	
	public static byte[] encripta(String mensagem, PublicKey publicKey) throws Exception {
       Cipher cipher = Cipher.getInstance("RSA");
       cipher.init(Cipher.ENCRYPT_MODE, publicKey);
       return cipher.doFinal(mensagem.getBytes(StandardCharsets.UTF_8));
   }
	
	
	public static String decripta(byte[] mensagemCifrada, PrivateKey privateKey) throws Exception {
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.DECRYPT_MODE, privateKey);
        byte[] mensagemAberta = cipher.doFinal(mensagemCifrada);
        return new String(mensagemAberta);
    }
	
	public static byte[] geraAssinaturaDigital(byte[] mensagem, PrivateKey privateKey) throws Exception {
	    MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
	    byte[] hashMensagem = messageDigest.digest(mensagem);
	     
	    Cipher cipher = Cipher.getInstance("RSA");
	    cipher.init(Cipher.ENCRYPT_MODE, privateKey);
	    return cipher.doFinal(hashMensagem);
	}
	
	public static boolean verifica(byte[] mensagem, byte[] assinaturaDigital, PublicKey publicKey) throws Exception {
        MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
        byte[] hashMensagem = messageDigest.digest(mensagem);
 
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);
        byte[] hashDecriptado = cipher.doFinal(assinaturaDigital);
         
        return Arrays.equals(hashDecriptado, hashMensagem);
    }
}