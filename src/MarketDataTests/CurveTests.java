package MarketDataTests;
import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.Test;

import MarketData.Curve;

public class CurveTests 
{
	@Test
	public void CurveInterpolate_AtKnownValue()
	{
		Curve curve = new Curve(new double[] {0.25, 0.5, 1.0}, new double[] {1, 2, 4});
		assertEquals(2.0, curve.InterpolateRates(0.5), 0.01);
	}
	
	
	@Test
	public void CurveInterpolate_BetweenKnownValues()
	{
		Curve curve = new Curve(new double[] {0.25, 0.5, 1.0}, new double[] {1, 2, 4});
		assertEquals(3.0, curve.InterpolateRates(0.75), 0.01);
	}
}
