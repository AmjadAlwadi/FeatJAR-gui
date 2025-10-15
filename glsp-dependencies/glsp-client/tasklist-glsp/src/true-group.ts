import { ShapeView } from '@eclipse-glsp/client';
import type { VNode } from 'snabbdom';
import { h } from 'snabbdom';

export class trueGroup extends ShapeView {
    render(): VNode {
        return h(
            'svg',
            {
                attrs: {
                    width: '800',
                    height: '600',
                    xmlns: 'http://www.w3.org/2000/svg'
                }
            },
            [
                h('g', { attrs: { id: 'Layer_1' } }, [
                    h('title', {}, 'Layer 1'),

                    h('line', {
                        attrs: {
                            id: 'svg_1',
                            x1: '400.99999',
                            y1: '1.8',
                            x2: '117.99999',
                            y2: '561.79999',
                            stroke: '#000',
                            fill: 'none',
                            'stroke-linecap': 'undefined',
                            'stroke-linejoin': 'undefined'
                        }
                    }),
                    h('line', {
                        attrs: {
                            id: 'svg_2',
                            x1: '400.99999',
                            y1: '1.8',
                            x2: '117.99999',
                            y2: '561.79999',
                            stroke: '#000',
                            fill: 'none',
                            'stroke-linecap': 'undefined',
                            'stroke-linejoin': 'undefined'
                        }
                    }),
                    h('line', {
                        attrs: {
                            id: 'svg_3',
                            x1: '400.99999',
                            y1: '1.8',
                            x2: '117.99999',
                            y2: '561.79999',
                            stroke: '#000',
                            fill: 'none',
                            'stroke-linecap': 'undefined',
                            'stroke-linejoin': 'undefined'
                        }
                    }),
                    h('line', {
                        attrs: {
                            id: 'svg_4',
                            x1: '400.99999',
                            y1: '1.8',
                            x2: '117.99999',
                            y2: '561.79999',
                            stroke: '#000',
                            fill: 'none',
                            'stroke-linecap': 'undefined',
                            'stroke-linejoin': 'undefined'
                        }
                    }),
                    h('line', {
                        attrs: {
                            id: 'svg_5',
                            x1: '400.99999',
                            y1: '1.8',
                            x2: '117.99999',
                            y2: '561.79999',
                            stroke: '#000',
                            fill: 'none',
                            'stroke-linecap': 'undefined',
                            'stroke-linejoin': 'undefined'
                        }
                    }),
                    h('line', {
                        attrs: {
                            id: 'svg_6',
                            x1: '400.99999',
                            y1: '1.8',
                            x2: '117.99999',
                            y2: '561.79999',
                            stroke: '#000',
                            fill: 'none',
                            'stroke-linecap': 'undefined',
                            'stroke-linejoin': 'undefined'
                        }
                    }),

                    h('path', {
                        attrs: {
                            id: 'svg_7',
                            d: 'm400.99999,1.8l-283,559.99999',
                            stroke: '#000',
                            fill: 'none',
                            'stroke-width': '15',
                            opacity: '1',
                            'stroke-linecap': 'round',
                            'stroke-linejoin': 'round'
                        }
                    }),
                    h('line', {
                        attrs: {
                            id: 'svg_8',
                            x1: '401.00001',
                            y1: '1.8',
                            x2: '676',
                            y2: '561.79999',
                            stroke: '#000',
                            fill: 'none',
                            'stroke-width': '15',
                            'stroke-linecap': 'round',
                            'stroke-linejoin': 'round'
                        }
                    }),

                    h('path', {
                        attrs: { id: 'svg_25', d: 'm33.99999,116.8', stroke: '#000', fill: 'none', 'stroke-width': '12', opacity: '1' }
                    }),
                    h('path', {
                        attrs: {
                            id: 'svg_30',
                            d: 'm402.39195,5.32761l-0.86956,-1.30435l-0.86956,1.30435l0.86956,1.30435l0.86956,-1.30435z',
                            stroke: '#000',
                            fill: 'none',
                            'stroke-width': '15'
                        }
                    })
                ])
            ]
        );
    }
}
