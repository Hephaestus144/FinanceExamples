package MarketData;

import java.util.Arrays;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;

public class Curve 
{
	// properties
	double[] tenors;
	double[] rates;
	
	
	// constructors
	public Curve(double[] tenors, double[] rates)
	{
		this.tenors = tenors;
		this.rates = rates;
	}
	
	
	// methods
	public double InterpolateRates(double tenor)
	{
		double leftTenor = -1;
		double leftRate = -1;
		double rightTenor = -1;
		double rightRate = -1;
		
		if (DoubleStream.of(this.tenors).anyMatch(x -> x == tenor))
		{
			int tenorIndex = IntStream.range(0, this.tenors.length)
								.filter(i -> tenor == this.tenors[(int) i])
								.findFirst()
								.orElse(-1);
			return this.rates[tenorIndex];
		}
		
		for(int i = 0; i < this.tenors.length; i++)
		{
			if (tenors[i] <= tenor)
			{
				leftTenor = tenors[i];
				leftRate = rates[i];
			}
			else
			{
				break;
			}
		}
		
		if (leftTenor != -1)
		{
			for(int i = this.tenors.length - 1; i >= 0; i--)
			{
				if(tenors[i] >= tenor)
				{
					rightTenor = tenors[i];
					rightRate = rates[i];
				}
				else
				{
					break;
				}
			}
		}
		else
		{
			return this.rates[0];
		}
		
		if (rightTenor == -1)
		{
			return this.rates[this.rates.length - 1];
		}
		
		return ((rightRate - leftRate)/(rightTenor - leftTenor))*(rightTenor - tenor) + leftRate;
	}
	
	
	public double DiscountFactor(double tenor)
	{
		double rate = this.InterpolateRates(tenor);
		return Math.exp(-rate * tenor);
	}
}
