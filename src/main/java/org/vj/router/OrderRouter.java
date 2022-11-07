/**
 * 
 */
package org.vj.router;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;
import org.vj.handler.OrderHandler;

/**
 * @author vijayakumar
 * @Since  02-Nov-2022
 *
 */
@Configuration
public class OrderRouter {
	
	@Autowired
	OrderHandler orderHandler;
	
	@Bean
	public RouterFunction<ServerResponse> orderRoutes() {
		return RouterFunctions.route(RequestPredicates.GET("/api/order/getAll"), orderHandler::getAllOrders)
				//.andRoute(RequestPredicates.GET("/api/order/{id}"), orderHandler::getOrderById)
				.andRoute(RequestPredicates.GET("/api/order"), orderHandler::getOrderById)
				//route for creating the order
				.andRoute(RequestPredicates.POST("/api/order/create").and(RequestPredicates.accept(MediaType.APPLICATION_JSON)), 
						orderHandler::createOrder)
				//route for creating multiple orders
				.andRoute(RequestPredicates.POST("/api/order/createMultiple").and(RequestPredicates.accept(MediaType.APPLICATION_JSON)), 
						orderHandler::createMultipleOrder)
				//route for modifying the order
				.andRoute(RequestPredicates.PUT("/api/order/update/{id}").and(RequestPredicates.accept(MediaType.APPLICATION_JSON)), 
						orderHandler::updateOrder)
				//route for deleting the order
				.andRoute(RequestPredicates.DELETE("/api/order/delete/{id}"), orderHandler::deleteOrder);
	}

}
