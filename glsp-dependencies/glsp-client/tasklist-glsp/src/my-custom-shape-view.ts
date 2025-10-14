// my-custom-shape-view.ts
import { ShapeView } from '@eclipse-glsp/client';
import { h } from 'snabbdom';
import type { VNode } from 'snabbdom';

export class MyCustomShapeView extends ShapeView {
    render(): VNode {
        return h('svg', { attrs: { width: 80, height: 60 } }, [
            h('rect', { attrs: { x: 5, y: 5, width: 70, height: 50, rx: 10, fill: '#4a90e2', stroke: '#1b3f73', 'stroke-width': 2 } }),
                 h('text', { attrs: { x: 40, y: 35, 'text-anchor': 'middle', fill: 'white', 'font-size': 12 } }, 'New')
        ]);
    }
}
