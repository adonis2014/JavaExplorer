package name.chenyuelin.common;

import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.AlgorithmParameterSpec;
import java.security.spec.InvalidKeySpecException;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;

public final class AesEncryptionUtil {
	private static final SecureRandom SECURE_RANDOM = new SecureRandom();
	private static final String DEFAULT_KEY_ALGORITHM="AES";
	private static final String DEFAULT_CIPHER_ALGORITHM="AES/ECB/PKCS5Padding";//"AES/CBC/PKCS5Padding"
	private static final int DEFAULT_KEY_SIZE=128;//128/192/256
	private static final int ALGORITHM_PARAMETER_SIZE=16;
	
	private AesEncryptionUtil(){}
	
	/**
	 * @return 生成56位密钥
	 */
	public static byte[] initkey(){
		return initkey(DEFAULT_KEY_SIZE);
	}
	
	/**
	 * 生成指定长度密钥
	 * @param keySize 密码长度
	 * @return　密钥
	 */
	public static byte[] initkey(int keySize){
		try {
			KeyGenerator kg = KeyGenerator.getInstance(DEFAULT_KEY_ALGORITHM);
			kg.init(keySize);
			SecretKey secretKey = kg.generateKey();
			return secretKey.getEncoded();
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * 取得8位盐对象
	 * @return 8位长度盐
	 */
	public static IvParameterSpec initIvParameterSpec(){
		return new IvParameterSpec(SECURE_RANDOM.generateSeed(ALGORITHM_PARAMETER_SIZE));
	}
	
	/**
	 * 生成指定长度的盐
	 * @param size 盐长度
	 * @return 盐对象
	 */
	public static IvParameterSpec initIvParameterSpec(int size){
		return new IvParameterSpec(SECURE_RANDOM.generateSeed(size));
	}
	
	/**
	 * AES/ECB/PKCS5Padding加密数据
	 * @param data 待加密数据
	 * @param key 密钥数据
	 * @return 已加密数据
	 */
	public static byte[] encrypt(byte[] data,byte[] key){
		return encrypt(data,DEFAULT_CIPHER_ALGORITHM,key,null);
	}
	
	/**
	 * 跟据指定的加密算法和盐对象加密数据
	 * @param data 待加密数据
	 * @param key 密钥数据
	 * @param cipherAlgorithm 加密算法名称
	 * @param ips 盐对象
	 * @return 已加密数据
	 */
	public static byte[] encrypt(byte[] data,String cipherAlgorithm,byte[] key,AlgorithmParameterSpec ips){
		return cipher(data,key,Cipher.ENCRYPT_MODE,cipherAlgorithm,ips);
	}
	
	/**
	 * AES/ECB/PKCS5Padding解密数据
	 * @param data 待解密数据
	 * @param key 密钥数据
	 * @return 已解密数据
	 */
	public static byte[] decrypt(byte[] data,byte[] key){
		return decrypt(data,DEFAULT_CIPHER_ALGORITHM,key,null);
	}
	
	/**
	 * 跟据指定的解密算法和盐对象解密数据
	 * @param data 待解密数据
	 * @param key 密钥数据
	 * @param cipherAlgorithm 解密算法名称
	 * @param ips 盐对象
	 * @return 已解密数据
	 */
	public static byte[] decrypt(byte[] data,String cipherAlgorithm,byte[] key,AlgorithmParameterSpec ips){
		return cipher(data,key,Cipher.DECRYPT_MODE,cipherAlgorithm,ips);
	}
	
	/**
	 * 加解密处理方法
	 * @param data 待处理密数据
	 * @param key 密钥数据
	 * @param cipherMode 加密或解密操作标记(Cipher.ENCRYPT_MODE,Cipher.DECRYPT_MODE)
	 * @param cipherAlgorithm 处理算法名称
	 * @param ips 盐对象
	 * @return 已处理数据
	 */
	private static byte[] cipher(byte[] data,byte[] key,int cipherMode,String cipherAlgorithm,AlgorithmParameterSpec ips){
		try {
			Key k = tokey(key);
			Cipher cipher = Cipher.getInstance(cipherAlgorithm);
			cipher.init(cipherMode, k, ips);
			return cipher.doFinal(data);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * 把密钥字节流数据转为密钥对象
	 * @param key 密钥字节流数据
	 * @return 密钥对象
	 * @throws InvalidKeyException
	 * @throws InvalidKeySpecException
	 * @throws NoSuchAlgorithmException
	 */
	private static Key tokey(byte[] key)throws InvalidKeyException,InvalidKeySpecException{
		SecretKey secretKey=new SecretKeySpec(key, DEFAULT_KEY_ALGORITHM);
		return secretKey;
	}
	
	public static void main(String[] args){
		System.out.println(DatatypeConverter.printBase64Binary(initkey()));
		System.out.println(DatatypeConverter.printBase64Binary(SECURE_RANDOM.generateSeed(ALGORITHM_PARAMETER_SIZE)));
	}
}
