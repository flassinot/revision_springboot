package com.example.taskflow;

import com.example.taskflow.dto.user.UserCreateDto;
import com.example.taskflow.dto.user.UserRecord;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.web.client.RestClient;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@ActiveProfiles("dev")
public class RestClientTest {

    @Test
    public void test() {
        RestClient client = RestClient.builder()
                .build();

        ResponseEntity<UserRecord> postResponse = client
                .post()
                .uri("http://localhost:8080/api/users")
                .contentType(MediaType.APPLICATION_JSON)
                .body(new UserCreateDto("john", "john@egtsmgsr.com", "123"))
                .retrieve()
                .toEntity(UserRecord.class);
        System.out.println(postResponse);

        RestClient.ResponseSpec getResponse = client
                .get()
                .uri("http://localhost:8080/api/users/1")
                .retrieve();
        System.out.println(getResponse.toEntity(UserRecord.class));
    }
}
