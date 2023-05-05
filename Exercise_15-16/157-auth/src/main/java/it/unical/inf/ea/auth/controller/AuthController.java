package it.unical.inf.ea.auth.controller;

import it.unical.inf.ea.auth.config.security.SecurityConstants;
import it.unical.inf.ea.auth.config.security.TokenUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
//@RequestMapping("/api")
public class AuthController {

    private final Log logger = LogFactory.getLog(getClass());

    @GetMapping(value = "/auth/login")
    public ResponseEntity<String> signIn(jakarta.servlet.http.HttpServletResponse response)  {

        try {

            String uname = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    
            logger.info("Principal/Username obtained from SecurityContextHolder: " + uname);
            logger.info("Is Authenticated? : " + SecurityContextHolder.getContext().getAuthentication().isAuthenticated());
    
            String msg = "OK " + uname + " ! You have been Logged In!";
            String token = TokenUtils.generateJWTUserToken(uname);
            logger.info("Generated JWT Token: " + token);

            response.addHeader(SecurityConstants.AUTH_HEADER, SecurityConstants.BEARER_TOKEN_PREFIX + token);
            return new ResponseEntity<>(msg, HttpStatus.OK);    

        } catch (Exception ex) {
            logger.info("Exception error: " + ex.getMessage()); 
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
        }
    }


    @PostMapping(value = "/auth/signup")
    public ResponseEntity<String> signUp() {

        String msg = "Ok! You have been Registered! - Now you can Login!";

        System.out.println(msg);
        try {
        
            return new ResponseEntity<>(msg, HttpStatus.OK);    

        } catch (Exception e) {
            
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        
    }
    
}