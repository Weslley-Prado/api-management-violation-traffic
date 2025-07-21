package br.com.agostini.api.managementviolationtraffic.application.domain.mapper;

import br.com.agostini.api.managementviolationtraffic.adapter.out.bound.TokenOutbound;
import br.com.agostini.api.managementviolationtraffic.application.domain.dto.TokenResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TokenOutboundMapperImplTest {

    private br.com.agostini.api.managementviolationtraffic.application.domain.mapper.TokenOutboundMapperImpl tokenOutboundMapper;

    @BeforeEach
    void setUp() {
        tokenOutboundMapper = new br.com.agostini.api.managementviolationtraffic.application.domain.mapper.TokenOutboundMapperImpl();
    }

    @Test
    void toTokenResponseRepresentation_shouldReturnNull_whenTokenResponseIsNull() {
        // Act
        TokenOutbound result = tokenOutboundMapper.toTokenResponseRepresentation(null);

        // Assert
        assertNull(result);
    }

    @Test
    void toTokenResponseRepresentation_shouldMapFieldsCorrectly_whenTokenResponseIsValid() {
        // Arrange
        TokenResponse tokenResponse = new TokenResponse("jwt-token");

        // Act
        TokenOutbound result = tokenOutboundMapper.toTokenResponseRepresentation(tokenResponse);

        // Assert
        assertNotNull(result);
        assertEquals("jwt-token", result.token());
    }
}