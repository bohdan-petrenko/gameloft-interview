package bohdan.petrenko.gameloft.pair.solution

import spock.lang.Specification
import spock.lang.Unroll

class UnorderedPairsCounterTest extends Specification {
	PairsCounter counter = new UnorderedPairsCounter()

	@Unroll
	def "should find #pairsCount pairs with requested sum #pairSum in a given collection #numbers"() {
		expect:
		counter.pairsCount(numbers, pairSum) == pairsCount

		where:
		numbers         | pairSum || pairsCount
		[]              | 1       || 0
		[1, 2]          | 4       || 0
		[1, 2, 3, 4]    | 5       || 2
		[5, 4, 1, 6, 2] | 8       || 1
		[1, 3, 3, 5, 2] | 6       || 2
		[1, 1, 3, 5, 5] | 6       || 1
	}
}
