package de.dagere.peass.ci.helper;

import de.dagere.peass.config.MeasurementConfig;
import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics;

import java.util.Arrays;

public class HistogramValues {

   private final double[] valuesCurrent;
   private final double[] valuesBefore;

   /**
    * Creates histogram values, assuming parameters are in nanoseconds
    */
   public HistogramValues(final double[] valuesCurrent, final double[] valuesBefore, final MeasurementConfig currentConfig) {
      final double mean = new DescriptiveStatistics(valuesCurrent).getMean();
      final int factor = UnitConverter.getFactorByMean(mean);

      this.valuesCurrent = Arrays.stream(valuesCurrent).map(value -> value / currentConfig.getRepetitions() / factor).toArray();
      this.valuesBefore = Arrays.stream(valuesBefore).map(value -> value / currentConfig.getRepetitions() / factor).toArray();
   }

   /**
    * Returns a javascript visualizable value array in the specified unit
    *
    * @return A javascript visualizable value array in the specified unit
    */
   public String getValuesCurrentReadable() {
      return Arrays.toString(valuesCurrent);
   }

   public String getValuesBeforeReadable() {
      return Arrays.toString(valuesBefore);
   }
}