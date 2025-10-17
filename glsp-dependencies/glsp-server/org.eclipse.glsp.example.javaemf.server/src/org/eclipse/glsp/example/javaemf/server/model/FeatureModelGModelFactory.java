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

   protected void clearAllGraphicalElements() {
      gElements.clear();
      gExpressions.clear();
      gEdges.clear();
      FeatureTreeLayouter.clear();
      featureIdMap.clear();
      emfFeatures.clear();

   }

   @Override
   protected void fillRootElement(final EObject semanticModel, final Diagram notationModel, final GModelRoot newRoot) {
      FeatureModel emfFeatureModel = FeatureModel.class.cast(semanticModel);
      GGraph graph = GGraph.class.cast(newRoot);

      // If no root element is found, then render nothing
      if (emfFeatureModel.getRoots().size() == 0) {
         return;
      }

      clearAllGraphicalElements();

      // Render for now only the first root
      Feature emfRoot = emfFeatureModel.getRoots().get(0);

      // Create the graphical elements without applying layout first
      FeatureSubtreeResult gRoot = createFeatureSubtree(emfRoot, true);

      // Autolayouting the feature tree
      layoutFeatureTree(gRoot);

      // Place constraints box under the rightmost (last) leaf, centered horizontally
      TreeNode rootTree = FeatureTreeLayouter.mapGNodeToTreeNode(gRoot.gNode);
      double marginY = 160; // tweak spacing below the last leaf

      double rootCenterX = rootTree.x + (nodeWidth / 2.0);
      double legendTopY = FeatureTreeLayouter.computeYBelowDeepestRightmostLeaf(rootTree, nodeHeight, marginY);

      createConstraintLegend(emfFeatureModel.getConstraints(),
         GraphUtil.point(gRoot.gNode.getPosition().getX() + 190, legendTopY));

      graph.getChildren().addAll(gElements);
      graph.getChildren().addAll(gEdges);
   }

   // Run to automatically layout the feature nodes nicely
   protected void layoutFeatureTree(final FeatureSubtreeResult gRoot) {
      LayoutContext ctx = new FeatureTreeLayouter.LayoutContext();
      FeatureTreeLayouter.computePositions(FeatureTreeLayouter.mapGNodeToTreeNode(gRoot.gNode), 0, horizontalGap,
         verticalGap, nodeWidth, nodeHeight, ctx);

      // Map computed positions back to the GNodes
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

      for (Feature child : feature.getFeatures()) {

         FeatureSubtreeResult gChild = createFeatureSubtree(child, false);

         // Add child in TreeLayouter
         current_node.addChild(gChild.treeNode);

         // Connect parent and child with an edge
         createEdge(feature, child, gFeature, gChild.gNode);

      }

      // Save all graphical elements and a semantic map
      gElements.add(gFeature);
      featureIdMap.put(gFeature.getId(), feature.getId());
      emfFeatures.add(feature);

      return new FeatureSubtreeResult(gFeature, current_node);

   }

   // Create the graphical representation of a feature
   protected GNode createFeatureNode(final Feature feature, final GPoint gPosition, final Node_type node_type) {

      GNodeBuilder taskNodeBuilder = new GNodeBuilder(DefaultTypes.NODE)
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

   // Create a box with all existing constraints as strings
   // coords is treated as: (centerX, topY) anchor under the rightmost leaf
   protected void createConstraintLegend(final List<Constraint> constraints, final GPoint coords) {

      // Calculate size based on content
      int lines = 1 /* title */ + 1 /* first sep */ + (constraints.size() * 2);
      int lineHeight = 20;
      int padding = 40;
      int legendHeight = Math.max(200, lines * lineHeight + padding);
      int legendWidth = 500; // fixed width; adjust as needed

      int id = 0;

      // Center horizontally on coords.getX(), place top at coords.getY()
      double topLeftX = coords.getX() - (legendWidth / 2.0);
      double topLeftY = coords.getY();

      GNodeBuilder legendBuilder = new GNodeBuilder("node:rectangle")
         .id("cross-tree-contraints")
         .size(GraphUtil.dimension(legendWidth, legendHeight))
         .position(GraphUtil.point(topLeftX, topLeftY))
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
