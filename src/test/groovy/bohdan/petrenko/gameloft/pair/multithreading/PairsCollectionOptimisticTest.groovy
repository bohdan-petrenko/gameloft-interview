package bohdan.petrenko.gameloft.pair.multithreading

class PairsCollectionOptimisticTest extends BasePairsCollectionTest {
	def target = new PairsCollectionOptimistic(NUMBERS)

	@Override
	PairsCollection target() {
		target
	}
}
