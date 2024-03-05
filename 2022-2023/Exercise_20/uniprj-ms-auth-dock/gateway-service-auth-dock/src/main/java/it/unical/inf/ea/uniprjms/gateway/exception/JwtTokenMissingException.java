package it.unical.inf.ea.uniprjms.gateway.exception;

import javax.naming.AuthenticationException;
import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class JwtTokenMissingException extends AuthenticationException 
{

	private static final long serialVersionUID = 1L;

	public JwtTokenMissingException(String msg) 
	{
		super(msg);
		log.warn(msg);
	}

}
