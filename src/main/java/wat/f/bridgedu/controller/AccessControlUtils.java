package wat.f.bridgedu.controller;

import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;

import wat.f.bridgedu.domain.entity.UserDetailsImpl;

public class AccessControlUtils {
    public static boolean isNotAccessibleStudentPage(UserDetailsImpl userDetails, String username) {
        String roles = userDetails.getAuthorities().stream().map(GrantedAuthority::toString).collect(Collectors.joining(""));
        return !(roles.contains("ADMIN") || roles.contains("TEACHER") || userDetails.getUsername().equals(username));
    }

    public static boolean containsRole(UserDetailsImpl userDetails, String role) {
        String authorities = userDetails.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.joining(""));
        return authorities.contains(role);
    }
}