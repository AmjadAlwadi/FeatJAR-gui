/**
 */
package featJAR;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Group</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link featJAR.Group#getFeatures <em>Features</em>}</li>
 *   <li>{@link featJAR.Group#getGroupParent <em>Group Parent</em>}</li>
 *   <li>{@link featJAR.Group#getType <em>Type</em>}</li>
 *   <li>{@link featJAR.Group#getLowerBound <em>Lower Bound</em>}</li>
 *   <li>{@link featJAR.Group#getUpperBound <em>Upper Bound</em>}</li>
 * </ul>
 *
 * @see featJAR.FeatJARPackage#getGroup()
 * @model
 * @generated
 */
public interface Group extends Identifiable {
	/**
	 * Returns the value of the '<em><b>Features</b></em>' containment reference list.
	 * The list contents are of type {@link featJAR.Feature}.
	 * It is bidirectional and its opposite is '{@link featJAR.Feature#getGroupIn <em>Group In</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Features</em>' containment reference list.
	 * @see featJAR.FeatJARPackage#getGroup_Features()
	 * @see featJAR.Feature#getGroupIn
	 * @model opposite="groupIn" containment="true"
	 * @generated
	 */
	EList<Feature> getFeatures();

	/**
	 * Returns the value of the '<em><b>Group Parent</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link featJAR.Feature#getGroups <em>Groups</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Group Parent</em>' container reference.
	 * @see #setGroupParent(Feature)
	 * @see featJAR.FeatJARPackage#getGroup_GroupParent()
	 * @see featJAR.Feature#getGroups
	 * @model opposite="groups" required="true" transient="false"
	 * @generated
	 */
	Feature getGroupParent();

	/**
	 * Sets the value of the '{@link featJAR.Group#getGroupParent <em>Group Parent</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Group Parent</em>' container reference.
	 * @see #getGroupParent()
	 * @generated
	 */
	void setGroupParent(Feature value);

	/**
	 * Returns the value of the '<em><b>Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Type</em>' attribute.
	 * @see #setType(String)
	 * @see featJAR.FeatJARPackage#getGroup_Type()
	 * @model
	 * @generated
	 */
	String getType();

	/**
	 * Sets the value of the '{@link featJAR.Group#getType <em>Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Type</em>' attribute.
	 * @see #getType()
	 * @generated
	 */
	void setType(String value);

	/**
	 * Returns the value of the '<em><b>Lower Bound</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Lower Bound</em>' attribute.
	 * @see #setLowerBound(int)
	 * @see featJAR.FeatJARPackage#getGroup_LowerBound()
	 * @model
	 * @generated
	 */
	int getLowerBound();

	/**
	 * Sets the value of the '{@link featJAR.Group#getLowerBound <em>Lower Bound</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Lower Bound</em>' attribute.
	 * @see #getLowerBound()
	 * @generated
	 */
	void setLowerBound(int value);

	/**
	 * Returns the value of the '<em><b>Upper Bound</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Upper Bound</em>' attribute.
	 * @see #setUpperBound(int)
	 * @see featJAR.FeatJARPackage#getGroup_UpperBound()
	 * @model
	 * @generated
	 */
	int getUpperBound();

	/**
	 * Sets the value of the '{@link featJAR.Group#getUpperBound <em>Upper Bound</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Upper Bound</em>' attribute.
	 * @see #getUpperBound()
	 * @generated
	 */
	void setUpperBound(int value);

} // Group
