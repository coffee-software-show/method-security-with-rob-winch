package org.example.methodsecurity;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.authorization.AuthorizationDeniedException;
import org.springframework.security.authorization.AuthorizationProxyFactory;
import org.springframework.security.authorization.method.AuthorizationAdvisorProxyFactory;
import org.springframework.security.test.context.support.WithMockUser;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

@SpringBootTest
class MethodSecurityApplicationTests {

	@Autowired
	BankAccountService bank;

	@WithMockUser("rob")
	@Test
	void findById() {
		bank.findById(1);
	}

	@WithMockUser("josh")
	@Test
	void findByIdWhenDenied() {
		assertThatExceptionOfType(AuthorizationDeniedException.class).isThrownBy(() -> bank.findById(1));
	}

	@WithMockUser("rob")
	@Test
	void getById() {
		bank.getById(1);
	}

	@WithMockUser("josh")
	@Test
	void getByIdWhenDenied() {
		assertThatExceptionOfType(AuthorizationDeniedException.class).isThrownBy(() -> bank.getById(1));
	}

	@WithMockUser(username = "accountant", roles = "ACCOUNTANT")
	@Test
	void findByIdWhenAccountant() {
		bank.findById(1);
	}

	@WithMockUser(username = "accountant", roles = "ACCOUNTANT")
	@Test
	void getByIdWhenAccountant() {
		bank.getById(1);
	}



	@WithMockUser(username = "accountant", roles = "ACCOUNTANT")
	@Test
	void findByIdWhenAccountantNumber() {
		assertThat(bank.findById(1).getAccountNumber()).isEqualTo("****");
	}
}
