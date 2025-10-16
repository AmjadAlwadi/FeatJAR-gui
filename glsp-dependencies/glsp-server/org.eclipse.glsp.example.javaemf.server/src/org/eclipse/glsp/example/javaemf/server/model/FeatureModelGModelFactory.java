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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.glsp.example.javaemf.server.FeatureModelTypes;
import org.eclipse.glsp.example.javaemf.server.model.FeatureTreeLayouter.FeatureSubtreeResult;
import org.eclipse.glsp.example.javaemf.server.model.FeatureTreeLayouter.LayoutContext;
import org.eclipse.glsp.example.javaemf.server.model.FeatureTreeLayouter.TreeNode;
import org.eclipse.glsp.graph.DefaultTypes;
import org.eclipse.glsp.graph.GEdge;
import org.eclipse.glsp.graph.GGraph;
import org.eclipse.glsp.graph.GLabel;
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

import featJAR.Constraint;
import featJAR.Feature;
import featJAR.FeatureModel;
import featJAR.Group;

public class FeatureModelGModelFactory extends EMFNotationGModelFactory {

   // All graphical elements (GModel Elements)
   public static Map<String, String> featureIdMap = new HashMap<>();
   public static List<Feature> emfFeatures = new ArrayList<>();
   List<GNode> gElements = new ArrayList<>();
   List<GEdge> gEdges = new ArrayList<>();
   List<String> gExpressions = new ArrayList<>();

   // Layout information
   double horizontalGap = 150;
   double verticalGap = 100;
   int nodeWidth = 100;
   int nodeHeight = 30;

   // GElement Type
   enum Node_type {
      ROOT,
      OPTIONAL,
      OBLIGATORY,
      EDGE,
      CONSTRAINT_LABEL,
      CONSTRAINT_TITLE,
      LABEL;

      public String cssClass() {
         return switch (this) {
            case ROOT -> "feature-node-root";
            case OPTIONAL -> "feature-node-optional";
            case OBLIGATORY -> "feature-node-obligatory";
            case EDGE -> "feature-node-root";
            case LABEL -> "labels";
            case CONSTRAINT_LABEL -> "constraint_label";
            case CONSTRAINT_TITLE -> "constraints_title";
         };
      }
   }

   enum Group_type {
      OR,
      XOR,
      SPECIAL,
      TRUE,
   }

   public static void render() {

   }

   @Override
   protected void fillRootElement(final EObject semanticModel, final Diagram notationModel, final GModelRoot newRoot) {
      FeatureModel emfFeatureModel = FeatureModel.class.cast(semanticModel);
      GGraph graph = GGraph.class.cast(newRoot);

      if (emfFeatureModel.getRoot().size() == 0) {
         return;
      }

      Feature emfRoot = emfFeatureModel.getRoot().get(0);

      gElements.clear();
      gExpressions.clear();
      gEdges.clear();
      featureIdMap.clear();
      emfFeatures.clear();
      FeatureTreeLayouter.clear();

      // Create the graphical elements without applying layout first
      FeatureSubtreeResult gRoot = createFeatureSubtree(emfRoot, true);

      // Autolayouting the feature tree
      layoutFeatureTree(gRoot);

      createConstraintLegend(emfFeatureModel.getConstraints(),
         GraphUtil.point(gRoot.gNode.getPosition().getX() - 200, -200));

      graph.getChildren().addAll(gElements);
      graph.getChildren().addAll(gEdges);

   }

   // Run to automatically layout the feature nodes nicely
   protected void layoutFeatureTree(final FeatureSubtreeResult gRoot) {

      LayoutContext ctx = new FeatureTreeLayouter.LayoutContext();
      FeatureTreeLayouter.computePositions(FeatureTreeLayouter.mapGNodeToTreeNode(gRoot.gNode), 0, horizontalGap,
         verticalGap, nodeWidth, nodeHeight,
         ctx);

      // Get the layout information from the TreeNodes, map them to our GNodes by id
      // and map there positions to our GNodes
      for (TreeNode node : FeatureTreeLayouter.allTreeNodes) {
         FeatureTreeLayouter.mapTreeNodeToGNode(node, gElements).setPosition(GraphUtil.point(node.x, node.y));
      }
   }

   // Create Feature Subtree without Autolayouting
   protected FeatureSubtreeResult createFeatureSubtree(final Feature feature, final boolean isRoot) {

      GNode gFeature;

      GPoint current_position = GraphUtil.point(0, 0);

      // Creating the GNode
      if (isRoot) {
         gFeature = createFeatureNode(feature, current_position, Node_type.ROOT);
      } else {

         if (feature.isOptional()) {
            gFeature = createFeatureNode(feature, current_position, Node_type.OPTIONAL);
         } else {
            gFeature = createFeatureNode(feature, current_position, Node_type.OBLIGATORY);
         }

      }

      // For Autolayouting
      TreeNode current_node = new TreeNode(gFeature.getId());

      // Rendering children nodes recursively
      // int children_number = feature.getParentOfGroup().;
      EList<Group> g = feature.getGroups();

      for (Group Flist : g) {
         for (Feature child : Flist.getFeatures()) {

            FeatureSubtreeResult gChild = createFeatureSubtree(child, false);

            // Add child in TreeLayouter
            current_node.addChild(gChild.treeNode);
            // Connect parent and child with an edge
            createEdge(feature, child, gFeature, gChild.gNode);
         }
      }

      gElements.add(gFeature);

      featureIdMap.put(gFeature.getId(), feature.getId());
      emfFeatures.add(feature);

      return new FeatureSubtreeResult(gFeature, current_node);

   }

   // Create the graphical representation of a feature
   protected GNode createFeatureNode(final Feature feature, final GPoint gPosition, final Node_type node_type) {

      GNodeBuilder taskNodeBuilder = new GNodeBuilder(FeatureModelTypes.ROOT)
         .id(idGenerator.getOrCreateId(feature))
         .addCssClass(node_type.cssClass())
         .position(gPosition)
         .layout(GConstants.Layout.VBOX)
         .layoutOptions(new GLayoutOptions()
            .vAlign(GConstants.VAlign.CENTER).hAlign(GConstants.HAlign.CENTER).minWidth(nodeWidth)
            .minHeight(nodeHeight))
         .add(new GLabelBuilder(DefaultTypes.LABEL).text(feature.getName()).id(feature.getId() + "_label")
            .addCssClass(Node_type.LABEL.cssClass()).build());

      applyShapeData(feature, taskNodeBuilder);
      GNode element = taskNodeBuilder.build();

      return element;
   }

   // Create the graphical representation of the feature relation
   protected void createEdge(final Feature source, final Feature target, final GNode gSource, final GNode gTarget) {

      // Connect parent and child with an edge
      GEdge edge = new GEdgeBuilder(FeatureModelTypes.LINK)
         .id(source.getId() + "_to_" + target.getId())
         .source(gSource)
         .addCssClass(Node_type.EDGE.cssClass())
         .target(gTarget)
         .build();

      gEdges.add(edge);

   }

   // // Create the graphical representation of the constraint
   // protected void createGroupConstraint(final Group_type type, final Feature parent_feature,
   // final GNode parent_node) {
   //
   // int shift = 30;
   // GPoint current_position = GraphUtil.copy(parent_node.getPosition());
   // current_position.setY(current_position.getY() + shift);
   //
   // GNode constraintNode = new GNodeBuilder("node:circle")
   // .id(idGenerator.getOrCreateId(parent_feature) + "_contraint")
   // .addCssClass(Node_type.ROOT.cssClass())
   // .position(
   // GraphUtil.point(current_position.getX() + node_width / 2, current_position.getY() - 15))
   // .size(GraphUtil.dimension(30, 30))
   // .build();
   //
   // }

   // Create a box with all existing constraints as strings
   protected void createConstraintLegend(final List<Constraint> constraints, final GPoint coords) {

      int dynamic_shift = 20 * constraints.size();
      int static_shift = 100;
      int id = 0;

      GPoint legend_position = GraphUtil.copy(coords);
      legend_position.setY(legend_position.getY() - dynamic_shift - static_shift);
      GNodeBuilder legendBuilder = new GNodeBuilder("node:rectangle")
         .id("cross-tree-contraints")
         .size(GraphUtil.dimension(500, 800))
         .position(legend_position)
         .layout(GConstants.Layout.VBOX)
         .addCssClass(Node_type.ROOT.cssClass());

      // Add title
      legendBuilder.add(createConstraintsTitle("Constraints"));
      legendBuilder.add(createLineLabel(id++, "."));

      // Add constraint strings
      for (Constraint constraint : constraints) {
         legendBuilder.add(createConstraintLabel(constraint.getName(), id++));
         legendBuilder.add(createLineLabel(id++, "-"));
      }

      GNode legend = legendBuilder.build();
      legend.setPosition(GraphUtil.point(legend.getPosition().getX() - legend.getSize().getWidth() + nodeWidth,
         legend.getPosition().getY()));

      gElements.add(legend);

   }

   // Create a label to draw a separating line using a specific symbol
   public GLabel createLineLabel(final int id, final String separator) {

      return new GLabelBuilder(DefaultTypes.LABEL)
         .id("constraints-label-line_" + id)
         .text(separator.repeat(30))
         .addCssClass(Node_type.OPTIONAL.cssClass())
         .addArgument("wrap", true)
         .build();

   }

   // Create the label for the constraints
   public GLabel createConstraintsTitle(final String label) {

      return new GLabelBuilder(DefaultTypes.LABEL)
         .id("constraints_title")
         .text(label)
         .addCssClass(Node_type.OPTIONAL.cssClass())
         .addArgument("wrap", true)
         .build();

   }

   // Create a label as GElement with a specific id and string value
   public GLabel createConstraintLabel(final String label, final int id) {

      return new GLabelBuilder(DefaultTypes.LABEL)
         .id("constraints-label_" + id)
         .text(label)
         .addCssClass(Node_type.LABEL.cssClass())
         .addArgument("wrap", true)
         .build();

   }

}
