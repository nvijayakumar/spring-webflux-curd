/**
 * 
 */
package org.vj.repository;

import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;
import org.vj.entity.Order;

/**
 * @author vijayakumar
 * @Since  02-Nov-2022
 *
 */

@Repository
public interface OrderRepository extends R2dbcRepository<Order, Long> {

}
