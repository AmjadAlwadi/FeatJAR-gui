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
 * An implementation of the model object '<em><b>Group</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link featJAR.impl.GroupImpl#getMadeOf <em>Made Of</em>}</li>
 *   <li>{@link featJAR.impl.GroupImpl#getGroupParent <em>Group Parent</em>}</li>
 *   <li>{@link featJAR.impl.GroupImpl#getType <em>Type</em>}</li>
 *   <li>{@link featJAR.impl.GroupImpl#getLowerBound <em>Lower Bound</em>}</li>
 *   <li>{@link featJAR.impl.GroupImpl#getUpperBound <em>Upper Bound</em>}</li>
 * </ul>
 *
 * @generated
 */
public class GroupImpl extends IdentifiableImpl implements Group {
	/**
	 * The cached value of the '{@link #getMadeOf() <em>Made Of</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMadeOf()
	 * @generated
	 * @ordered
	 */
	protected EList<Feature> madeOf;

	/**
	 * The cached value of the '{@link #getGroupParent() <em>Group Parent</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getGroupParent()
	 * @generated
	 * @ordered
	 */
	protected Feature groupParent;

	/**
	 * The default value of the '{@link #getType() <em>Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getType()
	 * @generated
	 * @ordered
	 */
	protected static final String TYPE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getType() <em>Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getType()
	 * @generated
	 * @ordered
	 */
	protected String type = TYPE_EDEFAULT;

	/**
	 * The default value of the '{@link #getLowerBound() <em>Lower Bound</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLowerBound()
	 * @generated
	 * @ordered
	 */
	protected static final int LOWER_BOUND_EDEFAULT = 0;

	/**
	 * The cached value of the '{@link #getLowerBound() <em>Lower Bound</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLowerBound()
	 * @generated
	 * @ordered
	 */
	protected int lowerBound = LOWER_BOUND_EDEFAULT;

	/**
	 * The default value of the '{@link #getUpperBound() <em>Upper Bound</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getUpperBound()
	 * @generated
	 * @ordered
	 */
	protected static final int UPPER_BOUND_EDEFAULT = 0;

	/**
	 * The cached value of the '{@link #getUpperBound() <em>Upper Bound</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getUpperBound()
	 * @generated
	 * @ordered
	 */
	protected int upperBound = UPPER_BOUND_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected GroupImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return FeatJARPackage.Literals.GROUP;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EList<Feature> getMadeOf() {
		if (madeOf == null) {
			madeOf = new EObjectResolvingEList<Feature>(Feature.class, this, FeatJARPackage.GROUP__MADE_OF);
		}
		return madeOf;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Feature getGroupParent() {
		if (groupParent != null && groupParent.eIsProxy()) {
			InternalEObject oldGroupParent = (InternalEObject) groupParent;
			groupParent = (Feature) eResolveProxy(oldGroupParent);
			if (groupParent != oldGroupParent) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, FeatJARPackage.GROUP__GROUP_PARENT,
							oldGroupParent, groupParent));
			}
		}
		return groupParent;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Feature basicGetGroupParent() {
		return groupParent;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setGroupParent(Feature newGroupParent) {
		Feature oldGroupParent = groupParent;
		groupParent = newGroupParent;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, FeatJARPackage.GROUP__GROUP_PARENT, oldGroupParent,
					groupParent));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getType() {
		return type;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setType(String newType) {
		String oldType = type;
		type = newType;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, FeatJARPackage.GROUP__TYPE, oldType, type));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public int getLowerBound() {
		return lowerBound;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setLowerBound(int newLowerBound) {
		int oldLowerBound = lowerBound;
		lowerBound = newLowerBound;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, FeatJARPackage.GROUP__LOWER_BOUND, oldLowerBound,
					lowerBound));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public int getUpperBound() {
		return upperBound;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setUpperBound(int newUpperBound) {
		int oldUpperBound = upperBound;
		upperBound = newUpperBound;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, FeatJARPackage.GROUP__UPPER_BOUND, oldUpperBound,
					upperBound));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
		case FeatJARPackage.GROUP__MADE_OF:
			return getMadeOf();
		case FeatJARPackage.GROUP__GROUP_PARENT:
			if (resolve)
				return getGroupParent();
			return basicGetGroupParent();
		case FeatJARPackage.GROUP__TYPE:
			return getType();
		case FeatJARPackage.GROUP__LOWER_BOUND:
			return getLowerBound();
		case FeatJARPackage.GROUP__UPPER_BOUND:
			return getUpperBound();
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
		case FeatJARPackage.GROUP__MADE_OF:
			getMadeOf().clear();
			getMadeOf().addAll((Collection<? extends Feature>) newValue);
			return;
		case FeatJARPackage.GROUP__GROUP_PARENT:
			setGroupParent((Feature) newValue);
			return;
		case FeatJARPackage.GROUP__TYPE:
			setType((String) newValue);
			return;
		case FeatJARPackage.GROUP__LOWER_BOUND:
			setLowerBound((Integer) newValue);
			return;
		case FeatJARPackage.GROUP__UPPER_BOUND:
			setUpperBound((Integer) newValue);
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
		case FeatJARPackage.GROUP__MADE_OF:
			getMadeOf().clear();
			return;
		case FeatJARPackage.GROUP__GROUP_PARENT:
			setGroupParent((Feature) null);
			return;
		case FeatJARPackage.GROUP__TYPE:
			setType(TYPE_EDEFAULT);
			return;
		case FeatJARPackage.GROUP__LOWER_BOUND:
			setLowerBound(LOWER_BOUND_EDEFAULT);
			return;
		case FeatJARPackage.GROUP__UPPER_BOUND:
			setUpperBound(UPPER_BOUND_EDEFAULT);
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
		case FeatJARPackage.GROUP__MADE_OF:
			return madeOf != null && !madeOf.isEmpty();
		case FeatJARPackage.GROUP__GROUP_PARENT:
			return groupParent != null;
		case FeatJARPackage.GROUP__TYPE:
			return TYPE_EDEFAULT == null ? type != null : !TYPE_EDEFAULT.equals(type);
		case FeatJARPackage.GROUP__LOWER_BOUND:
			return lowerBound != LOWER_BOUND_EDEFAULT;
		case FeatJARPackage.GROUP__UPPER_BOUND:
			return upperBound != UPPER_BOUND_EDEFAULT;
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
		result.append(" (type: ");
		result.append(type);
		result.append(", lowerBound: ");
		result.append(lowerBound);
		result.append(", upperBound: ");
		result.append(upperBound);
		result.append(')');
		return result.toString();
	}

} //GroupImpl
