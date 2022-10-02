package bohdan.petrenko.gameloft.pair.solution;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UnorderedPairsCounter implements PairsCounter {

	@Override
	public int pairsCount(List<Integer> numbers, Integer pairSum) {
		int doubleCount = 0;
		Map<Integer, Integer> countPerNumber = countPerNumber(numbers);

		for (int number : countPerNumber.keySet()) {
			int opposite = pairSum - number;
			if (number == opposite) {
				if (countPerNumber.get(number) > 1) {
					doubleCount += 2;
				}
			}
			else {
				if (countPerNumber.containsKey(opposite)) {
					doubleCount++;
				}
			}
		}

		return doubleCount / 2;
	}

	private Map<Integer, Integer> countPerNumber(Collection<Integer> numbers) {
		Map<Integer, Integer> map = new HashMap<>();
		for (Integer number : numbers) {
			map.compute(number, (key, count) -> count == null ? 1 : count + 1);
		}
		return map;
	}
}
