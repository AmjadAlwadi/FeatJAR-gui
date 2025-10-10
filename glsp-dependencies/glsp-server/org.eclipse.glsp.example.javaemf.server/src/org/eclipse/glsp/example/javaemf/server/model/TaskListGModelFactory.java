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
import org.eclipse.glsp.graph.builder.impl.GLayoutOptions;
import org.eclipse.glsp.graph.builder.impl.GNodeBuilder;
import org.eclipse.glsp.graph.util.GConstants;
import org.eclipse.glsp.graph.util.GraphUtil;
import org.eclipse.glsp.server.emf.model.notation.Diagram;
import org.eclipse.glsp.server.emf.notation.EMFNotationGModelFactory;

import featJAR.Feature;
import featJAR.FeatureModel;

public class TaskListGModelFactory extends EMFNotationGModelFactory {

   List<GNode> gElements = new ArrayList<>();
   List<GEdge> edges = new ArrayList<>();

   GPoint root_center = GraphUtil.point(400, 20);

   double horizontal_gap = 1500;
   double vertical_gap = 100;
   int node_width = 100;
   int node_height = 30;

   enum Node_type {
      ROOT,
      OPTIONAL,
      OBLIGATORY;

      public String cssClass() {
         return switch (this) {
            case ROOT -> "feature-node-root";
            case OPTIONAL -> "feature-node-optional";
            case OBLIGATORY -> "feature-node-obligatory";
         };
      }
   }

   enum Constraint_type {
      ROOT,
      OPTIONAL,
      OBLIGATORY;
   }

   @Override
   protected void fillRootElement(final EObject semanticModel, final Diagram notationModel, final GModelRoot newRoot) {
      FeatureModel emfFeatureModel = FeatureModel.class.cast(semanticModel);
      Feature emfRoot = emfFeatureModel.getRoot();
      GGraph graph = GGraph.class.cast(newRoot);

      edges.clear();
      GNode gRoot = createFeatureSubtree(0, 0, 0, root_center, emfRoot, true);

      graph.getChildren().addAll(gElements);
      graph.getChildren().addAll(edges);

   }

   protected void read(final Feature feature) {
      System.out.println("Feature name: " + feature.getName());
   }

   protected GNode createFeatureSubtree(final int current_horizontal_index, int current_vertical_index,
      final int current_layer_children_number,
      final GPoint parent_pos, final Feature feature,
      final boolean root) {

      GNode gFeature;

      // Calculating the position
      GPoint current_position = GraphUtil.copy(parent_pos);
      double current_layer_horizontal_gap = horizontal_gap;

      current_layer_horizontal_gap = horizontal_gap / (1 << current_vertical_index);

      double horizontal_starting_position = parent_pos.getX() - current_layer_horizontal_gap / 2;

      // set x position for center if there are 0 or 1 children
      if (current_layer_children_number > 2) {
         current_layer_horizontal_gap = current_layer_horizontal_gap / (current_layer_children_number - 2);
      }

      current_layer_horizontal_gap = Math.max(current_layer_horizontal_gap, node_width);

      // root position
      if (!(root || current_layer_children_number == 1)) {
         current_position
            .setX(horizontal_starting_position + current_horizontal_index * current_layer_horizontal_gap);
      }

      // set y position
      current_position.setY(current_vertical_index * vertical_gap);

      // System.out.println("current_vertical_index: " + current_vertical_index + ", Y: " + current_position.getY());
      // System.out.println("current_horizontal_index: " + current_horizontal_index + ", X: " +
      // current_position.getX());

      // Generating the GNode
      if (root) {
         gFeature = createFeatureNode(feature, current_position, Node_type.ROOT);
      } else {

         if (feature.isOptional()) {
            gFeature = createFeatureNode(feature, current_position, Node_type.OPTIONAL);
         } else {
            gFeature = createFeatureNode(feature, current_position, Node_type.OBLIGATORY);
         }

      }

      // Rendering children nodes
      int children_number = feature.getFeatures().size();
      int child_index = 0;
      current_vertical_index++;

      for (Feature child : feature.getFeatures()) {

         GNode gChild = createFeatureSubtree(child_index++, current_vertical_index, children_number,
            current_position, child, false);

         // Connect parent and child with an edge
         createEdge(feature, child, gFeature, gChild);
      }

      gElements.add(gFeature);

      // read(feature);
      // System.out.println("x: " + gFeature.getPosition().getX() + ", Y: " + gFeature.getPosition().getY());
      // System.out.println();

      return gFeature;

   }

   protected GNode createFeatureNode(final Feature feature, final GPoint gPosition, final Node_type node_type) {

      GNodeBuilder taskNodeBuilder = new GNodeBuilder(TaskListModelTypes.ROOT)
         .id(idGenerator.getOrCreateId(feature))
         .addCssClass(node_type.cssClass())
         .position(gPosition)
         .layout(GConstants.Layout.VBOX)
         .layoutOptions(new GLayoutOptions()
            .vAlign(GConstants.VAlign.CENTER).hAlign(GConstants.HAlign.CENTER).minWidth(node_width)
            .minHeight(node_height))
         .add(new GLabelBuilder(DefaultTypes.LABEL).text(feature.getName()).id(feature.getId() + "_label")
            .addCssClass("labels").build());

      applyShapeData(feature, taskNodeBuilder);
      GNode element = taskNodeBuilder.build();
      // element.setLayout(GConstants.HAlign.CENTER);
      return element;
   }

   protected void createEdge(final Feature source, final Feature target, final GNode gSource, final GNode gTarget) {

      // Connect parent and child with an edge
      GEdge edge = new GEdgeBuilder(TaskListModelTypes.LINK)
         .id(source.getId() + "_to_" + target.getId())
         .source(gSource)
         .addCssClass("feature-node-root")
         .target(gTarget)
         .build();

      edges.add(edge);

   }

   protected void createConstraint() {}

}
