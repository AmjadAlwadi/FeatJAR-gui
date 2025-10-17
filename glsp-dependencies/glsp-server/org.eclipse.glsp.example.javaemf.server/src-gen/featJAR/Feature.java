/**
 */
package featJAR;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Feature</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link featJAR.Feature#getFeatures <em>Features</em>}</li>
 *   <li>{@link featJAR.Feature#getParent <em>Parent</em>}</li>
 *   <li>{@link featJAR.Feature#isOptional <em>Optional</em>}</li>
 * </ul>
 *
 * @see featJAR.FeatJARPackage#getFeature()
 * @model
 * @generated
 */
public interface Feature extends Identifiable {
	/**
	 * Returns the value of the '<em><b>Features</b></em>' containment reference list.
	 * The list contents are of type {@link featJAR.Feature}.
	 * It is bidirectional and its opposite is '{@link featJAR.Feature#getParent <em>Parent</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Features</em>' containment reference list.
	 * @see featJAR.FeatJARPackage#getFeature_Features()
	 * @see featJAR.Feature#getParent
	 * @model opposite="parent" containment="true"
	 * @generated
	 */
	EList<Feature> getFeatures();

	/**
	 * Returns the value of the '<em><b>Parent</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link featJAR.Feature#getFeatures <em>Features</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Parent</em>' container reference.
	 * @see #setParent(Feature)
	 * @see featJAR.FeatJARPackage#getFeature_Parent()
	 * @see featJAR.Feature#getFeatures
	 * @model opposite="features" transient="false"
	 * @generated
	 */
	Feature getParent();

	/**
	 * Sets the value of the '{@link featJAR.Feature#getParent <em>Parent</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Parent</em>' container reference.
	 * @see #getParent()
	 * @generated
	 */
	void setParent(Feature value);

	/**
	 * Returns the value of the '<em><b>Optional</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Optional</em>' attribute.
	 * @see #setOptional(boolean)
	 * @see featJAR.FeatJARPackage#getFeature_Optional()
	 * @model
	 * @generated
	 */
	boolean isOptional();

	/**
	 * Sets the value of the '{@link featJAR.Feature#isOptional <em>Optional</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Optional</em>' attribute.
	 * @see #isOptional()
	 * @generated
	 */
	void setOptional(boolean value);

} // Feature
