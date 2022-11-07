/**
 * 
 */
package org.vj.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.vj.bean.OrderResponse;
import org.vj.entity.Order;
import org.vj.repository.OrderRepository;
import org.vj.request.OrderRequest;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * @author vijayakumar
 * @Since  02-Nov-2022
 *
 */
@Service
public class OrderService {

	@Autowired
	OrderRepository orderRepository;
	
	public Flux<Order> getAllOrders() {
		return orderRepository.findAll().log();
	}
	
	public Mono<Order> getOrderById(Long id) {
		return orderRepository.findById(id).log();
	}
	
	public Mono<Order> createOrder(Order order) {
		return orderRepository.save(order);
	}
	
	public Mono<Void> deleteOrder(Long id) {
		return orderRepository.deleteById(id);
	}
	
	public OrderResponse mapOrderToOrderResponse(Order order) {
		return new OrderResponse(order.getId(), order.getAmount(), order.getPlacedDate());
	}
	
	public Order mapOrderRequestToOrder(OrderRequest orderRequest) {
		return new Order(orderRequest.getAmount(), orderRequest.getPlacedDate());
	}
	
	public Order updateOrderRequestToOrder(OrderRequest orderRequest, Order order) {
		order.setAmount(orderRequest.getAmount());
		order.setPlacedDate(orderRequest.getPlacedDate());
		return order;
	}
}
