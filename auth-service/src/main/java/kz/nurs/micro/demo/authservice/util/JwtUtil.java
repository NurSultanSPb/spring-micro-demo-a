package kz.nurs.micro.demo.authservice.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import kz.nurs.micro.demo.authservice.model.entity.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtUtil implements Serializable {

    @Serial
    private static final long serialVersionUID = 2678983588884881295L;

    private static final long JWT_TOKEN_VALIDITY = 5 * 60 * 60;

    @Value("${jwt.secret}")
    private String secret;

    public String getUsernameFromToken(String token) {
        return getClaimFromToken(token, Claims::getSubject);
    }

    private Date getExpirationDateFromToken(String token) {
        return getClaimFromToken(token, Claims::getExpiration);
    }

    public String generateToken(User user) {
        Map<String, Object> claims = new HashMap<>();
        return doGenerateToken(claims, user.getEmail());
    }

    public Boolean validateToken(String token, User user) {
        final String username = getUsernameFromToken(token);
        return (username.equals(user.getEmail()) && !isTokenExpired(token));
    }

    private <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = getAllClaimsFromToken(token);
        return claimsResolver.apply(claims);
    }

    private Claims getAllClaimsFromToken(String token) {
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
    }

    private String doGenerateToken(Map<String, Object> claims, String subject) {
        return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY * 1000))
                .signWith(SignatureAlgorithm.HS256, secret).compact();
    }

    private Boolean isTokenExpired(String token) {
        final Date expiration = getExpirationDateFromToken(token);
        return expiration.before(new Date());
    }
}


//        Map<String, Object> tokenData = new HashMap<>();
//        tokenData.put("email", user.getEmail());
//        tokenData.put("password", user.getPassword());
//        tokenData.put("enable", user.getEnable());
//        tokenData.put("token_create_date", new Date().getTime());
//        Calendar calendar = Calendar.getInstance();
//        calendar.add(Calendar.YEAR, 100);
//        tokenData.put("token_expiration_date", calendar.getTime());
//        JwtBuilder jwtBuilder = Jwts.builder();
//        jwtBuilder.setExpiration(calendar.getTime());
//        jwtBuilder.setClaims(tokenData);
//        String token = jwtBuilder.signWith(SignatureAlgorithm.HS512, secret).compact();
