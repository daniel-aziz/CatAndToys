package com.jb.SpringProject.Security;


import com.jb.SpringProject.beans.Users.UserDetails;
import io.jsonwebtoken.*;
import org.springframework.stereotype.Service;

import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class JWTSecurity {
    //type of encryption
    private String signatureAlgorithm = SignatureAlgorithm.HS256.getJcaName();
    //our private key
    private String encodedSecretKey =
            "this+is+my+key+and+it+must+be+at+least+256+bits+long";
    //create our private key
    private Key decodedSecretKey = new SecretKeySpec(
            Base64.getDecoder().decode(encodedSecretKey), this.signatureAlgorithm);

    //generate key
    //we need user email, password and role (תפקיד) for create the token
    //since the userDetail is an instace of class, we need to make it hashcode
    //the token need to get clamis which is the information in hashcode
    public String generateToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("userPass", userDetails.getPassword());
        claims.put("UserType", userDetails.getUserType());
        return createToken(claims, userDetails.getEmail());
    }

    /**
     * create the actual JWT Token
     * @param claims fields of user data
     * @param email user email as a subject of the token
     * @return a JWT token with pulse
     */
    //we create the JWT token from the information that we got.
    private String createToken(Map<String, Object> claims, String email) {
        Instant now = Instant.now();//get current time
        return Jwts.builder()       //create JWT builder to assist us with JWT creation
                .setClaims(claims)  //set our data (clamis-user,password,role,etc...)
                .setSubject(email)  //set our subject, the first item that we will check
                .setIssuedAt(java.util.Date.from(now))  //create issued at , which is current time
                //experation date, which after the date, we need to create a new token
                .setExpiration(Date.from(now.plus(30, ChronoUnit.MINUTES)))
                .signWith(this.decodedSecretKey) //sign the token with our secret key
                .compact();   //compact our token, that it will be smaller :)
    }

    private Claims extractAllClaims(String token) throws ExpiredJwtException {
        JwtParser jwtParser = Jwts.parserBuilder()
                .setSigningKey(this.decodedSecretKey)
                .build();
        try {
            return jwtParser.parseClaimsJws(token).getBody();
        }
        catch (Exception err) {
            return null;
        }
    }

    public String extractEmail(String token) {
        return extractAllClaims(token).getSubject();
    }

    public Date extractExpirationDate (String token) {
        return extractAllClaims(token).getExpiration();
    }

    public boolean isTokenExpired(String token){
        try {
            extractAllClaims(token);
            return false;
        } catch (ExpiredJwtException err) {
            return true;
        }
    }

    public boolean validateToken(String token, UserDetails userDetails) {
        final String userEmail = extractEmail(token);
        return (userEmail.equals(userDetails.getEmail()) && !isTokenExpired(token));
    }

    public static void main(String[] args) {
        UserDetails admin = new UserDetails("admin@admin.com", "123456","Admin");
        JWTSecurity myUtil = new JWTSecurity();
        String myToken = myUtil.generateToken(admin);
        System.out.println("My new Token: \n" +myToken);
        System.out.println("our token body: \n"+myUtil.extractAllClaims(myToken));
        System.out.println("and the winner is: \n"+myUtil.extractEmail(myToken));
    }


}
