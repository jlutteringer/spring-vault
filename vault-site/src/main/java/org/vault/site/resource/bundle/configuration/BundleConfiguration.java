package org.vault.site.resource.bundle.configuration;

import java.util.List;

import org.alloy.metal.collections.lists._Lists;

public class BundleConfiguration {
	private String name;
	private List<BundleConfigurationComponent> components = _Lists.list();

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<BundleConfigurationComponent> getComponents() {
		return components;
	}

	public void setComponents(List<BundleConfigurationComponent> components) {
		this.components = components;
	}
}