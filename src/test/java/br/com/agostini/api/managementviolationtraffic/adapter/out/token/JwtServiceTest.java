package br.com.agostini.api.managementviolationtraffic.adapter.out.token;

import br.com.agostini.api.managementviolationtraffic.config.JwtProperties;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.lang.reflect.Field;
import java.security.Key;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class JwtServiceTest {

    @Mock
    private JwtProperties jwtProperties;

    @InjectMocks
    private JwtService jwtService;

    private Key key;

    @BeforeEach
    void setUp() throws NoSuchFieldException, IllegalAccessException {
        MockitoAnnotations.openMocks(this);

        // Mock JwtProperties
        when(jwtProperties.getSecret()).thenReturn("mySecretKeyWithMoreThan256BitsToBeValidForHS256");
        when(jwtProperties.getExpiration()).thenReturn(3600000L); // 1 hour in milliseconds

        // Initialize key using reflection to simulate @PostConstruct
        key = Keys.hmacShaKeyFor(jwtProperties.getSecret().getBytes());
        Field keyField = JwtService.class.getDeclaredField("key");
        keyField.setAccessible(true);
        keyField.set(jwtService, key);
    }

    @Test
    void generateToken_shouldReturnValidJwtToken() {
        // Arrange
        String subject = "testuser@example.com";

        // Act
        String token = jwtService.generateToken(subject);

        // Assert
        assertNotNull(token);
        io.jsonwebtoken.Claims claims = Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();

        assertEquals(subject, claims.getSubject());
        assertEquals("manager-auth", claims.getIssuer());
        assertEquals(List.of("EQUIPMENT_READ", "EQUIPMENT_WRITE"), claims.get("roles"));
        assertNotNull(claims.getIssuedAt());
        assertNotNull(claims.getExpiration());
        assertTrue(claims.getExpiration().after(new Date()));
    }

    @Test
    void isTokenValid_shouldReturnTrue_forValidToken() {
        // Arrange
        String subject = "testuser@example.com";
        String token = Jwts.builder()
                .setSubject(subject)
                .setIssuer("manager-auth")
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 3600000L))
                .signWith(key)
                .compact();

        // Act
        boolean isValid = jwtService.isTokenValid(token);

        // Assert
        assertTrue(isValid);
    }

    @Test
    void isTokenValid_shouldReturnFalse_forInvalidToken() {
        // Arrange
        String invalidToken = "invalid.token.here";

        // Act
        boolean isValid = jwtService.isTokenValid(invalidToken);

        // Assert
        assertFalse(isValid);
    }

    @Test
    void extractUsername_shouldReturnSubject_forValidToken() {
        // Arrange
        String subject = "testuser@example.com";
        String token = Jwts.builder()
                .setSubject(subject)
                .setIssuer("manager-auth")
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 3600000L))
                .signWith(key)
                .compact();

        // Act
        String username = jwtService.extractUsername(token);

        // Assert
        assertEquals(subject, username);
    }

    @Test
    void extractUsername_shouldThrowException_forInvalidToken() {
        // Arrange
        String invalidToken = "invalid.token.here";

        // Act & Assert
        assertThrows(io.jsonwebtoken.JwtException.class, () -> jwtService.extractUsername(invalidToken));
    }
}