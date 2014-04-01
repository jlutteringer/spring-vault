package org.vault.base.collections.tree;

import java.util.Collection;

import org.vault.base.collections.AbstractCollection;
import org.vault.base.collections.AbstractCollectionListener;
import org.vault.base.collections.V_Collections;

public abstract class AbstractTree<T> extends AbstractCollection<T> implements Tree<T> {
	@Override
	public Collection<T> getBackingCollection() {
		return V_Collections.listenableCollection(Trees.flatten(this), new AbstractCollectionListener<T>() {
			@Override
			public void onAdd(T element) {
				addChild(element);
			}
		});
	}

	@Override
	public String toString() {
		return Trees.stringify(this);
	}
}