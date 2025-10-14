"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
exports.MyCustomShapeView = void 0;
// my-custom-shape-view.ts
const client_1 = require("@eclipse-glsp/client");
const snabbdom_1 = require("snabbdom");
class MyCustomShapeView extends client_1.ShapeView {
    render() {
        return (0, snabbdom_1.h)('svg', { attrs: { width: 80, height: 60 } }, [
            (0, snabbdom_1.h)('rect', { attrs: { x: 5, y: 5, width: 70, height: 50, rx: 10, fill: '#4a90e2', stroke: '#1b3f73', 'stroke-width': 2 } }),
            (0, snabbdom_1.h)('text', { attrs: { x: 40, y: 35, 'text-anchor': 'middle', fill: 'white', 'font-size': 12 } }, 'New')
        ]);
    }
}
exports.MyCustomShapeView = MyCustomShapeView;
//# sourceMappingURL=my-custom-shape-view.js.map