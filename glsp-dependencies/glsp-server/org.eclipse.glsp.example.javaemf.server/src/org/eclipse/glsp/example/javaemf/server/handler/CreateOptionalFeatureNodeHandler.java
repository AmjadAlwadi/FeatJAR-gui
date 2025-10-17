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
import org.eclipse.glsp.server.emf.EMFCreateOperationHandler;
import org.eclipse.glsp.server.emf.EMFIdGenerator;
import org.eclipse.glsp.server.emf.notation.EMFNotationModelState;
import org.eclipse.glsp.server.operations.CreateNodeOperation;

import com.google.inject.Inject;

import featJAR.FeatJARFactory;
import featJAR.FeatJARPackage;
import featJAR.Feature;
import featJAR.FeatureModel;

public class CreateOptionalFeatureNodeHandler extends EMFCreateOperationHandler<CreateNodeOperation> {

   @Inject
   protected EMFNotationModelState modelState;

   @Inject
   protected EMFIdGenerator idGenerator;

   public CreateOptionalFeatureNodeHandler() {
      super(FeatureModelTypes.OPTIONAL_FEATURE);
   }

   static int i = 10;

   @Override
   public Optional<Command> createCommand(final CreateNodeOperation operation) {

      System.out.println("optioanl");
      Feature newFeature = createFeature();
      return Optional.of(createFeatureAndNode());

   }

   @Override
   public String getLabel() { return "New Optional Feature"; }

   protected FeatureModel getFeatureModel() { return modelState.getSemanticModel(FeatureModel.class).get(); }

   protected Optional<Feature> getRoot() {
      return modelState.getSemanticModel(FeatureModel.class)
         .flatMap(model -> model.getRoots().stream().findFirst());
   }

   protected Command createFeatureAndNode() {

      Optional<Feature> selectedFeature = modelState.getProperty("currentSelection",
         (Class<Feature>) (Class<?>) Feature.class);

      System.out.println("asdfdf: " + selectedFeature.get());

      // 2. If no element selected, default to root model
      Feature parentElement = selectedFeature
         .or(() -> getRoot())
         .orElse(null);

      EObject eParent = EObject.class.cast(getFeatureModel());

      if (parentElement != null) {

         eParent = EObject.class.cast(parentElement);

         // 3. Create the new feature instance
         Feature newFeature = createFeature();

         // 4. Build EMF AddCommand
         EditingDomain editingDomain = modelState.getEditingDomain();

         Command addCommand = AddCommand.create(
            editingDomain,
            eParent, // where to add
            FeatJARPackage.Literals.FEATURE__FEATURES, // the containment reference
            newFeature // what to add
         );

         // Setting id
         newFeature.setId(getLabel() + "_" + idGenerator.getOrCreateId(newFeature) + i++);
         // newFeature.setOptional(true);

         CompoundCommand compound = new CompoundCommand();
         compound.append(addCommand);

         return compound;

      }

      // Crreat Root otherwise

      // 3. Create the new feature instance
      Feature newFeature = createFeature();

      // 4. Build EMF AddCommand
      EditingDomain editingDomain = modelState.getEditingDomain();

      Command addCommand = AddCommand.create(
         editingDomain,
         eParent, // where to add
         FeatJARPackage.Literals.FEATURE_MODEL__ROOTS, // the containment reference
         newFeature // what to add
      );

      // Setting id
      newFeature.setId(getLabel() + "_" + idGenerator.getOrCreateId(newFeature) + i++);
      // newFeature.setOptional(true);

      CompoundCommand compound = new CompoundCommand();
      compound.append(addCommand);

      return compound;
   }

   protected Feature createFeature() {
      Feature newFeature = FeatJARFactory.eINSTANCE.createFeature();
      newFeature.setName(getLabel() + i++);
      newFeature.setOptional(true);
      return newFeature;
   }
}
