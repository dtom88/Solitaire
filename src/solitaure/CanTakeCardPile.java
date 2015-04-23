package solitaure;


public class CanTakeCardPile extends CardPile{
	
	public CanTakeCardPile(final int x, final int y) {
		super(x, y);
	}
	
	public void select(final int tx, final int ty) {

		if(getSelectedPile() == null){
		setSelectedPile(this);
		}else{
			Card tCard = getSelectedPile().top();
			if(canTake(tCard)){
				getSelectedPile().pop();
				addCard(tCard);
			}
			resetSelectedPile();
		}
	}

}
