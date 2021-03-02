package com.kata.bankAccount;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import com.kata.bankAccount.controller.dto.RecordDto;
import com.kata.bankAccount.model.Account;
import com.kata.bankAccount.model.Customer;
import com.kata.bankAccount.model.RecordType;
import com.kata.bankAccount.service.BankService;

@SpringBootApplication
@EnableJpaAuditing
public class BankAccountApplication {

	public static void main(String[] args) {
		SpringApplication.run(BankAccountApplication.class, args);
	}
	

	@Bean
	public CommandLineRunner run(BankService bankService) {
		Customer customer = new Customer();
		Account account = Account.builder()
				.client(customer)
				.balance(15000)
				.build();
		return args -> {
			bankService.addCustomer(customer);
			bankService.addAccount(account);
			bankService.addRecordToAccount(account.getId(), RecordDto.builder()
					.amount(1000)
					.type(RecordType.DEPOSIT)
					.build());
			bankService.addRecordToAccount(account.getId(), RecordDto.builder()
					.amount(1500)
					.type(RecordType.WITHDRAWAL)
					.build());
			bankService.addRecordToAccount(account.getId(), RecordDto.builder()
					.amount(200)
					.type(RecordType.DEPOSIT)
					.build());
		};
	}

}
