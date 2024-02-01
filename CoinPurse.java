package hw;

import java.util.Arrays;

public class CoinPurse {
	// instance variables
	private int numSickles;
	private int numGalleons;
	private int numKnuts;
	private static final double CAPACITY = 256;
		
	//constructors
	public CoinPurse(int g, int s, int k) {
		this.numGalleons = g;
		this.numSickles = s;
		this.numKnuts = k;
	}
	
	// adding and withdrawing functions
	//deposit functions: the largest value can be 256 as the capacity, and can't be a number less than 0
	public int depositGalleons(int n) {
		if(n > CAPACITY) {
			throw new IllegalArgumentException("Can't hold more then 256 coins!");
		}
		if (n < 0) {
			throw new IllegalArgumentException("Can't have a negative deposit number!");
		}
		return this.numGalleons + n;
	}
	public int depositSickles(int n) {
		if(n > CAPACITY) {
			throw new IllegalArgumentException("Can't hold more then 256 coins!");
		}
		if (n < 0) {
			throw new IllegalArgumentException("Can't have a negative deposit number!");
		}
		return this.numSickles + n;
	}
	public int depositKnuts(int n) {
		if(n > CAPACITY) {
			throw new IllegalArgumentException("Can't hold more then 256 coins!");
		}
		if (n < 0) {
			throw new IllegalArgumentException("Can't have a negative deposit number!");
		}
		return this.numKnuts + n;
	}
	
	//withdrawing functions: the value should atleast be what is present in the purse and can't take out negative values
		public void withdrawGalleons(int n) {
			if(n > this.numGalleons) {
				throw new IllegalArgumentException("Can't take out more coins!");
			}
			if (n < 0) {
				throw new IllegalArgumentException("Can't have a negative withdrawal number!");
			}
			this.numGalleons = this.numGalleons - n;
		}
		public void withdrawSickles(int n) {
			if(n > this.numSickles) {
				throw new IllegalArgumentException("Can't take out more coins!");
			}
			if (n < 0) {
				throw new IllegalArgumentException("Can't have a negative withdrawal number!");
			}
			this.numSickles = this.numKnuts - n;
		}
		public void withdrawKnuts(int n) {
			if(n > this.numKnuts) {
				throw new IllegalArgumentException("Can't take out more coins!");
			}
			if (n < 0) {
				throw new IllegalArgumentException("Can't have a negative withdrawal number!");
			}
			this.numKnuts = this.numKnuts - n;
		}
		
		
		
		//numsCoins() returns the total number of coins in the purse
		public double numCoins() {
			double number = 0;
			number = this.numSickles + this.numGalleons + this.numKnuts;
			return number;
		}	
		//totalValue() converts the coins into knuts and then adds up the value
		public int totalValue() {
			int total = 0;
			total += this.numSickles * 29;
			total += this.numGalleons * 493;
			total += this.numKnuts;
			return total;
		}	
		// toString()
		public String toString() {
			return "There are "+ this.numSickles + " sickles, " + this.numGalleons + " galleons, and "+ this.numKnuts +" knuts.";
		}
		
		//exact change based on the remainder
		public boolean exactChange(int n) {
			int totalg = 493;
			int totals = 29;
			int totalk = 1;
			int total = n;
			if(((total/totalg) >= 1) && this.numGalleons >= (total/totalg)) {
				total -= (total/totalg) * totalg;
			}
			if(((total/totals) >= 1) && this.numSickles >= (total/totals)) {
				total -= (total/totals) * totals;
			}
			if(((total/totalk) >= 1) && this.numKnuts >= (total/totalk)) {
				total -= (total/totalk) * totalk;
			}
			if (total == 0) {
				return true;
			}
			return false;
		}
		
		
		//withdraw change function
		public int[] withdraw(int n) {
			int gal = 0;
			int sic = 0;
			int knt = 0;
			int total = 0;
			if (exactChange(n)) {
				while (gal < this.numGalleons && n>= total + 493) {
					total+=493;
					gal++;
				}
				while (sic < this.numSickles && n>= total + 29) {
					total+=29;
					sic++;
				}
				while (knt < this.numKnuts && n>= total + 1) {
					total+=1;
					knt++;
				}
			}
			else {
				if (n> totalValue()) {
					throw new IllegalArgumentException("Can't withdraw coins more than avalaible");
				}
				while(n>= (total + 493) && gal < this.numGalleons) {
					total+=493;
					gal++;
				}
				while(n>= (total + 29) && sic < this.numSickles) {
					total+=29;
					sic++;
				}
				while(n >= (total +1) && knt < this.numKnuts) {
					total+=1;
					knt++;
				}
			}
			
			int[] withdrawcoins = {gal, sic, knt};
			return withdrawcoins;
			
			
		}
		
		
		// draw random coin: to get 50% probability of sickles; 25 to 75 will count as sickle
		//there should be more than 0 coins in the wallet to begin with
		public int drawRandCoin() {
			int randit = (int)((Math.random()*100)+1);
			double coin = this.numCoins();
			int draw = 0;
			double gal = (numGalleons/coin)*100;
			double sic = (numSickles/coin)*100;
			if (this.numCoins() == 0) {
				throw new IllegalArgumentException("There are no coins!");
			}
			if (randit > (gal + sic)) {
				draw = 0;
			}
			if (randit > gal && randit <= (gal+sic)) {
				draw = 1;
			}
			if (randit >= 1 && randit <= gal) {
				draw = 2;
			}
			
			return draw;
		}
		
		//draw random sequence; implemented through for loop
		//for n it will keep generating new coins in the sequence
		public int[] drawRandSequence(int n) {
			int[] sequence = new int[n];
			if (numCoins() <= 0) {
				throw new IllegalArgumentException("There are coins to be taken out from the purse.");
			}
			for (int i = 0; i < n; i++) {
				sequence[i] = drawRandCoin();
			}
			return sequence;
		}
		
		//compare sequence, compare two coin sequences
		public static int compareSequence(int[] coinSeq1, int[] coinSeq2) {
			int[] sequence = new int[coinSeq1.length];
			int bigseq = 0;
			if (coinSeq1.length != coinSeq2.length) {
				throw new IllegalArgumentException("The length of the coin sequences should be the same.");
			}
			for (int i = 0; i < coinSeq1.length; i++) {
				if(coinSeq1[i] - coinSeq2[i] < 0) {
					sequence[i] = -1;
				}
				else if(coinSeq1[i] - coinSeq2[i]>0) {
					sequence[i] = 1;
				}
				else {
					sequence[i] = 0;
				}
				bigseq += sequence[i];
			}
			return bigseq;
			
			
			
		}
		
	
	public static void main(String[] args) {
		
	}
}
