package com.spike.giantdataanalysis.model.graph.dsl.descriptionlogic.concept;

import com.spike.giantdataanalysis.model.graph.dsl.descriptionlogic.DescriptionLogicConstants;
import com.spike.giantdataanalysis.model.graph.dsl.descriptionlogic.DescriptionLogicOps;
import com.spike.giantdataanalysis.model.graph.dsl.descriptionlogic.role.RoleConstructor;

public class CQualifiedAtLeastRestriction extends ConceptConstructor {

  private final int n;
  private final RoleConstructor r;
  private final ConceptConstructor c;

  public CQualifiedAtLeastRestriction(int n, RoleConstructor r, ConceptConstructor c) {
    this.n = n;
    this.r = r;
    this.c = c;
  }

  @Override
  public String syntax() {
    StringBuilder sb = new StringBuilder();

    sb.append(DescriptionLogicOps.at_least.Name());
    sb.append(DescriptionLogicConstants.BLANK);
    sb.append(n);
    sb.append(DescriptionLogicConstants.BLANK);
    sb.append(r);
    sb.append(DescriptionLogicConstants.BLANK);
    sb.append(c);

    return sb.toString();
  }

}