package de.dagere.peass.ci.helper;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.fasterxml.jackson.databind.JsonMappingException;
import de.dagere.peass.measurement.rca.data.CauseSearchData;
import de.dagere.peass.utils.Constants;
import java.io.File;
import org.junit.jupiter.api.Test;

class TestJsonFile {

  @Test
  void testContainsNotNaN() {
    final File causeSearchDataFile = new File("src/test/resources/demo-results-measurements/NaN/meanOldNaN.json");

    final JsonMappingException jsonMappingException = assertThrows(JsonMappingException.class,
        () -> {
          Constants.OBJECTMAPPER.readValue(causeSearchDataFile, CauseSearchData.class);
        });
    assertTrue(jsonMappingException.getMessage().contains("Non-standard token 'NaN'"));

  }

}
