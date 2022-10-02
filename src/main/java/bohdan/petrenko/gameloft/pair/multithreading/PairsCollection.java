package bohdan.petrenko.gameloft.pair.multithreading;

/**
 * This interface responsible for managing collection of numbers.
 * Also, it finds pairs count, which sum is equal to the given value.
 * Instances of this interface is thread safe.
 */
public interface PairsCollection extends Sized {

	/**
	 * Adds value to the collection
	 * @param n number to add
	 */
	void addNumber(int n);

	/**
	 * Finds count of pairs, which sum is equal to <code>target</code>
	 * @param target requested pair sum value
	 * @return count of pairs, which sum is equal to <code>target</code>
	 */
	int findValue(int target);
}
