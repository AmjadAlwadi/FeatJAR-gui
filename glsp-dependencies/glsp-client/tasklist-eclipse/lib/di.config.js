"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
exports.createContainer = createContainer;
/********************************************************************************
 * Copyright (c) 2022 EclipseSource and others.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v. 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
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
const client_1 = require("@eclipse-glsp/client");
const ide_1 = require("@eclipse-glsp/ide");
const inversify_1 = require("inversify");
const tasklist_glsp_1 = require("tasklist-glsp");
function createContainer(options) {
    const container = (0, tasklist_glsp_1.initializeTasklistDiagramContainer)(new inversify_1.Container(), (0, client_1.createDiagramOptionsModule)(options), ide_1.ECLIPSE_DEFAULT_MODULE_CONFIG);
    container.rebind(client_1.TYPES.ILogger).to(client_1.ConsoleLogger).inSingletonScope();
    container.rebind(client_1.TYPES.LogLevel).toConstantValue(client_1.LogLevel.warn);
    return container;
}
//# sourceMappingURL=di.config.js.map