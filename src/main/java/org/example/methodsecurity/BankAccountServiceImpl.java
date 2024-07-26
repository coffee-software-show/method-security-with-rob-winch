package org.example.methodsecurity;

import org.springframework.security.authorization.AuthorizationProxyFactory;
import org.springframework.security.authorization.method.AuthorizationAdvisorProxyFactory;

public class BankAccountServiceImpl implements BankAccountService {

	@Override
	public BankAccount findById(int id) {
		BankAccount result = new BankAccount(id, "rob", "1234567890", 54321);
		return result;
	}

	@Override
	public BankAccount getById(int id) {
		return findById(id);
	}
}
