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
package org.eclipse.glsp.example.javaemf.server.model;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.eclipse.glsp.graph.GModelElement;
import org.eclipse.glsp.graph.GNode;
import org.eclipse.glsp.graph.util.GraphUtil;

public class FeatureTreeLayouter {

   private final double nodeWidth;
   private final double nodeHeight;
   private final double hSpacing;
   private final double vSpacing;

   // store per-element computed subtree width
   private final Map<GModelElement, Double> subtreeWidths = new HashMap<>();

   public FeatureTreeLayouter(final double nodeWidth, final double nodeHeight, final double hSpacing,
      final double vSpacing) {
      this.nodeWidth = nodeWidth;
      this.nodeHeight = nodeHeight;
      this.hSpacing = hSpacing;
      this.vSpacing = vSpacing;
   }

   /**
    * Public entry point. Expects a GNode root.
    */
   public void layout(final GNode root) {
      computeSubtreeWidth(root);
      assignPositions(root, 0, 0);
   }

   /**
    * Compute subtree width for the given model element (only GNode children are considered).
    * Stores result in subtreeWidths map and returns the value.
    */
   private double computeSubtreeWidth(final GModelElement element) {
      List<GNode> children = getChildNodes(element);

      if (children.isEmpty()) {
         subtreeWidths.put(element, nodeWidth);
         return nodeWidth;
      }

      double totalWidth = 0;
      for (GNode child : children) {
         totalWidth += computeSubtreeWidth(child) + hSpacing;
      }
      totalWidth -= hSpacing; // remove trailing space

      double width = Math.max(totalWidth, nodeWidth);
      subtreeWidths.put(element, width);
      return width;
   }

   /**
    * Assign positions recursively. This version takes GNode because we set positions on GNode.
    *
    * @param node The node to position
    * @param left The left X coordinate of this node's allocated area/subtree
    * @param top  The Y coordinate for this node
    */
   private void assignPositions(final GNode node, final double left, final double top) {
      Double subtreeWidthObj = subtreeWidths.get(node);
      if (subtreeWidthObj == null) {
         // Fallback: compute on the fly if missing
         computeSubtreeWidth(node);
         subtreeWidthObj = subtreeWidths.get(node);
      }
      double subtreeWidth = subtreeWidthObj;

      List<GNode> children = getChildNodes(node);
      if (children.isEmpty()) {
         // place leaf centered in its allocated subtree
         node.setPosition(GraphUtil.point(left + subtreeWidth / 2.0, top));
         return;
      }

      // Layout children left-to-right inside this node's allocated width
      double childX = left;
      for (GNode child : children) {
         double childWidth = subtreeWidths.getOrDefault(child, nodeWidth);
         assignPositions(child, childX, top + nodeHeight + vSpacing);
         childX += childWidth + hSpacing;
      }

      // After children are positioned, center this node over its children.
      // We use children's x positions to compute center.
      double firstX = children.get(0).getPosition().getX();
      double lastX = children.get(children.size() - 1).getPosition().getX();
      node.setPosition(GraphUtil.point((firstX + lastX) / 2.0, top));
   }

   /**
    * Helper: return only the children which are GNode instances (filter+cast).
    */
   private List<GNode> getChildNodes(final GModelElement element) {
      if (element.getChildren() == null || element.getChildren().isEmpty()) {
         return Collections.emptyList();
      }
      return element.getChildren().stream()
         .filter(child -> child instanceof GNode)
         .map(child -> (GNode) child)
         .collect(Collectors.toList());
   }
}
