# FeatJAR-gui

This repository contains classes for enabling a graphical user interface.

Some functionality included in this module:


The FeatJAR-gui is building upon a [GLSP-Example](https://github.com/eclipse-glsp/glsp-examples/tree/master/project-templates/java-emf-eclipse) to use it as its Server and Client.

The GLSP-Server has been given an EMF-Model representing the featJAR-Model (bare-bones perhaps).
In order to modify the EMF-Model it is required/recommended to use [Eclipse Modeling Tools](https://www.eclipse.org/downloads/packages/release/2025-09/r/eclipse-modeling-tools).


So far the programm is able to be launched and a example-model can be loaded. The model is a Tree-Structure.
The nodes are presented as squares with edges connecting them; representing parent and child relations. The root node has a different colour then the other nodes. The spacing is also reasonable.

Code for translating a featJAR-model into an emf-model-file (and vice-versa) exists, but is not yet implemented into the server/client.


---
# Dokumentation
Wir erstellten in einem GLSP-Java/EMF-Server Feature-Knoten (semantisch: featJAR.Feature) und visualisierten sie als GNodes im Diagramm. Das Anlegen erfolgte per Operation vom Client; die Darstellung entstand über eine GModel-Factory aus dem (EMF-)Semantik- und Notationsmodell.

Datei 1: CreateTaskNodeHandler
Zweck: Reagierte auf CreateNodeOperation und lag einen neuen Feature-Knoten an.
* Erbt von EMFCreateOperationHandler<CreateNodeOperation> und ist für Typ TaskListModelTypes.OBLIGATORY_FEATURE registrierte.
* Holt aus EMFNotationModelState:
    * Semantik: Wurzel-Feature (FeatJAR/Ecore).
    * Notation: Diagram (Notation-Modell).
    * EditingDomain für EMF-Commands (Undo/Redo).
* Semantikerzeugung: createTask() baut ein featJAR.Feature, setzt Name („Feature“ + Zähler über setInitialName), id, Flags (optional=false, root=false).
* Kopplung Semantik↔Notation: Mit EMFIdGenerator wird eine stabile ID erzeugt; diese kommt in eine SemanticElementReference der Shape.
* Notation/Visuals: createShape(...) erzeugt eine Shape mit Position (relativ zum Container via LayoutUtil) und Größe (60×25).
* Transaktion: Zwei AddCommands (Semantik hinzufügen + Shape ins Diagramm), in einem CompoundCommandkombiniert → Undo/Redo-fähig.

Datei 2: TaskListGModelFactory
Zweck: Übersetzt Semantik + Notation in das GModel (was der Client rendert).
* Erbt von EMFNotationGModelFactory (liefert u. a. applyShapeData(...) für Position/Größe aus dem Notationsmodell).
* In fillRootElement(...):
    * Kapselt das GModelRoot als GGraph.
    * Iteriert die Kinder des semantischen Feature und baut für jedes Kind einen GNode.
* Knotentypen/Styling:
    * createOptionalFeatureNode(...) → TaskListModelTypes.OPTIONAL_FEATURE
    * createObligatoryFeatureNode(...) → TaskListModelTypes.OBLIGATORY_FEATURE(Diese Typen matchen deine CSS/Client-Views.)
* Labels & Layout: Fügt ein GLabel mit feature.getName() hinzu, Layout HBOX mit linker Padding-Option; applyShapeData übernimmt Position/Size aus Notation.
Ablauf „Knoten anlegen“
1. Client sendet CreateNodeOperation → CreateTaskNodeHandler läuft.
2. Handler erzeugt Semantik (featJAR.Feature) und Notation (Shape) und führt einen CompoundCommand aus.
3. GLSP aktualisiert das GModel über die Factory → neuer GNode mit Label erscheint im Diagramm.
Was schon funktioniert
* Konsistentes Semantik-/Notation-Modell (EMF) mit Undo/Redo.
* Id-Stabilität zwischen Semantik und Notation (EMFIdGenerator).
* Visuelle Unterscheidung optional/obligatorisch über Knotentypen; Label-Text aus Feature.name.
* Position/Größe kommen aus dem Notation-Modell (Factory ruft applyShapeData).

## Prerequisites

Following libraries/frameworks are required for the programm:

-   [Node.js](https://nodejs.org/en/) `>=20`
-   [Yarn](https://classic.yarnpkg.com/en/docs/install#debian-stable) `>=1.7.0 < 2.x.x`
-   [Java](https://www.oracle.com/java/technologies/javase-jdk11-downloads.html) `>=17 < 25`
-   [Maven](https://maven.apache.org/) `>=3.6.0`


## TODOs

* Implement GLSP
* Buttons that do something
* Drag and Dropping Nodes
* A Legend
* Loading given FeatJAR Models and saving drawn Structures
* Alternative and Mandatory Groupings 
* Cardinality


## Building the examples & project templates

To build the GLSP part of the project simply execute the following in the repository root:

```bash
yarn build
```

The overall project is using a gradle wrapper, but is currently not working.

## More information

[FeatJAR](https://github.com/FeatureIDE/FeatJAR)
For more information, please visit the [Eclipse GLSP Umbrella repository](https://github.com/eclipse-glsp/glsp) and the [Eclipse GLSP Website](https://www.eclipse.org/glsp/).
If you have questions, please raise them in the [discussions](https://github.com/eclipse-glsp/glsp/discussions) and have a look at our [communication and support options](https://www.eclipse.org/glsp/contact/).

## License

This repository belongs to [FeatJAR](https://github.com/FeatureIDE/FeatJAR), a collection of Java libraries for feature-oriented software development.
FeatJAR is released under the GNU Lesser General Public License v3.0.
