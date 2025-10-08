/**
 */
package featJAR;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Core Feature</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link featJAR.CoreFeature#getFeatures <em>Features</em>}</li>
 *   <li>{@link featJAR.CoreFeature#getEdges <em>Edges</em>}</li>
 * </ul>
 *
 * @see featJAR.FeatJARPackage#getCoreFeature()
 * @model
 * @generated
 */
public interface CoreFeature extends Identifiable {
	/**
	 * Returns the value of the '<em><b>Features</b></em>' containment reference list.
	 * The list contents are of type {@link featJAR.Feature}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Features</em>' containment reference list.
	 * @see featJAR.FeatJARPackage#getCoreFeature_Features()
	 * @model containment="true"
	 * @generated
	 */
	EList<Feature> getFeatures();

	/**
	 * Returns the value of the '<em><b>Edges</b></em>' containment reference list.
	 * The list contents are of type {@link featJAR.Edge}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Edges</em>' containment reference list.
	 * @see featJAR.FeatJARPackage#getCoreFeature_Edges()
	 * @model containment="true"
	 * @generated
	 */
	EList<Edge> getEdges();

} // CoreFeature
