package br.com.agostini.api.managementviolationtraffic.application.domain.mapper;

import br.com.agostini.api.managementviolationtraffic.adapter.in.bound.LoginInbound;
import br.com.agostini.api.managementviolationtraffic.application.domain.dto.LoginRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LoginRequestMapperImplTest {

    private br.com.agostini.api.managementviolationtraffic.application.domain.mapper.LoginRequestMapperImpl loginRequestMapper;

    @BeforeEach
    void setUp() {
        loginRequestMapper = new br.com.agostini.api.managementviolationtraffic.application.domain.mapper.LoginRequestMapperImpl();
    }

    @Test
    void toLoginRequest_shouldReturnNull_whenLoginInboundIsNull() {
        // Act
        LoginRequest result = loginRequestMapper.toLoginRequest(null);

        // Assert
        assertNull(result);
    }

    @Test
    void toLoginRequest_shouldMapFieldsCorrectly_whenLoginInboundIsValid() {
        // Arrange
        LoginInbound loginInbound = new LoginInbound("testuser", "testpass");

        // Act
        LoginRequest result = loginRequestMapper.toLoginRequest(loginInbound);

        // Assert
        assertNotNull(result);
        assertEquals("testuser", result.username());
        assertEquals("testpass", result.password());
    }
}