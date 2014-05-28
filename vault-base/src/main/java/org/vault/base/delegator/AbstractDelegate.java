package org.vault.base.delegator;

import org.vault.base.utilities.function.VMethod;
import org.vault.base.utilities.matcher.Matcher;

public abstract class AbstractDelegate<N> implements Delegate<N> {
	@Override
	public <T> T invoke(VMethod<T> invocation) {
		return null;
	}

	@Override
	public boolean matches(N delegatee) {
		return this.getInternalMatcher().matches(delegatee);
	}

	@Override
	public int getOrder() {
		return 0;
	}

	protected abstract Matcher<N> getInternalMatcher();
}
