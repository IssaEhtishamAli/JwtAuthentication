package Backend.SpringBootCrud.SecurityConfig;

import Backend.SpringBootCrud.DTO.Users.UserLoginDTO;
import Backend.SpringBootCrud.Models.User;
import Backend.SpringBootCrud.Models.Users;
import io.jsonwebtoken.*;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Component;

import javax.naming.AuthenticationException;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Component
public class JwtUtil {
    private final String secret_key = "mysecretkey";
    private long accessTokenValidity = 1*1;
    private final String refreshTokenSecret = "your-refresh-token-secret";
    private final long refreshTokenExpirationMs = 259000000; // 30 days

    private final JwtParser jwtParser;

    private final String TOKEN_HEADER = "Authorization";
//    private final String TOKEN_PREFIX = "Bearer ";

    public JwtUtil(){
        this.jwtParser = Jwts.parser().setSigningKey(secret_key);
    }

    public String createToken(Users user) {
        Claims claims = Jwts.claims().setSubject(user.getUser_email());
//        claims.put("firstName",user.getFirstName());
//        claims.put("lastName",user.getLastName());
        Date tokenCreateTime = new Date();
        Date tokenValidity = new Date(tokenCreateTime.getTime() + TimeUnit.MINUTES.toMillis(accessTokenValidity));
        return Jwts.builder()
                .setClaims(claims)
                .setExpiration(tokenValidity)
                .signWith(SignatureAlgorithm.HS256, secret_key)
                .compact();
    }
    public String generateRefreshToken(Users userlogin) {
        Date now = new Date();
        Date expiration = new Date(now.getTime() + refreshTokenExpirationMs);
        return Jwts.builder()
                .setSubject(userlogin.getUser_email())
                .setIssuedAt(now)
                .setExpiration(expiration)
                .signWith(SignatureAlgorithm.HS256, refreshTokenSecret)
                .compact();
    }
    private Claims parseJwtClaims(String token) {
        return jwtParser.parseClaimsJws(token).getBody();
    }

    public Claims resolveClaims(HttpServletRequest req) {
        try {
            String token = resolveToken(req);
            token = token.split(" ")[1];
            if (token != null) {
                return parseJwtClaims(token);
            }
            return null;
        } catch (ExpiredJwtException ex) {
            req.setAttribute("expired", ex.getMessage());
            throw ex;
        } catch (Exception ex) {
            req.setAttribute("invalid", ex.getMessage());
            throw ex;
        }
    }

    public String resolveToken(HttpServletRequest request) {

        String bearerToken = request.getHeader(TOKEN_HEADER);
        if (bearerToken != null) {
            return bearerToken;
        }
        return null;
    }
//    public boolean isTokenExpired(String token) {
//        try {
//            Claims claims = Jwts.parser().setSigningKey(secret_key).parseClaimsJws(token).getBody();
//            return claims.getExpiration().before(new Date());
//        } catch (ExpiredJwtException | UnsupportedJwtException | MalformedJwtException | SignatureException | IllegalArgumentException e) {
//            return true;
//        }
//    }

    public boolean validateClaims(Claims claims) throws AuthenticationException {
        try {
            return claims.getExpiration().after(new Date());
        } catch (Exception e) {
            throw e;
        }
    }

    public String getEmail(Claims claims) {
        return claims.getSubject();
    }

    private List<String> getRoles(Claims claims) {
        return (List<String>) claims.get("roles");
    }

}
