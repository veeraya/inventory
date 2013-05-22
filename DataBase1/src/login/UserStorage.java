package login;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.security.MessageDigest;
import java.util.ArrayList;

/**
 * Performs read/Write operations for user data
 * @author raviab
 *
 */
public class UserStorage {

	/**
	 * Appends a new user to the user file
	 * @param user The new user registered
	 */
	public static void WriteNewUser(User user) {

		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			FileWriter fWriter = new FileWriter("Users.txt", true);
			String line = "";
			BufferedWriter out = new BufferedWriter(fWriter);
			line += user.getIbmId() + "~" + user.getNickname() + "~"
					+ User.getHashedPassword(user.getPassword())+ "~"+Integer.toString(user.getAccess());
			//md.digest(user.getPassword().getBytes("UTF-8"));
			if(!isEmpty())
			out.newLine();
			out.write(line);
			
			out.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private static boolean isEmpty() {
        // TODO Auto-generated method stub
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(new File("Users.txt"));
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } 
        int b = 0;
        try {
            b = fis.read();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } 
        if (b == -1) 
        {
          return true;
        } 
        return false;
    }
	
	
	/**
	 * @param id the id entered by the user
	 * @param hashedPassword md5 Hash of the password entered
	 * @return The user with the corresponding id and password
	 * @throws IOException 
	 */
	
	public static String checkIfExists(String ibmID,String ID) throws IOException
	{
		String IbmIDprob = "Sorry, this IBM id has already been registered!";
		FileInputStream fstream = new FileInputStream("Users.txt");
		DataInputStream in = new DataInputStream(fstream);
		BufferedReader br = new BufferedReader(new InputStreamReader(in));
		String str;		
		while ((str = br.readLine()) != null) {
			String tokens[] = str.split("~");
			if(ibmID.trim().equals(tokens[0].trim()))
					return IbmIDprob;
			}
		in.close();
		return "";
	}
	
	public static User checkUser(String id, String hashedPassword)
	{
		try{
		FileInputStream fstream = new FileInputStream("Users.txt");
		DataInputStream in = new DataInputStream(fstream);
		BufferedReader br = new BufferedReader(new InputStreamReader(in));
		String str;		
		while ((str = br.readLine()) != null) {
			String tokens[] = str.split("~");
			if(id.trim().equals(tokens[0].trim())&&User.getHashedPassword(hashedPassword).trim().equals(tokens[2].trim()))
			{
				User temp = new User();
				temp.setIbmId(tokens[0]);
				temp.setNickname(tokens[1]);
				temp.setPassword(tokens[2]);
				temp.setAccess(Integer.parseInt(tokens[3]));
				in.close();
				return temp;
			}
		}
		
		in.close();
		return null;
		}catch(Exception e)
	{
		
	}
		return null;
}

	/**
	 * @param bean user containing username/password entered
	 * @return the user logged in
	 */
	public static User login(User bean)
	{
		String username = bean.getIbmId(); 
		String password = bean.getPassword(); 
		if(checkUser(username, password)==null)
		{
			System.out.println("Sorry, you are not a registered user! Please sign up first"); 
			bean.setValid(false);
			return bean;
		}
		else
		{
			bean = checkUser(username,password);
			bean.setValid(true);
			return bean;
		}
	}
	public static ArrayList<User> getUsers()
	{
		ArrayList<User> userList = new ArrayList<User>();
		FileInputStream fstream;
		try {
			fstream = new FileInputStream("Users.txt");
		
		DataInputStream in = new DataInputStream(fstream);
		BufferedReader br = new BufferedReader(new InputStreamReader(in));
		String str;		
		while ((str = br.readLine()) != null) {
			String tokens[] = str.split("~");
			{
				User temp = new User();
				temp.setIbmId(tokens[0]);
				temp.setNickname(tokens[1]);
				temp.setPassword(tokens[2]);
				temp.setAccess(Integer.parseInt(tokens[3]));
				//temp.setAccess(Access.valueOf(tokens[3]);
				userList.add(temp);
			}
		}

		in.close();
		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return userList;

	}
	
	public static void editUser(User currentUser, String newPass) throws IOException {
		// TODO Auto-generated method stub
		ArrayList<User> userList = new ArrayList<User>();
		FileInputStream fstream;
		try {
			fstream = new FileInputStream("Users.txt");
		
		DataInputStream in = new DataInputStream(fstream);
		BufferedReader br = new BufferedReader(new InputStreamReader(in));
		String str;		
		while ((str = br.readLine()) != null) {
			String tokens[] = str.split("~");
			{
				User temp = new User();
				temp.setIbmId(tokens[0]);
				temp.setNickname(tokens[1]);
				temp.setPassword(tokens[2]);
				temp.setAccess(Integer.parseInt(tokens[3]));
				//temp.setAccess(Access.valueOf(tokens[3]);
				userList.add(temp);
			}
		}

		in.close();
		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	for(int i=0;i<userList.size();i++)
	{
		if(userList.get(i).getIbmId().equals(currentUser.ibmId))
		{	userList.get(i).setNickname(currentUser.nickname);
			userList.get(i).setPassword(newPass);
		}
	}
	FileWriter fWriter = new FileWriter("Users.txt");
	BufferedWriter out = new BufferedWriter(fWriter);
	out.close();
	
	for(int i=0;i<userList.size();i++)
	{
		WriteNewUser(userList.get(i));
	}
	
		
		
	}

	public static User findUser(String name) {
		// TODO Auto-generated method stub

		FileInputStream fstream;
		try {
			fstream = new FileInputStream("Users.txt");
		
		DataInputStream in = new DataInputStream(fstream);
		BufferedReader br = new BufferedReader(new InputStreamReader(in));
		String str;		
		while ((str = br.readLine()) != null) {
			String tokens[] = str.split("~");
			if(name.trim().equals(tokens[1].trim()))
			{
				User temp = new User();
				temp.setIbmId(tokens[0]);
				temp.setNickname(tokens[1]);
				temp.setAccess(Integer.parseInt(tokens[3]));
				in.close();
				return temp;
			}
		}

		in.close();
		return null;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
}

	public static void WriteNewUser2(User user) {
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			FileWriter fWriter = new FileWriter("Users.txt", true);
			String line = "";
			BufferedWriter out = new BufferedWriter(fWriter);
			line += user.getIbmId() + "~" + user.getNickname() + "~"
					+ user.getPassword()+ "~"+Integer.toString(user.getAccess());
			//md.digest(user.getPassword().getBytes("UTF-8"));
			if(!isEmpty())
			out.newLine();
			out.write(line);
			
			out.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
		// TODO Auto-generated method stub
		
	}



