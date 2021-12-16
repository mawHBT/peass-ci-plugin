package de.peass.ci.rts;

import java.io.File;
import java.io.IOException;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;

import de.dagere.peass.ci.PeassProcessConfiguration;
import de.dagere.peass.ci.logs.rts.RTSLogSummary;
import de.dagere.peass.ci.rts.RTSVisualizationCreator;
import de.dagere.peass.config.DependencyConfig;
import de.dagere.peass.config.MeasurementConfig;
import de.dagere.peass.dependency.analysis.data.ChangedEntity;
import de.dagere.peass.dependency.persistence.Dependencies;
import de.dagere.peass.dependency.persistence.ExecutionData;
import de.dagere.peass.folders.ResultsFolders;
import de.dagere.peass.utils.Constants;
import hudson.model.Run;

public class TestRTSVisualizationCreator {
   
   @Test
   public void testEmptyCreation() throws JsonGenerationException, JsonMappingException, IOException {
      PeassProcessConfiguration peassConfig = new PeassProcessConfiguration(true, new MeasurementConfig(15), new DependencyConfig(1, false), null, false, false, false);
      ResultsFolders localWorkspace = new ResultsFolders(new File("target/current"), "empty-test");
      
      writeEmptyDependencies(localWorkspace);
      writeEmptyExecutionData(localWorkspace);
      
      RTSVisualizationCreator creator = new RTSVisualizationCreator(localWorkspace, peassConfig);
      
      creator.visualize(Mockito.mock(Run.class), new RTSLogSummary(false, false));
   }

   private void writeEmptyExecutionData(final ResultsFolders localWorkspace) throws IOException, JsonGenerationException, JsonMappingException {
      ExecutionData data = new ExecutionData();
      Constants.OBJECTMAPPER.writeValue(localWorkspace.getExecutionFile(), data);
   }

   private void writeEmptyDependencies(final ResultsFolders localWorkspace) throws IOException, JsonGenerationException, JsonMappingException {
      Dependencies emptyDependencies = new Dependencies();
      emptyDependencies.getInitialversion().addDependency(new ChangedEntity("Test#test"), new ChangedEntity("SomeCallee#method"));
      Constants.OBJECTMAPPER.writeValue(localWorkspace.getDependencyFile(), emptyDependencies);
   }
}
