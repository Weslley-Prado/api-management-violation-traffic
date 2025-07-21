package br.com.agostini.api.managementviolationtraffic.application.service;

import br.com.agostini.api.managementviolationtraffic.adapter.in.bound.LoginInbound;
import br.com.agostini.api.managementviolationtraffic.adapter.out.bound.TokenOutbound;
import br.com.agostini.api.managementviolationtraffic.application.domain.dto.LoginRequest;
import br.com.agostini.api.managementviolationtraffic.application.domain.dto.TokenResponse;
import br.com.agostini.api.managementviolationtraffic.application.domain.mapper.LoginRequestMapper;
import br.com.agostini.api.managementviolationtraffic.application.domain.mapper.TokenOutboundMapper;
import br.com.agostini.api.managementviolationtraffic.application.port.out.UserAuthenticationPort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AuthServiceTest {

    @Mock
    private UserAuthenticationPort userAuthenticationPort;

    @Mock
    private LoginRequestMapper loginRequestMapper;

    @Mock
    private TokenOutboundMapper tokenOutboundMapper;

    @InjectMocks
    private AuthService authService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void login_shouldReturnTokenOutbound_whenCredentialsAreValid() {
        // Arrange
        LoginInbound loginInbound = new LoginInbound("testuser", "testpass");
        LoginRequest loginRequest = new LoginRequest("testuser", "testpass");
        TokenResponse tokenResponse = new TokenResponse("jwt-token");
        TokenOutbound tokenOutbound = new TokenOutbound("jwt-token");

        when(loginRequestMapper.toLoginRequest(loginInbound)).thenReturn(loginRequest);
        when(userAuthenticationPort.authenticate(loginRequest)).thenReturn(tokenResponse);
        when(tokenOutboundMapper.toTokenResponseRepresentation(tokenResponse)).thenReturn(tokenOutbound);

        // Act
        TokenOutbound result = authService.login(loginInbound);

        // Assert
        assertNotNull(result);
        assertEquals("jwt-token", result.token());
        verify(loginRequestMapper).toLoginRequest(loginInbound);
        verify(userAuthenticationPort).authenticate(loginRequest);
        verify(tokenOutboundMapper).toTokenResponseRepresentation(tokenResponse);
    }


    @Test
    void login_shouldThrowException_whenAuthenticationFails() {
        // Arrange
        LoginInbound loginInbound = new LoginInbound("testuser", "wrongpass");
        LoginRequest loginRequest = new LoginRequest("testuser", "wrongpass");

        when(loginRequestMapper.toLoginRequest(loginInbound)).thenReturn(loginRequest);
        when(userAuthenticationPort.authenticate(loginRequest))
                .thenThrow(new RuntimeException("Usuário ou senha inválidos"));

        // Act & Assert
        RuntimeException exception = assertThrows(
                RuntimeException.class,
                () -> authService.login(loginInbound)
        );
        assertEquals("Usuário ou senha inválidos", exception.getMessage());
        verify(loginRequestMapper).toLoginRequest(loginInbound);
        verify(userAuthenticationPort).authenticate(loginRequest);
        verifyNoInteractions(tokenOutboundMapper);
    }
}