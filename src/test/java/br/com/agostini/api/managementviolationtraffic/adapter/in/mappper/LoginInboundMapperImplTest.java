package br.com.agostini.api.managementviolationtraffic.adapter.in.mappper;

import br.com.agostini.api.managementviolationtraffic.adapter.in.bound.LoginInbound;
import br.com.agostini.openapi.provider.representation.LoginRequestRepresentation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LoginInboundMapperImplTest {

    private br.com.agostini.api.managementviolationtraffic.adapter.in.mapper.LoginInboundMapperImpl loginInboundMapper;

    @BeforeEach
    void setUp() {
        loginInboundMapper = new br.com.agostini.api.managementviolationtraffic.adapter.in.mapper.LoginInboundMapperImpl();
    }

    @Test
    void toLoginInbound_shouldReturnNull_whenLoginRequestRepresentationIsNull() {
        // Act
        LoginInbound result = loginInboundMapper.toLoginInbound(null);

        // Assert
        assertNull(result);
    }

    @Test
    void toLoginInbound_shouldMapFieldsCorrectly_whenLoginRequestRepresentationIsValid() {
        // Arrange
        LoginRequestRepresentation loginRequest = new LoginRequestRepresentation();
        loginRequest.setUsername("testuser");
        loginRequest.setPassword("testpass");

        // Act
        LoginInbound result = loginInboundMapper.toLoginInbound(loginRequest);

        // Assert
        assertNotNull(result);
        assertEquals("testuser", result.username());
        assertEquals("testpass", result.password());
    }
}