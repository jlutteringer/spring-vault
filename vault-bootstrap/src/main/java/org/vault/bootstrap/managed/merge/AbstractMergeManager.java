package org.vault.bootstrap.managed.merge;

import java.util.List;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.io.Resource;
import org.vault.base.resources.stream.ResourceInputStream;
import org.vault.base.spring.beans.AbstractLoggingBean;
import org.vault.base.utilities.configuration.ConfigurationLocation;
import org.vault.base.utilities.configuration.Configurations;
import org.vault.extensibility.context.merge.MergeXmlConfigResource;

public abstract class AbstractMergeManager extends AbstractLoggingBean implements MergeManager, ApplicationContextAware {
	protected ApplicationContext applicationContext;

	@Override
	public Resource getMergedResource(List<ConfigurationLocation> patchLocations) {
		List<ResourceInputStream> configurations = Configurations.getConfigurations(patchLocations, applicationContext);
		log.debug(configurations);

		MergeXmlConfigResource foo = new MergeXmlConfigResource(this.getMergeDescriptionLocation(), applicationContext);
		Resource mergedResource = foo.getMergedConfigResource(configurations);
		return mergedResource;
	}

	protected abstract String getMergeDescriptionLocation();

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext = applicationContext;
	}
}
