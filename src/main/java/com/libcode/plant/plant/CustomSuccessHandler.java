package com.libcode.plant.plant;

import java.io.IOException;


import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;

import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;


import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class CustomSuccessHandler implements AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {

        System.out.println("=== AUTHENTICATION SUCCESS ===");
        System.out.println("Authentication type: " + authentication.getClass().getSimpleName());
        System.out.println("Principal type: " + authentication.getPrincipal().getClass().getSimpleName());

        String role = null;

        if (authentication.getPrincipal() instanceof OidcUser oidcUser) {
            System.out.println("✅ Procesando OidcUser");

            String email = oidcUser.getEmail(); // Usamos el email como identificador
            System.out.println("🔎 Procesando usuario con email: " + email);
            //String idTokenValue = oidcUser.getIdToken().getTokenValue();
            //System.out.println("ID Token: " + idTokenValue);

            // Obtener el rol del claim personalizado
            role = (String) oidcUser.getIdToken().getClaim("https://dev-qi8b5nrabbvawh0y.us.auth0.com/claims/role");

            if (role != null) {
                System.out.println("✅ Rol encontrado en metadata: " + role);
            } else {
                System.out.println("❌ No se encontró rol en metadata");
            }
        } else {
            System.out.println("⚠️ No es OidcUser. No se puede extraer el email ni el rol.");
        }

        String redirectUrl = determineRedirectUrl(role);
        System.out.println("Redirigiendo a: " + redirectUrl);
        response.sendRedirect(redirectUrl);
    }

    private String determineRedirectUrl(String role) {
        if (role == null || role.trim().isEmpty()) {
            return "/"; // Redirigir a página por defecto si no tiene rol
        }

        // Redirigir según el rol
        return switch (role.trim().toLowerCase()) {
            case "admin" -> "/Admin";
            case "tutor" -> "/tutor";
            default -> "/"; // Redirigir a página por defecto si el rol no es reconocido
        };
    }
}