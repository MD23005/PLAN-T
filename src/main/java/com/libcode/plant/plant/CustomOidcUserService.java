package com.libcode.plant.plant;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserRequest;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserService;
import org.springframework.security.oauth2.core.oidc.OidcIdToken;
import org.springframework.security.oauth2.core.oidc.user.DefaultOidcUser;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Service;

@Service
public class CustomOidcUserService extends OidcUserService {

    // Usar la misma clave que en tu SuccessHandler
    private static final String ROLE_CLAIM_KEY = "https://plan-t-rol7.onrender.com/claims/role";

    @Override
    public OidcUser loadUser(OidcUserRequest userRequest) {
        // Cargar el usuario base
        OidcUser oidcUser = super.loadUser(userRequest);

        System.out.println("=== CUSTOM OIDC USER SERVICE ===");
        System.out.println("Cargando usuario: " + oidcUser.getAttribute("email"));

        // Obtener claims del ID token
        Map<String, Object> claims = new HashMap<>(oidcUser.getClaims());
        
        // Log de claims relevantes para debugging
        System.out.println("=== CLAIMS DISPONIBLES EN ID TOKEN ===");
        claims.forEach((key, value) -> {
            if (key.contains("role") || key.contains("dev-qi8b5nrabbvawh0y") || 
                key.equals("sub") || key.equals("email") || key.equals("name")) {
                System.out.println("🔑 " + key + " = " + value);
            }
        });

        // Extraer rol del token
        String role = extractRoleFromToken(oidcUser);
        
        if (role != null) {
            System.out.println("✅ Rol extraído en OidcUserService: " + role);
            
            // Asegurar que el rol esté disponible en los claims
            claims.put(ROLE_CLAIM_KEY, role);
            claims.put("role", role); // También poner la versión simplificada
        } else {
            System.out.println("❌ No se pudo extraer rol en OidcUserService");
        }

        // Crear las autoridades basadas en el rol
        Collection<GrantedAuthority> authorities = new ArrayList<>(oidcUser.getAuthorities());
        
        if (role != null && !role.trim().isEmpty()) {
            authorities.add(new SimpleGrantedAuthority("ROLE_" + role.toUpperCase()));
            System.out.println("✅ Autoridad agregada: ROLE_" + role.toUpperCase());
        }

        // Crear un nuevo OidcUser con los claims actualizados
        OidcIdToken updatedIdToken = new OidcIdToken(
            oidcUser.getIdToken().getTokenValue(),
            oidcUser.getIdToken().getIssuedAt(),
            oidcUser.getIdToken().getExpiresAt(),
            claims
        );

        return new DefaultOidcUser(authorities, updatedIdToken, "sub");
    }

    /**
     * Extrae el rol del token usando la misma lógica que tu SuccessHandler
     */
    private String extractRoleFromToken(OidcUser oidcUser) {
        Map<String, Object> claims = oidcUser.getIdToken().getClaims();
        
        // 1. Buscar con la clave completa del namespace
        String role = (String) oidcUser.getIdToken().getClaim(ROLE_CLAIM_KEY);
        if (role != null && !role.trim().isEmpty()) {
            System.out.println("✅ Rol encontrado con clave completa: " + role);
            return role.trim();
        }
        
        // 2. Buscar con clave simplificada
        role = (String) oidcUser.getIdToken().getClaim("role");
        if (role != null && !role.trim().isEmpty()) {
            System.out.println("✅ Rol encontrado con clave simplificada: " + role);
            return role.trim();
        }
        
        // 3. Buscar en app_metadata si está disponible
        Object appMetadata = claims.get("app_metadata");
        if (appMetadata instanceof Map) {
            @SuppressWarnings("unchecked")
            Map<String, Object> appMetadataMap = (Map<String, Object>) appMetadata;
            role = (String) appMetadataMap.get("role");
            if (role != null && !role.trim().isEmpty()) {
                System.out.println("✅ Rol encontrado en app_metadata: " + role);
                return role.trim();
            }
        }
        
        // 4. Buscar directamente en atributos del usuario
        Map<String, Object> attributes = oidcUser.getAttributes();
        role = (String) attributes.get(ROLE_CLAIM_KEY);
        if (role != null && !role.trim().isEmpty()) {
            System.out.println("✅ Rol encontrado en atributos: " + role);
            return role.trim();
        }
        
        System.out.println("❌ No se encontró rol en ninguna ubicación");
        return null;
    }

}
