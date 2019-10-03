package simple;

import java.util.Scanner;

public class Poker {
	static class Card implements Comparable<Card>{
		private char suit; 
		private String rank;
		private String[] allSuites = {"S", "H", "C", "D"};
		private String[] allRanks = {"A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K" };
		// default constructor 
		public Card(){
			suit = 'D'; 
			rank = "A";
		}
		//parameterized constructor 
		public Card (String rank, char suit){

			// call the default constructor this();
			// if valid cards are given then update the Card 
			if (checkValidity(rank, suit)){
				this.rank = rank; this.suit = suit;}}


		// method to check for the validity of a Card 

		public boolean checkValidity(String rank, char suit2){

			// check for validity of Suit 
			boolean indexSuit = arrayContains(allSuites,suit2); 
			boolean indexRank = arrayContainsS(allRanks,rank);
			// check that both rank and suit are valid. 
			if (indexSuit && indexRank)
				return true; 
			else
				return false;}

		private  boolean arrayContains(String[] arr,char suit2){
			// search whole array 
			for(int index = 0; index < arr.length; index++){
				String suit=arr[index];
				if(suit.charAt(0)==suit2){
					return true;}
			}
			return false; 

		}
		private boolean arrayContainsS(String[] allRanks2, String rank2) {
			// TODO Auto-generated method stub
			for(int index = 0; index < allRanks2.length; index++) 
				if(allRanks2[index].equals (rank2)){
					return true;}
			return false; 

		}
		// method to get the rank of the Card 
		public String getRank (){
			// return the rank 
			return rank;}
		// method to get the suit of the Card 
		public char getSuit(){
			// return the suit 
			return suit;}
		@Override 
		// method to return the String form of the Card 
		public String toString(){
			// return rank and suit 
			return rank + suit;}


		//----------------------------

		@Override 
		//method to compare two cards 
		public int compareTo (Card o){
			// first check the Suit of the Card 
			if (getSuit()== o.getSuit()){
				// cehck for the rank
				int indexFirst = getIndexS (allRanks,getRank()); 
				int indexSecond = getIndexS (allRanks,getRank());

				return indexFirst - indexSecond;}
			else{
				// check for the suit 
				int indexFirst = getIndex(allSuites,getSuit()); 
				int indexSecond = getIndex(allSuites,getSuit());

				return indexFirst - indexSecond;
			}}

		private int getIndexS(String[] allRanks2, String rank2) {
			// TODO Auto-generated method stub
			for(int index = 0; index < allRanks2.length; index++){
				if (allRanks2[index].equals(rank2))
					return index; }
			return -1;
		}
		// method to check if a key is present in the arr 


		// method to get the index of the key from the // array 

		private  int getIndex (String[] arr, char c){
			for(int index = 0; index < arr.length; index++){
				if (arr[index].equals(c))
					return index; }
			return -1;}

		@Override // method to check two Card for equality 
		public boolean equals(Object obj){
			return this.toString().equals(obj.toString());}
	}


	// method to check if a Card is present in the H Hand 

	private static boolean containsCard (Card[] playerHand, String value, boolean checksuit){
		boolean isPresent = false; 
		for(int idx = 0; idx < playerHand.length; idx++)
		{
			// check for suit 
			if (!checksuit)
			{
				if (playerHand[idx].getSuit() == value.charAt(0))
				{
					isPresent = true; 
					break;
				}
				// check for rank
				else
				{
					if (playerHand[idx].getRank().equalsIgnoreCase(value))
					{
						isPresent = true; break;
					}
				}
			}
		}
		return isPresent;
	}


	public static boolean checkRoyalFlush(Card[] playerHand){

		if (!checkFlush(playerHand)) return false;

		if (!containsCard (playerHand, "10", false))
			return false; 
		if(!containsCard (playerHand, "J", false))
			return false; 
		if (!containsCard (playerHand, "Q", false))
			return false; 
		if (!containsCard (playerHand, "K", false))
			return false; 
		if (!containsCard (playerHand, "A", false))
			return false;
		return true;

	}
	// method to check if it is Four of a Kind 

	public static boolean checkfourofAKind(Card[] playerHand){
		int counter = 1; 
		for(int idx = 0; idx < playerHand.length; idx++){
			counter = 1; 
			Card current = playerHand[idx];
			for(int jdx = 0; jdx < playerHand.length;jdx++){
				if(idx == jdx)
					continue ;
				if (current.getRank().equals(
						playerHand [jdx].getRank())) counter++;
			}
			if (counter==4)
				return true;
		}
		return false;
	}

	// method to check for three of a Kind 
	public static boolean checkThreeOfAKind(Card[] playerHand){

		int counter = 1; 
		for(int idx = 0; idx < playerHand.length; idx++)
		{
			counter = 1;
			Card current = playerHand[idx]; 
			for (int jdx = 0; jdx < playerHand.length;jdx++)
			{
				if(idx == jdx)
					continue;

				if (current.getRank().equals(playerHand[jdx].getRank())) 
					counter++;
			}
			if (counter >= 3)
				return true;

			//			----------------
		}
		return false;
		// method to check for One Pair 

	}
	public static boolean checkOnePair (Card[] playerHand){
		int counter = 1; 
		for(int idx = 0; idx < playerHand.length; idx++){
			counter = 1; 
			Card current = playerHand[idx]; 
			for(int jdx = 0; jdx < playerHand.length;jdx++){

				if(idx == jdx)
					continue;

				if (current.getRank().equals(playerHand[jdx].getRank()))
					counter++;
			}
			if (counter >= 2)
				return true;
		}
		return false;
	}


	public static boolean checkTwoPair (Card[] playerHand){

		int counter = 1; Card first = null; 
		for(int idx = 0; idx < playerHand.length; idx++){
			counter = 1; 
			Card current = playerHand[idx]; 
			for(int jdx = 0; jdx < playerHand.length; jdx++){

				if (idx == jdx)
					continue;
				if (current.getRank().equals(playerHand[jdx].getRank()))
					counter++;
			}
			if (counter==2)
				first = playerHand[idx];
		}
		if (first == null)
			return false;
		for(int idx = 0; idx < playerHand.length; idx++){
			counter = 1; 
			Card current = playerHand[idx]; 
			for(int jdx = 0; jdx < playerHand.length;jdx++){
				if(idx ==jdx)
					continue;
				if (current.getRank().equals(playerHand[jdx].getRank ()) && ! current.getRank().equals(	first.getRank ())) 
					counter++;
			}
			if (counter >= 2)

				return true;
		}
		return false;}
	// method to check for a Full House 
	public static boolean checkFullHouse(Card[] playerHand){
		int counter = 1;
		Card first = null; 
		for (int idx = 0; idx < playerHand.length; idx++)
		{
			counter = 1; 
			Card current = playerHand[idx]; 
			//			System.out.println("iteration of "+current);
			for(int jdx = 0; jdx < playerHand.length;jdx++)
			{
				//				System.out.println("--"+playerHand[jdx]+""+idx);
				if(idx == jdx)
					continue;
				if (current.getRank().equals(playerHand[ jdx].getRank())){
					//					&&(current.getSuit()!=playerHand[ jdx].getSuit())   if suit should different add this condition
					counter++; 
					//					System.out.println(""+current.getRank()+"=="+playerHand[ jdx].getRank()+current.getSuit()+"=="+playerHand[ jdx].getSuit()+counter);
				}

			}

			if (counter == 3){
				//				System.out.println("count"+counter);
				first = playerHand[idx];
			}
		}
		if (first== null)
			return false;
		else{
			for(int idx = 0; idx< playerHand.length; idx++)
			{
				counter = 1; 
				Card current = playerHand[idx]; 
				for (int jdx = 0; jdx < playerHand.length;jdx++)
				{
					if(idx == jdx)
						continue;

					if (current.getRank().equals(playerHand[jdx].getRank ()) && !current.getRank().equals(first.getRank()))
						counter++;
				}
				if (counter == 2)
					return true;
			}
		}

		return false;
	}




	public static boolean checkFlush (Card[] playerHand){
		char suit = playerHand[0].getSuit(); 
		for(int idx = 0; idx < playerHand.length; idx++){
			if (playerHand[idx].getSuit() != suit)
				return false;
		}
		return true;}

	public static boolean checkStraight (Card[] playerHand)
	{
		if (containsCard (playerHand, "2", false))
		{
			if (containsCard (playerHand, "6", false)
					&& containsCard (playerHand, "3", false) 
					&& containsCard (playerHand, "4", false) 
					&& containsCard (playerHand, "5", false))
				return true; 
		}
		else if (containsCard (playerHand, "10", false))
		{
			if (containsCard (playerHand, "J", false)
					&& containsCard (playerHand,"Q", false)
					&& containsCard (playerHand, "K", false)
					&& containsCard (playerHand, "A",false)) 
				return true;
		}
		else if (containsCard (playerHand, "3", false)){

			if (containsCard (playerHand, "6", false)
					&& containsCard (playerHand,"7", false) 
					&& containsCard (playerHand, "4", false) 
					&& containsCard (playerHand,"5",false)) 
				return true;
		}
		else if (containsCard (playerHand, "4", false)){
			if (containsCard (playerHand, "6", false)
					&& containsCard (playerHand, "7", false) 
					&& containsCard (playerHand,"8", false) 
					&& containsCard (playerHand, "5", false)) 
				return true;
		}
		else if (containsCard (playerHand, "5", false))
		{
			if (containsCard (playerHand, "6", false)
					&& containsCard (playerHand,"7" , false) 
					&& containsCard (playerHand, "8", false) 
					&& containsCard (playerHand,"9", false)
					&& containsCard (playerHand,"10", false))
				return true;
		}
		else if (containsCard (playerHand, "6", false))
		{
			if (containsCard (playerHand, "10", false)
					&& containsCard (playerHand, "7", false) 
					&& containsCard (playerHand, "8", false) 
					&& containsCard (playerHand,"9", false)) 
				return true;
		}
		else if (containsCard (playerHand,"7", false)) 
		{
			if (containsCard (playerHand, "10", false)
					&& containsCard (playerHand, "J", false) 
					&& containsCard (playerHand, "8", false) 
					&& containsCard (playerHand, "9", false)) 
				return true;
		}
		else if (containsCard (playerHand, "8", false))
		{
			if (containsCard (playerHand, "10", false)
					&& containsCard (playerHand, "J", false) 
					&& containsCard (playerHand, "Q", false) 
					&& containsCard (playerHand, "9", false)) 
				return true;
		}
		else if (containsCard (playerHand,"9", false)){
			if (containsCard (playerHand, "10", false)
					&& containsCard (playerHand, "J", false) 
					&& containsCard (playerHand,"Q", false) 
					&& containsCard (playerHand,"K", false))

				return true;
		}
		return false;
	}

	// method to check for a Straight Flush 

	public static boolean checkstraightFlush(Card[] playerHand){

		return checkFlush(playerHand) && checkStraight(playerHand);}

	public static void main(String[] args) {
		Card[] playerHand=new Card[5];
		for (int i=0; i<args.length;i++){
			String rank=null;
			char suit;
			if(args[i].length()==3){
				rank=args[i].substring(0,2);
				suit=args[i].charAt(2);
			}
			else{
				rank=args[i].substring(0,1);
				suit=args[i].charAt(1);
			}

			Card c=new Card(rank,suit);
			playerHand[i]=c;
		}
		for(int i=0;i<playerHand.length;i++){
			System.out.println("Card "+i+" - "+playerHand[i]);
		}
		boolean d=checkTwoPair(playerHand);
		System.out.println("checkTwoPair "+d);

	}

}
