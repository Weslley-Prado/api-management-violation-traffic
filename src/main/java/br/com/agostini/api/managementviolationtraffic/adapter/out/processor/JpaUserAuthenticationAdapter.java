package br.com.agostini.api.managementviolationtraffic.adapter.out.processor;

import br.com.agostini.api.managementviolationtraffic.adapter.out.repository.postgresql.UsuarioJpaRepository;
import br.com.agostini.api.managementviolationtraffic.application.domain.dto.LoginRequest;
import br.com.agostini.api.managementviolationtraffic.application.domain.dto.TokenResponse;
import br.com.agostini.api.managementviolationtraffic.application.port.out.TokenServicePort;
import br.com.agostini.api.managementviolationtraffic.application.port.out.UserAuthenticationPort;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class JpaUserAuthenticationAdapter implements UserAuthenticationPort {

    private final UsuarioJpaRepository usuarioJpaRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final TokenServicePort tokenService;


    @Override
    public TokenResponse authenticate(LoginRequest loginRequest) {

        var senha = passwordEncoder.encode(loginRequest.password());
        var user0 = usuarioJpaRepository.findByUsername(loginRequest.username());
        return usuarioJpaRepository.findByUsername(loginRequest.username())
                .filter(user -> passwordEncoder.matches(loginRequest.password(), user.getPassword()))
                .map(usuario -> {
                    String token = tokenService.generateToken(usuario.getEmail());
                    return new TokenResponse(token);
                })
                .orElseThrow(() -> new RuntimeException("Usuário ou senha inválidos"));
    }
}