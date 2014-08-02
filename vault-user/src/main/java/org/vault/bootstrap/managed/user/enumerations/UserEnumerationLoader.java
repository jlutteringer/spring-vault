package org.vault.bootstrap.managed.user.enumerations;

import java.util.List;

import org.alloy.metal.enumeration.ExtendableEnumeration;
import org.vault.bootstrap.managed.logging.initialization.AbstractEnumerationLoader;
import org.vault.user.domain.PermissionType;
import org.vault.user.domain.UserType;

public class UserEnumerationLoader extends AbstractEnumerationLoader {
	@Override
	protected void registerEnumerations(List<Class<? extends ExtendableEnumeration>> registry) {
		registry.add(PermissionType.class);
		registry.add(UserType.class);
	}
}