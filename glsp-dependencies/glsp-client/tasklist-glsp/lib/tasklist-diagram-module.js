"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
exports.initializeTasklistDiagramContainer = initializeTasklistDiagramContainer;
const client_1 = require("@eclipse-glsp/client");
const inversify_1 = require("inversify");
require("../css/diagram.css");
const my_custom_shape_view_1 = require("./my-custom-shape-view");
const taskListDiagramModule = new inversify_1.ContainerModule((bind, unbind, isBound, rebind) => {
    rebind(client_1.TYPES.ILogger).to(client_1.ConsoleLogger).inSingletonScope();
    rebind(client_1.TYPES.LogLevel).toConstantValue(client_1.LogLevel.warn);
    const context = { bind, isBound }; // ✅ only these are needed
    (0, client_1.configureDefaultModelElements)(context);
    // ✅ Register your custom shape (older GLSP API)
    (0, client_1.configureModelElement)(context, 'new-shape-type', client_1.GNode, my_custom_shape_view_1.MyCustomShapeView);
});
function initializeTasklistDiagramContainer(container, ...containerConfiguration) {
    return (0, client_1.initializeDiagramContainer)(container, taskListDiagramModule, ...containerConfiguration);
}
//# sourceMappingURL=tasklist-diagram-module.js.map