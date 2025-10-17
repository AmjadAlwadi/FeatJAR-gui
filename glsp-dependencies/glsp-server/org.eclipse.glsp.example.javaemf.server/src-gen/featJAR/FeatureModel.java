/**
 */
package featJAR;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Feature Model</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link featJAR.FeatureModel#getRoot <em>Root</em>}</li>
 *   <li>{@link featJAR.FeatureModel#getConstraints <em>Constraints</em>}</li>
 * </ul>
 *
 * @see featJAR.FeatJARPackage#getFeatureModel()
 * @model
 * @generated
 */
public interface FeatureModel extends Identifiable {
   /**
    * Returns the value of the '<em><b>Root</b></em>' containment reference.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @return the value of the '<em>Root</em>' containment reference.
    * @see #setRoot(Feature)
    * @see featJAR.FeatJARPackage#getFeatureModel_Root()
    * @model containment="true"
    * @generated
    */
   Feature getRoot();

   /**
    * Sets the value of the '{@link featJAR.FeatureModel#getRoot <em>Root</em>}' containment reference.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @param value the new value of the '<em>Root</em>' containment reference.
    * @see #getRoot()
    * @generated
    */
   void setRoot(Feature value);

   /**
    * Returns the value of the '<em><b>Constraints</b></em>' containment reference list.
    * The list contents are of type {@link featJAR.Constraint}.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @return the value of the '<em>Constraints</em>' containment reference list.
    * @see featJAR.FeatJARPackage#getFeatureModel_Constraints()
    * @model containment="true"
    * @generated
    */
   EList<Constraint> getConstraints();

} // FeatureModel
