package org.eclipse.glsp.example.javaemf.server.handler;

import java.util.Optional;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.command.AddCommand;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.glsp.server.emf.EMFIdGenerator;
import org.eclipse.glsp.server.emf.EMFOperationHandler;
import org.eclipse.glsp.server.emf.notation.EMFNotationModelState;
import org.eclipse.glsp.server.operations.PasteOperation;

import com.google.inject.Inject;

import featJAR.Constraint;
import featJAR.FeatJARFactory;
import featJAR.FeatJARPackage;
import featJAR.FeatureModel;

public class PasteOperationHandler extends EMFOperationHandler<PasteOperation> {

   @Inject
   protected EMFNotationModelState modelState;

   @Inject
   protected EMFIdGenerator idGenerator;

   public PasteOperationHandler() {
      super();
   }

   static int i = 10;

   @Override
   public Optional<Command> createCommand(final PasteOperation operation) {

      // String constraint = operation.getClipboardData().get(operation);
      String constraint = "asdfasdfdsa";
      return Optional.of(createConstraintAndPlaceInsideBox(constraint));

   }

   @Override
   public String getLabel() { return "New Constraint"; }

   protected Command createConstraintAndPlaceInsideBox(final String constraint) {

      System.out.println("before");

      // 2. If no element selected, default to root model
      EObject parentElement = modelState.getSemanticModel(FeatureModel.class).get();

      System.out.println("after");

      // 3. Create the new feature instance
      Constraint newConstraint = createConstraint();

      System.out.println("after");
      // 4. Build EMF AddCommand
      EditingDomain editingDomain = modelState.getEditingDomain();

      Command addCommand = AddCommand.create(
         editingDomain,
         parentElement, // where to add
         FeatJARPackage.Literals.FEATURE_MODEL__CONSTRAINTS, // the containment reference
         newConstraint // what to add
      );
      System.out.println("after");

      CompoundCommand compound = new CompoundCommand();
      compound.append(addCommand);
      System.out.println("after");

      return compound;
   }

   protected Constraint createConstraint() {
      Constraint newConstraint = FeatJARFactory.eINSTANCE.createConstraint();
      newConstraint.setName(getLabel() + i++);
      newConstraint.setId("new_" + getLabel() + "_" + idGenerator.getOrCreateId(newConstraint) + i++);
      return newConstraint;
   }

}
