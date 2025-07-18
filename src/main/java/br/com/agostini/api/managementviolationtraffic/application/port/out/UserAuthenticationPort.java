package br.com.agostini.api.managementviolationtraffic.application.port.out;

public interface UserAuthenticationPort {
    Boolean authenticate(String username, String password);
}
