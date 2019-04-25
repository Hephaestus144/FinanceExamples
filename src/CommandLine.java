import MarketData.Curve;

public class CommandLine 
{
	public static void main(String[] args) 
	{
		// TODO Auto-generated method stub
		System.out.println("Hello world");
		Curve curve = new Curve(new double[] {0.25, 0.5, 1}, new double[] {1, 2, 4});
		System.out.println(curve.InterpolateRates(0.75));
	}
}
