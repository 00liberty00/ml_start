/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ml.authentication;

import ml.query.user.RoleUser;
import java.util.ArrayList;
import java.util.List;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

/**
 *
 * @author dave
 */
public class AuthenticationManag implements AuthenticationManager {

    private final RoleUser roleUser = new RoleUser();
    static final List<GrantedAuthority> AUTHORITIES = new ArrayList<GrantedAuthority>();
    
    @Override
    public Authentication authenticate(Authentication auth) throws AuthenticationException {
        String l = (String) roleUser.listUserRoles(auth.getName());
        AUTHORITIES.add(new SimpleGrantedAuthority(l));
        /*if (l.get(0).equals(AUTHORITIES.get(0).toString())){
        System.out.println("РАВНЫ!!!");
        }*/
        
        return new UsernamePasswordAuthenticationToken(auth.getName(),
                auth.getCredentials(), AUTHORITIES);
    }
}
