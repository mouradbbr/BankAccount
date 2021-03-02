package com.kata.bankAccount.model;

import lombok.Builder;
import lombok.Data;

import javax.persistence.*;

@Data
@Builder
@Entity
public class Account {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	private double balance;
	@OneToOne
	private Customer client;
	public Account() {
		super();
	}
	public Account(Long id, double balance, Customer client) {
		super();
		this.id = id;
		this.balance = balance;
		this.client = client;
	}
	

}