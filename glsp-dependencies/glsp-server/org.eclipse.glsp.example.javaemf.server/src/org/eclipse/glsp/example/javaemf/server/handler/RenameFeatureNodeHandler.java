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
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.command.RemoveCommand;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.glsp.example.javaemf.server.model.FeatureModelGModelFactory;
import org.eclipse.glsp.server.emf.AbstractEMFOperationHandler;
import org.eclipse.glsp.server.emf.notation.EMFNotationModelState;
import org.eclipse.glsp.server.operations.DeleteOperation;

import com.google.inject.Inject;

import featJAR.Feature;

public class RenameFeatureNodeHandler extends AbstractEMFOperationHandler<DeleteOperation> {

   @Inject
   protected EMFNotationModelState modelState;

   // public static Optional<String> gmodelIdToEmfId(final EMFNotationModelIndex index, final String gModelId) {
   // return index.get(gModelId) // Optional<GModelElement>
   // .flatMap(index::getEObject) // Optional<EObject>
   // .map(eObj -> {
   // // 1) EMF @id (if your Ecore has an ID attribute)
   // String emfId = EcoreUtil.getID(eObj);
   //
   // // 2) Domain-specific id (if your model class has one, e.g., Feature.getId())
   // if (emfId == null && eObj instanceof Feature f) {
   // emfId = f.getId();
   // }
   //
   // // 3) Fallback: URI fragment (stable within the resource)
   // if (emfId == null && eObj.eResource() != null) {
   // emfId = eObj.eResource().getURIFragment(eObj);
   // }
   //
   // return emfId; // may still be null if none apply
   // });
   // }

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

   public static int getEmfFeatureById(final String id) {

      if (FeatureModelGModelFactory.emfFeatures == null || id == null) {
         return -1;
      }

      for (int i = 0; i < FeatureModelGModelFactory.emfFeatures.size(); i++) {
         if (id.equals(FeatureModelGModelFactory.emfFeatures.get(i).getId())) {
            return i;
         }
      }
      return -1; // not found
   }

   @Override
   public Optional<Command> createCommand(final DeleteOperation operation) {

      List<String> gModelIds = operation.getElementIds();
      // EMFNotationModelIndex index = modelState.getIndex();
      //
      // // EMFModelState emfState = modelState.set
      //
      // FeatureModel.class.cast(modelState.getSemanticModel());
      //
      //
      //
      //
      // // modelState.getIndex().getAllByClass(GNode.class).stream().findFirst().get().set;
      //
      // // modelState.get;
      //
      // // Optional<String> id = gmodelIdToEmfId(index, gModelIds.get(0));
      //
      // // FeatureModel emfFeatureModel = FeatureModel.class.cast(modelState.getSemanticModel());
      // //
      // // System.out.println(emfFeatureModel.getRoot().get(0).getName());
      // //
      findFeatureById(FeatureModelGModelFactory.featureIdMap.get(gModelIds.get(0))).setName("Mohammad");
      // //
      // // System.out.println(emfFeatureModel.getRoot().get(0).getName());
      //
      // GModelRoot newRoot = modelState.getRoot();
      // modelState.updateRoot(newRoot);
      //
      // EObject semantic = modelState.getSemanticModel();
      // Resource resource = semantic.eResource(); // same Resource currently backing the model
      // // resource.save(Collections.emptyMap()); // or /pass XMLResource options

      // GNode task = modelState.getIndex().findElementByClass(operation.getElementIds().get(0), GNode.class)
      // .orElseThrow(
      // () -> new RuntimeException("Cannot find task with id '" + operation.getElementIds().get(0) + "'"));

      // GLabel label = (GLabel) task.getChildren().get(0);
      // label.setText("Mohammad");

      // return Optional.of(new CompoundCommand("Updated label", Collections.emptyList()));

      System.out.println("sdfsd : " + gModelIds.get(0));

      return Optional.of(delete(gModelIds.get(0)));

      // modelState.execute(null);

      // System.out.println("feature model: " + FeatureModel.class.cast(modelState.getSemanticModel()).getName());
   }

   protected Command delete(final String gModelId) {
      // int emfIndex = getEmfFeatureById(FeatureModelGModelFactory.featureIdMap.get(gModelId));
      // FeatureModelGModelFactory.emfFeatures.get(emfIndex);

      Feature element = findFeatureById(FeatureModelGModelFactory.featureIdMap.get(gModelId));

      EditingDomain editingDomain = modelState.getEditingDomain();
      return RemoveCommand.create(editingDomain, EObject.class.cast(element.eContainer()), element.eContainingFeature(),
         element);

   }

   // protected Feature createRoot() {
   // Feature newTask = FeatJARFactory.eINSTANCE.();
   // newTask.setName(getLabel());
   // newTask.setId(idGenerator.getOrCreateId(newTask));
   // newTask.setOptional(false);
   // return newTask;
   // }

}
