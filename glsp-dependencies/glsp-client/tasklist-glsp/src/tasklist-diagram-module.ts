import {
    configureDefaultModelElements,
    ConsoleLogger,
    ContainerConfiguration,
    initializeDiagramContainer,
    LogLevel,
    TYPES,
    configureModelElement,
    editLabelFeature,
    GLabel,
    GLabelView,
    GNode,
    GPort,
    CircularNodeView,
    selectFeature
} from '@eclipse-glsp/client';
import { Container, ContainerModule } from 'inversify';
import '../css/diagram.css';
import { MyCustomShapeView } from './my-custom-shape-view';

const taskListDiagramModule = new ContainerModule((bind, unbind, isBound, rebind) => {
    rebind(TYPES.ILogger).to(ConsoleLogger).inSingletonScope();
    rebind(TYPES.LogLevel).toConstantValue(LogLevel.warn);

    const context = { bind, isBound };
    configureDefaultModelElements(context);

    configureModelElement(context, 'new-shape-type', GNode, MyCustomShapeView);
    configureModelElement(context, 'label:heading', GLabel, GLabelView, {enable : [editLabelFeature]});
    configureModelElement(context, 'event:port', GPort, CircularNodeView, { disable: [selectFeature] });

});

export function initializeTasklistDiagramContainer(container: Container, ...containerConfiguration: ContainerConfiguration): Container {
    return initializeDiagramContainer(container, taskListDiagramModule, ...containerConfiguration);
}