package org.eclipse.glsp.example.javaemf.server.model;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.glsp.graph.GNode;

public class FeatureTreeLayouter {

   // class datastructure which holds GNode and corresponding Layout Treenode
   protected static class FeatureSubtreeResult {
      public final GNode gNode;
      public final FeatureTreeLayouter.TreeNode treeNode;

      public FeatureSubtreeResult(final GNode gNode, final FeatureTreeLayouter.TreeNode treeNode) {
         this.gNode = gNode;
         this.treeNode = treeNode;
      }
   }

   // Static list to keep track of all TreeNode instances
   public static List<TreeNode> allTreeNodes = new ArrayList<>();

   public static class TreeNode {
      public String id;
      public double x;
      public double y;
      public List<TreeNode> children = new ArrayList<>();

      public TreeNode(final String id) {
         this.id = id;
         // Add this instance to the static list whenever a new node is created
         allTreeNodes.add(this);
      }

      public void addChild(final TreeNode child) {
         children.add(child);
      }

      @Override
      public String toString() {
         return String.format("%s(%.1f, %.1f)", id, x, y);
      }
   }

   public static class LayoutContext {
      public double nextX = 0; // keeps track of horizontal position
   }

   public static void computePositions(final TreeNode node, final double startY,
      double horizontalSpacing, double verticalSpacing, final double nodeWidth, final double nodeHeight,
      final LayoutContext ctx) {
      if (node == null) {
         return;
      }

      node.y = startY;

      horizontalSpacing = Math.max(horizontalSpacing, nodeWidth * 1.2);
      verticalSpacing = Math.max(verticalSpacing, nodeHeight * 1.2);

      // Leaf node
      if (node.children.isEmpty()) {
         node.x = ctx.nextX;
         ctx.nextX += horizontalSpacing;
      }
      // Internal node
      else {
         for (TreeNode child : node.children) {
            computePositions(child, startY + verticalSpacing,
               horizontalSpacing, verticalSpacing, nodeWidth, nodeHeight, ctx);
         }

         double left = node.children.get(0).x;
         double right = node.children.get(node.children.size() - 1).x;
         node.x = (left + right) / 2.0;
      }
   }

   public static GNode mapTreeNodeToGNode(final TreeNode treeNode, final List<GNode> gNodeList) {
      for (GNode g : gNodeList) {
         if (g.getId().equals(treeNode.id)) { // Use equals() for String comparison
            return g;
         }
      }
      return null; // return null if no match is found
   }

   public static TreeNode mapGNodeToTreeNode(final GNode gnode) {
      for (TreeNode t : allTreeNodes) {
         if (t.id.equals(gnode.getId())) { // Use equals() for String comparison
            return t;
         }
      }
      return null; // return null if no match is found
   }

   public static void clear() {
      allTreeNodes.clear();

   }

}
