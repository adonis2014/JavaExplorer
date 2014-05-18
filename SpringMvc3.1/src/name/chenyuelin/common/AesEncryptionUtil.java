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
	 * @return ����56λ��Կ
	 */
	public static byte[] initkey(){
		return initkey(DEFAULT_KEY_SIZE);
	}
	
	/**
	 * ����ָ��������Կ
	 * @param keySize ���볤��
	 * @return����Կ
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
	 * ȡ��8λ�ζ���
	 * @return 8λ������
	 */
	public static IvParameterSpec initIvParameterSpec(){
		return new IvParameterSpec(SECURE_RANDOM.generateSeed(ALGORITHM_PARAMETER_SIZE));
	}
	
	/**
	 * ����ָ�����ȵ���
	 * @param size �γ���
	 * @return �ζ���
	 */
	public static IvParameterSpec initIvParameterSpec(int size){
		return new IvParameterSpec(SECURE_RANDOM.generateSeed(size));
	}
	
	/**
	 * AES/ECB/PKCS5Padding��������
	 * @param data ����������
	 * @param key ��Կ����
	 * @return �Ѽ�������
	 */
	public static byte[] encrypt(byte[] data,byte[] key){
		return encrypt(data,DEFAULT_CIPHER_ALGORITHM,key,null);
	}
	
	/**
	 * ����ָ���ļ����㷨���ζ����������
	 * @param data ����������
	 * @param key ��Կ����
	 * @param cipherAlgorithm �����㷨����
	 * @param ips �ζ���
	 * @return �Ѽ�������
	 */
	public static byte[] encrypt(byte[] data,String cipherAlgorithm,byte[] key,AlgorithmParameterSpec ips){
		return cipher(data,key,Cipher.ENCRYPT_MODE,cipherAlgorithm,ips);
	}
	
	/**
	 * AES/ECB/PKCS5Padding��������
	 * @param data ����������
	 * @param key ��Կ����
	 * @return �ѽ�������
	 */
	public static byte[] decrypt(byte[] data,byte[] key){
		return decrypt(data,DEFAULT_CIPHER_ALGORITHM,key,null);
	}
	
	/**
	 * ����ָ���Ľ����㷨���ζ����������
	 * @param data ����������
	 * @param key ��Կ����
	 * @param cipherAlgorithm �����㷨����
	 * @param ips �ζ���
	 * @return �ѽ�������
	 */
	public static byte[] decrypt(byte[] data,String cipherAlgorithm,byte[] key,AlgorithmParameterSpec ips){
		return cipher(data,key,Cipher.DECRYPT_MODE,cipherAlgorithm,ips);
	}
	
	/**
	 * �ӽ��ܴ�����
	 * @param data ������������
	 * @param key ��Կ����
	 * @param cipherMode ���ܻ���ܲ������(Cipher.ENCRYPT_MODE,Cipher.DECRYPT_MODE)
	 * @param cipherAlgorithm �����㷨����
	 * @param ips �ζ���
	 * @return �Ѵ�������
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
	 * ����Կ�ֽ�������תΪ��Կ����
	 * @param key ��Կ�ֽ�������
	 * @return ��Կ����
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
