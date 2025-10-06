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

import org.eclipse.emf.ecore.EObject;
import org.eclipse.glsp.graph.GGraph;
import org.eclipse.glsp.graph.GModelRoot;
import org.eclipse.glsp.server.emf.model.notation.Diagram;
import org.eclipse.glsp.server.emf.notation.EMFNotationGModelFactory;

import featJAR.Feature;

public class TaskListGModelFactory extends EMFNotationGModelFactory {

   @Override
   protected void fillRootElement(final EObject semanticModel, final Diagram notationModel, final GModelRoot newRoot) {
      Feature feature = Feature.class.cast(semanticModel);
      GGraph graph = GGraph.class.cast(newRoot);

      // if (notationModel.getSemanticElement() != null
      // && notationModel.getSemanticElement().getResolvedSemanticElement() != null) {
      // taskList.getTasks().stream()
      // .map(this::createFeatureNode)
      // .forEachOrdered(graph.getChildren()::add);
      // }

      String featureName = feature.getName();
      // feature.isOptional();
      System.out.println(featureName);

   }

   // protected GNode createOptionalFeatureNode(final Feature feature) {
   //
   // GNodeBuilder taskNodeBuilder = new GNodeBuilder(TaskListModelTypes.TASK)
   // .id(idGenerator.getOrCreateId(task))
   // .addCssClass("feature-node-root")
   // .add(new GLabelBuilder(DefaultTypes.LABEL).text(task.getName()).id(task.getId() + "_label").build())
   // .layout(GConstants.Layout.HBOX, Map.of(GLayoutOptions.KEY_PADDING_LEFT, 5));
   //
   // applyShapeData(task, taskNodeBuilder);
   // return taskNodeBuilder.build();
   // }
   //
   // protected GNode createObligatoryFeatureNode(final Feature feature) {
   //
   // GNodeBuilder taskNodeBuilder = new GNodeBuilder(TaskListModelTypes.TASK)
   // .id(idGenerator.getOrCreateId(task))
   // .addCssClass("feature-node-root")
   // .add(new GLabelBuilder(DefaultTypes.LABEL).text(task.getName()).id(task.getId() + "_label").build())
   // .layout(GConstants.Layout.HBOX, Map.of(GLayoutOptions.KEY_PADDING_LEFT, 5));
   //
   // applyShapeData(task, taskNodeBuilder);
   // return taskNodeBuilder.build();
   // }

}
