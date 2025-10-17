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
package org.eclipse.glsp.example.javaemf.server.handler;

import java.util.List;

import org.eclipse.glsp.example.javaemf.server.model.FeatureModelGModelFactory;
import org.eclipse.glsp.server.actions.Action;
import org.eclipse.glsp.server.actions.ActionHandler;
import org.eclipse.glsp.server.actions.SelectAction;
import org.eclipse.glsp.server.emf.EMFIdGenerator;
import org.eclipse.glsp.server.emf.notation.EMFNotationModelState;

import com.google.inject.Inject;

import featJAR.Feature;

public class SelectionActionHandler implements ActionHandler {

   @Inject
   protected EMFNotationModelState modelState;

   @Inject
   protected EMFIdGenerator idGenerator;

   public static Feature findFeatureById(final String id) {

      if (FeatureModelGModelFactory.emfFeatures == null || id == null) {
         return null;
      }

      for (Feature f : FeatureModelGModelFactory.emfFeatures) {
         if (id.equals(f.getId())) {
            return f;
         }
      }

      return null; // not found
   }

   @Override
   public boolean handles(final Action action) {
      return action instanceof SelectAction;
   }

   @Override
   public List<Class<? extends Action>> getHandledActionTypes() { return List.of(SelectAction.class); }

   @Override
   public List<Action> execute(final Action action) {

      SelectAction selectAction = (SelectAction) action;

      // Get the selected element IDs from the action
      List<String> selectedIds = selectAction.getSelectedElementsIDs();

      if (selectedIds.size() > 0) {
         Feature element = findFeatureById(FeatureModelGModelFactory.featureIdMap.get(selectedIds.get(0)));
         modelState.setProperty("currentSelection", element);
      }

      return List.of();
   }

}
