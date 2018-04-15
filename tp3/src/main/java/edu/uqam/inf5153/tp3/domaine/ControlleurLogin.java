package edu.uqam.inf5153.tp3.domaine;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.security.auth.callback.Callback;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.callback.NameCallback;
import javax.security.auth.callback.PasswordCallback;
import javax.security.auth.callback.UnsupportedCallbackException;

/*
 * Le code de la classe provient en partie de
 * https://www.youtube.com/watch?v=xv2xltxnnBU
 * 
 * */
public class ControlleurLogin implements CallbackHandler {
	
	

	public void handle(Callback[] cbArray) throws IOException, UnsupportedCallbackException {
		NameCallback nameCallBack = null;
		PasswordCallback pwdCallBack = null;
		int counter = 0;
		
		while(counter < cbArray.length){
			if(cbArray[counter] instanceof NameCallback){
				nameCallBack = (NameCallback) cbArray[counter++];
				System.out.println(nameCallBack.getPrompt());
				nameCallBack.setName((new BufferedReader(new InputStreamReader(System.in))).readLine());
				nameCallBack.setName((new BufferedReader(new InputStreamReader(System.in))).readLine());
				
			}else if(cbArray[counter] instanceof PasswordCallback){
				pwdCallBack = (PasswordCallback) cbArray[counter++];
				System.out.println(pwdCallBack.getPrompt());
				pwdCallBack.setPassword((new BufferedReader(new InputStreamReader(System.in))).readLine().toCharArray());
				
				
				
			}
		}
		

	}

}