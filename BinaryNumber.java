

//Susmitha Shailesh
//I pledge my honor that I have abided by the Stevens Honor System.

package hw1;

public class BinaryNumber {
	
	//data fields
	private int[] data; //stores digits of BinaryNumber
	private boolean overflow = false; //true until cleared if overflow error was raised
	
	//constructors
	BinaryNumber(int length){
		//parameters: int length
		for(int i=0; i<length; i++) {
			data[i] = 0;
		}
	}
	
	BinaryNumber(String str){
		//parameters: String str
		data = new int[str.length()];
		
		for(int i=0; i<str.length(); i++) {
			data[i]=java.lang.Character.getNumericValue(str.charAt(i));
		}
	}
	
	//methods
	public int getLength() {
		//returns length of binary number
		return data.length;
	}
	
	public int getDigit(int index) {
		//returns the digit at a certain index of binary number
		//does not work if index is negative or greater than length of binary number
		if(index<0 | index>=data.length) {
			System.out.println("index out of bounds");
			return -1;
		}
		return data[index];
	}
	
	public int toDecimal() {
		//returns conversion of binary number to decimal number
		int num = 0;
		for(int i=0; i<data.length; i++) {
			num += (data[i]*(Math.pow(2, i)));
		}
		return num;
	}
	
	public void shiftR(int amount) {
		//shifts all digits of binary number amount places to the right
		//replaces all newly empty spaces with 0
		//does not work if amount is less than 1
		if(amount<0) {
			System.out.println("invalid shift amount");
		}
		else {
			int[] newArr = new int[data.length+amount];
			for(int i=0; i<amount; i++) {
				//add zeros
				newArr[i] = 0;
			}
			for(int i=amount; i<newArr.length; i++) {
				//copy array contents
				newArr[i] = data[i-amount];
			}		
			data = newArr;
		}
		
	}
	
	public void add(BinaryNumber aBinaryNumber) {
		//adds two BinaryNumbers and replaces receiving number with the sum
		//does not work if the two BinaryNumbers have different lengths
		//does not work if the sum is longer than the summands; causes overflow 
		int cin = 0;
		int[] ans = new int[data.length];
		if(this.getLength() != aBinaryNumber.getLength()) {
			//check that BinaryNumbers have same length
			System.out.println("Lengths of binary numbers do now coincide");
		}
		else {
			//adds numbers to ans[i] based on addition of the digits of the BinaryNumbers 
			//at index i
			for(int i=0; i<this.getLength(); i++) {
				if((this.getDigit(i) + aBinaryNumber.getDigit(i) + cin) == 0 ) {
					ans[i] = 0;
					cin=0;
				}
				else if((this.getDigit(i) + aBinaryNumber.getDigit(i) + cin) == 1 ) {
					ans[i] = 1;
					cin=0;
				}
				else if((this.getDigit(i) + aBinaryNumber.getDigit(i) + cin) == 2 ) {
					if(i==(this.getLength()-1)) {
						//catches overflow if there is a carryover digit at the addition
						//of the last digits of the BinaryNumbers
						overflow = true;
						break;
					}
					ans[i] = 0;
					cin=1;
				}
				else if((this.getDigit(i) + aBinaryNumber.getDigit(i) + cin) == 3 ) {
					if(i==(this.getLength()-1)) {
						overflow = true;
						break;
					}
					ans[i] = 1;
					cin=1;
				}
			}
		}
		
		data = ans;
	}
	
	public void clearOverflow() {
		//resets overflow boolean to false
		overflow = false;
	}
	
	public String toString() {
		//toString prints BinaryNumber digits
		//returns overflow if error was previously raised
		if(overflow) {
			return "Overflow";
		}
		String dataStr = "";
		for(int i=0; i<data.length; i++) {
			dataStr += data[i];
		}
		return dataStr;
	}
	
}