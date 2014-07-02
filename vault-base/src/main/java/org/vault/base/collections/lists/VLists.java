package org.vault.base.collections.lists;

import java.util.List;
import java.util.function.Function;
import java.util.function.Supplier;

import com.google.common.collect.Lists;

public class VLists {
	@SafeVarargs
	public static <T> List<T> list(T... items) {
		return Lists.newArrayList(items);
	}

	public static <T> List<T> list(Iterable<T> items) {
		return Lists.newArrayList(items);
	}

	public static <T> Supplier<List<T>> listSupplier() {
		return () -> Lists.newArrayList();
	}

	public static <T, N> List<T> transform(List<N> list, Function<N, T> transformer) {
		List<T> transformedList = Lists.newArrayList();
		for (N item : list) {
			transformedList.add(transformer.apply(item));
		}
		return transformedList;
	}
}
