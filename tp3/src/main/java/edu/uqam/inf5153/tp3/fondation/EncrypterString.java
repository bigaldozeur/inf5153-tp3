package edu.uqam.inf5153.tp3.fondation;

import org.mindrot.jbcrypt.BCrypt;

public class EncrypterString {
	
	public static String encrypter(String pwd){
	// Hash a password for the first time
	String hashed = BCrypt.hashpw(pwd, BCrypt.gensalt());

	return hashed;
	}
	
	public static boolean verifierHash(String chaine, String hash){
		
		return BCrypt.checkpw(chaine, hash);
	}
}
