package com.kata.bankAccount;

import static com.kata.bankAccount.model.RecordType.DEPOSIT;
import static com.kata.bankAccount.model.RecordType.WITHDRAWAL;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import com.kata.bankAccount.controller.dto.RecordDto;
import com.kata.bankAccount.model.Account;
import com.kata.bankAccount.model.Record;
import com.kata.bankAccount.repository.AccountRepository;
import com.kata.bankAccount.repository.RecordRepository;
import com.kata.bankAccount.repository.exception.BusinessException;
import com.kata.bankAccount.service.BankService;
import com.kata.bankAccount.service.BankServiceImpl;
@RunWith(MockitoJUnitRunner.class)
public class BankAccountApplicationTests {

	@InjectMocks
	BankServiceImpl bankService;
	@Mock
	AccountRepository accountRepository;
	@Mock
	RecordRepository recordRepository;
	@Captor
	ArgumentCaptor<Account> accountCaptor;
	@Captor
	ArgumentCaptor<Record> recordCaptor;


	@Test
	public void should_make_deposit_correctly() throws BusinessException {
		// Given
		float balance = 15000;
		Account account = Account.builder()
				.balance(15000f)
				.id(2l)
				.build();
		RecordDto record = RecordDto.builder()
				.amount(10000f)
				.type(DEPOSIT)
				.build();
		when(accountRepository.findById(eq(account.getId()))).thenReturn(Optional.ofNullable(account));
		when(accountRepository.save(any())).thenReturn(null);
		when(recordRepository.save(any())).thenReturn(null);

		// When
		Record addedRecord = bankService.addRecordToAccount(account.getId(), record);

		// Then
		verify(accountRepository).save(accountCaptor.capture());
		verify(recordRepository).save(recordCaptor.capture());
		Account savedAccount = accountCaptor.getValue();
		Record savedRecord = recordCaptor.getValue();
		assertThat(savedAccount.getBalance()).isEqualTo(25000);
		assertThat(savedRecord.getDate().getDayOfMonth()).isEqualTo(LocalDateTime.now().getDayOfMonth());
		assertThat(savedRecord.getType()).isEqualTo(DEPOSIT);
	}

	@Test
	public void should_make_withdrawal_correctly() throws BusinessException {
		// Given
		Account account = Account.builder()
				.balance(15000f)
				.id(2l)
				.build();
		RecordDto record = RecordDto.builder()
				.amount(10000f)
				.type(WITHDRAWAL)
				.build();
		when(accountRepository.findById(eq(account.getId()))).thenReturn(Optional.ofNullable(account));
		when(accountRepository.save(any())).thenReturn(null);
		when(recordRepository.save(any())).thenReturn(null);

		// When
		bankService.addRecordToAccount(account.getId(), record);

		// Then
		verify(accountRepository).save(accountCaptor.capture());
		verify(recordRepository).save(recordCaptor.capture());
		Account savedAccount = accountCaptor.getValue();
		Record savedRecord = recordCaptor.getValue();
		assertThat(savedAccount.getBalance()).isEqualTo(5000);
		assertThat(savedRecord.getDate().getDayOfMonth()).isEqualTo(LocalDateTime.now().getDayOfMonth());
		assertThat(savedRecord.getType()).isEqualTo(WITHDRAWAL);
	}

	@Test(expected = BusinessException.class)
	public void should_throw_exception_when_withdrawal_amount_more_than_balance() throws BusinessException {
		// Given
		Account account = Account.builder()
				.balance(15000f)
				.id(2l)
				.build();
		RecordDto record = RecordDto.builder()
				.amount(100000f)
				.type(WITHDRAWAL)
				.build();
		when(accountRepository.findById(eq(account.getId()))).thenReturn(Optional.ofNullable(account));

		// When
		bankService.addRecordToAccount(account.getId(), record);

		// Then an exception should be thrown
	}

	@Test(expected = BusinessException.class)
	public void should_throw_exception_when_account_not_found() throws BusinessException {
		// given
		long id = 2;
		when(accountRepository.findById(id)).thenReturn(Optional.empty());

		// When
		bankService.getAccount(id);
	}

	@Test
	public void should_see_account_history() {
		// Given


		Account account = Account.builder()
				.balance(15000f)
				.id(2l)
				.build();
		RecordDto record1 = RecordDto.builder()
				.amount(10000f)
				.type(WITHDRAWAL)
				.build();
		RecordDto record2 = RecordDto.builder()
				.amount(500f)
				.type(DEPOSIT)
				.build();

		//List<Account> accounts = bankService.getRecordsByAccountId();

	}
}