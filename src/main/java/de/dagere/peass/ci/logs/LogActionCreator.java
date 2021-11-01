package de.dagere.peass.ci.logs;

import java.io.File;
import java.io.IOException;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import de.dagere.peass.ci.PeassProcessConfiguration;
import de.dagere.peass.ci.helper.VisualizationFolderManager;
import de.dagere.peass.ci.logs.measurement.MeasurementActionCreator;
import de.dagere.peass.ci.logs.rca.RCAActionCreator;
import de.dagere.peass.ci.logs.rts.RTSActionCreator;
import de.dagere.peass.dependency.analysis.data.TestCase;
import hudson.model.Run;

public class LogActionCreator {
   
   private static final Logger LOG = LogManager.getLogger(LogActionCreator.class);
   
   private final PeassProcessConfiguration peassConfig;
   private final Run<?, ?> run;
   private final LogFileReader reader;
   private final VisualizationFolderManager visualizationFolders;
   
   public LogActionCreator(final PeassProcessConfiguration peassConfig, final Run<?, ?> run, final File localWorkspace) {
      this.peassConfig = peassConfig;
      this.run = run;
      visualizationFolders = new VisualizationFolderManager(localWorkspace, run);
      reader = new LogFileReader(visualizationFolders, peassConfig.getMeasurementConfig());
   }
   
   public void createRTSActions() throws IOException {
      LOG.debug("Creating RTS log actions");
      RTSLogFileReader rtsReader = new RTSLogFileReader(visualizationFolders, peassConfig.getMeasurementConfig());
      RTSActionCreator rtsActionCreator = new RTSActionCreator(rtsReader, run, peassConfig.getMeasurementConfig());
      rtsActionCreator.createRTSActions();
   }

   public void createMeasurementActions(final Set<TestCase> tests) throws IOException {
      MeasurementActionCreator measurementActionCreator = new MeasurementActionCreator(reader, run, peassConfig.getMeasurementConfig());
      measurementActionCreator.createMeasurementActions(tests);
   }
   
   public void createRCAActions() throws IOException {
      RCAActionCreator rcaActionCreator = new RCAActionCreator(reader, run, peassConfig.getMeasurementConfig());
      rcaActionCreator.createRCAActions();
   }
}
