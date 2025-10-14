import {
    configureDefaultModelElements,
    ConsoleLogger,
    ContainerConfiguration,
    initializeDiagramContainer,
    LogLevel,
    TYPES,
    configureModelElement,
    GNode
} from '@eclipse-glsp/client';
import { Container, ContainerModule } from 'inversify';
import '../css/diagram.css';
import { MyCustomShapeView } from './my-custom-shape-view';

const taskListDiagramModule = new ContainerModule((bind, unbind, isBound, rebind) => {
    rebind(TYPES.ILogger).to(ConsoleLogger).inSingletonScope();
    rebind(TYPES.LogLevel).toConstantValue(LogLevel.warn);

    const context = { bind, isBound }; // ✅ only these are needed
    configureDefaultModelElements(context);

    // ✅ Register your custom shape (older GLSP API)
    configureModelElement(context, 'new-shape-type', GNode, MyCustomShapeView);
});

export function initializeTasklistDiagramContainer(container: Container, ...containerConfiguration: ContainerConfiguration): Container {
    return initializeDiagramContainer(container, taskListDiagramModule, ...containerConfiguration);
}
