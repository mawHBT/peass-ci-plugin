package de.peass.ci.helper;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

import de.dagere.peass.ci.helper.UnitConverter;

public class TestUnitConverter {

   final double[] nanoSecondValues = new double[] { 123 };
   final double[] mikroSecondValues = new double[] { 1234 };
   final double[] milliSecondValues = new double[] { 1234567 };
   final double[] secondValues = new double[] { 12345678E6 };

   @Test
   public void testGetFactorByMean() {
      Assert.assertEquals(1, UnitConverter.getFactorByMean(nanoSecondValues));
      Assert.assertEquals(UnitConverter.NANOSECONDS_TO_MICROSECONDS, UnitConverter.getFactorByMean(mikroSecondValues));
      Assert.assertEquals(UnitConverter.NANOSECONDS_TO_MILLISECONDS, UnitConverter.getFactorByMean(milliSecondValues));
      Assert.assertEquals(UnitConverter.NANOSECONDS_TO_SECONDS, UnitConverter.getFactorByMean(secondValues));
   }

   @Test
   public void testGetUnitByFactor() {
      Assert.assertEquals(UnitConverter.NANOSECONDS, UnitConverter.getUnitByFactor(1));
      Assert.assertEquals(UnitConverter.MICROSECONDS, UnitConverter.getUnitByFactor(UnitConverter.NANOSECONDS_TO_MICROSECONDS));
      Assert.assertEquals(UnitConverter.MILLISECONDS, UnitConverter.getUnitByFactor(UnitConverter.NANOSECONDS_TO_MILLISECONDS));
      Assert.assertEquals(UnitConverter.SECONDS, UnitConverter.getUnitByFactor(UnitConverter.NANOSECONDS_TO_SECONDS));
   }

}
