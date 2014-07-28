package org.vault.site.managed.resource.handler;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.vault.base.request.Path;
import org.vault.site.managed.resource.service.ResourceBundlingService;
import org.vault.site.resource.handler.AbstractVaultResourceResolver;

public class BundleResourceResolver extends AbstractVaultResourceResolver {
	@Autowired
	private ResourceBundlingService resourceBundlingService;

	@Override
	public boolean canHandle(Path path) {
		return resourceBundlingService.hasBundleForResource(path.getPath());
	}

	@Override
	public Resource getFileContents(Path path, List<Resource> locations) {
		return resourceBundlingService.getBundleForResource(path.getPath(), locations);
	}

	@Override
	public int getOrder() {
		return 1000;
	}
}