package skatgame;

/**
 * CSC301H5 S - Introduction to Software Engineering
 * Assignment - Exercise 5 - Complete Coding and Testing
 * Team 5
 *   - Michael Sansone
 *   - Gabriel Tan-Chen
 *   - Ganish Toolsie
 *   - Michael Yousef
 * March 11, 2013
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Player1 implements IPlayer
{
	private GameTypeOptions currentGameType;
	private int myIndex;  // Our index
	private int declarerIndex; // The index of the declarer, will be our index if we are declarer.
	private Pile declarerHand;  // For ouvert games
	private Pile declarerCardsWon; // Pile of all the cards won by the declarer.
	private Pile defenderCardsWon; // Pile of all the cards won by the defenders.
	private int[] allScores; // List of the scores of all the players.
	private int maxBid; // The maximum we want to bid, considering our current hand.
	private Pile jackFlush; // The flush run of Jacks, in order from highest to lowest.
	private Pile clubsFlush; // The flush run of Clubs, in order from highest to lowest.
	private Pile spadesFlush; // The flush run of Spades, in order from highest to lowest.
	private Pile heartsFlush; // The flush run of Hearts, in order from highest to lowest.
	private Pile diamondsFlush; // The flush run of Diamonds, in order from highest to lowest.
	private boolean pickUpSkat; // If we want to pick up the skat should we win the bid.
	private GameTypeOptions desiredGameType; // What we want to play should we win the bid.
	
	public Player1()
	{
		declarerCardsWon=new Pile();
		defenderCardsWon=new Pile();
		jackFlush=new Pile();
		jackFlush.addCard(new Card(Card.CARD_SUIT.CLUBS,Card.FACE_VALUE.JACK));
		jackFlush.addCard(new Card(Card.CARD_SUIT.SPADES,Card.FACE_VALUE.JACK));
		jackFlush.addCard(new Card(Card.CARD_SUIT.HEARTS,Card.FACE_VALUE.JACK));
		jackFlush.addCard(new Card(Card.CARD_SUIT.DIAMONDS,Card.FACE_VALUE.JACK));
		clubsFlush=new Pile();
		clubsFlush.addCard(new Card(Card.CARD_SUIT.CLUBS,Card.FACE_VALUE.ACE));
		clubsFlush.addCard(new Card(Card.CARD_SUIT.CLUBS,Card.FACE_VALUE.TEN));
		clubsFlush.addCard(new Card(Card.CARD_SUIT.CLUBS,Card.FACE_VALUE.KING));
		clubsFlush.addCard(new Card(Card.CARD_SUIT.CLUBS,Card.FACE_VALUE.QUEEN));
		clubsFlush.addCard(new Card(Card.CARD_SUIT.CLUBS,Card.FACE_VALUE.NINE));
		clubsFlush.addCard(new Card(Card.CARD_SUIT.CLUBS,Card.FACE_VALUE.EIGHT));
		clubsFlush.addCard(new Card(Card.CARD_SUIT.CLUBS,Card.FACE_VALUE.SEVEN));
		spadesFlush=new Pile();
		spadesFlush.addCard(new Card(Card.CARD_SUIT.SPADES,Card.FACE_VALUE.ACE));
		spadesFlush.addCard(new Card(Card.CARD_SUIT.SPADES,Card.FACE_VALUE.TEN));
		spadesFlush.addCard(new Card(Card.CARD_SUIT.SPADES,Card.FACE_VALUE.KING));
		spadesFlush.addCard(new Card(Card.CARD_SUIT.SPADES,Card.FACE_VALUE.QUEEN));
		spadesFlush.addCard(new Card(Card.CARD_SUIT.SPADES,Card.FACE_VALUE.NINE));
		spadesFlush.addCard(new Card(Card.CARD_SUIT.SPADES,Card.FACE_VALUE.EIGHT));
		spadesFlush.addCard(new Card(Card.CARD_SUIT.SPADES,Card.FACE_VALUE.SEVEN));
		heartsFlush=new Pile();
		heartsFlush.addCard(new Card(Card.CARD_SUIT.HEARTS,Card.FACE_VALUE.ACE));
		heartsFlush.addCard(new Card(Card.CARD_SUIT.HEARTS,Card.FACE_VALUE.TEN));
		heartsFlush.addCard(new Card(Card.CARD_SUIT.HEARTS,Card.FACE_VALUE.KING));
		heartsFlush.addCard(new Card(Card.CARD_SUIT.HEARTS,Card.FACE_VALUE.QUEEN));
		heartsFlush.addCard(new Card(Card.CARD_SUIT.HEARTS,Card.FACE_VALUE.NINE));
		heartsFlush.addCard(new Card(Card.CARD_SUIT.HEARTS,Card.FACE_VALUE.EIGHT));
		heartsFlush.addCard(new Card(Card.CARD_SUIT.HEARTS,Card.FACE_VALUE.SEVEN));
		diamondsFlush=new Pile();
		diamondsFlush.addCard(new Card(Card.CARD_SUIT.DIAMONDS,Card.FACE_VALUE.ACE));
		diamondsFlush.addCard(new Card(Card.CARD_SUIT.DIAMONDS,Card.FACE_VALUE.TEN));
		diamondsFlush.addCard(new Card(Card.CARD_SUIT.DIAMONDS,Card.FACE_VALUE.KING));
		diamondsFlush.addCard(new Card(Card.CARD_SUIT.DIAMONDS,Card.FACE_VALUE.QUEEN));
		diamondsFlush.addCard(new Card(Card.CARD_SUIT.DIAMONDS,Card.FACE_VALUE.NINE));
		diamondsFlush.addCard(new Card(Card.CARD_SUIT.DIAMONDS,Card.FACE_VALUE.EIGHT));
		diamondsFlush.addCard(new Card(Card.CARD_SUIT.DIAMONDS,Card.FACE_VALUE.SEVEN));
		
		/* To trigger bid() to calculate maxBid the first time called before a game. */
		maxBid=-1;
	}
	
	@Override
	/**
	 * @see IPlayer#assignIndex(int)
	 * 
	 * Store our assigned index, essentially our player number.
	 */
	public void assignIndex(int playerIndex)
	{
		/* Store our index. This should not change once set. */
		myIndex=playerIndex;
	}
	
	@Override
	/**
	 * @see IPlayer#bid(int, Pile)
	 * 
	 * Return an integer of our bid. The bid must be higher than the current highest bid.
	 * Use the cards in our hand to determine how high we want to bid.
	 */
	public boolean bid(int currentHighestBid,Pile hand,boolean canMatch)
	{
		if(maxBid==-1)
		{
			/* First time called before a game. */
			/* Calculate the bid and set it. */
			calculateHighestBid(hand);
		}
		
		if((currentHighestBid<maxBid)&&(canMatch==false))
		{
			/* We must increase from the current highest bid. */
			
			return true;
		}
		else if((currentHighestBid<=maxBid)&&(canMatch==true))
		{
			/* We are able to match the current highest bid. */
			
			return true;
		}
		
		/* If we get here, then the bidding is too high, so pass. */
		
		return false;
	}
	
	@Override
	/**
	 * @see IPlayer#decideTakeSkat(Pile)
	 * 
	 * Return a boolean to indicate whether we want to pick up the skat. Return true
	 * if we want to pick up the skat, and false otherwise. Use the cards in our hand
	 * to determine this decision.
	 */
	public boolean decideTakeSkat(Pile hand)
	{
		/* This is already calculated in calculateHighestBid(). Just return the result. */
		
		return pickUpSkat;
	}
	
	@Override
	/**
	 * @see IPlayer#giveSkat(Pile, Pile)
	 * 
	 * Return the skat back to the Game class. The skat returned should be the cards we discard
	 * after picking up the skat. We can return the same cards we picked up, or swap cards out of
	 * our hand to the skat. Note that we only return the skat, indicating that we need not
	 * replace cards from the variable hand, since the Game class keeps the original copy of our
	 * hand. The Game class will calculate our current hand after we return the skat.
	 */
	public Pile giveSkat(Pile hand,Pile skat)
	{
		/* Our desired game is already calculated in calculateHighestBid(). If there
		 * are any cards of the trump we desire, swap it out with the lowest non-trump
		 * card in our hand.
		 */
		int s;
		if(desiredGameType.getGameType()==GameTypeOptions.GameType.Suit)
		{
			/* A Suit game. */
			for(s=0;s<1;s++)
			{
				if((desiredGameType.getTrumpSuit()==GameTypeOptions.TrumpSuit.Clubs)&&
				(skat.getCard(s).getSuit()==Card.CARD_SUIT.CLUBS))
				{
					/* We want Clubs to be trump. */
					skat.replaceCards(skat.getCard(s),findLowestCard(hand));
				}
				else if((desiredGameType.getTrumpSuit()==GameTypeOptions.TrumpSuit.Spades)&&
				(skat.getCard(s).getSuit()==Card.CARD_SUIT.SPADES))
				{
					/* We want Spades to be trump. */
					skat.replaceCards(skat.getCard(s),findLowestCard(hand));
				}
				else if((desiredGameType.getTrumpSuit()==GameTypeOptions.TrumpSuit.Hearts)&&
				(skat.getCard(s).getSuit()==Card.CARD_SUIT.HEARTS))
				{
					/* We want Hearts to be trump. */
					skat.replaceCards(skat.getCard(s),findLowestCard(hand));
				}
				else
				{
					/* We want Diamonds to be trump. */
					skat.replaceCards(skat.getCard(s),findLowestCard(hand));
				}
			}
		}
		else if(desiredGameType.getGameType()==GameTypeOptions.GameType.Grand)
		{
			/* A Grand game. */
			for(s=0;s<1;s++)
			{
				if(skat.getCard(s).getFaceValue()==Card.FACE_VALUE.JACK)
				{
					/* It's a Jack! Take it. */
					skat.replaceCards(skat.getCard(s),findLowestCard(hand));
				}
			}
		}
		
		System.out.println("Skat size: " + skat.getNumCards());
		
		return skat;
	}
	
	@Override
	/**
	 * @see IPlayer#decideGameType(Pile)
	 * 
	 * Return a GameTypeOptions instance defining the game we want to play. This method will only
	 * be called if we are the declarer.
	 */
	public GameTypeOptions decideGameType(Pile hand)
	{
		/* This is already calculated in calculateHighestBid(). Simply return the result. */
		
		return desiredGameType;
	}
	
	@Override
	/**
	 * @see IPlayer#setGameType(GameTypeOptions, int)
	 * 
	 * Store the game type for the current game. This is mostly for the defender's information, since
	 * the declarer is the one that creates the game type. However, the declarer should store this
	 * information too, if it hasn't already done so.
	 */
	public void setGameType(GameTypeOptions gameType,int decInd)
	{
		currentGameType=gameType;
		declarerIndex=decInd;
	}
	
	@Override
	/**
	 * @see IPlayer#giveDeclarersHand(Pile)
	 * 
	 * Store the hand of the declarer. This method will only be called if we are one of the defenders,
	 * and the declarer decided to play an open game.
	 */
	public void giveDeclarersHand(Pile decHand)
	{
		declarerHand=decHand;
	}
	
	@Override
	/**
	 * @see IPlayer#playTurn(Pile, Pile, int)
	 * 
	 * Return a card from our hand that we wish to play in the current trick. Use the cards already
	 * played in the trick, and who played what cards to make the decision. The variable whoPlayedFirstCard
	 * is the index of the player that played the first card in the trick. Note that with this, we can
	 * determine who played all the cards. Even if we are the last player in the trick, we know who played
	 * the first card, and by reduction know who played the second card.
	 */
	public Card playTurn(Pile hand,Pile cardsPlayed,int whoPlayedFirstCard)
	{
		/*
		 * The current AI tries to win every trick it can
		 * If it sees it cannot win the trick based on what has been played
		 * It will play the lowest card it can
		 * The next iteration of this code will try to keep track of what has been played
		 * And who is winning the trick, if the declarer has played, etc.
		 * 
		 * Additionally, this code should be refactored
		 * However, since the methods will be modified significantly for the next iteration
		 * That is, each game will be played out differently in terms of strategy
		 * The methods are left as-is
		 */
		
		if(currentGameType.getGameType() == GameTypeOptions.GameType.Null){
			return chooseCardNull(hand, cardsPlayed, whoPlayedFirstCard);
		}
		else if(currentGameType.getGameType() == GameTypeOptions.GameType.Grand){
			return chooseCardGrand(hand, cardsPlayed, whoPlayedFirstCard);
		}
		//this is guaranteed to be a suit game type
		//you cannot explicitly check with an else if otherwise java will want a return
		//in case no case is true, cause it won't check that itself
		else{ 
			return chooseCardSuit(hand, cardsPlayed, whoPlayedFirstCard);
		}
	}
	
	@Override
	/**
	 * @see IPlayer#endTrick(Pile, int)
	 * 
	 * Store the cards won in the previous trick in their respective piles. If the declarer won the trick,
	 * store the cards in the declarer's pile. If one of the defenders won the trick, store the cards
	 * in the defenders' pile. From here we can easily calculate who is winning, by looking at the cards
	 * in each pile. 
	 */
	public void endTrick(Pile cardsPlayed,int whoWonIndex)
	{
		Card c;
		for(int i=0;i<3;i++)
		{
			c=cardsPlayed.getCard(i);
			if(whoWonIndex==declarerIndex) declarerCardsWon.addCard(c);
			else defenderCardsWon.addCard(c);
		}
	}
	
	@Override
	/**
	 * @see IPlayer#endGameInfo(boolean, int[])
	 * 
	 * Store the scores of all the players. From this we can determine how daring or conservative
	 * we make our strategy for the next game.
	 */
	public void endGameInfo(boolean decWon,int[] scores)
	{
		/* Store the scores. */
		allScores=scores;
		
		/* Reset all the Piles. */
		declarerCardsWon=new Pile();
		defenderCardsWon=new Pile();
		
		/* Make sure to set this at the end of each game so we know to calculate
		 * our maxBid at the beginning of the next game.
		 */
		maxBid=-1;
	}
	
	/**
	 * Calculate the maximum bid we can bid with our hand. Store the maximum
	 * bid, and store the game type we wish to play if we win the bid.
	 * 
	 * @param hand - A Pile of cards of size 10.
	 */
	private void calculateHighestBid(Pile hand)
	{
		/* Calculate matador. */
		int clubsMatador=0;
		int spadesMatador=0;
		int heartsMatador=0;
		int diamondsMatador=0;
		
		int i=1;
		boolean ret=true;
		boolean clubsFin=false;
		boolean spadesFin=false;
		boolean heartsFin=false;
		boolean diamondsFin=false;
		
		if(isInPile(hand,jackFlush.getCard(0)))
		{
			/* We have the Jack of Clubs. We want to calculate a with matador,
			 * by adding up the flush until we encounter the first card in the
			 * flush that we don't have.
			 */
			clubsMatador++;
			spadesMatador++;
			heartsMatador++;
			diamondsMatador++;
			
			while(ret && i < 7)
			{
				if((clubsMatador<4)&&(spadesMatador<4)&&(heartsMatador<4)&&(diamondsMatador<4))
				{
					/* Checking to see if we have all the Jacks. */
					ret=isInPile(hand,jackFlush.getCard(i));
					if(ret)
					{
						/* We have the next card in the flush run. Add that to our matador. */
						clubsMatador++;
						spadesMatador++;
						heartsMatador++;
						diamondsMatador++;
					}
				}
				else
				{
					/* If we get here, then we have the full flush of Jacks. Calculate
					 * the matadors for each suit.
					 */
					
					/* Just starting to check for the flush of suits, so we want to
					 * start at the first index of the suit flush runs.
					 */
					if((clubsMatador==4)&&(spadesMatador==4)&&(heartsMatador==4)&&(diamondsMatador==4))
						i=0;
					
					/* Check the next card in each flush run. */
					if(!clubsFin)
					{
						if(isInPile(hand,clubsFlush.getCard(i))) clubsMatador++;
						else clubsFin=true;
					}
					if(!spadesFin)
					{
						if(isInPile(hand,spadesFlush.getCard(i))) spadesMatador++;
						else spadesFin=true;
					}
					if(!heartsFin)
					{
						if(isInPile(hand,heartsFlush.getCard(i))) heartsMatador++;
						else heartsFin=true;
					}
					if(!diamondsFin)
					{
						if(isInPile(hand,diamondsFlush.getCard(i))) diamondsMatador++;
						else diamondsFin=true;
					}
					if(clubsFin&&spadesFin&&heartsFin&&diamondsFin)
					{
						/* We are all done. */
						ret=false;
					}
				}
				i++;
			}
		}
		else
		{
			/* We don't have the Jack of Clubs. We want to calculate an against matador,
			 * by subtracting the flush until we encounter the first card in the flush
			 * that we do have.
			 * 
			 */
			clubsMatador--;
			spadesMatador--;
			heartsMatador--;
			diamondsMatador--;
			
			while(ret && i < 7)
			{
				if((clubsMatador>-4)&&(spadesMatador>-4)&&(heartsMatador>-4)&&(diamondsMatador>-4))
				{
					/* Checking to see what Jacks we don't have. */
					if(isInPile(hand,jackFlush.getCard(i)))
					{
						/* We found a card, we're done. */
						ret=false;
					}
					else
					{
						clubsMatador--;
						spadesMatador--;
						heartsMatador--;
						diamondsMatador--;
					}
				}
				else{
					/* If we get here, then we are missing the full flush of Jacks.
					 * Calculate the matadors for each suit.
					 */
					
					/* Just starting to check for the flush of suits, so we want to
					 * start at the first index of the suit flush runs.
					 */
					if((clubsMatador==-4)&&(spadesMatador==-4)&&(heartsMatador==-4)&&(diamondsMatador==-4))
						i=0;
					
					if(!clubsFin)
					{
						if(isInPile(hand,clubsFlush.getCard(i))) clubsFin=true;
						else clubsMatador--;
					}
					if(!spadesFin)
					{
						if(isInPile(hand,spadesFlush.getCard(i))) spadesFin=true;
						else spadesMatador--;
					}
					if(!heartsFin)
					{
						if(isInPile(hand,heartsFlush.getCard(i))) heartsFin=true;
						else heartsMatador--;
					}
					if(!diamondsFin)
					{
						if(isInPile(hand,diamondsFlush.getCard(i))) diamondsFin=true;
						else diamondsMatador--;
					}
					
					if(clubsFin&&spadesFin&&heartsFin&&diamondsFin)
					{
						/* We are all done. */
						ret=false;
					}
				}
				i++;
			}
		}
		
		/* Count how many Jack's we have. */
		int numJacks=0;
		for(int k=0;k<4;k++)
		{
			if(isInPile(hand,jackFlush.getCard(k))) numJacks++;
		}
		
		/* Count how many Ace's and 10's we have for each suit. */
		int clubsHigh=0;
		int spadesHigh=0;
		int heartsHigh=0;
		int diamondsHigh=0;
		
		for(int j=0;j<2;j++)
		{
			if(isInPile(hand,clubsFlush.getCard(j))) clubsHigh++;
			if(isInPile(hand,spadesFlush.getCard(j))) spadesHigh++;
			if(isInPile(hand,heartsFlush.getCard(j))) heartsHigh++;
			if(isInPile(hand,diamondsFlush.getCard(j))) diamondsHigh++;
		}
		
		/* Now that we have all our matadors, we can figure out what game we would
		 * want to play should we win the bid.
		 */
		if((clubsMatador+spadesMatador+heartsMatador+diamondsMatador)==-22)
		{
			/* We have the lowest possible cards. Play an Open Null Hand game. */
			pickUpSkat=false;
			desiredGameType=new GameTypeOptions(GameTypeOptions.SkatHandType.Hand,
					GameTypeOptions.GameType.Null,GameTypeOptions.TrumpSuit.None,
					true,false,false);
			maxBid=59;
		}
		else if((clubsMatador+spadesMatador+heartsMatador+diamondsMatador)<=-20)
		{
			/* Our hand consists of the lowest cards. Play a Null Hand game. */
			pickUpSkat=false;
			desiredGameType=new GameTypeOptions(GameTypeOptions.SkatHandType.Hand,
					GameTypeOptions.GameType.Null,GameTypeOptions.TrumpSuit.None,
					false,false,false);
			maxBid=35; // The value of a Null Hand game.
		}
		else if(clubsMatador==10)
		{
			/* We have a full flush of the Clubs suit. Play an Open Suit Hand game with
			 * Clubs as trump, and announce Schneider and Schwarz.
			 */
			pickUpSkat=false;
			desiredGameType=new GameTypeOptions(GameTypeOptions.SkatHandType.Hand,
					GameTypeOptions.GameType.Suit,GameTypeOptions.TrumpSuit.Clubs,
					true,true,true);
			maxBid=204; // The value of an Open Clubs Hand game with Schneider and Schwarz.
		}
		else if(spadesMatador==10)
		{
			/* We have a full flush of the Spades suit. Play an Open Suit Hand game with
			 * Spades as trump, and announce Schneider and Schwarz.
			 */
			pickUpSkat=false;
			desiredGameType=new GameTypeOptions(GameTypeOptions.SkatHandType.Hand,
					GameTypeOptions.GameType.Suit,GameTypeOptions.TrumpSuit.Spades,
					true,true,true);
			maxBid=187; // The value of an Open Spades Hand game with Schneider and Schwarz.
		}
		else if(heartsMatador==10)
		{
			/* We have a full flush of the Hearts suit. Play an Open Suit Hand game with
			 * Hearts as trump, and announce Schneider and Schwarz.
			 */
			pickUpSkat=false;
			desiredGameType=new GameTypeOptions(GameTypeOptions.SkatHandType.Hand,
					GameTypeOptions.GameType.Suit,GameTypeOptions.TrumpSuit.Hearts,
					true,true,true);
			maxBid=170; // The value of an Open Hearts Hand game with Schneider and Schwarz.
		}
		else if(diamondsMatador==10)
		{
			/* We have a full flush of the Diamonds suit. Play an Open Suit Hand game with
			 * Diamonds as trump, and announce Schneider and Schwarz.
			 */
			pickUpSkat=false;
			desiredGameType=new GameTypeOptions(GameTypeOptions.SkatHandType.Hand,
					GameTypeOptions.GameType.Suit,GameTypeOptions.TrumpSuit.Diamonds,
					true,true,true);
			maxBid=153; // The value of an Open Diamonds Hand game with Schneider and Schwarz.
		}
		else if((clubsMatador>=8)&&((spadesMatador>4)||(heartsMatador>4)||(diamondsMatador>4)))
		{
			/* We have an extremely good hand, but not perfect. Call Schneider and Schwarz, 
			 * but don't play an open game.
			 */
			pickUpSkat=false;
			desiredGameType=new GameTypeOptions(GameTypeOptions.SkatHandType.Hand,
					GameTypeOptions.GameType.Suit,GameTypeOptions.TrumpSuit.Clubs,
					false,true,true);
			maxBid=12*(clubsMatador+5);
		}
		else if((spadesMatador>=8)&&((clubsMatador>4)||(heartsMatador>4)||(diamondsMatador>4)))
		{
			/* We have an extremely good hand, but not perfect. Call Schneider and Schwarz, 
			 * but don't play an open game.
			 */
			pickUpSkat=false;
			desiredGameType=new GameTypeOptions(GameTypeOptions.SkatHandType.Hand,
					GameTypeOptions.GameType.Suit,GameTypeOptions.TrumpSuit.Spades,
					false,true,true);
			maxBid=11*(spadesMatador+5);
		}
		else if((heartsMatador>=8)&&((clubsMatador>4)||(spadesMatador>4)||(diamondsMatador>4)))
		{
			/* We have an extremely good hand, but not perfect. Call Schneider and Schwarz, 
			 * but don't play an open game.
			 */
			pickUpSkat=false;
			desiredGameType=new GameTypeOptions(GameTypeOptions.SkatHandType.Hand,
					GameTypeOptions.GameType.Suit,GameTypeOptions.TrumpSuit.Hearts,
					false,true,true);
			maxBid=10*(heartsMatador+5);
		}
		else if((diamondsMatador>=8)&&((clubsMatador>4)||(spadesMatador>4)||(heartsMatador>4)))
		{
			/* We have an extremely good hand, but not perfect. Call Schneider and Schwarz, 
			 * but don't play an open game.
			 */
			pickUpSkat=false;
			desiredGameType=new GameTypeOptions(GameTypeOptions.SkatHandType.Hand,
					GameTypeOptions.GameType.Suit,GameTypeOptions.TrumpSuit.Diamonds,
					false,true,true);
			maxBid=9*(diamondsMatador+5);
		}
		else if((clubsMatador>=6)&&((spadesMatador>4)||(heartsMatador>4)||(diamondsMatador>4)))
		{
			/* We have a really good hand, but not perfect. Call Schneider. */
			pickUpSkat=false;
			desiredGameType=new GameTypeOptions(GameTypeOptions.SkatHandType.Hand,
					GameTypeOptions.GameType.Suit,GameTypeOptions.TrumpSuit.Clubs,
					false,true,false);
			maxBid=12*(clubsMatador+3);
		}
		else if((spadesMatador>=6)&&((clubsMatador>4)||(heartsMatador>4)||(diamondsMatador>4)))
		{
			/* We have a really good hand, but not perfect. Call Schneider. */
			pickUpSkat=false;
			desiredGameType=new GameTypeOptions(GameTypeOptions.SkatHandType.Hand,
					GameTypeOptions.GameType.Suit,GameTypeOptions.TrumpSuit.Spades,
					false,true,false);
			maxBid=11*(spadesMatador+3);
		}
		else if((heartsMatador>=6)&&((clubsMatador>4)||(spadesMatador>4)||(diamondsMatador>4)))
		{
			/* We have a really good hand, but not perfect. Call Schneider. */
			pickUpSkat=false;
			desiredGameType=new GameTypeOptions(GameTypeOptions.SkatHandType.Hand,
					GameTypeOptions.GameType.Suit,GameTypeOptions.TrumpSuit.Hearts,
					false,true,false);
			maxBid=10*(heartsMatador+3);
		}
		else if((diamondsMatador>=6)&&((clubsMatador>4)||(spadesMatador>4)||(heartsMatador>4)))
		{
			/* We have a really good hand, but not perfect. Call Schneider. */
			pickUpSkat=false;
			desiredGameType=new GameTypeOptions(GameTypeOptions.SkatHandType.Hand,
					GameTypeOptions.GameType.Suit,GameTypeOptions.TrumpSuit.Diamonds,
					false,true,false);
			maxBid=9*(diamondsMatador+3);
		}
		else if((clubsMatador>=4)||(spadesMatador>=4)||(heartsMatador>=4)||(diamondsMatador>=4))
		{
			/* We have a pretty good Grand hand. */
			pickUpSkat=true;
			desiredGameType=new GameTypeOptions(GameTypeOptions.SkatHandType.Skat,
					GameTypeOptions.GameType.Grand,GameTypeOptions.TrumpSuit.None,
					false,false,false);
			maxBid=24;
		}
		else if((numJacks>=1)&&(clubsHigh>=2))
		{
			/* A decent hand for Clubs. Pick up the skat. */
			pickUpSkat=true;
			desiredGameType=new GameTypeOptions(GameTypeOptions.SkatHandType.Skat,
					GameTypeOptions.GameType.Suit,GameTypeOptions.TrumpSuit.Clubs,
					false,false,false);
			maxBid=12*(clubsMatador+1);
		}
		else if((numJacks>=1)&&(spadesHigh>=2))
		{
			/* A decent hand for Spades. Pick up the skat. */
			pickUpSkat=true;
			desiredGameType=new GameTypeOptions(GameTypeOptions.SkatHandType.Skat,
					GameTypeOptions.GameType.Suit,GameTypeOptions.TrumpSuit.Spades,
					false,false,false);
			maxBid=11*(spadesMatador+1);
		}
		else if((numJacks>=1)&&(heartsHigh>=2))
		{
			/* A decent hand for Hearts. Pick up the skat. */
			pickUpSkat=true;
			desiredGameType=new GameTypeOptions(GameTypeOptions.SkatHandType.Skat,
					GameTypeOptions.GameType.Suit,GameTypeOptions.TrumpSuit.Hearts,
					false,false,false);
			maxBid=10*(heartsMatador+1);
		}
		else if((numJacks>=1)&&(diamondsHigh>=2))
		{
			/* A decent hand for Diamonds. Pick up the skat. */
			pickUpSkat=true;
			desiredGameType=new GameTypeOptions(GameTypeOptions.SkatHandType.Skat,
					GameTypeOptions.GameType.Suit,GameTypeOptions.TrumpSuit.Diamonds,
					false,false,false);
			maxBid=9*(diamondsMatador+1);
		}
		else
		{
			/* A mediocre hand. We want to be the defender. */
			maxBid=0;
		}
		
		
	}
	
	/**
	 * 
	 * @param p - A Pile to see if it contains Card c.
	 * @param c - Card object to see if it is in Pile p.
	 * @return - A boolean true if Card c is in Pile p, or false otherwise.
	 */
	private boolean isInPile(Pile p,Card c)
	{
		int pileSize=p.getNumCards();
		Card r;
		for(int i=0;i<pileSize;i++)
		{
			r=p.getCard(i);
			if((r.getSuit()==c.getSuit())&&(r.getFaceValue()==c.getFaceValue()))
			{
				return true;
			}
		}
		
		return false;
	}
	
	/**
	 * Return the lowest non trump card in the Pile hand.
	 * 
	 * @param hand
	 * @return - The lowest non-trump card in our hand.
	 */
	private Card findLowestCard(Pile hand)
	{
		Card lowestCard=hand.getCard(0);
		int indLowestCard=0;
		int j;
		
		/*
		 * Go through each of the 10 cards in our hand, and find the lowest. To find the
		 * lowest card, determine the index of the card in the flush run of its suit.
		 * Note that the flush run has the highest cards at the beginning, so a higher index
		 * means a lower card.
		 */
		for(int i=0;i<10;i++)
		{
			if((desiredGameType.getTrumpSuit()==GameTypeOptions.TrumpSuit.Clubs)&&
					(hand.getCard(i).getSuit()!=Card.CARD_SUIT.CLUBS))
			{
				/* We want clubs to be trump. Only check cards that are not clubs. */
				for(j=0;j<7;j++)
				{
					if((hand.getCard(i).getSuit()==spadesFlush.getCard(j).getSuit())&&
					(hand.getCard(i).getFaceValue()==spadesFlush.getCard(j).getFaceValue()))
					{
						/* We found a match. */
						if(j>indLowestCard)
						{
							lowestCard=hand.getCard(i);
							indLowestCard=j;
						}
					}
					else if((hand.getCard(i).getSuit()==heartsFlush.getCard(j).getSuit())&&
					(hand.getCard(i).getFaceValue()==heartsFlush.getCard(j).getFaceValue()))
					{
						/* We found a match. */
						if(j>indLowestCard)
						{
							/* We found a lower card. */
							lowestCard=hand.getCard(i);
							indLowestCard=j;
						}
					}
					else if((hand.getCard(i).getSuit()==diamondsFlush.getCard(j).getSuit())&&
					(hand.getCard(i).getFaceValue()==diamondsFlush.getCard(j).getFaceValue()))
					{
						/* We found a match. */
						if(j>indLowestCard)
						{
							/* We found a lower card. */
							lowestCard=hand.getCard(i);
							indLowestCard=j;
						}
					}
				}
			}
			else if((desiredGameType.getTrumpSuit()==GameTypeOptions.TrumpSuit.Spades)&&
					(hand.getCard(i).getSuit()!=Card.CARD_SUIT.SPADES))
			{
				/* We want spades to be trump. Only check cards that are not spades. */
				for(j=0;j<7;j++)
				{
					if((hand.getCard(i).getSuit()==clubsFlush.getCard(j).getSuit())&&
					(hand.getCard(i).getFaceValue()==clubsFlush.getCard(j).getFaceValue()))
					{
						/* We found a match. */
						if(j>indLowestCard)
						{
							lowestCard=hand.getCard(i);
							indLowestCard=j;
						}
					}
					else if((hand.getCard(i).getSuit()==heartsFlush.getCard(j).getSuit())&&
					(hand.getCard(i).getFaceValue()==heartsFlush.getCard(j).getFaceValue()))
					{
						/* We found a match. */
						if(j>indLowestCard)
						{
							/* We found a lower card. */
							lowestCard=hand.getCard(i);
							indLowestCard=j;
						}
					}
					else if((hand.getCard(i).getSuit()==diamondsFlush.getCard(j).getSuit())&&
					(hand.getCard(i).getFaceValue()==diamondsFlush.getCard(j).getFaceValue()))
					{
						/* We found a match. */
						if(j>indLowestCard)
						{
							/* We found a lower card. */
							lowestCard=hand.getCard(i);
							indLowestCard=j;
						}
					}
				}
			}
			else if((desiredGameType.getTrumpSuit()==GameTypeOptions.TrumpSuit.Hearts)&&
					(hand.getCard(i).getSuit()!=Card.CARD_SUIT.HEARTS))
			{
				/* We want hearts to be trump. Only check cards that are not hearts. */
				for(j=0;j<7;j++)
				{
					if((hand.getCard(i).getSuit()==clubsFlush.getCard(j).getSuit())&&
					(hand.getCard(i).getFaceValue()==clubsFlush.getCard(j).getFaceValue()))
					{
						/* We found a match. */
						if(j>indLowestCard)
						{
							lowestCard=hand.getCard(i);
							indLowestCard=j;
						}
					}
					else if((hand.getCard(i).getSuit()==spadesFlush.getCard(j).getSuit())&&
					(hand.getCard(i).getFaceValue()==spadesFlush.getCard(j).getFaceValue()))
					{
						/* We found a match. */
						if(j>indLowestCard)
						{
							/* We found a lower card. */
							lowestCard=hand.getCard(i);
							indLowestCard=j;
						}
					}
					else if((hand.getCard(i).getSuit()==diamondsFlush.getCard(j).getSuit())&&
					(hand.getCard(i).getFaceValue()==diamondsFlush.getCard(j).getFaceValue()))
					{
						/* We found a match. */
						if(j>indLowestCard)
						{
							/* We found a lower card. */
							lowestCard=hand.getCard(i);
							indLowestCard=j;
						}
					}
				}
			}
			else if((desiredGameType.getTrumpSuit()==GameTypeOptions.TrumpSuit.Diamonds)&&
					(hand.getCard(i).getSuit()!=Card.CARD_SUIT.DIAMONDS))
			{
				/* We want diamonds to be trump. Only check cards that are not diamonds. */
				for(j=0;j<7;j++)
				{
					if((hand.getCard(i).getSuit()==clubsFlush.getCard(j).getSuit())&&
					(hand.getCard(i).getFaceValue()==clubsFlush.getCard(j).getFaceValue()))
					{
						/* We found a match. */
						if(j>indLowestCard)
						{
							lowestCard=hand.getCard(i);
							indLowestCard=j;
						}
					}
					else if((hand.getCard(i).getSuit()==spadesFlush.getCard(j).getSuit())&&
					(hand.getCard(i).getFaceValue()==spadesFlush.getCard(j).getFaceValue()))
					{
						/* We found a match. */
						if(j>indLowestCard)
						{
							/* We found a lower card. */
							lowestCard=hand.getCard(i);
							indLowestCard=j;
						}
					}
					else if((hand.getCard(i).getSuit()==heartsFlush.getCard(j).getSuit())&&
					(hand.getCard(i).getFaceValue()==heartsFlush.getCard(j).getFaceValue()))
					{
						/* We found a match. */
						if(j>indLowestCard)
						{
							/* We found a lower card. */
							lowestCard=hand.getCard(i);
							indLowestCard=j;
						}
					}
				}
			}
		}
		
		return lowestCard;
	}
	
	/**
	 * Choose the card to play for a Suit Game
	 * @param hand - our hand of cards
	 * @param cardsPlayed - the cards already played this trick
	 * @param whoPlayedFirstCard - the person who played the first card this trick
	 * @return a Card denoting which card we will play
	 */
	public Card chooseCardSuit(Pile hand, Pile cardsPlayed, int whoPlayedFirstCard){
		
		/*
		 * If we're first, play the highest non-trump in our hand
		 * Unless we have the jack of clubs, then play that
		 * 
		 * Always try to follow suit if we're not the first player
		 * Always try to win the trick
		 * If we can't, play the lowest card we can
		 */
		
		int cardIndex = 0;
		int highestIndex = -1;
		int lowestIndex = -1;
		boolean cardFound = false;
		
		if(cardsPlayed.getNumCards() == 0){
			Card c = new Card(Card.CARD_SUIT.CLUBS, Card.FACE_VALUE.JACK);
			if(hand.containsCard(c)){
				for(int j=0;j<hand.getNumCards();j++)
				{
					if((c.getSuit()==hand.getCard(j).getSuit())&&(c.getFaceValue()==hand.getCard(j).getFaceValue()))
					{
						return hand.getCard(j);
					}
				}
			}
			for(int i = 1; i < hand.getNumCards(); i++){
				//if the card is not in the trump suit
				if(!(hand.getCard(i).getSuit() == Card.CARD_SUIT.values()[this.currentGameType.getTrumpSuit().ordinal() - 1])){
					if(this.cardHigher(hand.getCard(i), hand.getCard(cardIndex), this.currentGameType.getGameType())){
						cardIndex = i;
					}
				}
			}
			return hand.getCard(cardIndex);
		}
		//find out which is the highest card of the suit led that has been played
		else{
			Card.CARD_SUIT suit = cardsPlayed.getCard(0).getSuit();
			Card highest = cardsPlayed.getCard(0);
			//if there are two cards played, see which is higher and make highest that
			if(cardsPlayed.getNumCards() == 2){
				if(cardsPlayed.getCard(1).getSuit() == cardsPlayed.getCard(0).getSuit() && 
						this.cardHigher(cardsPlayed.getCard(1), cardsPlayed.getCard(0), this.currentGameType.getGameType())){
					highest = cardsPlayed.getCard(1);
				}
			}
			//see if we have a card that follows suit
			//find the highest and lowest one simultaneously
			for(int i = 0; i < hand.getNumCards(); i++){
				if(hand.getCard(i).getSuit() == suit){
					if(!cardFound){ //if we haven't found one yet, take this to be the card
						highestIndex = i;
						lowestIndex = i;
						cardFound = true;
					}
					if(this.cardHigher(hand.getCard(i), hand.getCard(highestIndex), this.currentGameType.getGameType())){
						highestIndex = i;
					}
					if(this.cardHigher(hand.getCard(lowestIndex), hand.getCard(i), this.currentGameType.getGameType())){
						lowestIndex = i;
					}
				}
			}
			//no card of suit, so find the highest and lowest we have
			if((highestIndex == -1) && (lowestIndex == -1)){
				highestIndex = 0;
				lowestIndex = 0;
				for(int i = 1; i < hand.getNumCards(); i++){
					if(this.cardHigher(hand.getCard(i), hand.getCard(highestIndex), this.currentGameType.getGameType())){
						highestIndex = i;
					}
					if(this.cardHigher(hand.getCard(lowestIndex), hand.getCard(i), this.currentGameType.getGameType())){
						lowestIndex = i;
					}
				}
			}
		}
		
		//see if we can win, if yes, play highest, if not, play lowest
		boolean playHighest = false;
		if(cardsPlayed.getNumCards() == 1){
			if(this.cardHigher(hand.getCard(highestIndex), cardsPlayed.getCard(0), GameTypeOptions.GameType.Null)){
				playHighest = true;
			}
		}
		else{
			if((this.cardHigher(hand.getCard(highestIndex), cardsPlayed.getCard(0), GameTypeOptions.GameType.Null)) && 
				(this.cardHigher(hand.getCard(highestIndex), cardsPlayed.getCard(1), GameTypeOptions.GameType.Null))){
				playHighest = true;
			}
		}
		
		if(playHighest){
			return hand.getCard(highestIndex);
		}
		else{
			return hand.getCard(lowestIndex);
		}
	}
	
	/**
	 * Choose the card to play for a Grand Game
	 * @param hand - our hand of cards
	 * @param cardsPlayed - the cards already played this trick
	 * @param whoPlayedFirstCard - the person who played the first card this trick
	 * @return a Card denoting which card we will play
	 */
	public Card chooseCardGrand(Pile hand, Pile cardsPlayed, int whoPlayedFirstCard){
		
		/*
		 * If we're first, play the highest non-Jack in our hand
		 * Unless we have the Jack of Clubs, then we play that
		 * 
		 * Always try to follow suit if we're not the first player
		 * Always try to win the trick
		 * If we can't, play the lowest card we can
		 */
		
		int cardIndex = 0;
		int highestIndex = -1;
		int lowestIndex = -1;
		boolean cardFound = false;
		
		if(cardsPlayed.getNumCards() == 0){
			Card c = new Card(Card.CARD_SUIT.CLUBS, Card.FACE_VALUE.JACK);
			if(hand.containsCard(c)){
				for(int j=0;j<hand.getNumCards();j++)
				{
					if((c.getSuit()==hand.getCard(j).getSuit())&&(c.getFaceValue()==hand.getCard(j).getFaceValue()))
					{
						return hand.getCard(j);
					}
				}
			}
			//we don't have jack of clubs
			for(int i = 1; i < hand.getNumCards(); i++){
				if(!(hand.getCard(i).getFaceValue() == Card.FACE_VALUE.JACK)){
					if(this.cardHigher(hand.getCard(i), hand.getCard(cardIndex), this.currentGameType.getGameType())){
						cardIndex = i;
					}
				}
			}
			return hand.getCard(cardIndex);
		}
		//find out which is the highest card of the suit led that has been played
		else{
			Card.CARD_SUIT suit = cardsPlayed.getCard(0).getSuit();
			Card highest = cardsPlayed.getCard(0);
			//if there are two cards played, see which is higher and make highest that
			if(cardsPlayed.getNumCards() == 2){
				if(cardsPlayed.getCard(1).getSuit() == cardsPlayed.getCard(0).getSuit() && 
						this.cardHigher(cardsPlayed.getCard(1), cardsPlayed.getCard(0), this.currentGameType.getGameType())){
					highest = cardsPlayed.getCard(1);
				}
			}
			//see if we have a card that follows suit
			//find the highest and lowest one simultaneously
			for(int i = 0; i < hand.getNumCards(); i++){
				if(hand.getCard(i).getSuit() == suit){
					if(!cardFound){ //if we haven't found one yet, take this to be the card
						highestIndex = i;
						lowestIndex = i;
						cardFound = true;
					}
					//see if this is lower than lowest and higher than highest
					if(this.cardHigher(hand.getCard(i), hand.getCard(highestIndex), this.currentGameType.getGameType())){
						highestIndex = i;
					}
					if(this.cardHigher(hand.getCard(lowestIndex), hand.getCard(i), this.currentGameType.getGameType())){
						lowestIndex = i;
					}
				}
			}
			//no card of suit, so find the highest and lowest we have
			if((highestIndex == -1) && (lowestIndex == -1)){
				highestIndex = 0;
				lowestIndex = 0;
				for(int i = 1; i < hand.getNumCards(); i++){
					//see if this is lower than lowest and higher than highest
					if(this.cardHigher(hand.getCard(i), hand.getCard(highestIndex), this.currentGameType.getGameType())){
						highestIndex = i;
					}
					if(this.cardHigher(hand.getCard(lowestIndex), hand.getCard(i), this.currentGameType.getGameType())){
						lowestIndex = i;
					}
				}
			}
		}
		
		//see if we can win, if yes, play highest, if not, play lowest
		boolean playHighest = false;
		if(cardsPlayed.getNumCards() == 1){
			if(this.cardHigher(hand.getCard(highestIndex), cardsPlayed.getCard(0), GameTypeOptions.GameType.Null)){
				playHighest = true;
			}
		}
		else{
			if((this.cardHigher(hand.getCard(highestIndex), cardsPlayed.getCard(0), GameTypeOptions.GameType.Null)) && 
				(this.cardHigher(hand.getCard(highestIndex), cardsPlayed.getCard(1), GameTypeOptions.GameType.Null))){
				playHighest = true;
			}
		}
		
		if(playHighest){
			return hand.getCard(highestIndex);
		}
		else{
			return hand.getCard(lowestIndex);
		}
	}
	
	/**
	 * Choose the card to play for a Null Game
	 * @param hand - our hand of cards
	 * @param cardsPlayed - the cards already played this trick
	 * @param whoPlayedFirstCard - the person who played the first card this trick
	 * @return a Card denoting which card we will play
	 */
	public Card chooseCardNull(Pile hand, Pile cardsPlayed, int whoPlayedFirstCard)
	{
		/*
		 * Check if we are playing first or not
		 * 
		 * If we are playing first, play the lowest card we have
		 * 
		 * If we are playing second or third, we can just try lose the trick
		 * Play the highest card of the suit led that doesn't win us the trick
		 * If we can't follow suit, play the highest card we have
		 * 
		 * Note that in the case that we're the declarer or defender, we play the same
		 * Declarer has to lose every trick or lose the game
		 * Defender has to lose at least one trick => declarer wins one game
		 */
		
		int cardIndex = -1; //the index of the card we will play
		boolean cardFound = false;
		
		//we're not first so find the highest card played
		if(cardsPlayed.getNumCards() > 0){
			Card.CARD_SUIT suit = cardsPlayed.getCard(0).getSuit();
			Card highest = cardsPlayed.getCard(0);
			//if there are two cards played, see which is higher and make highest that
			if(cardsPlayed.getNumCards() == 2){
				if(cardsPlayed.getCard(1).getSuit() == cardsPlayed.getCard(0).getSuit() && 
						this.cardHigher(cardsPlayed.getCard(1), cardsPlayed.getCard(0), GameTypeOptions.GameType.Null)){
					highest = cardsPlayed.getCard(1);
				}
			}
			//see if we have a card that follows suit
			//find the highest one that doesn't win us the trick
			//if we can't find such a card, just play what we found already
			for(int i = 0; i < hand.getNumCards(); i++){
				if(hand.getCard(i).getSuit() == suit){
					if(!cardFound){ //if we haven't found one yet, take this to be the card
						cardIndex = i;
						cardFound = true;
					}
					//our card must be
					//lower than the highest one played and 
					//higher than the one we have as highest
					else if((this.cardHigher(highest, hand.getCard(i), GameTypeOptions.GameType.Null)) && 
							this.cardHigher(hand.getCard(i), hand.getCard(cardIndex), GameTypeOptions.GameType.Null)){
						cardIndex = i; //this one is lower
					}
				}
			}
			//if index == -1, no card of suit, so take the highest card we have
			if(cardIndex == -1){
				cardIndex = 0; //set the first card as highest, we'll sort it out after
				for(int i = 1; i < hand.getNumCards(); i++){
					if(this.cardHigher(hand.getCard(i), hand.getCard(cardIndex), GameTypeOptions.GameType.Null)){
						cardIndex = i;
					}
				}
			}

		}
		//play the lowest card we have since we're first
		else{
			cardIndex = 0;
			for(int i = 1; i < hand.getNumCards(); i++){
				if(this.cardHigher(hand.getCard(cardIndex), hand.getCard(i), GameTypeOptions.GameType.Null)){
					cardIndex = i;
				}
			}
		}

		return hand.getCard(cardIndex);
	}
	
	/**
	 * Check if card1 > card2 
	 * @param card1 - a Card to compare
	 * @param card2 - a Card to compare
	 * @param gameType - used to specify which type of game we're checking the cards as
	 * so we can consider suit and game type
	 * @return true if card1 is higher than card2
	 * false if card1 is lower or they are equal
	 */
	public boolean cardHigher(Card card1, Card card2, GameTypeOptions.GameType gameType){
		
		/*
		 * If the game is null, just check which has the bigger index in the ArrayList
		 * If the game is suit, check if a card is of the trump
		 * If only one is trump, it's automatically higher
		 * If both are trump, just check which has the bigger index in the ArrayList
		 */
		
		/*
		 * Arrays list the cards in lower to higher value
		 * This way, the card with the higher index is higher
		 */
		
		if(gameType == GameTypeOptions.GameType.Null){
			/*
			 * Check which one has a higher face value
			 */
			Card.FACE_VALUE[] order = {Card.FACE_VALUE.SEVEN, Card.FACE_VALUE.EIGHT, 
					Card.FACE_VALUE.NINE, Card.FACE_VALUE.TEN, Card.FACE_VALUE.JACK, 
					Card.FACE_VALUE.QUEEN, Card.FACE_VALUE.KING, Card.FACE_VALUE.ACE};
		    ArrayList<Card.FACE_VALUE> orderList = new ArrayList<Card.FACE_VALUE>(Arrays.asList(order));  

			if(orderList.indexOf(card1.getFaceValue()) > orderList.indexOf(card2.getFaceValue())){
				return true;
			}
			else{
				return false;
			}
		}
		if(gameType == GameTypeOptions.GameType.Grand){
			
			/*
			 * If card1 && card2 are jacks, take the one with the higher suit
			 * If card1 xor card2 is jack, take the one that's a jack
			 * If neither are jack, calculate which is higher face value
			 */

			Card.FACE_VALUE[] order = {Card.FACE_VALUE.SEVEN, Card.FACE_VALUE.EIGHT, 
					Card.FACE_VALUE.NINE, Card.FACE_VALUE.QUEEN, Card.FACE_VALUE.KING, 
					Card.FACE_VALUE.TEN, Card.FACE_VALUE.ACE};
			ArrayList<Card.FACE_VALUE> orderList = new ArrayList<Card.FACE_VALUE>(Arrays.asList(order)); 
			Card.CARD_SUIT[] jacks = {Card.CARD_SUIT.DIAMONDS, Card.CARD_SUIT.HEARTS, 
					Card.CARD_SUIT.SPADES, Card.CARD_SUIT.CLUBS};
			ArrayList<Card.CARD_SUIT> jackList = new ArrayList<Card.CARD_SUIT>(Arrays.asList(jacks));
			
			if((card1.getFaceValue() == Card.FACE_VALUE.JACK) && (card2.getFaceValue() == Card.FACE_VALUE.JACK)){
				if(jackList.indexOf(card1.getFaceValue()) > jackList.indexOf(card2.getFaceValue())){
					return true;
				}
				else{
					return false;
				}
			}
			//guaranteed only one the two checks are true
			else if(card1.getFaceValue() == Card.FACE_VALUE.JACK){
				return true;
			}
			else if(card2.getFaceValue() == Card.FACE_VALUE.JACK){
				return false;
			}
			
			//at this point, both are not jacks, so just see which is a higher face value
			if(orderList.indexOf(card1.getFaceValue()) > orderList.indexOf(card2.getFaceValue())){
				return true;
			}
			else{
				return false;
			}
		}
		else{ //suit game
			
			/*
			 * If card1 && card2 are jacks, take the one with the higher suit
			 * If card1 xor card2 is jack, take the one that's a jack
			 * If neither are jacks, take the higher trump suit card
			 * If neither are trump, take the higher card
			 */
			
			Card.FACE_VALUE[] order = {Card.FACE_VALUE.SEVEN, Card.FACE_VALUE.EIGHT, 
					Card.FACE_VALUE.NINE, Card.FACE_VALUE.QUEEN, Card.FACE_VALUE.KING, 
					Card.FACE_VALUE.TEN, Card.FACE_VALUE.ACE};
			ArrayList<Card.FACE_VALUE> orderList = new ArrayList<Card.FACE_VALUE>(Arrays.asList(order));
			Card.CARD_SUIT[] jacks = {Card.CARD_SUIT.DIAMONDS, Card.CARD_SUIT.HEARTS, 
					Card.CARD_SUIT.SPADES, Card.CARD_SUIT.CLUBS};
			ArrayList<Card.CARD_SUIT> jackList = new ArrayList<Card.CARD_SUIT>(Arrays.asList(jacks));
			
			if((card1.getFaceValue() == Card.FACE_VALUE.JACK) && (card2.getFaceValue() == Card.FACE_VALUE.JACK)){
				if(jackList.indexOf(card1.getFaceValue()) > jackList.indexOf(card2.getFaceValue())){
					return true;
				}
				else{
					return false;
				}
			}
			else if(card1.getFaceValue() == Card.FACE_VALUE.JACK){
				return true;
			}
			else if(card2.getFaceValue() == Card.FACE_VALUE.JACK){
				return false; 
			}
			
			//both are trump, not jacks, take the highest value
			if((card1.getSuit() == Card.CARD_SUIT.values()[this.currentGameType.getTrumpSuit().ordinal() - 1]) && 
				(card2.getSuit() == Card.CARD_SUIT.values()[this.currentGameType.getTrumpSuit().ordinal() - 1])){
				if(orderList.indexOf(card1.getFaceValue()) > jackList.indexOf(card2.getFaceValue())){
					return true;
				}
				else{
					return false;
				}
			}
			
			//only max 1 can be trump, take the one that's trump
			else if(card1.getSuit() == Card.CARD_SUIT.values()[this.currentGameType.getTrumpSuit().ordinal() - 1]){
				return true;
			}
			else if(card2.getSuit() == Card.CARD_SUIT.values()[this.currentGameType.getTrumpSuit().ordinal() - 1]){
				return false;
			}
			
			//at this point neither is any sort of trump, return the higher face value
			if(orderList.indexOf(card1.getFaceValue()) > jackList.indexOf(card2.getFaceValue())){
				return true;
			}
			else{
				return false;
			}
		}				
	}
}
