package de.dagere.peass.ci.helper;

import com.fasterxml.jackson.databind.JsonMappingException;
import de.dagere.peass.measurement.rca.data.CauseSearchData;
import de.dagere.peass.utils.Constants;
import java.io.File;
import java.io.IOException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class CheckJsonFiles {

  private static final Logger LOG = LogManager.getLogger(CheckJsonFiles.class);

  public static boolean causeSearchDataFileContainsNaN(final File causeSearchDataFile) {

    try {
      Constants.OBJECTMAPPER.readValue(causeSearchDataFile, CauseSearchData.class);
    } catch (IOException e) {
      if (e instanceof JsonMappingException && e.getMessage().contains("Non-standard token 'NaN'")) {
        LOG.error("JsonFile contains 'NaN', probably error occured!");
        return true;
      }
      e.printStackTrace();
    }
    return false;
  }

}
