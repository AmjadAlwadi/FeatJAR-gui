"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
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
require("reflect-metadata");
const client_1 = require("@eclipse-glsp/client");
const ide_1 = require("@eclipse-glsp/ide");
const protocol_1 = require("@eclipse-glsp/protocol");
const di_config_1 = require("./di.config");
const urlParameters = (0, ide_1.getParameters)();
const filePath = urlParameters.path;
// In the Eclipse Integration, port is dynamic, as multiple editors
// and/or Eclipse Servers may be running in parallel (e.g. 1/Eclipse IDE)
const port = parseInt(urlParameters.port, 10);
const applicationId = urlParameters.application;
const id = 'tasklist';
const diagramType = 'tasklist-diagram';
const clientId = urlParameters.client || protocol_1.ApplicationIdProvider.get();
const widgetId = urlParameters.widget || clientId;
setWidgetId(widgetId);
let container;
const webSocketUrl = `ws://localhost:${port}/${id}`;
let glspClient;
const wsProvider = new client_1.GLSPWebSocketProvider(webSocketUrl);
wsProvider.listen({ onConnection: initialize, onReconnect: reconnect, logger: console });
async function initialize(connectionProvider, isReconnecting = false) {
    glspClient = new protocol_1.BaseJsonrpcGLSPClient({ id, connectionProvider });
    // Java's URLEncoder.encode encodes spaces as plus sign but decodeURI expects spaces to be encoded as %20.
    // See also https://en.wikipedia.org/wiki/Query_string#URL_encoding for URL encoding in forms vs generic URL encoding.
    const sourceUri = decodeURI(filePath.replace(/\+/g, '%20'));
    const glspClientProvider = async () => glspClient;
    container = (0, di_config_1.createContainer)({ clientId, diagramType, glspClientProvider, sourceUri });
    const diagramLoader = container.get(client_1.DiagramLoader);
    await diagramLoader.load({
        requestModelOptions: { isReconnecting },
        initializeParameters: { applicationId },
        enableNotifications: false
    });
    const actionDispatcher = container.get(client_1.TYPES.IActionDispatcher);
    if (isReconnecting) {
        const message = `Connection to the ${id} glsp server got closed. Connection was successfully re-established.`;
        const timeout = 5000;
        const severity = 'WARNING';
        actionDispatcher.dispatchAll([client_1.StatusAction.create(message, { severity, timeout }), client_1.MessageAction.create(message, { severity })]);
        return;
    }
}
async function reconnect(connectionProvider) {
    glspClient.stop();
    initialize(connectionProvider, true /* isReconnecting */);
}
function setWidgetId(mainWidgetId) {
    const mainWidget = document.getElementById('sprotty');
    if (mainWidget) {
        mainWidget.id = mainWidgetId;
    }
}
//# sourceMappingURL=app.js.map