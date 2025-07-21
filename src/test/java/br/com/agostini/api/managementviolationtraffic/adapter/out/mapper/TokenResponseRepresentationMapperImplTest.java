package br.com.agostini.api.managementviolationtraffic.adapter.out.mapper;

import br.com.agostini.api.managementviolationtraffic.adapter.out.bound.TokenOutbound;
import br.com.agostini.openapi.provider.representation.TokenResponseRepresentation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TokenResponseRepresentationMapperImplTest {

    private br.com.agostini.api.managementviolationtraffic.adapter.out.mapper.TokenResponseRepresentationMapperImpl tokenResponseRepresentationMapper;

    @BeforeEach
    void setUp() {
        tokenResponseRepresentationMapper = new br.com.agostini.api.managementviolationtraffic.adapter.out.mapper.TokenResponseRepresentationMapperImpl();
    }

    @Test
    void toTokenResponseRepresentation_shouldReturnNull_whenTokenOutboundIsNull() {
        // Act
        TokenResponseRepresentation result = tokenResponseRepresentationMapper.toTokenResponseRepresentation(null);

        // Assert
        assertNull(result);
    }

    @Test
    void toTokenResponseRepresentation_shouldMapFieldsCorrectly_whenTokenOutboundIsValid() {
        // Arrange
        TokenOutbound tokenOutbound = new TokenOutbound("jwt-token");

        // Act
        TokenResponseRepresentation result = tokenResponseRepresentationMapper.toTokenResponseRepresentation(tokenOutbound);

        // Assert
        assertNotNull(result);
        assertEquals("jwt-token", result.getToken());
    }
}