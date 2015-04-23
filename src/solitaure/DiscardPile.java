package solitaure;


class DiscardPile extends CanTakeCardPile {

	DiscardPile(final int x, final int y) {
		super(x, y);
	}

	public void addCard(final Card aCard) {
		if (!aCard.isFaceUp()) {
			aCard.flip();
		}
		super.addCard(aCard);
	}

	public void select(final int tx, final int ty) {
		if (empty()) {
			return;
		}
		if(getSelectedPile() == null){
			setSelectedPile(this);
			}else{
				resetSelectedPile();
			}
		}
}