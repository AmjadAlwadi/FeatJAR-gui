/********************************************************************************
 * Copyright (c) 2020 EclipseSource and others.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v. 2.0 which is available at
 * https://www.eclipse.org/legal/epl-2.0.
 *
 * This Source Code may also be made available under the following Secondary
 * Licenses when the conditions for such availability set forth in the Eclipse
 * Public License v. 2.0 are satisfied: GNU General Public License, version 2
 * with the GNU Classpath Exception which is available at
 * https://www.gnu.org/software/classpath/license.html.
 *
 * SPDX-License-Identifier: EPL-2.0 OR GPL-2.0 WITH Classpath-exception-2.0
 ********************************************************************************/
package org.eclipse.glsp.example.javaemf.server.handler;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Stream;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.command.IdentityCommand;
import org.eclipse.glsp.example.javaemf.server.FeatureModelTypes;
import org.eclipse.glsp.example.javaemf.server.model.FeatureModelGModelFactory;
import org.eclipse.glsp.graph.GLabel;
import org.eclipse.glsp.graph.GNode;
import org.eclipse.glsp.server.actions.Action;
import org.eclipse.glsp.server.emf.EMFOperationHandler;

import featJAR.Constraint;
import featJAR.Feature;

public class FeatureNodeLabelEditOperationHandler extends EMFOperationHandler<FeatureNodeLabelEditOperation> {

   public static Feature findFeatureById(final String id) {

      if (FeatureModelGModelFactory.emfFeatures == null || id == null) {
         return null;
      }

      for (Feature f : FeatureModelGModelFactory.emfFeatures) {
         if (id.equals(f.getId())) {
            return f;
         }
      }

      return null; // not found
   }

   public static Constraint findConstraintById(final String id) {

      if (FeatureModelGModelFactory.emfConstraints == null || id == null) {
         return null;
      }

      for (Constraint c : FeatureModelGModelFactory.emfConstraints) {
         if (id.equals(c.getId())) {
            return c;
         }
      }

      return null; // not found
   }

   public static GLabel findLabelById(final String id, final List<GLabel> allLabels) {

      if (allLabels == null || id == null) {
         return null;
      }

      for (GLabel l : allLabels) {
         if (id.equals(l.getId())) {
            return l;
         }
      }

      return null; // not found
   }

   @Override
   public Optional<Command> createCommand(final FeatureNodeLabelEditOperation operation) {

      Set<GNode> featureNodes = modelState.getIndex().getAllByClass(GNode.class);

      Stream<GLabel> allLabels = featureNodes.stream()
         .flatMap(n -> n.getChildren().stream())
         .filter(c -> FeatureModelTypes.EDITABLE_LABEL.equals(c.getType()))
         .filter(GLabel.class::isInstance)
         .map(GLabel.class::cast);

      List<GLabel> allLabelsList = allLabels.toList();

      GLabel foundLabel = findLabelById(operation.gLabelId, allLabelsList);

      List<Action> actions = new ArrayList<>();

      if (foundLabel.getParent().getId() == "cross-tree-contraints") {
         Constraint element = findConstraintById(
            FeatureModelGModelFactory.constraintIdMap.get(foundLabel.getId()));
         element.setName(operation.newText);
      } else {
         Feature element = findFeatureById(FeatureModelGModelFactory.featureIdMap.get(foundLabel.getParent().getId()));
         element.setName(operation.newText);

      }

      return Optional.of(new IdentityCommand());

   }

}
