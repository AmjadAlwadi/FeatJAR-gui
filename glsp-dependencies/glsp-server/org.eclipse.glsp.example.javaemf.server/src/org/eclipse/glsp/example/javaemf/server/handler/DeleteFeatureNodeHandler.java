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
import java.util.Optional;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.command.RemoveCommand;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.glsp.example.javaemf.server.model.FeatureModelGModelFactory;
import org.eclipse.glsp.server.emf.AbstractEMFOperationHandler;
import org.eclipse.glsp.server.emf.notation.EMFNotationModelState;
import org.eclipse.glsp.server.operations.DeleteOperation;

import com.google.inject.Inject;

import featJAR.Feature;

public class DeleteFeatureNodeHandler extends AbstractEMFOperationHandler<DeleteOperation> {

   @Inject
   protected EMFNotationModelState modelState;

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
   public Optional<Command> createCommand(final DeleteOperation operation) {

      List<String> gModelIds = operation.getElementIds();

      CompoundCommand command = new CompoundCommand();

      for (String id : gModelIds) {
         command.append(delete(id));
      }

      return Optional.of(command);

   }

   protected Command delete(final String gModelId) {

      Feature element = findFeatureById(FeatureModelGModelFactory.featureIdMap.get(gModelId));

      EditingDomain editingDomain = modelState.getEditingDomain();
      return RemoveCommand.create(editingDomain, EObject.class.cast(element.eContainer()), element.eContainingFeature(),
         element);

   }

}
