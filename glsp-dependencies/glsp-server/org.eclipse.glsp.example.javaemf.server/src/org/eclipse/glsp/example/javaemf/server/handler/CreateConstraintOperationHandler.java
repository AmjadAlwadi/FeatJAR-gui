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

import featJAR.Constraint;
import featJAR.FeatJARFactory;
import featJAR.FeatJARPackage;
import featJAR.FeatureModel;

public class CreateConstraintOperationHandler extends EMFCreateOperationHandler<CreateNodeOperation> {

   @Inject
   protected EMFNotationModelState modelState;

   @Inject
   protected EMFIdGenerator idGenerator;

   public CreateConstraintOperationHandler() {
      super(FeatureModelTypes.CONSTRAINT);
   }

   static int i = 0;

   @Override
   public Optional<Command> createCommand(final CreateNodeOperation operation) {

      return Optional.of(createConstraintAndPlaceInsideBox());

   }

   @Override
   public String getLabel() { return "New Constraint"; }

   protected Command createConstraintAndPlaceInsideBox() {

      EObject parentElement = modelState.getSemanticModel(FeatureModel.class).get();

      Constraint newConstraint = createConstraint();

      EditingDomain editingDomain = modelState.getEditingDomain();

      Command addCommand = AddCommand.create(
         editingDomain,
         parentElement, // where to add
         FeatJARPackage.Literals.FEATURE_MODEL__CONSTRAINTS, // the containment reference
         newConstraint // what to add
      );

      CompoundCommand compound = new CompoundCommand();
      compound.append(addCommand);

      return compound;
   }

   protected Constraint createConstraint() {
      Constraint newConstraint = FeatJARFactory.eINSTANCE.createConstraint();
      newConstraint.setName(getLabel() + " " + i++);
      newConstraint.setId(getLabel() + "_" + idGenerator.getOrCreateId(newConstraint) + i++);
      return newConstraint;
   }

}
