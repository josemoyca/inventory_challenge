package demo.retail.inventory.config;

import demo.retail.inventory.handlers.usecase.*;
import demo.retail.inventory.models.DTO.InventoryDto;
import demo.retail.inventory.models.DTO.SalesDTO;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.*;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class ResourceRoutes {
    public static final int PAGE_SIZE = 100;

    @Bean
    public RouterFunction<ServerResponse> getAllAccountsRouter(GetPageableInventoryUseCase getPageableInventoryUseCase) {
        return route(
                GET("/api/v1/inventories"),
                request -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(BodyInserters.fromPublisher(getPageableInventoryUseCase.apply(
                                PageRequest.of(
                                        request.queryParam("page").isEmpty() ? 0 : Integer.parseInt(request.queryParam("page").get()), PAGE_SIZE)
                        ), InventoryDto.class)));
    }

    @Bean
    public RouterFunction<ServerResponse> createInventoryRoute(CreateInventoryUseCase createInventoryUseCase) {
        return route(POST("/api/v1/inventories")
                        .and(accept(MediaType.APPLICATION_JSON)),
                request -> request.bodyToMono(InventoryDto.class)
                        .flatMap(inventoryDto -> createInventoryUseCase.apply(inventoryDto)
                                .flatMap(result -> ServerResponse.ok()
                                        .contentType(MediaType.APPLICATION_JSON)
                                        .bodyValue(result))));
    }

    @Bean
    public RouterFunction<ServerResponse> createBatchInventoryRoute(CreateBatchInventoryUseCase createBatchInventoryUseCase) {
        return route(POST("/api/v1/inventories/batch")
                        .and(accept(MediaType.APPLICATION_JSON)),
//                createBatchInventoryUseCase::createInventoryBatch);
                request ->
                        ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
                                .body(createBatchInventoryUseCase.apply(request.bodyToFlux(InventoryDto.class)), InventoryDto.class)
        );
    }

    @Bean
    public RouterFunction<ServerResponse> createDetailSale(CreateDetailSaleUseCase createDetailSaleUseCase) {
        return route(POST("/api/v1/sales/detail")
                        .and(accept(MediaType.APPLICATION_JSON)),
                request -> request.bodyToMono(SalesDTO.class)
                        .flatMap(salesDto -> createDetailSaleUseCase.apply(salesDto)
                                .flatMap(result -> ServerResponse.ok()
                                        .contentType(MediaType.APPLICATION_JSON)
                                        .bodyValue(result))));
    }

    @Bean
    public RouterFunction<ServerResponse> createWholeSale(CreateWholeSaleUseCase createWholeSaleUseCase) {
        return route(POST("/api/v1/sales/wholesale")
                        .and(accept(MediaType.APPLICATION_JSON)),
                request -> request.bodyToMono(SalesDTO.class)
                        .flatMap(salesDto -> createWholeSaleUseCase.apply(salesDto)
                                .flatMap(result -> ServerResponse.ok()
                                        .contentType(MediaType.APPLICATION_JSON)
                                        .bodyValue(result))));
    }

}
