/**
 */
package featJAR;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>root</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link featJAR.root#getCore <em>Core</em>}</li>
 *   <li>{@link featJAR.root#getGroup <em>Group</em>}</li>
 * </ul>
 *
 * @see featJAR.FeatJARPackage#getroot()
 * @model
 * @generated
 */
public interface root extends EObject {
	/**
	 * Returns the value of the '<em><b>Core</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Core</em>' attribute.
	 * @see #setCore(String)
	 * @see featJAR.FeatJARPackage#getroot_Core()
	 * @model required="true"
	 * @generated
	 */
	String getCore();

	/**
	 * Sets the value of the '{@link featJAR.root#getCore <em>Core</em>}' attribute.
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
	 * It is bidirectional and its opposite is '{@link featJAR.Group#getRoot <em>Root</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Group</em>' reference list.
	 * @see featJAR.FeatJARPackage#getroot_Group()
	 * @see featJAR.Group#getRoot
	 * @model opposite="root"
	 * @generated
	 */
	EList<Group> getGroup();

} // root
