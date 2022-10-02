package bohdan.petrenko.gameloft.pair.multithreading;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Function;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.summingInt;

public class PairsCollectionOptimistic implements PairsCollection {
	private final AtomicReference<Map<Integer, Integer>> groupedNumbersRef;

	public PairsCollectionOptimistic(Collection<Integer> numbers) {
		Map<Integer, Integer> groupedNumbers = numbers.stream()
				.collect(groupingBy(Function.identity(), summingInt(e -> 1)));
		this.groupedNumbersRef = new AtomicReference<>(groupedNumbers);
	}

	@Override
	public void addNumber(int number) {
		groupedNumbersRef.updateAndGet(groupedNumbers -> groupedNumbersPlusNew(number, groupedNumbers));
	}

	private Map<Integer, Integer> groupedNumbersPlusNew(int numberToAdd, Map<Integer, Integer> groupedNumbers) {
		Map<Integer, Integer> newGroupedNumbers = new HashMap<>(groupedNumbers);
		newGroupedNumbers.compute(numberToAdd, (key, count) -> count == null ? 1 : count + 1);
		return newGroupedNumbers;
	}

	@Override
	public int findValue(int pairSum) {
		return PairCounter.pairsCount(groupedNumbersRef.get(), pairSum);
	}

	@Override
	public int size() {
		return calculateSize(groupedNumbersRef.get());
	}
}
