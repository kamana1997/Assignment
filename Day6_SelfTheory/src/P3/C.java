package P3;

public class C implements A,B{

	@Override
	public double compute(double d1, double d2) {		
		return d1+d2;
	}
	
	@Override
	public double compute(double d1) {
		return d1;
	}
	
}
