package com.spike.giantdataanalysis.neo4j.dsl.dl.role;

import com.spike.giantdataanalysis.neo4j.dsl.dl.DLConstants;
import com.spike.giantdataanalysis.neo4j.dsl.dl.DLOps;

public class RReflexiveTransitiveClosure extends RoleConstructor {

  private final RoleConstructor r;

  public RReflexiveTransitiveClosure(RoleConstructor r) {
    this.r = r;
  }

  @Override
  public String syntax() {
    StringBuilder sb = new StringBuilder();

    sb.append(DLOps.transitive_reflexive_closure.Name());
    sb.append(DLConstants.BLANK);
    sb.append(r);

    return sb.toString();
  }

}