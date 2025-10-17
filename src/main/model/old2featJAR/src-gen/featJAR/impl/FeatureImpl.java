/**
 */
package featJAR.impl;

import featJAR.FeatJARPackage;
import featJAR.Feature;
import featJAR.Group;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.util.EObjectResolvingEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Feature</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link featJAR.impl.FeatureImpl#getGroupIn <em>Group In</em>}</li>
 *   <li>{@link featJAR.impl.FeatureImpl#getParentOfGroup <em>Parent Of Group</em>}</li>
 *   <li>{@link featJAR.impl.FeatureImpl#isOptional <em>Optional</em>}</li>
 * </ul>
 *
 * @generated
 */
public class FeatureImpl extends IdentifiableImpl implements Feature {
	/**
	 * The cached value of the '{@link #getGroupIn() <em>Group In</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getGroupIn()
	 * @generated
	 * @ordered
	 */
	protected Group groupIn;

	/**
	 * The cached value of the '{@link #getParentOfGroup() <em>Parent Of Group</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getParentOfGroup()
	 * @generated
	 * @ordered
	 */
	protected EList<Group> parentOfGroup;

	/**
	 * The default value of the '{@link #isOptional() <em>Optional</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isOptional()
	 * @generated
	 * @ordered
	 */
	protected static final boolean OPTIONAL_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isOptional() <em>Optional</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isOptional()
	 * @generated
	 * @ordered
	 */
	protected boolean optional = OPTIONAL_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected FeatureImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return FeatJARPackage.Literals.FEATURE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Group getGroupIn() {
		if (groupIn != null && groupIn.eIsProxy()) {
			InternalEObject oldGroupIn = (InternalEObject) groupIn;
			groupIn = (Group) eResolveProxy(oldGroupIn);
			if (groupIn != oldGroupIn) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, FeatJARPackage.FEATURE__GROUP_IN,
							oldGroupIn, groupIn));
			}
		}
		return groupIn;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Group basicGetGroupIn() {
		return groupIn;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setGroupIn(Group newGroupIn) {
		Group oldGroupIn = groupIn;
		groupIn = newGroupIn;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, FeatJARPackage.FEATURE__GROUP_IN, oldGroupIn,
					groupIn));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EList<Group> getParentOfGroup() {
		if (parentOfGroup == null) {
			parentOfGroup = new EObjectResolvingEList<Group>(Group.class, this,
					FeatJARPackage.FEATURE__PARENT_OF_GROUP);
		}
		return parentOfGroup;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean isOptional() {
		return optional;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setOptional(boolean newOptional) {
		boolean oldOptional = optional;
		optional = newOptional;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, FeatJARPackage.FEATURE__OPTIONAL, oldOptional,
					optional));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
		case FeatJARPackage.FEATURE__GROUP_IN:
			if (resolve)
				return getGroupIn();
			return basicGetGroupIn();
		case FeatJARPackage.FEATURE__PARENT_OF_GROUP:
			return getParentOfGroup();
		case FeatJARPackage.FEATURE__OPTIONAL:
			return isOptional();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
		case FeatJARPackage.FEATURE__GROUP_IN:
			setGroupIn((Group) newValue);
			return;
		case FeatJARPackage.FEATURE__PARENT_OF_GROUP:
			getParentOfGroup().clear();
			getParentOfGroup().addAll((Collection<? extends Group>) newValue);
			return;
		case FeatJARPackage.FEATURE__OPTIONAL:
			setOptional((Boolean) newValue);
			return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eUnset(int featureID) {
		switch (featureID) {
		case FeatJARPackage.FEATURE__GROUP_IN:
			setGroupIn((Group) null);
			return;
		case FeatJARPackage.FEATURE__PARENT_OF_GROUP:
			getParentOfGroup().clear();
			return;
		case FeatJARPackage.FEATURE__OPTIONAL:
			setOptional(OPTIONAL_EDEFAULT);
			return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean eIsSet(int featureID) {
		switch (featureID) {
		case FeatJARPackage.FEATURE__GROUP_IN:
			return groupIn != null;
		case FeatJARPackage.FEATURE__PARENT_OF_GROUP:
			return parentOfGroup != null && !parentOfGroup.isEmpty();
		case FeatJARPackage.FEATURE__OPTIONAL:
			return optional != OPTIONAL_EDEFAULT;
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String toString() {
		if (eIsProxy())
			return super.toString();

		StringBuilder result = new StringBuilder(super.toString());
		result.append(" (optional: ");
		result.append(optional);
		result.append(')');
		return result.toString();
	}

} //FeatureImpl
