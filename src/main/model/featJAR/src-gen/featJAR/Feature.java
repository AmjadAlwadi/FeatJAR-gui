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
 *   <li>{@link featJAR.Feature#getGroupIn <em>Group In</em>}</li>
 *   <li>{@link featJAR.Feature#getGroups <em>Groups</em>}</li>
 *   <li>{@link featJAR.Feature#isOptional <em>Optional</em>}</li>
 * </ul>
 *
 * @see featJAR.FeatJARPackage#getFeature()
 * @model
 * @generated
 */
public interface Feature extends Identifiable {
	/**
	 * Returns the value of the '<em><b>Group In</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link featJAR.Group#getFeatures <em>Features</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Group In</em>' container reference.
	 * @see #setGroupIn(Group)
	 * @see featJAR.FeatJARPackage#getFeature_GroupIn()
	 * @see featJAR.Group#getFeatures
	 * @model opposite="features" transient="false"
	 * @generated
	 */
	Group getGroupIn();

	/**
	 * Sets the value of the '{@link featJAR.Feature#getGroupIn <em>Group In</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Group In</em>' container reference.
	 * @see #getGroupIn()
	 * @generated
	 */
	void setGroupIn(Group value);

	/**
	 * Returns the value of the '<em><b>Groups</b></em>' containment reference list.
	 * The list contents are of type {@link featJAR.Group}.
	 * It is bidirectional and its opposite is '{@link featJAR.Group#getGroupParent <em>Group Parent</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Groups</em>' containment reference list.
	 * @see featJAR.FeatJARPackage#getFeature_Groups()
	 * @see featJAR.Group#getGroupParent
	 * @model opposite="groupParent" containment="true"
	 * @generated
	 */
	EList<Group> getGroups();

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
