/**
 */
package featJAR.impl;

import featJAR.Constraint;
import featJAR.FeatJARPackage;
import featJAR.Feature;
import featJAR.FeatureModel;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Feature Model</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link featJAR.impl.FeatureModelImpl#getRoot <em>Root</em>}</li>
 *   <li>{@link featJAR.impl.FeatureModelImpl#getConstraints <em>Constraints</em>}</li>
 * </ul>
 *
 * @generated
 */
public class FeatureModelImpl extends IdentifiableImpl implements FeatureModel {
   /**
    * The cached value of the '{@link #getRoot() <em>Root</em>}' containment reference.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see #getRoot()
    * @generated
    * @ordered
    */
   protected Feature root;

   /**
    * The cached value of the '{@link #getConstraints() <em>Constraints</em>}' containment reference list.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see #getConstraints()
    * @generated
    * @ordered
    */
   protected EList<Constraint> constraints;

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   protected FeatureModelImpl() {
      super();
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   @Override
   protected EClass eStaticClass() {
      return FeatJARPackage.Literals.FEATURE_MODEL;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   @Override
   public Feature getRoot() {
      return root;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public NotificationChain basicSetRoot(Feature newRoot, NotificationChain msgs) {
      Feature oldRoot = root;
      root = newRoot;
      if (eNotificationRequired()) {
         ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, FeatJARPackage.FEATURE_MODEL__ROOT, oldRoot, newRoot);
         if (msgs == null) msgs = notification; else msgs.add(notification);
      }
      return msgs;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   @Override
   public void setRoot(Feature newRoot) {
      if (newRoot != root) {
         NotificationChain msgs = null;
         if (root != null)
            msgs = ((InternalEObject)root).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - FeatJARPackage.FEATURE_MODEL__ROOT, null, msgs);
         if (newRoot != null)
            msgs = ((InternalEObject)newRoot).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - FeatJARPackage.FEATURE_MODEL__ROOT, null, msgs);
         msgs = basicSetRoot(newRoot, msgs);
         if (msgs != null) msgs.dispatch();
      }
      else if (eNotificationRequired())
         eNotify(new ENotificationImpl(this, Notification.SET, FeatJARPackage.FEATURE_MODEL__ROOT, newRoot, newRoot));
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   @Override
   public EList<Constraint> getConstraints() {
      if (constraints == null) {
         constraints = new EObjectContainmentEList<Constraint>(Constraint.class, this, FeatJARPackage.FEATURE_MODEL__CONSTRAINTS);
      }
      return constraints;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   @Override
   public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
      switch (featureID) {
         case FeatJARPackage.FEATURE_MODEL__ROOT:
            return basicSetRoot(null, msgs);
         case FeatJARPackage.FEATURE_MODEL__CONSTRAINTS:
            return ((InternalEList<?>)getConstraints()).basicRemove(otherEnd, msgs);
      }
      return super.eInverseRemove(otherEnd, featureID, msgs);
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   @Override
   public Object eGet(int featureID, boolean resolve, boolean coreType) {
      switch (featureID) {
         case FeatJARPackage.FEATURE_MODEL__ROOT:
            return getRoot();
         case FeatJARPackage.FEATURE_MODEL__CONSTRAINTS:
            return getConstraints();
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
         case FeatJARPackage.FEATURE_MODEL__ROOT:
            setRoot((Feature)newValue);
            return;
         case FeatJARPackage.FEATURE_MODEL__CONSTRAINTS:
            getConstraints().clear();
            getConstraints().addAll((Collection<? extends Constraint>)newValue);
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
         case FeatJARPackage.FEATURE_MODEL__ROOT:
            setRoot((Feature)null);
            return;
         case FeatJARPackage.FEATURE_MODEL__CONSTRAINTS:
            getConstraints().clear();
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
         case FeatJARPackage.FEATURE_MODEL__ROOT:
            return root != null;
         case FeatJARPackage.FEATURE_MODEL__CONSTRAINTS:
            return constraints != null && !constraints.isEmpty();
      }
      return super.eIsSet(featureID);
   }

} //FeatureModelImpl
