package com.to.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tbl_order")
public class Order {

	@Id
	//defining the sequence 
	@SequenceGenerator(
			name = "order_sequence",
			sequenceName = "order_sequence",
			allocationSize = 1
			)
	//give the sequence to generated value
	@GeneratedValue(
			strategy = GenerationType.SEQUENCE,
			generator = "order_sequence"
			)
	private Long orderId;
	private String emailId;
	private Integer orderSize;
	private Long orderPrise;
}
