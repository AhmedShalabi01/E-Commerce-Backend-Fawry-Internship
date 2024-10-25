package com.programming.techie.apigateway.filter;

import com.programming.techie.apigateway.services.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class AuthenticationFilter extends AbstractGatewayFilterFactory<AuthenticationFilter.Config> {

    @Autowired private RouteValidator validator;
    @Autowired private JwtService jwtService;

    public AuthenticationFilter(){
        super(Config.class);
    }

    @Override
    public GatewayFilter apply(AuthenticationFilter.Config config) {

        return (((exchange, chain) -> {

            if (validator.isSecured.test(exchange.getRequest())){
                //check if header contain token or not
                if(!exchange.getRequest().getHeaders().containsKey(HttpHeaders.AUTHORIZATION)){
                    throw new RuntimeException("There is no Token");
                }

                String authHeader = exchange.getRequest().getHeaders().get(HttpHeaders.AUTHORIZATION).get(0);
                if (authHeader!=null && authHeader.startsWith("Bearer ")){
                    authHeader=authHeader.substring(7);
                }

                try {
                    jwtService.validateToken(authHeader);
                }catch (Exception e){
                    throw new RuntimeException(e.getMessage());
                }

            }

            return chain.filter(exchange);
        }));
    }

    public static class Config{

}

}