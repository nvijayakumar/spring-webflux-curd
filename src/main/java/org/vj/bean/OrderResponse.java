/**
 * 
 */
package org.vj.bean;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * @author vijayakumar
 * @Since  03-Nov-2022
 *
 */
@Getter
@Setter
@AllArgsConstructor
public class OrderResponse {

	private Long id;
	private Double amount;
	private LocalDateTime placedDate;
}
