package com.exercise.manager.client;

import com.exercise.manager.config.ConfigProperties;
import com.exercise.manager.dto.UserAccountDTO;
import com.exercise.manager.exception.errorHandler.UserAccountClientErrorHandler;
import com.exercise.manager.pagination.CustomPageImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@Component
public class UserAccountClient {

    private final RestTemplate restTemplate;
    private final String url;

    public UserAccountClient(ConfigProperties configProperties) {
        this.restTemplate = new RestTemplate();
        this.url = configProperties.getUserAccountServiceUrl();
        this.restTemplate.setErrorHandler(new UserAccountClientErrorHandler(new ObjectMapper()));
    }

    public List<UserAccountDTO> getAllUserAccounts() {
        ResponseEntity<List<UserAccountDTO>> responseEntity =
                restTemplate.exchange(
                        url,
                        HttpMethod.GET,
                        null,
                        new ParameterizedTypeReference<>() {
                        }
                );

        return responseEntity.getBody();

    }

    public UserAccountDTO getUserAccountById(Long id) {
        ResponseEntity<UserAccountDTO> responseEntity =
                restTemplate.getForEntity(url + "/" + id,
                        UserAccountDTO.class);

        return responseEntity.getBody();
    }

    public Page<UserAccountDTO> searchByName(String name, Pageable pageable) {
        UriComponentsBuilder uriComponentsBuilder = UriComponentsBuilder.fromUriString(url + "/search")
                .queryParam("name", name)
                .queryParam("page", pageable.getPageNumber())
                .queryParam("size", pageable.getPageSize())
                .queryParam("sortField", pageable.getSort())
                .queryParam("sortDirection", pageable.getSortOr(pageable.getSort()));

        ResponseEntity<CustomPageImpl<UserAccountDTO>> responseEntity = restTemplate.exchange(uriComponentsBuilder.toUriString(),
                HttpMethod.GET, null, new ParameterizedTypeReference<>() {
                });

        return responseEntity.getBody();
    }

    public UserAccountDTO createUserAccount(UserAccountDTO userAccountDto) {
        ResponseEntity<UserAccountDTO> responseEntity =
                restTemplate.postForEntity(url, userAccountDto, UserAccountDTO.class);
        return responseEntity.getBody();
    }

    public UserAccountDTO updateUserAccount(UserAccountDTO userAccountDto) {
        restTemplate.put(url, userAccountDto);
        ResponseEntity<UserAccountDTO> responseEntity =
                restTemplate.getForEntity(url + "/" + userAccountDto.getId(), UserAccountDTO.class);
        return responseEntity.getBody();
    }

    public void deleteUserAccount(Long id) {
        restTemplate.delete(url + "/" + id);
    }


}
