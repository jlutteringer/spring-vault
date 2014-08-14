package org.alloy.forge.managed.web.enumerations;

import java.util.List;

import org.alloy.forge.enumeration.AbstractEnumerationLoader;
import org.alloy.metal.enumeration.ExtendableEnumeration;
import org.alloy.site.request.RequestLifecycle;

public class WebEnumerationLoader extends AbstractEnumerationLoader {
	@Override
	protected void registerEnumerations(List<Class<? extends ExtendableEnumeration>> registry) {
		registry.add(RequestLifecycle.class);
	}
}