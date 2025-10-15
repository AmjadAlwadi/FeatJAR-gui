import {
    configureDefaultModelElements,
    configureModelElement,
    ConsoleLogger,
    ContainerConfiguration,
    GNode,
    initializeDiagramContainer,
    LogLevel,
    TYPES
} from '@eclipse-glsp/client';
import { Container, ContainerModule } from 'inversify';
import '../css/diagram.css';
import { orGroup } from './or-group';
import { trueGroup } from './true-group';
import { xorGroup } from './xor-group';

const taskListDiagramModule = new ContainerModule((bind, unbind, isBound, rebind) => {
    rebind(TYPES.ILogger).to(ConsoleLogger).inSingletonScope();
    rebind(TYPES.LogLevel).toConstantValue(LogLevel.warn);

    const context = { bind, isBound };
    configureDefaultModelElements(context);

    configureModelElement(context, 'xorGroup', GNode, xorGroup);
    configureModelElement(context, 'trueGroup', GNode, trueGroup);
    configureModelElement(context, 'orGroup', GNode, orGroup);
});

export function initializeTasklistDiagramContainer(container: Container, ...containerConfiguration: ContainerConfiguration): Container {
    return initializeDiagramContainer(container, taskListDiagramModule, ...containerConfiguration);
}
