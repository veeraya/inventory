package login;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class User {
String ibmId;
String nickname;
String password;
int access;
public boolean valid;


public int getAccess() {
	return access;
}
public void setAccess(int access) {
	this.access = access;
}
public String getIbmId() {
	return ibmId;
}
public void setIbmId(String ibmId) {
	this.ibmId = ibmId;
}
public String getNickname() {
	return nickname;
}
public void setNickname(String nickname) {
	this.nickname = nickname;
}
public String getPassword() {
	return password;
}
public void setPassword(String password) {
	this.password = password;
}

public boolean isValid() 
{ return valid;
}

public void setValid(boolean newValid) 
{
	valid = newValid; 
}
public static String getHashedPassword(String password) {
	// TODO Auto-generated method stub
	MessageDigest md = null;
	try {
		 md = MessageDigest.getInstance("MD5");
	} catch (NoSuchAlgorithmException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	byte[] bytes= null;
	try {
		bytes = md.digest(password.getBytes("UTF-8"));
	} catch (UnsupportedEncodingException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	BigInteger bi = new BigInteger(1, bytes);
    	return String.format("%0" + (bytes.length << 1) + "X", bi);


} 

}
