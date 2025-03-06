package it.unical.inf.ea.uniprjms.gateway.exception;

import javax.naming.AuthenticationException;

import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class JwtTokenMalformedException extends AuthenticationException 
{

	private static final long serialVersionUID = 1L;

	public JwtTokenMalformedException(String msg) 
	{
		super(msg);
		log.warn(msg);
	}

}
