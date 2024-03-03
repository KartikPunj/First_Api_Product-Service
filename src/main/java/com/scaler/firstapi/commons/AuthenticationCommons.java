package com.scaler.firstapi.commons;

import com.scaler.firstapi.dtos.UserDto;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
@Service
public class AuthenticationCommons { // this class is used to send request to user-service to validate the token
    // but not used now as we have implemented spring security
    //to validate this token I have to send request to user-service which is another microservice
    //I will use RestTemplate to send request to user-service
    //I will create a bean of RestTemplate in ApplicationConfiguration

    private RestTemplate restTemplate;
    public AuthenticationCommons(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }
    public UserDto validateToken(String token){
        ResponseEntity<UserDto> userDtoResponseEntity= restTemplate.postForEntity(
                "http://localhost:8080/users/validate/" + token,
                null,
                UserDto.class

        );
        if(userDtoResponseEntity.getBody()==null)
            return null;

        return userDtoResponseEntity.getBody();
    }
}
