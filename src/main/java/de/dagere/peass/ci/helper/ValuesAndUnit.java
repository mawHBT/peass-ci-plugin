package de.dagere.peass.ci.helper;

public class ValuesAndUnit {

   final double[] values;
   final String unit;

   public ValuesAndUnit(final double[] values, final String unit) {
      this.values = values;
      this.unit = unit;
   }

   public double[] getValues() {
      return values;
   }

   public String getUnit() {
      return unit;
   }

}
