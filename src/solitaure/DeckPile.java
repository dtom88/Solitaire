package solitaure;

class DeckPile extends CardPile {

	DeckPile(final int x, final int y) {
		// first initialize parent
		super(x, y);
		// then create the new deck
		// first put them into a local pile
		CardPile pileOne = new CardPile(0, 0);
		CardPile pileTwo = new CardPile(0, 0);
		int count = 0;
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j <= 12; j++) {
				pileOne.addCard(new Card(i, j));
				count++;
			}
		}
		// then pull them out randomly
		for (; count > 0; count--) {
			int limit = ((int) (Math.random() * 1000)) % count;
			// move down to a random location
			for (int i = 0; i < limit; i++) {
				pileTwo.addCard(pileOne.pop());
			}
			// then add the card found there
			addCard(pileOne.pop());
			// then put the decks back together
			while (!pileTwo.empty()) {
				pileOne.addCard(pileTwo.pop());
			}
		}
	}

	public void select(final int tx, final int ty) {
//		if(CanTakeCardPile.getSelectedPile()!=null){
//			CanTakeCardPile.resetSelectedPile();
//			return;
//		}
		if (empty()) {
			backToDeckPile();
			return;
		}
	
	//	CanTakeCardPile.resetSelectedPile();

		Solitaire.discardPile.addCard(pop());
	}
	
	private void backToDeckPile(){
		
		while( !Solitaire.discardPile.empty()) {
			Card topCard = Solitaire.discardPile.pop();
			topCard.flip();
			addCard(topCard);
			
		}	
		
	}
}