package org.example.methodsecurity;

import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;

public interface BankAccountService {

	@PostReadBankAccount
	BankAccount findById(int id);

	@PostReadBankAccount
	BankAccount getById(int id);
}
