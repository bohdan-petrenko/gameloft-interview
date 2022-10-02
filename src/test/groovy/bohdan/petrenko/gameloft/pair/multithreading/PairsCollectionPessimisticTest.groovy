package bohdan.petrenko.gameloft.pair.multithreading

import spock.lang.Specification

class PairsCollectionPessimisticTest extends BasePairsCollectionTest {
	def target = new PairsCollectionPessimistic(NUMBERS)

	@Override
	PairsCollection target() {
		target
	}
}
