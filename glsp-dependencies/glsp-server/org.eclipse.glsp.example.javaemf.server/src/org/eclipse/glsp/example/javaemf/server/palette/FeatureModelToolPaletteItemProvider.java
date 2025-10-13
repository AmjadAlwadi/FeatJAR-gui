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
package org.eclipse.glsp.example.javaemf.server.palette;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.eclipse.glsp.example.javaemf.server.FeatureModelTypes;
import org.eclipse.glsp.server.actions.TriggerNodeCreationAction;
import org.eclipse.glsp.server.features.toolpalette.PaletteItem;
import org.eclipse.glsp.server.features.toolpalette.ToolPaletteItemProvider;

public class FeatureModelToolPaletteItemProvider implements ToolPaletteItemProvider {

   @Override
   public List<PaletteItem> getItems(final Map<String, String> args) {
      return combinePalletes();
   }

   private PaletteItem nodeCreation() {
      PaletteItem createOptionalFeature = node(FeatureModelTypes.OPTIONAL_FEATURE, "Optional Feature");
      PaletteItem createObligatoryFeature = node(FeatureModelTypes.OBLIGATORY_FEATURE, "Obligatory Feature");
      PaletteItem createConstraint = node(FeatureModelTypes.CONSTRAINT, "Constraint");
      List<PaletteItem> nodes = new ArrayList<>();
      nodes.add(createOptionalFeature);
      nodes.add(createObligatoryFeature);
      nodes.add(createConstraint);
      return PaletteItem.createPaletteGroup("nodes", "Add Features", nodes, "symbol-property");
   }

   private PaletteItem informationLegend() {

      // info about node types
      PaletteItem root_node = new PaletteItem("root_node", "🟩 Root");
      // root_node.setIcon("root_node_icon");
      root_node.setSortString("1");

      PaletteItem obligatory_node = new PaletteItem("obligatory_node", "🟥 Obligatory");
      // obligatory_node.setIcon("obligatory_node_icon");
      obligatory_node.setSortString("2");

      PaletteItem optional_node = new PaletteItem("optional_node", "🟪 Optional");
      // optional_node.setIcon("optional_node_icon");
      optional_node.setSortString("3");

      // Info about group types

      PaletteItem true_group = new PaletteItem("TRUE", "True Group");
      // true_group.setIcon("true_group_icon");
      true_group.setIcon("loading");
      true_group.setSortString("4");

      PaletteItem or_group = new PaletteItem("OR", "Or Group");
      // or_group.setIcon("or_group_icon");
      or_group.setIcon("triangle-up");
      or_group.setSortString("5");

      PaletteItem xor_group = new PaletteItem("XOR", "Xor Group");
      // xor_group.setIcon("xor_group_icon");
      xor_group.setIcon("debug-breakpoint-log-unverified");
      xor_group.setSortString("6");

      PaletteItem speical_group = new PaletteItem("SPECIAL", "Special Group");
      // speical_group.setIcon("special_group_icon");
      speical_group.setIcon("loading");
      speical_group.setSortString("7");

      List<PaletteItem> legend = new ArrayList<>();

      legend.add(xor_group);
      legend.add(true_group);
      legend.add(or_group);
      legend.add(speical_group);
      legend.add(root_node);
      legend.add(obligatory_node);
      legend.add(optional_node);

      return PaletteItem.createPaletteGroup("information", "Information", legend, "book");
   }

   private PaletteItem node(final String elementTypeId, final String label) {
      return new PaletteItem(elementTypeId, label, new TriggerNodeCreationAction(elementTypeId));
   }

   private List<PaletteItem> combinePalletes() {
      List<PaletteItem> palletes = new ArrayList<>();

      palletes.add(nodeCreation());
      palletes.add(informationLegend());

      return palletes;
   }

}
