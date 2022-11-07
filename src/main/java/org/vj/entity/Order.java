/**
 * 
 */
package org.vj.entity;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import lombok.Getter;
import lombok.Setter;

/**
 * @author vijayakumar
 * @Since  02-Nov-2022
 *
 */

@Getter
@Setter
@Table("orders")
public class Order {
	
	@Id
	@Column("id")
	private Long id;
	@Column("amount")
	private Double amount;
	@Column("placed_date")
	private LocalDateTime placedDate;

	public Order(Double amount, LocalDateTime placedDate) {
		this.amount = amount;
		this.placedDate = placedDate;
	}
}
