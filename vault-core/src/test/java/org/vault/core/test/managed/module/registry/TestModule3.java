package org.vault.core.test.managed.module.registry;

import org.springframework.stereotype.Component;
import org.vault.core.module.domain.simple.SimpleModule;

@Component
public class TestModule3 extends SimpleModule {
	public TestModule3() {
		this.name = "vault-core-test-module3";
		this.friendlyName = "Test Module 3";
	}
}