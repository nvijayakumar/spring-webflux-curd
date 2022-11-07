/**
 * 
 */
package org.vj.request;

import java.time.LocalDateTime;

import lombok.Data;

/**
 * @author vijayakumar
 * @Since  06-Nov-2022
 *
 */
@Data
public class OrderRequest {

	private Double amount;
	
	private LocalDateTime placedDate;
}
