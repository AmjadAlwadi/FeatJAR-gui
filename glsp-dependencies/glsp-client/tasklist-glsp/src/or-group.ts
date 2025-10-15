import { ShapeView } from '@eclipse-glsp/client';
import type { VNode } from 'snabbdom';
import { h } from 'snabbdom';

export class orGroup extends ShapeView {
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
                        attrs: { id: 'svg_1', x1: '400.99999', y1: '1.8', x2: '117.99999', y2: '561.79999', stroke: '#000', fill: 'none' }
                    }),
                    h('line', {
                        attrs: { id: 'svg_2', x1: '400.99999', y1: '1.8', x2: '117.99999', y2: '561.79999', stroke: '#000', fill: 'none' }
                    }),
                    h('line', {
                        attrs: { id: 'svg_3', x1: '400.99999', y1: '1.8', x2: '117.99999', y2: '561.79999', stroke: '#000', fill: 'none' }
                    }),
                    h('line', {
                        attrs: { id: 'svg_4', x1: '400.99999', y1: '1.8', x2: '117.99999', y2: '561.79999', stroke: '#000', fill: 'none' }
                    }),
                    h('line', {
                        attrs: { id: 'svg_5', x1: '400.99999', y1: '1.8', x2: '117.99999', y2: '561.79999', stroke: '#000', fill: 'none' }
                    }),
                    h('line', {
                        attrs: { id: 'svg_6', x1: '400.99999', y1: '1.8', x2: '117.99999', y2: '561.79999', stroke: '#000', fill: 'none' }
                    }),

                    h('path', {
                        attrs: { id: 'svg_7', d: 'm400.99999,1.8l-283,559.99999', stroke: '#000', fill: 'none', 'stroke-width': '15' }
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
                            'stroke-width': '15'
                        }
                    }),

                    h('path', { attrs: { id: 'svg_25', d: 'm33.99999,116.8', stroke: '#000', fill: 'none', 'stroke-width': '12' } }),
                    h('path', {
                        attrs: {
                            id: 'svg_24',
                            d: 'm290.99999,214.8c115.46081,69 218.87354,4 217.86955,3.2',
                            stroke: '#000',
                            fill: 'none',
                            'stroke-width': '12'
                        }
                    }),
                    h('path', {
                        attrs: {
                            id: 'svg_30',
                            d: 'm402.39195,5.32761l-0.86956,-1.30435l-0.86956,1.30435l0.86956,1.30435l0.86956,-1.30435z',
                            stroke: '#000',
                            fill: 'none',
                            'stroke-width': '15'
                        }
                    }),

                    h('line', {
                        attrs: {
                            id: 'svg_32',
                            x1: '471.77418',
                            y1: '159.51614',
                            x2: '378.22579',
                            y2: '245.00001',
                            stroke: '#000',
                            fill: 'none',
                            'stroke-width': '7'
                        }
                    }),
                    h('line', {
                        attrs: {
                            id: 'svg_40',
                            x1: '462.09676',
                            y1: '131.29033',
                            x2: '347.58064',
                            y2: '236.12904',
                            stroke: '#000',
                            fill: 'none',
                            'stroke-width': '7'
                        }
                    }),
                    h('line', {
                        attrs: {
                            id: 'svg_41',
                            x1: '447.58063',
                            y1: '103.06453',
                            x2: '314.51612',
                            y2: '226.45162',
                            stroke: '#000',
                            fill: 'none',
                            'stroke-width': '7'
                        }
                    }),
                    h('line', {
                        attrs: {
                            id: 'svg_42',
                            x1: '434.67741',
                            y1: '75.64518',
                            x2: '301.6129',
                            y2: '199.03226',
                            stroke: '#000',
                            fill: 'none',
                            'stroke-width': '7'
                        }
                    }),
                    h('line', {
                        attrs: {
                            id: 'svg_43',
                            x1: '422.58063',
                            y1: '51.45163',
                            x2: '332.25806',
                            y2: '130.48388',
                            stroke: '#000',
                            fill: 'none',
                            'stroke-width': '7'
                        }
                    }),
                    h('line', {
                        attrs: {
                            id: 'svg_44',
                            x1: '415.32257',
                            y1: '26.45163',
                            x2: '366.93548',
                            y2: '70.00001',
                            stroke: '#000',
                            fill: 'none',
                            'stroke-width': '7'
                        }
                    }),
                    h('line', {
                        attrs: {
                            id: 'svg_45',
                            x1: '495.96773',
                            y1: '181.29033',
                            x2: '425.80644',
                            y2: '244.19355',
                            stroke: '#000',
                            fill: 'none',
                            'stroke-width': '7'
                        }
                    })
                ])
            ]
        );
    }
}
