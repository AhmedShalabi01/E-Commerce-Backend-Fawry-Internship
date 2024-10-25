package com.example.orderapi.services;

import com.example.orderapi.dtos.PostProductDto;
import com.example.orderapi.exceptions.exceptiontypes.ProductOutOfStockException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Map;

@Service
public class StoreService {
    @Autowired
    private WebClient webClient;
    private final String url = "http://localhost:8084/store/warehouses";
    public List<PostProductDto> validateProductsInStore(Map<Integer, Integer> productsLMap, long warId){
        return webClient.post()
                .uri(url + "/{warId}/check/product", warId)
                .bodyValue(productsLMap)
                .retrieve()
                .onStatus(HttpStatusCode::is4xxClientError, response -> {
                    return Mono.error(new ProductOutOfStockException(response.toString()));
                })
                .bodyToFlux(PostProductDto.class)
                .collectList()
                .block();
    }
    public boolean consumeProductsFromWarehouse(Map<Integer, Integer> productsLMap, long warId){
        return Boolean.TRUE.equals(webClient.post()
                .uri(url + "/{warId}/products/consume", warId)
                .bodyValue(productsLMap)
                .retrieve()
                .onStatus(HttpStatusCode::is4xxClientError, response -> {
                    return Mono.error(new ProductOutOfStockException(response.toString()));
                })
                .bodyToMono(Boolean.class)
                .block());
    }
}
