/**
 */
package featJAR;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Root</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link featJAR.Root#getCore <em>Core</em>}</li>
 *   <li>{@link featJAR.Root#getGroup <em>Group</em>}</li>
 * </ul>
 *
 * @see featJAR.FeatJARPackage#getRoot()
 * @model
 * @generated
 */
public interface Root extends EObject {
	/**
	 * Returns the value of the '<em><b>Core</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Core</em>' attribute.
	 * @see #setCore(String)
	 * @see featJAR.FeatJARPackage#getRoot_Core()
	 * @model required="true"
	 * @generated
	 */
	String getCore();

	/**
	 * Sets the value of the '{@link featJAR.Root#getCore <em>Core</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Core</em>' attribute.
	 * @see #getCore()
	 * @generated
	 */
	void setCore(String value);

	/**
	 * Returns the value of the '<em><b>Group</b></em>' reference list.
	 * The list contents are of type {@link featJAR.Group}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Group</em>' reference list.
	 * @see featJAR.FeatJARPackage#getRoot_Group()
	 * @model
	 * @generated
	 */
	EList<Group> getGroup();

} // Root
