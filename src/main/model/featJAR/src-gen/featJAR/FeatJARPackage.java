/**
 */
package featJAR;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

/**
 * <!-- begin-user-doc -->
 * The <b>Package</b> for the model.
 * It contains accessors for the meta objects to represent
 * <ul>
 *   <li>each class,</li>
 *   <li>each feature of each class,</li>
 *   <li>each operation of each class,</li>
 *   <li>each enum,</li>
 *   <li>and each data type</li>
 * </ul>
 * <!-- end-user-doc -->
 * @see featJAR.FeatJARFactory
 * @model kind="package"
 * @generated
 */
public interface FeatJARPackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "featJAR";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "http://www.example.org/featJAR";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "featJAR";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	FeatJARPackage eINSTANCE = featJAR.impl.FeatJARPackageImpl.init();

	/**
	 * The meta object id for the '{@link featJAR.impl.IdentifiableImpl <em>Identifiable</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see featJAR.impl.IdentifiableImpl
	 * @see featJAR.impl.FeatJARPackageImpl#getIdentifiable()
	 * @generated
	 */
	int IDENTIFIABLE = 1;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IDENTIFIABLE__ID = 0;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IDENTIFIABLE__NAME = 1;

	/**
	 * The number of structural features of the '<em>Identifiable</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IDENTIFIABLE_FEATURE_COUNT = 2;

	/**
	 * The number of operations of the '<em>Identifiable</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IDENTIFIABLE_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link featJAR.impl.FeatureModelImpl <em>Feature Model</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see featJAR.impl.FeatureModelImpl
	 * @see featJAR.impl.FeatJARPackageImpl#getFeatureModel()
	 * @generated
	 */
	int FEATURE_MODEL = 0;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FEATURE_MODEL__ID = IDENTIFIABLE__ID;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FEATURE_MODEL__NAME = IDENTIFIABLE__NAME;

	/**
	 * The feature id for the '<em><b>Root</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FEATURE_MODEL__ROOT = IDENTIFIABLE_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Constraints</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FEATURE_MODEL__CONSTRAINTS = IDENTIFIABLE_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Feature Model</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FEATURE_MODEL_FEATURE_COUNT = IDENTIFIABLE_FEATURE_COUNT + 2;

	/**
	 * The number of operations of the '<em>Feature Model</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FEATURE_MODEL_OPERATION_COUNT = IDENTIFIABLE_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link featJAR.impl.FeatureImpl <em>Feature</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see featJAR.impl.FeatureImpl
	 * @see featJAR.impl.FeatJARPackageImpl#getFeature()
	 * @generated
	 */
	int FEATURE = 2;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FEATURE__ID = IDENTIFIABLE__ID;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FEATURE__NAME = IDENTIFIABLE__NAME;

	/**
	 * The feature id for the '<em><b>Features</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FEATURE__FEATURES = IDENTIFIABLE_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Parent</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FEATURE__PARENT = IDENTIFIABLE_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Optional</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FEATURE__OPTIONAL = IDENTIFIABLE_FEATURE_COUNT + 2;

	/**
	 * The number of structural features of the '<em>Feature</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FEATURE_FEATURE_COUNT = IDENTIFIABLE_FEATURE_COUNT + 3;

	/**
	 * The number of operations of the '<em>Feature</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FEATURE_OPERATION_COUNT = IDENTIFIABLE_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link featJAR.impl.ConstraintImpl <em>Constraint</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see featJAR.impl.ConstraintImpl
	 * @see featJAR.impl.FeatJARPackageImpl#getConstraint()
	 * @generated
	 */
	int CONSTRAINT = 3;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONSTRAINT__ID = IDENTIFIABLE__ID;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONSTRAINT__NAME = IDENTIFIABLE__NAME;

	/**
	 * The number of structural features of the '<em>Constraint</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONSTRAINT_FEATURE_COUNT = IDENTIFIABLE_FEATURE_COUNT + 0;

	/**
	 * The number of operations of the '<em>Constraint</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONSTRAINT_OPERATION_COUNT = IDENTIFIABLE_OPERATION_COUNT + 0;

	/**
	 * Returns the meta object for class '{@link featJAR.FeatureModel <em>Feature Model</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Feature Model</em>'.
	 * @see featJAR.FeatureModel
	 * @generated
	 */
	EClass getFeatureModel();

	/**
	 * Returns the meta object for the containment reference '{@link featJAR.FeatureModel#getRoot <em>Root</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Root</em>'.
	 * @see featJAR.FeatureModel#getRoot()
	 * @see #getFeatureModel()
	 * @generated
	 */
	EReference getFeatureModel_Root();

	/**
	 * Returns the meta object for the containment reference list '{@link featJAR.FeatureModel#getConstraints <em>Constraints</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Constraints</em>'.
	 * @see featJAR.FeatureModel#getConstraints()
	 * @see #getFeatureModel()
	 * @generated
	 */
	EReference getFeatureModel_Constraints();

	/**
	 * Returns the meta object for class '{@link featJAR.Identifiable <em>Identifiable</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Identifiable</em>'.
	 * @see featJAR.Identifiable
	 * @generated
	 */
	EClass getIdentifiable();

	/**
	 * Returns the meta object for the attribute '{@link featJAR.Identifiable#getId <em>Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Id</em>'.
	 * @see featJAR.Identifiable#getId()
	 * @see #getIdentifiable()
	 * @generated
	 */
	EAttribute getIdentifiable_Id();

	/**
	 * Returns the meta object for the attribute '{@link featJAR.Identifiable#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see featJAR.Identifiable#getName()
	 * @see #getIdentifiable()
	 * @generated
	 */
	EAttribute getIdentifiable_Name();

	/**
	 * Returns the meta object for class '{@link featJAR.Feature <em>Feature</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Feature</em>'.
	 * @see featJAR.Feature
	 * @generated
	 */
	EClass getFeature();

	/**
	 * Returns the meta object for the reference list '{@link featJAR.Feature#getFeatures <em>Features</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Features</em>'.
	 * @see featJAR.Feature#getFeatures()
	 * @see #getFeature()
	 * @generated
	 */
	EReference getFeature_Features();

	/**
	 * Returns the meta object for the reference '{@link featJAR.Feature#getParent <em>Parent</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Parent</em>'.
	 * @see featJAR.Feature#getParent()
	 * @see #getFeature()
	 * @generated
	 */
	EReference getFeature_Parent();

	/**
	 * Returns the meta object for the attribute '{@link featJAR.Feature#isOptional <em>Optional</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Optional</em>'.
	 * @see featJAR.Feature#isOptional()
	 * @see #getFeature()
	 * @generated
	 */
	EAttribute getFeature_Optional();

	/**
	 * Returns the meta object for class '{@link featJAR.Constraint <em>Constraint</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Constraint</em>'.
	 * @see featJAR.Constraint
	 * @generated
	 */
	EClass getConstraint();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	FeatJARFactory getFeatJARFactory();

	/**
	 * <!-- begin-user-doc -->
	 * Defines literals for the meta objects that represent
	 * <ul>
	 *   <li>each class,</li>
	 *   <li>each feature of each class,</li>
	 *   <li>each operation of each class,</li>
	 *   <li>each enum,</li>
	 *   <li>and each data type</li>
	 * </ul>
	 * <!-- end-user-doc -->
	 * @generated
	 */
	interface Literals {
		/**
		 * The meta object literal for the '{@link featJAR.impl.FeatureModelImpl <em>Feature Model</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see featJAR.impl.FeatureModelImpl
		 * @see featJAR.impl.FeatJARPackageImpl#getFeatureModel()
		 * @generated
		 */
		EClass FEATURE_MODEL = eINSTANCE.getFeatureModel();

		/**
		 * The meta object literal for the '<em><b>Root</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference FEATURE_MODEL__ROOT = eINSTANCE.getFeatureModel_Root();

		/**
		 * The meta object literal for the '<em><b>Constraints</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference FEATURE_MODEL__CONSTRAINTS = eINSTANCE.getFeatureModel_Constraints();

		/**
		 * The meta object literal for the '{@link featJAR.impl.IdentifiableImpl <em>Identifiable</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see featJAR.impl.IdentifiableImpl
		 * @see featJAR.impl.FeatJARPackageImpl#getIdentifiable()
		 * @generated
		 */
		EClass IDENTIFIABLE = eINSTANCE.getIdentifiable();

		/**
		 * The meta object literal for the '<em><b>Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute IDENTIFIABLE__ID = eINSTANCE.getIdentifiable_Id();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute IDENTIFIABLE__NAME = eINSTANCE.getIdentifiable_Name();

		/**
		 * The meta object literal for the '{@link featJAR.impl.FeatureImpl <em>Feature</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see featJAR.impl.FeatureImpl
		 * @see featJAR.impl.FeatJARPackageImpl#getFeature()
		 * @generated
		 */
		EClass FEATURE = eINSTANCE.getFeature();

		/**
		 * The meta object literal for the '<em><b>Features</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference FEATURE__FEATURES = eINSTANCE.getFeature_Features();

		/**
		 * The meta object literal for the '<em><b>Parent</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference FEATURE__PARENT = eINSTANCE.getFeature_Parent();

		/**
		 * The meta object literal for the '<em><b>Optional</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute FEATURE__OPTIONAL = eINSTANCE.getFeature_Optional();

		/**
		 * The meta object literal for the '{@link featJAR.impl.ConstraintImpl <em>Constraint</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see featJAR.impl.ConstraintImpl
		 * @see featJAR.impl.FeatJARPackageImpl#getConstraint()
		 * @generated
		 */
		EClass CONSTRAINT = eINSTANCE.getConstraint();

	}

} //FeatJARPackage
