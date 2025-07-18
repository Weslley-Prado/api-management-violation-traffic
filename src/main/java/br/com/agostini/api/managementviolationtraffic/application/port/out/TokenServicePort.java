package br.com.agostini.api.managementviolationtraffic.application.port.out;

public interface TokenServicePort {
    String generateToken(String subject);
    Boolean isTokenValid(String token);
    String extractUsername(String token);
}
