package de.peass.ci.helper;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

import de.dagere.peass.ci.helper.UnitConverter;

public class TestUnitConverter {

   final static int NANOSECONDS_TO_MICROSECONDS = 1000;
   final static int NANOSECONDS_TO_MILLISECONDS = 1000 * 1000;
   final static int NANOSECONDS_TO_SECONDS = 1000 * 1000 * 1000;

   final static String NANOSECONDS = "ns";
   final static String MICROSECONDS = "\u00B5s";
   final static String MILLISECONDS = "ms";
   final static String SECONDS = "s";

   final double[] nanoSecondValues = new double[] { 123 };
   final double[] mikroSecondValues = new double[] { 1234 };
   final double[] milliSecondValues = new double[] { 1234567 };
   final double[] secondValues = new double[] { 12345678E6 };

   @Test
   public void testGetFactorByMean() {
      Assert.assertEquals(1, UnitConverter.getFactorByMean(nanoSecondValues));
      Assert.assertEquals(NANOSECONDS_TO_MICROSECONDS, UnitConverter.getFactorByMean(mikroSecondValues));
      Assert.assertEquals(NANOSECONDS_TO_MILLISECONDS, UnitConverter.getFactorByMean(milliSecondValues));
      Assert.assertEquals(NANOSECONDS_TO_SECONDS, UnitConverter.getFactorByMean(secondValues));
   }

   @Test
   public void testGetUnitByFactor() {
      Assert.assertEquals(NANOSECONDS, UnitConverter.getUnitByFactor(1));
      Assert.assertEquals(MICROSECONDS, UnitConverter.getUnitByFactor(NANOSECONDS_TO_MICROSECONDS));
      Assert.assertEquals(MILLISECONDS, UnitConverter.getUnitByFactor(NANOSECONDS_TO_MILLISECONDS));
      Assert.assertEquals(SECONDS, UnitConverter.getUnitByFactor(NANOSECONDS_TO_SECONDS));
   }

}
