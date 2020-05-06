package com.teck.kai.rest;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Service
public class RestClient {

    private static final String PATH_SEPARATOR = "/";

    private RestTemplate getRestTemplate(){
        return new RestTemplate();
    }

    private String getHost(){
        return "http://localhost:8080/";
    }

    public <T> T delete(final String path, final ParameterizedTypeReference<T> responseType, final MultiValueMap<String, String> headers, final Object... params) {
        final RestTemplate restTemplate = this.getRestTemplate();
        final URI uri = UriComponentsBuilder.fromHttpUrl(this.getUrl(path)).buildAndExpand(params).toUri();
        final ResponseEntity<T> responseEntity = restTemplate.exchange(uri, HttpMethod.DELETE, new HttpEntity<>(null, headers), responseType);
        return responseEntity.getBody();
    }

    public <T> T get(final String path, final ParameterizedTypeReference<T> responseType, final Object... params) {
        final RestTemplate restTemplate = this.getRestTemplate();
        final URI uri = UriComponentsBuilder.fromHttpUrl(this.getUrl(path)).buildAndExpand(params).toUri();
        final ResponseEntity<T> responseEntity = restTemplate.exchange(uri, HttpMethod.GET, null, responseType);
        return responseEntity.getBody();
    }

    public <T> T post(final String path, final ParameterizedTypeReference<T> responseType, final Object... params) {
        final RestTemplate restTemplate = this.getRestTemplate();
        final URI uri = UriComponentsBuilder.fromHttpUrl(this.getUrl(path)).buildAndExpand(params).toUri();
        final ResponseEntity<T> responseEntity = restTemplate.exchange(uri, HttpMethod.POST, null, responseType);
        return responseEntity.getBody();
    }

    public <T, E> T postByEntity(final String path, final ParameterizedTypeReference<T> responseType, final E ent, final MultiValueMap<String, String> headers) {
        final HttpEntity<E> requestEntity = new HttpEntity<>(ent, headers);
        final RestTemplate restTemplate = this.getRestTemplate();
        final URI uri = UriComponentsBuilder.fromHttpUrl(this.getUrl(path)).build().toUri();
        final ResponseEntity<T> responseEntity = restTemplate.exchange(uri, HttpMethod.POST, requestEntity, responseType);
        return responseEntity.getBody();
    }

    public <T, E> T postByEntityAndParam(final String path, final ParameterizedTypeReference<T> responseType, final E ent, final Object... params) {
        final HttpHeaders requestHeaders = new HttpHeaders();
        final HttpEntity<E> requestEntity = new HttpEntity<>(ent, requestHeaders);
        final RestTemplate restTemplate = this.getRestTemplate();
        final URI uri = UriComponentsBuilder.fromHttpUrl(this.getUrl(path)).buildAndExpand(params).toUri();
        final ResponseEntity<T> responseEntity = restTemplate.exchange(uri, HttpMethod.POST, requestEntity, responseType);
        return responseEntity.getBody();
    }

    private String getUrl(@NonNull String path) {
        String host = getHost();

        if(path.startsWith("http")){
            return path;
        }

        if(!path.startsWith(PATH_SEPARATOR)) {
            path = PATH_SEPARATOR + path;
        }

        if(host.endsWith("/")){
            host = host.substring(0, host.length() - 1);
        }

        if(host.startsWith("http")){
            return String.format("%s%s", host, path);
        }

        return String.format("http://%s%s", host, path);
    }
}
