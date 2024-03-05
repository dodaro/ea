package it.unical.inf.ea.auth.config.security;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor
@Slf4j
public class CustomAuthenticationProvider implements AuthenticationProvider {

    private final LoggedUserDetailsService userService;

    private final PasswordEncoder passwordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        UsernamePasswordAuthenticationToken authToken = null;

        if (authentication == null) {
            return null;
        }
        String uname = String.valueOf(authentication.getName());
        String upassw = String.valueOf(authentication.getCredentials());

        log.info("Username: " + uname + " Password: " + upassw);

        //User is an org.springframework.security.core.userdetails.User object
        UserDetails userDetails = userService.loadUserByUsername(uname);

        if (passwordEncoder.matches(upassw, userDetails.getPassword())) {
            authToken = new UsernamePasswordAuthenticationToken(userDetails.getUsername(), null, userDetails.getAuthorities());
        }    
        return authToken;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
    
}











// 1st - initial version
// @Component
// public class MyFirstCustomAuthenticationProvider implements AuthenticationProvider {

//     private String uname = "";  
//     private String upassw = "";
//     // private String existingUserName = "Aladdin";
//     // private String existingPassword = "open sesame";


//     @Autowired
//     MyCustomUserDetailsService userService;

//     @Override
//     public Authentication authenticate(Authentication authentication) throws AuthenticationException {
//         // Put here your authentication logic 
//         // You can access the username via the authentication object 
//         // If the authentication is successful
//         // then return a valid authentication object
//         // e.g. using the UsernamePasswordAuthenticationToken class
//         // It may return null if the AuthenticationProvider is unable 
//         // to support authentication of the passed Authentication object. 
//         // In such a case, the next AuthenticationProvider that supports 
//         //the presented Authentication class will be tried.
         
//         uname = String.valueOf(authentication.getName());
//         upassw = String.valueOf(authentication.getCredentials());

//         //User is an org.springframework.security.core.userdetails.User object
//         User user = (User) userService.loadUserByUsername(uname);
//         if (user == null) return null; //throw new UsernameNotFoundException(String.format("Username not found"));
//         if (user.getPassword().equals(upassw)) {
//             UsernamePasswordAuthenticationToken authenticationToken;
//             authenticationToken = new UsernamePasswordAuthenticationToken(user.getUsername(), null, user.getAuthorities());
//             return authenticationToken;
//         } else {
//             return null;
//         }    
    
//     }

//     @Override
//     public boolean supports(Class<?> authentication) {
//         // Here compare the Authentication object 
//         // with your preferable implementation class 
//         // of such an Authentication object, e.g.:
//         // with UserUsernamePasswordAuthenticationToken class.
//         // Returning true does not guarantee that the AuthenticationProvider
//         // will be able to authenticate the presented instance of the Authentication class. 
//         // However, if the comparison is true, this will signal to the
//         // Provider Manager that this Authentication Provider 
//         // is a candidate (=supports) the indicated Authentication object
//         return authentication.equals(UsernamePasswordAuthenticationToken.class);
//     }


//     // private List<GrantedAuthority> getAuthorities() {
        
//     //     List<GrantedAuthority> authorities = new ArrayList<>();
//     //     List<String> roles = Arrays.asList("ADMIN", "USER");
//     //     roles.forEach(role -> authorities.add(new SimpleGrantedAuthority(role)));
//     //     return authorities;

//     // }


    
// }













