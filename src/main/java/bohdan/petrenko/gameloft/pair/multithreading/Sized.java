package bohdan.petrenko.gameloft.pair.multithreading;

import java.util.Map;

public interface Sized {
	/**
	 * Used in tests, to show collection size at thread completion. Part of API, cause need to be thread safe
	 * @return count of all numbers in collection, including duplicates
	 */
	int size();

	default int calculateSize(Map<?, Integer> map) {
		return map.values().stream()
				.reduce(0, Integer::sum);
	}
}
