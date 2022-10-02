package bohdan.petrenko.gameloft.pair.solution;

import java.util.List;

public class OrderedPairsCounter implements PairsCounter {

	@Override
	public int pairsCount(List<Integer> numbers, Integer pairSum) {
		if (numbers.size() < 2) {
			return 0;
		}
		int count = 0;
		int leftIndex = 0;
		int rightIndex = numbers.size() - 1;
		int leftValue = numbers.get(leftIndex);
		int rightValue = numbers.get(rightIndex);
		int lastLeftValue = leftValue - 1;
		while (leftIndex < rightIndex) {
			if (leftValue + rightValue > pairSum) {
				rightValue = numbers.get(--rightIndex);
			} else if (leftValue + rightValue < pairSum) {
				leftValue = numbers.get(++leftIndex);
			} else {
				if (lastLeftValue != leftValue) {
					lastLeftValue = leftValue;
					count++;
				}
				leftValue = numbers.get(++leftIndex);
				rightValue = numbers.get(--rightIndex);
			}
		}
		return count;
	}
}
