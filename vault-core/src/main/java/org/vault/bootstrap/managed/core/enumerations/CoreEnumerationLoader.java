package org.vault.bootstrap.managed.core.enumerations;

import java.util.List;

import org.vault.base.enumeration.VEnumeration;
import org.vault.base.enviornment.EnvironmentType;
import org.vault.bootstrap.managed.logging.initialization.AbstractEnumerationLoader;

public class CoreEnumerationLoader extends AbstractEnumerationLoader {
	@Override
	protected void registerEnumerations(List<Class<? extends VEnumeration>> registry) {
		registry.add(EnvironmentType.class);
	}
}