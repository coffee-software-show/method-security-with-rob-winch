package org.example.methodsecurity;

import org.springframework.security.authorization.AuthorizationDecision;
import org.springframework.security.authorization.AuthorizationDeniedException;
import org.springframework.security.core.context.SecurityContextHolder;

import java.security.Principal;

public class BankAccountServiceProxy implements BankAccountService {

	final BankAccountService delegate;

	public BankAccountServiceProxy(BankAccountService delegate) {
		this.delegate = delegate;
	}

	@Override
	public BankAccount findById(int id) {
		BankAccount result = delegate.findById(id);
		Principal principal = SecurityContextHolder.getContext().getAuthentication();


		if (!result.getOwner().equals(principal.getName())) {
			throw new AuthorizationDeniedException("Denied", new AuthorizationDecision(false));
		}
		return result;
	}

	@Override
	public BankAccount getById(int id) {
		return delegate.getById(id);
	}
}
