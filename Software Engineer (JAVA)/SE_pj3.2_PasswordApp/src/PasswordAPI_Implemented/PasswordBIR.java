package PasswordAPI_Implemented;

import java.util.UUID;
import org.bioapi.data.BIR;
import org.bioapi.template.BIR_Implemented;

public class PasswordBIR extends BIR_Implemented
{
	String username = null;
	String password = null;
	UUID id;
	
	public PasswordBIR(String username, String password, UUID id)
	{
		super(null, null, false, null, null, null, null, null);
		
		this.username = username;
		this.password = password;
		this.id = id;
	}
	
	public PasswordBIR(String username, String password)
	{
		this(username, password, UUID.randomUUID());
	}

	public String getPassword()
	{
		return password;
	}

	public String getUsername()
	{
		return username;
	}

	public UUID getIndex()
	{
		return id;
	}

	public BIR getValue()
	{
		return (BIR)this;
	}
}
