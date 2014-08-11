package io.prediction.examples.java.recommendations.tutorial4;

import io.prediction.controller.java.EmptyParams;
import io.prediction.controller.java.IJavaEngineFactory;
import io.prediction.controller.java.JavaEngine;
import io.prediction.controller.java.JavaEngineBuilder;
import io.prediction.controller.java.JavaEngineParams;
import io.prediction.controller.java.JavaEngineParamsBuilder;
import io.prediction.workflow.JavaAPIDebugWorkflow;

import java.util.HashMap;

import io.prediction.controller.IdentityPreparator;

public class Runner4a {
  
  private static class HalfBakedEngineFactory implements IJavaEngineFactory {
    public JavaEngine<TrainingData, EmptyParams, TrainingData, Query, Float, Object> apply() {
      return new JavaEngineBuilder<
        TrainingData, EmptyParams, TrainingData, Query, Float, Object> ()
        .dataSourceClass(DataSource.class)
        .build();
    }
  }

  public static void main(String[] args) {
    JavaEngineParams engineParams = new JavaEngineParamsBuilder()
      .dataSourceParams(new DataSourceParams("data/ml-100k/", true))
      .build();

    JavaAPIDebugWorkflow.runEngine(
      "Recommendation.tutorial4.Runner4a", 
      new HashMap<String, String>(),
      3, // verbose
      (new HalfBakedEngineFactory()).apply(),
      engineParams
    );
  }
}
