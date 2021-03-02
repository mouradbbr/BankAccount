package com.kata.bankAccount.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
@Entity
@EntityListeners(AuditingEntityListener.class)
public class Record {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	private double amount;
	@Enumerated(EnumType.STRING)
	private RecordType type;
	@Column(nullable = false, updatable = false)
    @CreatedDate
	private LocalDateTime date;
	@ManyToOne()
	@JoinColumn(name = "accountId")
	private Account account;


	
}