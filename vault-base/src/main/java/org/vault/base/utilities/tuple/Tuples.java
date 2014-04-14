package org.vault.base.utilities.tuple;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.vault.base.utilities.tuple.Tuple.Pair;
import org.vault.base.utilities.tuple.Tuple.Single;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

public class Tuples {
	public static <T, N> List<Pair<T, N>> flatten(Map<T, N> map) {
		List<Pair<T, N>> flattenedMap = Lists.newArrayList();
		for (Entry<T, N> entry : map.entrySet()) {
			flattenedMap.add(Tuple.of(entry.getKey(), entry.getValue()));
		}
		return flattenedMap;
	}

	public static <T, N> Map<T, N> buildMap(Iterable<Pair<T, N>> list) {
		Map<T, N> map = Maps.newHashMap();
		for (Pair<T, N> pair : list) {
			map.put(pair.getFirst(), pair.getSecond());
		}
		return map;
	}

	public static <T, N> Map<N, T> buildReverseMap(Iterable<Pair<T, N>> list) {
		Map<N, T> map = Maps.newHashMap();
		for (Pair<T, N> pair : list) {
			map.put(pair.getSecond(), pair.getFirst());
		}
		return map;
	}

	public static <T, N> Pair<T, N> toTuple(Entry<T, N> entry) {
		return Tuple.of(entry.getKey(), entry.getValue());
	}

	public static <T> List<T> coalesceFirst(List<? extends Single<T>> singles) {
		List<T> coalescedList = Lists.newArrayList();
		for (Single<T> single : singles) {
			coalescedList.add(single.getFirst());
		}
		return coalescedList;
	}

	public static <T, N> List<N> coalesceSecond(List<? extends Pair<T, N>> pairs) {
		List<N> coalescedList = Lists.newArrayList();
		for (Pair<T, N> pair : pairs) {
			coalescedList.add(pair.getSecond());
		}
		return coalescedList;
	}

	public static <T, N> Comparator<Pair<T, N>> sortSecond(final Comparator<N> comparator) {
		return new Comparator<Pair<T, N>>() {
			@Override
			public int compare(Pair<T, N> o1, Pair<T, N> o2) {
				return comparator.compare(o1.getSecond(), o2.getSecond());
			}
		};
	}
}