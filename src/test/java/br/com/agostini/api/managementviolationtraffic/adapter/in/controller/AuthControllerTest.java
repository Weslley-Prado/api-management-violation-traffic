package br.com.agostini.api.managementviolationtraffic.adapter.in.controller;

import br.com.agostini.api.managementviolationtraffic.adapter.in.bound.LoginInbound;
import br.com.agostini.api.managementviolationtraffic.adapter.in.mapper.LoginInboundMapper;
import br.com.agostini.api.managementviolationtraffic.adapter.out.bound.TokenOutbound;
import br.com.agostini.api.managementviolationtraffic.adapter.out.mapper.TokenResponseRepresentationMapper;
import br.com.agostini.api.managementviolationtraffic.application.port.in.AuthUseCase;
import br.com.agostini.openapi.provider.representation.LoginRequestRepresentation;
import br.com.agostini.openapi.provider.representation.TokenResponseRepresentation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class AuthControllerTest {

    @Mock
    private AuthUseCase authUseCase;

    @Mock
    private LoginInboundMapper loginInboundMapper;

    @Mock
    private TokenResponseRepresentationMapper tokenResponseRepresentationMapper;

    @InjectMocks
    private AuthController authController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void login_shouldReturnTokenResponseRepresentation_whenValidLoginRequest() {
        // Arrange
        LoginRequestRepresentation loginRequest = new LoginRequestRepresentation();
        loginRequest.setUsername("testuser");
        loginRequest.setPassword("testpass");

        LoginInbound loginInbound = new LoginInbound("testuser", "testpass");
        TokenOutbound tokenOutbound = new TokenOutbound("jwt-token");
        TokenResponseRepresentation tokenResponse = new TokenResponseRepresentation();
        tokenResponse.setToken("jwt-token");

        when(loginInboundMapper.toLoginInbound(any(LoginRequestRepresentation.class))).thenReturn(loginInbound);
        when(authUseCase.login(any(LoginInbound.class))).thenReturn(tokenOutbound);
        when(tokenResponseRepresentationMapper.toTokenResponseRepresentation(any(TokenOutbound.class)))
                .thenReturn(tokenResponse);

        // Act
        ResponseEntity<TokenResponseRepresentation> response = authController.login(loginRequest);

        // Assert
        assertNotNull(response);
        assertEquals(200, response.getStatusCodeValue());
        assertNotNull(response.getBody());
        assertEquals("jwt-token", response.getBody().getToken());
    }

    @Test
    void login_shouldMapLoginRequestCorrectly() {
        // Arrange
        LoginRequestRepresentation loginRequest = new LoginRequestRepresentation();
        loginRequest.setUsername("testuser");
        loginRequest.setPassword("testpass");

        LoginInbound loginInbound = new LoginInbound("testuser", "testpass");
        TokenOutbound tokenOutbound = new TokenOutbound("jwt-token");
        TokenResponseRepresentation tokenResponse = new TokenResponseRepresentation();
        tokenResponse.setToken("jwt-token");

        when(loginInboundMapper.toLoginInbound(loginRequest)).thenReturn(loginInbound);
        when(authUseCase.login(loginInbound)).thenReturn(tokenOutbound);
        when(tokenResponseRepresentationMapper.toTokenResponseRepresentation(tokenOutbound))
                .thenReturn(tokenResponse);

        // Act
        ResponseEntity<TokenResponseRepresentation> response = authController.login(loginRequest);

        // Assert
        assertNotNull(response);
        assertEquals(200, response.getStatusCodeValue());
        assertNotNull(response.getBody());
        assertEquals("jwt-token", response.getBody().getToken());
    }
}