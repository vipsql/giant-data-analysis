package com.spike.giantdataanalysis.model.graph.dsl.descriptionlogic.concept;

import com.spike.giantdataanalysis.model.graph.dsl.descriptionlogic.DescriptionLogicConstants;
import com.spike.giantdataanalysis.model.graph.dsl.descriptionlogic.DescriptionLogicOps;

/** @see {@link CAggreement} */
public class CSameAs extends ConceptConstructor {

  private final CIndividual u1;
  private final CIndividual u2;

  public CSameAs(CIndividual u1, CIndividual u2) {
    this.u1 = u1;
    this.u2 = u2;
  }

  @Override
  public String syntax() {
    StringBuilder sb = new StringBuilder();

    sb.append(DescriptionLogicOps.same_as.Name());
    sb.append(DescriptionLogicConstants.BLANK);
    sb.append(u1);
    sb.append(DescriptionLogicConstants.BLANK);
    sb.append(u2);

    return sb.toString();
  }
}