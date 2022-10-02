package bohdan.petrenko.gameloft.pair.multithreading;

import java.util.Map;

import lombok.experimental.UtilityClass;

@UtilityClass
public class PairCounter {

	public static int pairsCount(Map<Integer, Integer> groupedNumbers, int pairSum) {
		int doubleCount = 0;
		for (int number : groupedNumbers.keySet()) {
			int opposite = pairSum - number;
			if (number == opposite) {
				if (groupedNumbers.get(number) > 1) {
					doubleCount += 2;
				}
			}
			else {
				if (groupedNumbers.containsKey(opposite)) {
					doubleCount++;
				}
			}
		}
		return doubleCount / 2;
	}
}
