package com.spring_boot.online.book.store.config;

import com.spring_boot.online.book.store.controller.RequestLoginDTO;
import com.spring_boot.online.book.store.controller.ResponseLoginDTO;
import com.spring_boot.online.book.store.dto.UserPrincipal;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;


    public ResponseLoginDTO login(RequestLoginDTO requestLoginDTO){
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(requestLoginDTO.getUsername()
                        , requestLoginDTO.getPassword())
        );

       UserPrincipal user= (UserPrincipal) authentication.getPrincipal();

       String token = jwtUtil.generateAccessToken(user);

       return new ResponseLoginDTO( token);



    }

}
