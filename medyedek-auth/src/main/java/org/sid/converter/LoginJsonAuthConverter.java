package org.sid.converter;

import org.codehaus.jackson.map.ObjectMapper;
import org.sid.services.jwt.beans.JwtRequest;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.io.IOException;
import java.util.function.Function;

public class LoginJsonAuthConverter implements Function<ServerWebExchange, Mono<Authentication>> {

    private final ObjectMapper mapper;

    public LoginJsonAuthConverter(ObjectMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public Mono<Authentication> apply(ServerWebExchange exchange) {
        return exchange.getRequest().getBody()
                .next()
                .flatMap(buffer -> {
                    try {
                        JwtRequest request = mapper.readValue(buffer.asInputStream(), JwtRequest.class);
                        return Mono.just(request);
                    } catch (IOException e) {
                        return Mono.error(e);
                    }
                })
                .map(request -> new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
    }
}