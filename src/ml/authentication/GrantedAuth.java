/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ml.authentication;

import java.util.Collection;
import java.util.Iterator;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.GrantedAuthority;

/**
 *
 * @author dave
 */
public class GrantedAuth {

    //Выводит последнюю роль в коллекции
    public Object role() {
        Authentication authenticationn = SecurityContextHolder.getContext().
                getAuthentication();
        Collection<? extends GrantedAuthority> auth = authenticationn.getAuthorities();
        Object pass = authenticationn.getCredentials();
        final Iterator itr = auth.iterator();
        Object lastElement = itr.next();
        while (itr.hasNext()) {
            lastElement = itr.next();
        }
        return lastElement;
    }
}
