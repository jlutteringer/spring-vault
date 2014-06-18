package org.vault.module.registry.core.test;

import org.springframework.stereotype.Component;
import org.vault.core.module.domain.simple.ManagedModule;

@Component
public class TestModule3 extends ManagedModule {
	public TestModule3() {
		this.name = "vault-core-test-module3";
		this.friendlyName = "Test Module 3";
	}
}