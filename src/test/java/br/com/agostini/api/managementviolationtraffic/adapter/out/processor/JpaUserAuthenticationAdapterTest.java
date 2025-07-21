package br.com.agostini.api.managementviolationtraffic.adapter.out.processor;

import br.com.agostini.api.managementviolationtraffic.adapter.out.repository.postgresql.UsuarioJpaRepository;
import br.com.agostini.api.managementviolationtraffic.application.domain.dto.LoginRequest;
import br.com.agostini.api.managementviolationtraffic.application.domain.dto.TokenResponse;
import br.com.agostini.api.managementviolationtraffic.application.domain.model.UserEntity;
import br.com.agostini.api.managementviolationtraffic.application.port.out.TokenServicePort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class JpaUserAuthenticationAdapterTest {

    @Mock
    private UsuarioJpaRepository usuarioJpaRepository;

    @Mock
    private BCryptPasswordEncoder passwordEncoder;

    @Mock
    private TokenServicePort tokenService;

    @InjectMocks
    private JpaUserAuthenticationAdapter jpaUserAuthenticationAdapter;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void authenticate_shouldReturnTokenResponse_whenCredentialsAreValid() {
        // Arrange
        LoginRequest loginRequest = new LoginRequest("testuser", "testpass");
        UserEntity user = new UserEntity();
        user.setUsername("testuser");
        user.setPassword("encodedPassword");
        user.setEmail("testuser@example.com");

        when(usuarioJpaRepository.findByUsername("testuser")).thenReturn(Optional.of(user));
        when(passwordEncoder.matches("testpass", "encodedPassword")).thenReturn(true);
        when(tokenService.generateToken("testuser@example.com")).thenReturn("jwt-token");

        // Act
        TokenResponse result = jpaUserAuthenticationAdapter.authenticate(loginRequest);

        // Assert
        assertNotNull(result);
        assertEquals("jwt-token", result.token());
        verify(usuarioJpaRepository).findByUsername("testuser");
        verify(passwordEncoder).matches("testpass", "encodedPassword");
        verify(tokenService).generateToken("testuser@example.com");
    }

    @Test
    void authenticate_shouldThrowException_whenUserNotFound() {
        // Arrange
        LoginRequest loginRequest = new LoginRequest("testuser", "testpass");
        when(usuarioJpaRepository.findByUsername("testuser")).thenReturn(Optional.empty());

        // Act & Assert
        RuntimeException exception = assertThrows(
                RuntimeException.class,
                () -> jpaUserAuthenticationAdapter.authenticate(loginRequest)
        );
        assertEquals("Usuário ou senha inválidos", exception.getMessage());
        verify(usuarioJpaRepository).findByUsername("testuser");
        verifyNoInteractions(passwordEncoder, tokenService);
    }

    @Test
    void authenticate_shouldThrowException_whenPasswordIsInvalid() {
        // Arrange
        LoginRequest loginRequest = new LoginRequest("testuser", "wrongpass");
        UserEntity user = new UserEntity();
        user.setUsername("testuser");
        user.setPassword("encodedPassword");

        when(usuarioJpaRepository.findByUsername("testuser")).thenReturn(Optional.of(user));
        when(passwordEncoder.matches("wrongpass", "encodedPassword")).thenReturn(false);

        // Act & Assert
        RuntimeException exception = assertThrows(
                RuntimeException.class,
                () -> jpaUserAuthenticationAdapter.authenticate(loginRequest)
        );
        assertEquals("Usuário ou senha inválidos", exception.getMessage());
        verify(usuarioJpaRepository).findByUsername("testuser");
        verify(passwordEncoder).matches("wrongpass", "encodedPassword");
        verifyNoInteractions(tokenService);
    }
}