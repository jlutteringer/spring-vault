package org.alloy.forge.configuration;

import java.util.List;

import org.alloy.forge.module.Module;
import org.alloy.forge.module.ModuleLoader;
import org.alloy.metal.configuration.ConfigurationLocation;
import org.springframework.beans.factory.annotation.Autowired;

import com.google.common.collect.Lists;

public abstract class ConfigurationManager {
	@Autowired
	private ModuleLoader moduleLoader;

	public List<ConfigurationLocation> buildConfigurationLocations() {
		List<ConfigurationLocation> configurationLocations = Lists.newArrayList();
		for (Module module : moduleLoader.getModuleLoadOrder()) {
			configurationLocations.addAll(getDefaultConfigurationLocations(module));
			configurationLocations.addAll(getSpecificConfigurationLocations(module));
		}

		return configurationLocations;
	}

	protected abstract List<ConfigurationLocation> getSpecificConfigurationLocations(Module module);

	protected abstract List<ConfigurationLocation> getDefaultConfigurationLocations(Module module);
}