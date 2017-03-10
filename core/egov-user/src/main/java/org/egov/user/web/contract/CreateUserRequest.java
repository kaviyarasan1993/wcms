package org.egov.user.web.contract;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.egov.user.domain.model.User;
import org.springframework.security.crypto.password.PasswordEncoder;

@AllArgsConstructor
@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class CreateUserRequest {
    @JsonProperty("RequestInfo")
    private RequestInfo requestInfo;

    @JsonProperty("User")
    private UserRequest userRequest;

    public User toDomainForCreate(PasswordEncoder passwordEncoder) {
        return userRequest.toDomainForCreate(passwordEncoder);
    }
}
