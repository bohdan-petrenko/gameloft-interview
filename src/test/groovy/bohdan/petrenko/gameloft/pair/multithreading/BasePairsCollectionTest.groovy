package bohdan.petrenko.gameloft.pair.multithreading

import spock.lang.Specification

abstract class BasePairsCollectionTest extends Specification {
	static def ITERATIONS = 200_000
	static def NUMBERS = [1, 3, 5, 7, 9]

	abstract PairsCollection target()

	def "should count pairs in multiple threads safe"() {
		given:
		def threads = [
				addNumber9(),
				addRandomNumberMoreThan100(),
				pairsOf10(),
				pairsOf16()
		]
		threads.each { it.start() }
		threads.each { it.join() }
	}

	private Thread addNumber9() {
		newThread(
				{ -> target().addNumber(9) }, "add-number-9"
		)
	}

	private Thread addRandomNumberMoreThan100() {
		newThread(
				{ -> target().addNumber(100 + Math.abs((int) System.currentTimeMillis())) },
				"add-random-number-more-than-100"
		)
	}

	private Thread pairsOf10() {
		newThread(
				{ ->
					def pairSum = 10
					def pairsCount = target().findValue(pairSum)
					def expected = 2
					if (pairsCount != expected) {
						throw new RuntimeException("Invalid pairs count $pairsCount of $pairSum. Should be $expected")
					}
				}, "pairs-of-10"
		)
	}

	private Thread pairsOf16() {
		newThread(
				{ ->
					def pairSum = 16
					def pairsCount = target().findValue(pairSum)
					def expected = 1
					if (pairsCount != expected) {
						throw new RuntimeException("Invalid pairs count $pairsCount of $pairSum. Should be $expected")
					}
				}, "pairs-of-16"
		)
	}

	private Thread newThread(def task, String name) {
		new Thread(
				{ ->
					int i
					for (i = 0; i < ITERATIONS; i++) {
						task()
					}
					println "Thread ${Thread.currentThread().getName()} finished after $i iterations with collection size ${target().size()}"
				}, name
		)
	}
}
