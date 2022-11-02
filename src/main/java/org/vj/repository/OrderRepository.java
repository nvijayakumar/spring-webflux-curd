/**
 * 
 */
package org.vj.repository;

import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.vj.entity.Order;

/**
 * @author vijayakumar
 * @Since  02-Nov-2022
 *
 */
public interface OrderRepository extends R2dbcRepository<Order, Long> {

}
