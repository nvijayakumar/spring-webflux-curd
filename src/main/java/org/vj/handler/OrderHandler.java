/**
 * 
 */
package org.vj.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import org.vj.bean.OrderResponse;
import org.vj.request.OrderRequest;
import org.vj.service.OrderService;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * @author vijayakumar
 * @Since  02-Nov-2022
 *
 */
@Slf4j
@Component
public class OrderHandler {
	
	@Autowired
	OrderService orderService;

	public Mono<ServerResponse> getAllOrders(ServerRequest serverRequest) {
		return ServerResponse.ok()
				.contentType(MediaType.TEXT_EVENT_STREAM)
				.body(orderService.getAllOrders().map(orderService::mapOrderToOrderResponse), 
						OrderResponse.class);
	}
	
	public Mono<ServerResponse> getOrderById(ServerRequest serverRequest) {
		//get the orderId from the path variable
		//Long id = Long.valueOf(serverRequest.pathVariable("id"));
		
		//get the orderId from the query param
		Long id = Long.valueOf(serverRequest.queryParam("id").get());
		
		String firstHeader = serverRequest.headers().firstHeader("first-Header");
		String secondHeader = serverRequest.headers().firstHeader("second-Header");
		
		log.info("firstHeader {}", firstHeader);
		log.info("secondHeader {}", secondHeader);
		
		return ServerResponse.ok()
				.contentType(MediaType.TEXT_EVENT_STREAM)
				.header("response-first-header", "first-Header")
				.header("response-second-header", "second-Header")
				.body(orderService.getOrderById(id).map(orderService::mapOrderToOrderResponse), 
						OrderResponse.class);
	}
	
	public Mono<ServerResponse> createOrder(ServerRequest serverRequest) {
		Mono<OrderRequest> monoCreateOrderRequest = serverRequest.bodyToMono(OrderRequest.class);
		
		return ServerResponse.ok()
				.contentType(MediaType.TEXT_EVENT_STREAM)
				.body(monoCreateOrderRequest
						.map(orderService::mapOrderRequestToOrder)
						.flatMap(orderService::createOrder)
						.map(orderService::mapOrderToOrderResponse), OrderResponse.class);
	}
	
	public Mono<ServerResponse> createMultipleOrder(ServerRequest serverRequest) {
		Flux<OrderRequest> fluxCreateOrderRequest = serverRequest.bodyToFlux(OrderRequest.class);
		
		return ServerResponse.ok()
				.contentType(MediaType.TEXT_EVENT_STREAM)
				.body(fluxCreateOrderRequest
						.map(orderService::mapOrderRequestToOrder)
						.flatMap(orderService::createOrder)
						.map(orderService::mapOrderToOrderResponse), OrderResponse.class);
	}
	
	public Mono<ServerResponse> updateOrder(ServerRequest serverRequest) {
		Mono<OrderRequest> monoCreateOrderRequest = serverRequest.bodyToMono(OrderRequest.class);
		Long id = Long.valueOf(serverRequest.pathVariable("id"));
		
		return ServerResponse.ok()
				.contentType(MediaType.TEXT_EVENT_STREAM)
				.body(orderService.getOrderById(id)
						.flatMap(order -> monoCreateOrderRequest
								.map(orderRequest -> orderService.updateOrderRequestToOrder(orderRequest, order)))
						.flatMap(orderService::createOrder)
						.map(orderService::mapOrderToOrderResponse), 
						OrderResponse.class);
	}
	
	public Mono<ServerResponse> deleteOrder(ServerRequest serverRequest) {
		Long id = Long.valueOf(serverRequest.pathVariable("id"));
		
		return ServerResponse.ok()
				.contentType(MediaType.TEXT_PLAIN)
				.body(orderService.deleteOrder(id).map(m -> "Order has been deleted."), 
						String.class);
	}
}
