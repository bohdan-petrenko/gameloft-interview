package bohdan.petrenko.gameloft.pair.multithreading;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.function.Function;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.summingInt;

public class PairsCollectionPessimistic implements PairsCollection {
	private final Map<Integer, Integer> groupedNumbers;
	private final ReadWriteLock lock;

	public PairsCollectionPessimistic(Collection<Integer> numbers) {
		this.lock = new ReentrantReadWriteLock();
		this.groupedNumbers = numbers.stream()
				.collect(groupingBy(Function.identity(), summingInt(e -> 1)));
	}

	@Override
	public void addNumber(int number) {
		Lock writeLock = lock.writeLock();
		try {
			writeLock.lock();
			groupedNumbers.compute(number, (key, count) -> count == null ? 1 : count + 1);
		} finally {
			writeLock.unlock();
		}
	}

	@Override
	public int findValue(int pairSum) {
		Lock readLock = lock.readLock();
		try {
			readLock.lock();
			return PairCounter.pairsCount(groupedNumbers, pairSum);
		} finally {
			readLock.unlock();
		}
	}

	@Override
	public int size() {
		Lock readLock = lock.readLock();
		try {
			readLock.lock();
			return calculateSize(groupedNumbers);
		} finally {
			readLock.unlock();
		}
	}
}
