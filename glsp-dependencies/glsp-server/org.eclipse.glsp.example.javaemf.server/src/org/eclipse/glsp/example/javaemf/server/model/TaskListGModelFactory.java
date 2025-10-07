/********************************************************************************
 * Copyright (c) 2022 EclipseSource and others.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v. 2.0 which is available at
 * https://www.eclipse.org/legal/epl-2.0.
 *
 * This Source Code may also be made available under the following Secondary
 * Licenses when the conditions for such availability set forth in the Eclipse
 * Public License v. 2.0 are satisfied:
 * -- GNU General Public License, version 2 with the GNU Classpath Exception
 * which is available at https://www.gnu.org/software/classpath/license.html
 * -- MIT License which is available at https://opensource.org/license/mit.
 *
 * SPDX-License-Identifier: EPL-2.0 OR GPL-2.0 WITH Classpath-exception-2.0 OR MIT
 ********************************************************************************/
package org.eclipse.glsp.example.javaemf.server.model;

import java.util.Map;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.glsp.example.javaemf.server.TaskListModelTypes;
import org.eclipse.glsp.graph.DefaultTypes;
import org.eclipse.glsp.graph.GGraph;
import org.eclipse.glsp.graph.GModelRoot;
import org.eclipse.glsp.graph.GNode;
import org.eclipse.glsp.graph.builder.impl.GLabelBuilder;
import org.eclipse.glsp.graph.builder.impl.GLayoutOptions;
import org.eclipse.glsp.graph.builder.impl.GNodeBuilder;
import org.eclipse.glsp.graph.util.GConstants;
import org.eclipse.glsp.server.emf.model.notation.Diagram;
import org.eclipse.glsp.server.emf.notation.EMFNotationGModelFactory;

import featJAR.Feature;

public class TaskListGModelFactory extends EMFNotationGModelFactory {

   @Override
   protected void fillRootElement(final EObject semanticModel, final Diagram notationModel, final GModelRoot newRoot) {
      Feature feature = Feature.class.cast(semanticModel);
      GGraph graph = GGraph.class.cast(newRoot);

      if (notationModel.getSemanticElement() != null
         && notationModel.getSemanticElement().getResolvedSemanticElement() != null) {
         feature.getChildren().stream().map(this::createFeatureNode)
            .forEachOrdered(graph.getChildren()::add);
      }

      // Feature f1 = Feature.class.cast(semanticModel);
      // f1.setName("Amjad");
      // f1.setOptional(false);
      // f1.setRoot(true);
      // f1.setID(10);
      //
      // createFeatureNode(f1);

   }

   protected GNode createFeatureNode(final Feature feature) {

      if (feature.isOptional()) {
         return createOptionalFeatureNode(feature);
      }
      return createObligatoryFeatureNode(feature);

   }

   protected GNode createOptionalFeatureNode(final Feature feature) {

      GNodeBuilder taskNodeBuilder = new GNodeBuilder(TaskListModelTypes.OPTIONAL_FEATURE)
         .id(idGenerator.getOrCreateId(feature))
         // .addCssClass("feature-node-root")
         .add(new GLabelBuilder(DefaultTypes.LABEL).text(feature.getName()).id(feature.getID() + "_label").build())
         .layout(GConstants.Layout.HBOX, Map.of(GLayoutOptions.KEY_PADDING_LEFT, 5));

      applyShapeData(feature, taskNodeBuilder);
      return taskNodeBuilder.build();
   }

   protected GNode createObligatoryFeatureNode(final Feature feature) {

      GNodeBuilder taskNodeBuilder = new GNodeBuilder(TaskListModelTypes.OBLIGATORY_FEATURE)
         .id(idGenerator.getOrCreateId(feature))
         // .addCssClass("feature-node-root")
         .add(new GLabelBuilder(DefaultTypes.LABEL).text(feature.getName()).id(feature.getID() + "_label").build())
         .layout(GConstants.Layout.HBOX, Map.of(GLayoutOptions.KEY_PADDING_LEFT, 5));

      applyShapeData(feature, taskNodeBuilder);
      return taskNodeBuilder.build();
   }

}
