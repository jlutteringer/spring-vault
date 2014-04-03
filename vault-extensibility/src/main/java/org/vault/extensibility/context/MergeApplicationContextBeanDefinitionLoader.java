package org.vault.extensibility.context;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.BeansException;
import org.springframework.beans.FatalBeanException;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.core.io.Resource;
import org.vault.base.resources.stream.ResourceInputStream;
import org.vault.base.utilities.configuration.Configurations;
import org.vault.extensibility.context.merge.ImportProcessor;
import org.vault.extensibility.context.merge.exceptions.MergeException;

import com.google.common.collect.Lists;

public class MergeApplicationContextBeanDefinitionLoader {
	private MergeApplicationContext parent;

	public MergeApplicationContextBeanDefinitionLoader(MergeApplicationContext parent) {
		this.parent = parent;
	}

	/**
	* Load the bean definitions with the given XmlBeanDefinitionReader.
	* <p>The lifecycle of the bean factory is handled by the refreshBeanFactory method;
	* therefore this method is just supposed to load and/or register bean definitions.
	* <p>Delegates to a ResourcePatternResolver for resolving location patterns
	* into Resource instances.
	* @throws org.springframework.beans.BeansException in case of bean registration errors
	* @throws java.io.IOException if the required XML document isn't found
	* @see #refreshBeanFactory
	* @see #getConfigLocations
	* @see #getResources
	* @see #getResourcePatternResolver
	*/
	public void loadBeanDefinitions(XmlBeanDefinitionReader reader) throws BeansException, IOException {
		List<ResourceInputStream> configurations = Lists.newArrayList();
		for (ResourceInputStream resource : Configurations.resolveResources(parent.getPatchLocations(), parent)) {
			if (resource.available() <= 0) {
				resource.close();
				throw new IOException("Unable to open an input stream on specified application context resource: " + resource.getName());
			}

			configurations.add(resource);
		}

		ImportProcessor importProcessor = new ImportProcessor(parent);
		try {
			configurations = importProcessor.extract(configurations);
		} catch (MergeException e) {
			throw new FatalBeanException("Unable to merge source and patch locations", e);
		}

		List<Resource> resources = new MergeApplicationContextXmlConfigResource().getConfigResources(configurations);

		reader.loadBeanDefinitions(resources.toArray(new Resource[0]));
	}
}