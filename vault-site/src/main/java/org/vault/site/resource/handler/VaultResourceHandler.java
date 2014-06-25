package org.vault.site.resource.handler;

import java.util.List;

import org.springframework.core.Ordered;
import org.springframework.core.io.Resource;

public interface VaultResourceHandler extends Ordered {
	/**
	* @param path
	* @return booelean determining whether or not this handler is able to handle the given request
	*/
	public boolean canHandle(String path);

	/**
	* @param path
	* @param locations
	* @return the Resource representing this file
	*/
	public Resource getFileContents(String path, List<Resource> locations);

	/**
	* Attempts to retrive the requested resource from cache. If not cached, generates the resource, caches it,
	* and then returns it
	*
	* @param request
	* @param location
	* @return the generated resource
	*/
	public Resource getResource(final String path, final List<Resource> locations);
}
