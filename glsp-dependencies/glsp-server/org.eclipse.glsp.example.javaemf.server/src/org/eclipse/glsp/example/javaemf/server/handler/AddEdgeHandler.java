package org.eclipse.glsp.example.javaemf.server.handler;

import java.util.Optional;

import org.eclipse.emf.common.command.Command;
import org.eclipse.glsp.example.javaemf.server.TaskListModelTypes;
import org.eclipse.glsp.server.emf.EMFCreateOperationHandler;
import org.eclipse.glsp.server.emf.EMFIdGenerator;
import org.eclipse.glsp.server.emf.notation.EMFNotationModelState;
import org.eclipse.glsp.server.operations.CreateEdgeOperation;

import com.google.inject.Inject;

public class AddEdgeHandler extends EMFCreateOperationHandler<CreateEdgeOperation> {

   @Inject
   protected EMFNotationModelState modelState;

   @Inject
   protected EMFIdGenerator idGenerator;

   public static int generalId = 10;

   public AddEdgeHandler() {
      super(TaskListModelTypes.OBLIGATORY_FEATURE);
   }

   @Override
   public Optional<Command> createCommand(final CreateEdgeOperation operation) {

      // Create new Feature
      // Based on type, do the operation (optional/obligatory)
      // Add the new feature to the emf model via modelState
      // Place at first the new feature at operation location
      // Then we it get's connected via an edge to a parent, let TaskListGModelFactory
      // redraw the emfmodel and draws the new feature correctly

      // The ability to change name
      // Add option in contextmenu to make optional

      // Add operation handlers for the missing operations

      // Feature f = FeatJARFactory.eINSTANCE.createFeature();
      //
      // modelState.getNotationModel().getElements().add(NotationElement.class.cast(f));

      return Optional.of(null);
   }

   @Override
   public String getLabel() { return "Feature"; }

   // protected Command createTaskAndShape(final Optional<GPoint> relativeLocation) {
   // // CoreFeature root = modelState.getSemanticModel(CoreFeature.class).orElseThrow();
   // Diagram diagram = modelState.getNotationModel();
   // EditingDomain editingDomain = modelState.getEditingDomain();
   //
   // // Feature newFeature = createTask();
   // // Command taskCommand = AddCommand.create(editingDomain, root,
   // // FeatJARPackage.Literals.FEATURE, newFeature);
   //
   // // Shape shape = createShape((newFeature.getId()), relativeLocation);
   // // Command shapeCommand = AddCommand.create(editingDomain, diagram,
   // // NotationPackage.Literals.DIAGRAM__ELEMENTS, shape);
   //
   // CompoundCommand compoundCommand = new CompoundCommand();
   // // compoundCommand.append(taskCommand);
   // // compoundCommand.append(shapeCommand);
   //
   // return compoundCommand;
   // }

   // protected Feature createEdge() {
   //
   // Feature newTask = FeatJARFactory.eINSTANCE.createFeature();
   // newTask.setName(getLabel());
   // newTask.setId("Feature_" + generalId++);
   // newTask.setOptional(false);
   // return newTask;
   // }

}
