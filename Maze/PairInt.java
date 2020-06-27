package hw3;

public class PairInt {

	private int x = -1;
	private int y = -1;
	
	public PairInt(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public void setX(int x){
		this.x = x;
	}
	
	public void setY(int y) {
		this.y = y;
	}
	
	
	public boolean equals(Object p){
		if (p.getClass() == this.getClass()){
            return this.x == ((PairInt)p).getX() && this.y == ((PairInt)p).getY();
        }
        return false;
	}
	
	public String toString() {
		return "(" + x + "," + y + ")";
	}
	
	public PairInt copy() {
		PairInt ret = new PairInt(this.x, this.y);
		return ret;
	}
	
}
