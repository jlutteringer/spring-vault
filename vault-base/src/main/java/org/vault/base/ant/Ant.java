package org.vault.base.ant;

import java.util.function.Predicate;

public class Ant {
	public static Predicate<String> pathMatcher(String pattern) {
		return new AntPathPredicate(pattern);
	}
}