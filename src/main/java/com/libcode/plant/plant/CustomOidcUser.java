package com.libcode.plant.plant;

import java.util.Collection;
import java.util.Map;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.core.oidc.OidcIdToken;
import org.springframework.security.oauth2.core.oidc.OidcUserInfo;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;

public class CustomOidcUser implements OidcUser {
    
    private final OidcUser delegate;
    
    public CustomOidcUser(OidcUser oidcUser) {
        this.delegate = oidcUser;
    }
    
    // Delegar todos los métodos de OidcUser
    @Override
    public Map<String, Object> getClaims() {
        return delegate.getClaims();
    }
    
    @Override
    public OidcUserInfo getUserInfo() {
        return delegate.getUserInfo();
    }
    
    @Override
    public OidcIdToken getIdToken() {
        return delegate.getIdToken();
    }
    
    @Override
    public Map<String, Object> getAttributes() {
        return delegate.getAttributes();
    }
    
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return delegate.getAuthorities();
    }
    
    @Override
    public String getName() {
        return delegate.getName();
    }
    
    public String getEmail() {
        // Intenta obtener email de diferentes fuentes
        String email = getAttribute("email");
        
        if (email == null && getUserInfo() != null) {
            email = getUserInfo().getEmail();
        }
        
        if (email == null && getIdToken() != null) {
            email = getIdToken().getEmail();
        }
        
        if (email == null) {
            email = getAttribute("preferred_username");
        }
        
        return email;
    }
    
    // Método auxiliar para obtener atributos
    @SuppressWarnings("unchecked")
    public <T> T getAttribute(String name) {
        return (T) getAttributes().get(name);
    }
}