package solitaure;

import java.awt.Graphics;

class TablePile extends CanTakeCardPile {
	static final int cardMargin = 35;
	int flippedCardsCount;
	private static int numberOfSelectedCards;

	TablePile(final int x, final int y, final int c) {
		// initialize the parent class
		super(x, y);
		// then initialize our pile of cards
		for (int i = 0; i < c; i++) {
			addCard(Solitaire.deckPile.pop());
		}
		// flip topmost card face up
		top().flip();
		flippedCardsCount = 1;
	}
	
	public static int getNumberOfSelectedCards(){
		return numberOfSelectedCards;
	}
	public void addCard(final Card aCard) {
		if (aCard.isFaceUp()) {
			flippedCardsCount++;
		}
		super.addCard(aCard);
	}
	
	public Card pop() {
		flippedCardsCount--;
		return super.pop();
	}

	public int getFlippedCardsCount() {
		return flippedCardsCount;
	}

	public boolean canTake(final Card aCard) {
		if (empty()) {
			return aCard.isKing();
		}
		Card topCard = top();
		return (aCard.color() != topCard.color())
				&& (aCard.getRank() == topCard.getRank() - 1);
	}

	public void display(final Graphics g) {
		stackDisplay(g, top());
	}

	public boolean includes(final int tx, final int ty) {
		// don't test bottom of card
		int margin = cardMargin * (getCardsCount() - 1);
		margin = margin >= 0 ? margin : 0;
		int flippedCounter = flippedCardsCount - 1;
		flippedCounter = flippedCounter >= 0 ? flippedCounter : 0;
		return x <= tx && tx <= x + Card.width
				&& y + margin - flippedCounter * cardMargin <= ty
				&& ty <= y + margin + Card.height;
	}
	
	public void select(final int tx, final int ty) {
		
		if (empty() && getSelectedPile() == null) {
			return;
		}

		// if face down, then flip
		Card topCard = top();
		if (topCard != null && !topCard.isFaceUp()) {
			topCard.flip();
			flippedCardsCount++;
			resetSelectedPile();
			return;
		}
		if (getSelectedPile() == null) {
			setSelectedPile(this);
			int margin = cardMargin * (getCardsCount() - 1);
			margin = margin >= 0 ? margin : 0;
			int ordinate = y + margin;
			numberOfSelectedCards = 1;
			while (ty <= ordinate) {
				numberOfSelectedCards++;
				ordinate -= cardMargin;
			}
		} else {
			Card fCard = CardPile.getSelectedPile().top();
			for(int i = 0; i < numberOfSelectedCards-1; i++){
				fCard = fCard.link;
			}
			if (canTake(fCard)) {
				CardPile tempPile = new CardPile(-100, -100);
				for (int i = 0; i < numberOfSelectedCards; i++) {
					tempPile.addCard(CardPile.getSelectedPile().pop());
				}
				for (int i = 0; i < numberOfSelectedCards; i++) {
					addCard(tempPile.pop());
				}
			}
			resetSelectedPile();
			numberOfSelectedCards = 1;
		}
	}

	private int stackDisplay(final Graphics g, final Card aCard) {
		int localy;
		if (aCard == null) {
			return y;
		}
		localy = stackDisplay(g, aCard.link);
		aCard.draw(g, x, localy);
		return localy + cardMargin;
	}

}