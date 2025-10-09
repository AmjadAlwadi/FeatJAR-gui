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

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.glsp.example.javaemf.server.TaskListModelTypes;
import org.eclipse.glsp.graph.DefaultTypes;
import org.eclipse.glsp.graph.GEdge;
import org.eclipse.glsp.graph.GGraph;
import org.eclipse.glsp.graph.GModelRoot;
import org.eclipse.glsp.graph.GNode;
import org.eclipse.glsp.graph.builder.impl.GEdgeBuilder;
import org.eclipse.glsp.graph.builder.impl.GGraphBuilder;
import org.eclipse.glsp.graph.builder.impl.GLabelBuilder;
import org.eclipse.glsp.graph.builder.impl.GLayoutOptions;
import org.eclipse.glsp.graph.builder.impl.GNodeBuilder;
import org.eclipse.glsp.graph.util.GConstants;
import org.eclipse.glsp.graph.util.GConstants.Layout;
import org.eclipse.glsp.graph.util.GraphUtil;
import org.eclipse.glsp.server.emf.model.notation.Diagram;
import org.eclipse.glsp.server.emf.notation.EMFNotationGModelFactory;

import featJAR.Feature;
import featJAR.FeatureModel;

public class TaskListGModelFactory extends EMFNotationGModelFactory {

   public static void convertFutureToEmf() {

   }

   private final List<GEdge> edges = new ArrayList<>();
   int pos_x = 0;

   @Override
   protected void fillRootElement(final EObject semanticModel, final Diagram notationModel, final GModelRoot newRoot) {
      FeatureModel emfFeatureModel = FeatureModel.class.cast(semanticModel);
      Feature emfRoot = emfFeatureModel.getRoot();

      edges.clear();
      GNode gRoot = createRootNode(emfRoot);

      for (Feature child : emfRoot.getFeatures()) {
         gRoot.getChildren().add(createFeatureNode(child));
      }

      GGraph newModel = new GGraphBuilder()
         .id("entity-graph")
         .add(gRoot)
         .addAll(edges)
         .build();

      modelState.updateRoot(newModel);

   }

   protected void read(final Feature feature) {

      System.out.println("Feature name: " + feature.getName());

   }

   protected GNode createFeatureNode(final Feature feature) {

      GNode parent;

      if (feature.isOptional()) {
         parent = createOptionalFeatureNode(feature);
      } else {
         parent = createObligatoryFeatureNode(feature);
      }

      for (Feature child : feature.getFeatures()) {

         GNode childNode = createFeatureNode(child);

         // Connect parent and child with an edge
         GEdge edge = new GEdgeBuilder(TaskListModelTypes.LINK)
            .id(feature.getId() + "_to_" + child.getId())
            .source(parent)
            .trace("asdfdsaf")
            .target(childNode)
            .build();

         parent.getChildren().add(childNode);
         // edges.add(edge);
      }

      parent.setLayout(Layout.FREEFORM);
      pos_x += 50;
      parent.setPosition(GraphUtil.point(pos_x, 10));

      return parent;

   }

   protected GNode createOptionalFeatureNode(final Feature feature) {

      GNodeBuilder taskNodeBuilder = new GNodeBuilder(TaskListModelTypes.OPTIONAL_FEATURE)
         .id(idGenerator.getOrCreateId(feature))
         .addCssClass("feature-node-optional")
         .add(new GLabelBuilder(DefaultTypes.LABEL).text(feature.getName()).id(feature.getId() + "_label").build())
         .layout(GConstants.Layout.HBOX, Map.of(GLayoutOptions.KEY_PADDING_LEFT, 5));

      applyShapeData(feature, taskNodeBuilder);
      return taskNodeBuilder.build();
   }

   protected GNode createObligatoryFeatureNode(final Feature feature) {

      GNodeBuilder taskNodeBuilder = new GNodeBuilder(TaskListModelTypes.OBLIGATORY_FEATURE)
         .id(idGenerator.getOrCreateId(feature))
         .addCssClass("feature-node-obligatory")
         .add(new GLabelBuilder(DefaultTypes.LABEL).text(feature.getName()).id(feature.getId() + "_label").build())
         .layout(GConstants.Layout.HBOX, Map.of(GLayoutOptions.KEY_PADDING_LEFT, 5));

      applyShapeData(feature, taskNodeBuilder);
      return taskNodeBuilder.build();
   }

   protected GNode createRootNode(final Feature feature) {

      GNodeBuilder taskNodeBuilder = new GNodeBuilder(TaskListModelTypes.ROOT)
         .id(idGenerator.getOrCreateId(feature))
         .addCssClass("feature-node-root")
         .add(new GLabelBuilder(DefaultTypes.LABEL).text(feature.getName()).id(feature.getId() + "_label").build())
         .layout(GConstants.Layout.HBOX, Map.of(GLayoutOptions.KEY_PADDING_LEFT, 5));

      applyShapeData(feature, taskNodeBuilder);
      return taskNodeBuilder.build();
   }

}
