package org.vault.module.registry.site.security;

import org.springframework.stereotype.Component;
import org.vault.base.module.domain.Dependencies;
import org.vault.core.module.domain.simple.ManagedModule;
import org.vault.module.registry.security.SecurityModule;
import org.vault.module.registry.site.SiteModule;

@Component
public class SiteSecurityModule extends ManagedModule {
	public SiteSecurityModule() {
		this.name = "vault-site-security";
		this.friendlyName = "Site Security Module";

		this.dependencies
				.add(Dependencies.builder().of(SecurityModule.class)
						.weak()
						.create(),
						Dependencies.builder().of(SiteModule.class)
								.weak()
								.create());
	}
}