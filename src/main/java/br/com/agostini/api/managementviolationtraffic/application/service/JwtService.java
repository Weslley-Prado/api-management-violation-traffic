//package br.com.agostini.api.managementviolationtraffic.application.service;
//
//import org.springframework.stereotype.Service;
//
//import java.util.Date;
//
//@Service
//public class JwtService {
//    private final String secret = "minha-chave-top-secreta";
//
//    public String generateToken(String username) {
//        return Jwts.builder()
//                .setSubject(username)
//                .setIssuedAt(new Date())
//                .setExpiration(new Date(System.currentTimeMillis() + 3600000))
//                .signWith(Keys.hmacShaKeyFor(secret.getBytes()), SignatureAlgorithm.HS256)
//                .compact();
//    }
//
//    public boolean validate(String token) {
//        try {
//            Jwts.parserBuilder().setSigningKey(secret.getBytes()).build().parseClaimsJws(token);
//            return true;
//        } catch (Exception e) {
//            return false;
//        }
//    }
//
//    public String extractUsername(String token) {
//        return Jwts.parserBuilder()
//                .setSigningKey(secret.getBytes())
//                .build()
//                .parseClaimsJws(token)
//                .getBody()
//                .getSubject();
//    }