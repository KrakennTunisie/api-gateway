package com.KrakennTunisie.gateway.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.server.resource.authentication.BearerTokenAuthenticationToken;
import org.springframework.security.web.server.authentication.ServerAuthenticationConverter;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

public class CookieBearerTokenServerAuthenticationConverter implements ServerAuthenticationConverter {

    private static final String COOKIE_NAME = "access_token";

    @Override
    public Mono<Authentication> convert(ServerWebExchange exchange) {
        return Mono.justOrEmpty(exchange.getRequest().getCookies().getFirst(COOKIE_NAME))
                .map(cookie -> new BearerTokenAuthenticationToken(cookie.getValue()));
    }
}
