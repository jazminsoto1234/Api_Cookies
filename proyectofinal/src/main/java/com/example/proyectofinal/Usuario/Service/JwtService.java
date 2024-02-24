package com.example.proyectofinal.Usuario.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.websocket.Decoder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
@Slf4j
public class JwtService { //Es lo mismo que el jwtUtil

    @Value("${jwt.secret.key}")
    private String secretKey;

    @Value("${jwt.time.expiration}")
    private String TimeExpiration;

    //Generar token de acceso
    public String generateAccessToken(Map<String,Object> extraClaim, UserDetails userDetails){
        return Jwts.builder().setClaims(extraClaim).setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis())) //Fecha de creacion del token
                .setExpiration(new Date(System.currentTimeMillis() +  Long.parseLong(TimeExpiration)))
                .signWith(getSignatureKey(), SignatureAlgorithm.HS256).compact(); // Se coloca la firma del metodo
    }

    public String generateAccessToken(UserDetails userDetails){
        return generateAccessToken(new HashMap<>(), userDetails);
    }

    //Validar el token de acceso
    public Boolean isTokenValid(String token){
        try{
            Jwts.parserBuilder()
                    .setSigningKey(getSignatureKey())
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
            return true;

        }catch (Exception e){
            log.error("Token invalido, error :".concat(e.getMessage())); //Imprimira por consola
            return false;
        }
    }

    //Obtener todos los claims del token - Claims: Cuerpo del token (informacion del usuario)
    public Claims extractAllClaims(String token){
        return Jwts.parserBuilder()
                .setSigningKey(getSignatureKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    //Obtener el username de un token
    public String getUsernameFromToken(String token){
        return getClaim(token, Claims::getSubject); //Claim::getSubject - Es
    }

    //Obtener un solo claim:
    public <T> T getClaim(String token, Function<Claims, T> claimsTFunction){ // Public/Private <Generico> Generico Namefunct
        Claims claims = extractAllClaims(token);
        return claimsTFunction.apply(claims);
    }



    // Obtener firma del token
    public Key getSignatureKey(){
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    //Extraer la fecha de expiracion del token
    public Date extractExpiration(String token){
        return getClaim(token, Claims::getExpiration);
    }


    // Validacion si el token expiro
    public Boolean isTokenExpired(String token){
        return extractExpiration(token).before(new Date());
    }



}
