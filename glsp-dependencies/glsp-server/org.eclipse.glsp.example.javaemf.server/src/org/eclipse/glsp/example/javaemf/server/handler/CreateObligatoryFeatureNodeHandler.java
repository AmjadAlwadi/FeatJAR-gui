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
package org.eclipse.glsp.example.javaemf.server.handler;

import java.util.Optional;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.command.AddCommand;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.glsp.example.javaemf.server.FeatureModelTypes;
import org.eclipse.glsp.graph.GModelElement;
import org.eclipse.glsp.graph.GPoint;
import org.eclipse.glsp.server.emf.EMFCreateOperationHandler;
import org.eclipse.glsp.server.emf.EMFIdGenerator;
import org.eclipse.glsp.server.emf.notation.EMFNotationModelState;
import org.eclipse.glsp.server.operations.CreateNodeOperation;
import org.eclipse.glsp.server.utils.LayoutUtil;

import com.google.inject.Inject;

import featJAR.FeatJARFactory;
import featJAR.FeatJARPackage;
import featJAR.Feature;
import featJAR.FeatureModel;

public class CreateObligatoryFeatureNodeHandler extends EMFCreateOperationHandler<CreateNodeOperation> {

   @Inject
   protected EMFNotationModelState modelState;

   @Inject
   protected EMFIdGenerator idGenerator;

   public CreateObligatoryFeatureNodeHandler() {
      super(FeatureModelTypes.OBLIGATORY_FEATURE);
   }

   static int i = 10;

   @Override
   public Optional<Command> createCommand(final CreateNodeOperation operation) {

      GModelElement container = modelState.getIndex().get(operation.getContainerId()).orElseGet(modelState::getRoot);
      Optional<GPoint> absoluteLocation = operation.getLocation();
      Optional<GPoint> relativeLocation = absoluteLocation
         .map(location -> LayoutUtil.getRelativeLocation(location, container));

      Feature newFeature = createFeature();

      return Optional.of(createFeatureAndNode(absoluteLocation));

   }

   @Override
   public String getLabel() { return "New Feature"; }

   protected Optional<EObject> getSelectedElement() {
      return modelState.getSemanticModel(FeatureModel.class)
         .flatMap(model -> model.getRoots().stream().findFirst().map(e -> (EObject) e));
   }

   protected Command createFeatureAndNode(final Optional<GPoint> relativeLocation) {
      // 1. Retrieve the selected (clicked) element
      Optional<EObject> selectedElementOpt = getSelectedElement();

      // 2. If no element selected, default to root model
      EObject parentElement = selectedElementOpt
         .filter(Feature.class::isInstance)
         .orElseGet(() -> modelState.getSemanticModel(FeatureModel.class)
            .map(FeatureModel::getRoots).stream().findFirst().map(f -> (EObject) f)
            .orElseThrow());

      // 3. Create the new feature instance
      Feature newFeature = createFeature();

      // 4. Build EMF AddCommand
      EditingDomain editingDomain = modelState.getEditingDomain();

      Command addCommand = AddCommand.create(
         editingDomain,
         parentElement, // where to add
         FeatJARPackage.Literals.FEATURE__FEATURES, // the containment reference
         newFeature // what to add
      );

      CompoundCommand compound = new CompoundCommand();
      compound.append(addCommand);

      return compound;
   }

   protected Feature createFeature() {
      Feature newFeature = FeatJARFactory.eINSTANCE.createFeature();
      newFeature.setName(getLabel() + i++);
      newFeature.setId(getLabel() + "_" + idGenerator.getOrCreateId(newFeature) + i++);
      newFeature.setOptional(false);
      return newFeature;
   }

}
