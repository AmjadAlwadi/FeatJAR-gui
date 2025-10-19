/********************************************************************************
 * Copyright (c) 2022 EclipseSource and others.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v. 2.0 which is available at
 * https://www.eclipse.org/legal/epl-2.0.
 *
 * This Source Code may also be made available under the following Secondary
 * Licenses when the conditions for such availability set forth in the Eclipse
 * Public License v. 2.0 are satisfied:
 * -- GNU General Public License, version 2 with the GNU Classpath Exception
 * which is available at https://www.gnu.org/software/classpath/license.html
 * -- MIT License which is available at https://opensource.org/license/mit.
 *
 * SPDX-License-Identifier: EPL-2.0 OR GPL-2.0 WITH Classpath-exception-2.0 OR MIT
 ********************************************************************************/
package org.eclipse.glsp.example.javaemf.server;

import org.eclipse.glsp.graph.DefaultTypes;

public final class FeatureModelTypes {
   private FeatureModelTypes() {}

   public static final String NODE = "node";
   public static final String ROOT = "root:node";
   public static final String OPTIONAL_FEATURE = "op:node";
   public static final String OBLIGATORY_FEATURE = "ob:node";
   public static final String CONSTRAINT = DefaultTypes.EDGE;
   public static final String LINK = DefaultTypes.EDGE;
   public static final String EDITABLE_LABEL = "label:heading";

}
