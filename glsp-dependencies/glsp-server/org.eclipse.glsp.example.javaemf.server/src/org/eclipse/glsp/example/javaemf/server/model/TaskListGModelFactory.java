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

import org.eclipse.emf.ecore.EObject;
import org.eclipse.glsp.example.javaemf.server.TaskListModelTypes;
import org.eclipse.glsp.graph.DefaultTypes;
import org.eclipse.glsp.graph.GEdge;
import org.eclipse.glsp.graph.GGraph;
import org.eclipse.glsp.graph.GModelRoot;
import org.eclipse.glsp.graph.GNode;
import org.eclipse.glsp.graph.GPoint;
import org.eclipse.glsp.graph.builder.impl.GEdgeBuilder;
import org.eclipse.glsp.graph.builder.impl.GLabelBuilder;
import org.eclipse.glsp.graph.builder.impl.GNodeBuilder;
import org.eclipse.glsp.graph.util.GConstants;
import org.eclipse.glsp.graph.util.GraphUtil;
import org.eclipse.glsp.server.emf.model.notation.Diagram;
import org.eclipse.glsp.server.emf.notation.EMFNotationGModelFactory;

import featJAR.Feature;
import featJAR.FeatureModel;

public class TaskListGModelFactory extends EMFNotationGModelFactory {

   public static void convertFutureToEmf() {

   }

   private final List<GEdge> edges = new ArrayList<>();
   int padding_y = 100;
   List<GNode> gElements = new ArrayList<>();

   GPoint root_center = GraphUtil.point(300, 20);

   @Override
   protected void fillRootElement(final EObject semanticModel, final Diagram notationModel, final GModelRoot newRoot) {
      FeatureModel emfFeatureModel = FeatureModel.class.cast(semanticModel);
      Feature emfRoot = emfFeatureModel.getRoot();
      GGraph graph = GGraph.class.cast(newRoot);

      edges.clear();
      GNode gRoot = createFeatureNode(0, 0, emfRoot.getFeatures().size(), root_center, emfRoot, true);

      graph.getChildren().addAll(gElements);
      graph.getChildren().addAll(edges);

   }

   protected void read(final Feature feature) {
      System.out.println("Feature name: " + feature.getName());
   }

   protected GNode createFeatureNode(final int current_horizontal_index, int current_vertical_index,
      final int current_layer_children_number,
      final GPoint parent_pos, final Feature feature,
      final boolean root) {

      GNode gFeature;

      // Calculating the position
      int horizontal_distance = (int) ((parent_pos.getX() * 2) / current_layer_children_number - 1);
      GPoint current_position = GraphUtil.point(root_center.getX(), root_center.getY());

      if (root) {
         current_position = parent_pos;
      }

      if (current_layer_children_number < 2) {
         current_position.setX(parent_pos.getX());
      }

      else {
         current_position
            .setX(parent_pos.getX() - (parent_pos.getX() / 2) + current_horizontal_index * horizontal_distance);
      }

      current_position.setY(current_vertical_index * padding_y);

      // System.out.println("current_vertical_index: " + current_vertical_index + ", Y: " + current_position.getY());
      // System.out.println("current_horizontal_index: " + current_horizontal_index + ", X: " +
      // current_position.getX());

      // Generating the GNode
      if (root) {
         gFeature = createRootNode(feature, current_position);
      } else {

         if (feature.isOptional()) {
            gFeature = createOptionalFeatureNode(feature, current_position);
         } else {
            gFeature = createObligatoryFeatureNode(feature, current_position);
         }

      }

      // Rendering children nodes
      int children_number = feature.getFeatures().size();
      int child_index = 0;
      current_vertical_index++;

      for (Feature child : feature.getFeatures()) {

         GNode childNode = createFeatureNode(child_index++, current_vertical_index, children_number,
            current_position, child, false);

         // Connect parent and child with an edge
         GEdge edge = new GEdgeBuilder(TaskListModelTypes.LINK)
            .id(feature.getId() + "_to_" + child.getId())
            .source(gFeature)
            .addCssClass("feature-node-root")
            // .addRoutingPoint(current_position)
            // .addRoutingPoint(childNode.getPosition())
            .target(childNode)
            .build();

         // gFeature.getChildren().add(childNode);
         edges.add(edge);
      }

      gElements.add(gFeature);

      // System.out.println("x: " + gFeature.getPosition().getX() + ", Y: " + gFeature.getPosition().getY());
      System.out.println();

      return gFeature;

   }

   protected GNode createOptionalFeatureNode(final Feature feature, final GPoint gPosition) {

      GNodeBuilder taskNodeBuilder = new GNodeBuilder(TaskListModelTypes.OPTIONAL_FEATURE)
         .id(idGenerator.getOrCreateId(feature))
         .addCssClass("feature-node-optional")
         .position(gPosition)
         .layout(GConstants.Layout.FREEFORM)
         .add(new GLabelBuilder(DefaultTypes.LABEL).text(feature.getName()).id(feature.getId() + "_label")
            .addCssClass("labels").build());

      applyShapeData(feature, taskNodeBuilder);
      GNode element = taskNodeBuilder.build();
      return element;
   }

   protected GNode createObligatoryFeatureNode(final Feature feature, final GPoint gPosition) {

      GNodeBuilder taskNodeBuilder = new GNodeBuilder(TaskListModelTypes.OBLIGATORY_FEATURE)
         .id(idGenerator.getOrCreateId(feature))
         .addCssClass("feature-node-obligatory")
         .position(gPosition)
         .layout(GConstants.Layout.FREEFORM)
         .add(new GLabelBuilder(DefaultTypes.LABEL).text(feature.getName()).id(feature.getId() + "_label")
            .addCssClass("labels").build());

      applyShapeData(feature, taskNodeBuilder);
      GNode element = taskNodeBuilder.build();
      return element;
   }

   protected GNode createRootNode(final Feature feature, final GPoint gPosition) {

      GNodeBuilder taskNodeBuilder = new GNodeBuilder(TaskListModelTypes.ROOT)
         .id(idGenerator.getOrCreateId(feature))
         .addCssClass("feature-node-root")
         .position(gPosition)
         .layout(GConstants.Layout.FREEFORM)
         .add(new GLabelBuilder(DefaultTypes.LABEL).text(feature.getName()).id(feature.getId() + "_label")
            .addCssClass("labels").build());

      applyShapeData(feature, taskNodeBuilder);
      GNode element = taskNodeBuilder.build();
      return element;
   }

}
