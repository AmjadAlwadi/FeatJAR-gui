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




# FeatJAR-gui

This repository contains classes for enabling a graphical user interface.
The FeatJAR-gui is building upon a [GLSP-Example](https://github.com/eclipse-glsp/glsp-examples/tree/master/project-templates/java-emf-eclipse) to use it as its server and client.

## Ziel

Ein kompakter Editor, der featJAR-Featuremodelle als interaktiven Baum rendert, optional/obligatorisch klar kennzeichnet und Cross-Tree-Constraints gut lesbar darstellt.


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

* Code for translating a featJAR-model into an EMF-model-file (and vice-versa) exists, but is not yet implemented into the server/client.

## Prerequisites

Following libraries/frameworks are required for the programm:

-   [Node.js](https://nodejs.org/en/) `>=20`
-   [Yarn](https://classic.yarnpkg.com/en/docs/install#debian-stable) `>=1.7.0 < 2.x.x`
-   [Java](https://www.oracle.com/java/technologies/javase-jdk11-downloads.html) `>=17 < 25`
-   [Maven](https://maven.apache.org/) `>=3.6.0`

## TODOs

* Buttons that do something
* Drag and Dropping Nodes
* Loading given FeatJAR Models and saving drawn Structures
* Alternative and Mandatory Groupings 
* Cardinality

## EMF-Model

The GLSP-server has been given an EMF-Model representing the featJAR-Model (although bare-bones perhaps). The model itself is in `FeatJAR/FeatJAR-gui/src/main/model`.
In order to modify the EMF-model it is required/recommended to use [Eclipse Modeling Tools](https://www.eclipse.org/downloads/packages/release/2025-09/r/eclipse-modeling-tools).
In Eclipse you can import the project as a general existing project. If the class-diagramm of the model doesn´t show up, you can go to
`featJAR/model/featJAR.aird/Design/Entities in a Class Diagramm/featJAR` and double click on that.
Based on the class-diagramm you can click on `featJAR/model/featJAR.genmodel`, right-click on FeatJAR in the newly opened tab and click on `Genereate Model Code`.

## Building the project

To build the GLSP part of the project simply execute the following in the repository root:

```bash
yarn build
```

The overall project is using a gradle wrapper, but is currently not working.

## Running/Debugging the example

To test the Tasklist diagram editor a launch configuration is provided. In your [Eclipse Workspace](#eclipse-workspace) navigate to the
`org.eclipse.glsp.example.javaemf.editor` plugin. Start or debug the example by via right-clicking on the `TaskListEditor.launch` file (`Run as -> TaskListEditor`).

This opens a second instance of Eclipse, which has the GLSP task list editor plugins preinstalled.
Import the provided [`example project`](glsp-server/workspace/TaskListExample/) into this workspace and double click on the `example.tasklist` file to open the diagram editor.

## Running server with other clients

Currently the server is not expecting/waiting for connections from other clients like Theia or VSCode; it can only be started with `TaskListEditor.launch`.

## Bugs

Removing elements from the EMF-Model (in Eclipse Modeling Tools) may cause errors, when trying to generated the source-code.
In that case you have to delete `featJAR/src-gen` and generate the model-code again; or you may have to redo the whole ecore-project.

The Auto-layouting the server does at the start, is not working as intended; it messes up the positions of feature-nodes of parents with more than three children.

# Feature-Model-Editor (GLSP/EMF)

Visualisierung eines Feature-Modells (featJAR) als Baumdiagramm mit:
- farbcodierten Knoten für **Root**, **obligatorische** und **optionale** Features
- **Markerpunkten** (gefüllt = optional, hohl = obligatorisch/root) oberhalb des Labels
- **Constraints-Box**, dynamisch unter dem letzten Blatt und horizontal am Root ausgerichtet


## Überblick & Funktionen

- **Auto-Layout** der Feature-Baumstruktur
- **Marker** oberhalb des Labels:
  - gefüllt = optional
  - hohl = obligatorisch bzw. Root
- **Constraints-Box**:
  - **X-Position:** am Root zentriert (bleibt “in der Mitte” des Baums)
  - **Y-Position:** dynamisch **unter dem rechten/letzten Blatt** (wächst mit dem Baum mit)

---

## Technik / Architektur

- **Server:** GLSP + EMF
- **Modell:** `featJAR.FeatureModel` (EMF)
- **Darstellung:** `FeatureModelGModelFactory` erzeugt `GNode`/`GEdge`, `FeatureTreeLayouter` berechnet Positionen
- **Styling:** CSS-Klassen für Root/Obligatorisch/Optional + Marker

---

## Wesentliche Änderungen

### Styling (CSS)
- Konsistente Palette & Schatten
- Klassen:
  - `.feature-node-root`, `.feature-node-obligatory`, `.feature-node-optional`
  - `.feature-marker`, `.feature-marker-optional`, `.feature-marker-mandatory`
- Marker als SVG-Kreis mit klaren Stroke/Fill-Regeln

### Server-Code

**`FeatureModelGModelFactory`**
- Knotenaufbau: VBox-Layout, Marker (Kreis) + Label
- Kinderauflösung robust: `Feature → groups → features` **oder** `Feature → features`
- Kanten als `GEdge`
- Constraints-Box:
  - X am Root ausgerichtet
  - Y via Anker unter rechtem Blatt (mit `marginY` Abstand)

**`FeatureTreeLayouter`**
- In-order-Layout (Blätter erhalten fortlaufend X, Y = Ebene)
- Interne Knoten zentrieren sich über ihren Kindern
- Helfer:
  - `findRightmostLeaf`
  - `computeAnchorBelowRightmostLeaf`
  - Mapping TreeNode ↔︎ GNode

---

## Konfiguration & Tuning

In `FeatureModelGModelFactory`:
- `horizontalGap`, `verticalGap`: Abstände zwischen Knoten
- `nodeWidth`, `nodeHeight`: Mindestgröße Knoten
- Marker-Größe: `size(16, 16)` im Kreis-Shape
- Constraints-Abstand: `marginY` beim Ankerpunkt

---

## Troubleshooting

- **JFace/SWT-Icons fehlen** (`/icons/full/eview16/*.svg`):  
  Fehlende Eclipse-Plug-in-Ressourcen; kosmetisch, Diagramm funktioniert dennoch.
- **Log4j/SLF4J Warnungen**:  
  Logging-Thema (keine Implementierung/Provider gefunden); Funktionalität nicht betroffen.
- **Marker werden nicht angezeigt**:  
  Prüfen, ob das Marker-Shape (`shape:circle`) erzeugt und die CSS-Klassen geladen werden.

---

## Was (noch) nicht geklappt hat

Wir wollten zusätzlich **zwei weitere Clients** bereitstellen:
- **VS Code-Client**
- **Theia-Client**

Beide Setups sind bislang gescheitert (typische Ursachen: Client-Dependencies, Packaging, WebSocket/Endpoint/Diagrammtyp-Konfiguration).  





# Feature-Model-Editor (GLSP/EMF)

Visualisierung eines Feature-Modells (featJAR) als Baumdiagramm mit:
- farbcodierten Knoten für **Root**, **obligatorische** und **optionale** Features
- **Markerpunkten** (gefüllt = optional, hohl = obligatorisch/root) oberhalb des Labels
- **Constraints-Box**, dynamisch unter dem letzten Blatt und horizontal am Root ausgerichtet



## Ziel

Ein kompakter Editor, der featJAR-Featuremodelle als interaktiven Baum rendert, optional/obligatorisch klar kennzeichnet und Cross-Tree-Constraints gut lesbar darstellt.

---

## Überblick & Funktionen

- **Auto-Layout** der Feature-Baumstruktur
- **Marker** oberhalb des Labels:
  - gefüllt = optional
  - hohl = obligatorisch bzw. Root
- **Constraints-Box**:
  - **X-Position:** am Root zentriert (bleibt “in der Mitte” des Baums)
  - **Y-Position:** dynamisch **unter dem rechten/letzten Blatt** (wächst mit dem Baum mit)

---

## Technik / Architektur

- **Server:** GLSP + EMF
- **Modell:** `featJAR.FeatureModel` (EMF)
- **Darstellung:** `FeatureModelGModelFactory` erzeugt `GNode`/`GEdge`, `FeatureTreeLayouter` berechnet Positionen
- **Styling:** CSS-Klassen für Root/Obligatorisch/Optional + Marker

---

## Wesentliche Änderungen

### Styling (CSS)
- Konsistente Palette & Schatten
- Klassen:
  - `.feature-node-root`, `.feature-node-obligatory`, `.feature-node-optional`
  - `.feature-marker`, `.feature-marker-optional`, `.feature-marker-mandatory`
- Marker als SVG-Kreis mit klaren Stroke/Fill-Regeln

### Server-Code

**`FeatureModelGModelFactory`**
- Knotenaufbau: VBox-Layout, Marker (Kreis) + Label
- Kinderauflösung robust: `Feature → groups → features` **oder** `Feature → features`
- Kanten als `GEdge`
- Constraints-Box:
  - X am Root ausgerichtet
  - Y via Anker unter rechtem Blatt (mit `marginY` Abstand)

**`FeatureTreeLayouter`**
- In-order-Layout (Blätter erhalten fortlaufend X, Y = Ebene)
- Interne Knoten zentrieren sich über ihren Kindern
- Helfer:
  - `findRightmostLeaf`
  - `computeAnchorBelowRightmostLeaf`
  - Mapping TreeNode ↔︎ GNode

---

## Konfiguration & Tuning

In `FeatureModelGModelFactory`:
- `horizontalGap`, `verticalGap`: Abstände zwischen Knoten
- `nodeWidth`, `nodeHeight`: Mindestgröße Knoten
- Marker-Größe: `size(16, 16)` im Kreis-Shape
- Constraints-Abstand: `marginY` beim Ankerpunkt

---

## Troubleshooting

- **JFace/SWT-Icons fehlen** (`/icons/full/eview16/*.svg`):  
  Fehlende Eclipse-Plug-in-Ressourcen; kosmetisch, Diagramm funktioniert dennoch.
- **Log4j/SLF4J Warnungen**:  
  Logging-Thema (keine Implementierung/Provider gefunden); Funktionalität nicht betroffen.
- **Marker werden nicht angezeigt**:  
  Prüfen, ob das Marker-Shape (`shape:circle`) erzeugt und die CSS-Klassen geladen werden.

---

## Was (noch) nicht geklappt hat

Wir wollten zusätzlich **zwei weitere Clients** bereitstellen:
- **VS Code-Client**
- **Theia-Client**

Beide Setups sind bislang gescheitert (typische Ursachen: Client-Dependencies, Packaging, WebSocket/Endpoint/Diagrammtyp-Konfiguration).  
To-Dos:
1. Versionsabgleich GLSP-Server ↔︎ Client-Pakete (Theia/VS Code).
2. Minimalbeispiel-Client (GLSP Example) starten und unseren Diagrammtyp registrieren.
3. Build/Run-Skripte fixen (yarn/npm, Peer-Dependencies).
4. Webview/DevTools-Logs prüfen (404/WS-Fehler).


