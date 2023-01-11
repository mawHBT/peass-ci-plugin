package de.dagere.peass.ci.helper;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import org.junit.jupiter.api.Test;

class TestJsonFile {

  @Test
  void testCauseSearchDataFileContainsNaN() {
    final File causeSearchDataFile = new File("src/test/resources/demo-results-measurements/NaN/meanOldNaN.json");
    assertTrue(CheckJsonFiles.causeSearchDataFileContainsNaN(causeSearchDataFile));
  }

}
