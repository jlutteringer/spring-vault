package org.vault.base.utilities.matcher;

public abstract class AbstractContextualSelector<T, N> extends AbstractSelector<T> implements ContextualMatcher<T, N> {
	protected N context;

	@Override
	public Boolean hasContext() {
		return context != null;
	}

	@Override
	public void setContext(N context) {
		this.context = context;
	}
}