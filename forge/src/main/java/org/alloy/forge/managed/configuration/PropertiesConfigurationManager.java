package org.alloy.forge.managed.configuration;

import java.util.List;

import org.alloy.forge.configuration.ConfigurationManager;
import org.alloy.forge.context.BootstrappedBean;
import org.alloy.metal.application.ApplicationMetaData;
import org.alloy.metal.configuration.ConfigurationLocation;
import org.alloy.metal.configuration._Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.vault.base.module.domain.Module;
import org.vault.base.module.domain.PrimaryModuleFacet;
import org.vault.base.utilities.constants.AlloyConfigurationConstants;

import com.google.common.collect.Lists;

@Service
public class PropertiesConfigurationManager extends ConfigurationManager implements BootstrappedBean {
	@Autowired
	private ApplicationMetaData application;

	@Override
	protected List<ConfigurationLocation> getSpecificConfigurationLocations(Module module) {
		return module.getFacet(PrimaryModuleFacet.class).getPropertyConfigurationLocations();
	}

	@Override
	protected List<ConfigurationLocation> getDefaultConfigurationLocations(Module module) {
		List<ConfigurationLocation> locations = Lists.newArrayList();

		locations.add(_Configuration.optional(_Configuration.moduleRelative(
				_Configuration.createEnvironmentLocation(AlloyConfigurationConstants.PROPERTIES_FILE_STRUCTURE, application.getEnvironment().getType()), module)));

		locations.add(_Configuration.optional(_Configuration.moduleRelative(
				_Configuration.createEnvironmentLocation(
						AlloyConfigurationConstants.PROPERTIES_DIRECTORY + AlloyConfigurationConstants.PROPERTIES_FILE_STRUCTURE, application.getEnvironment().getType()), module)));

		return locations;
	}
}