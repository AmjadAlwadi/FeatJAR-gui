/*
 * Copyright (C) 2025 FeatJAR-Development-Team
 *
 * This file is part of FeatJAR-gui.
 *
 * gui is free software: you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3.0 of the License,
 * or (at your option) any later version.
 *
 * gui is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 * See the GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with gui. If not, see <https://www.gnu.org/licenses/>.
 *
 * See <https://github.com/FeatureIDE/FeatJAR-gui> for further information.
 */
package de.featjar.gui;

import de.featjar.base.FeatJAR;
import de.featjar.base.data.identifier.Identifiers;
import de.featjar.base.io.IO;
import de.featjar.base.tree.Trees;
import de.featjar.base.tree.visitor.TreePrinter;
import de.featjar.feature.model.FeatureModel;
import de.featjar.feature.model.IConstraint;
import de.featjar.feature.model.IFeature;
import de.featjar.feature.model.IFeatureModel;
import de.featjar.feature.model.IFeatureTree;
import de.featjar.feature.model.io.uvl.UVLFeatureModelFormat;
import de.featjar.formula.structure.Expressions;

import java.nio.file.Paths;

public class Gui {

    public static void main(String[] args) {
        FeatJAR.defaultConfiguration().initialize();

        IFeatureModel newFeatureModel = new FeatureModel(Identifiers.newCounterIdentifier());
        newFeatureModel.mutate().setName("My Model");
        newFeatureModel.mutate().setDescription("awesome description");
        IFeature rootFeature = newFeatureModel.mutate().addFeature("root");
        IFeature childFeature = newFeatureModel.mutate().addFeature("child1");
        IFeature childFeature2 = newFeatureModel.mutate().addFeature("child2");
        IFeature childFeature3 = newFeatureModel.mutate().addFeature("child3");
        IFeature childFeature4 = newFeatureModel.mutate().addFeature("child4");   
        IFeature childchild2Feature = newFeatureModel.mutate().addFeature("child5");
        IFeature childchild3Feature = newFeatureModel.mutate().addFeature("child6");

        IFeatureTree rootTree = newFeatureModel.mutate().addFeatureTreeRoot(rootFeature);
        IFeatureTree childTree = rootTree.mutate().addFeatureBelow(childFeature);
        
        rootTree.mutate().addFeatureBelow(childFeature2);
        rootTree.mutate().addFeatureBelow(childFeature3);
        rootTree.mutate().addFeatureBelow(childFeature4);
        childTree.mutate().addFeatureBelow(childchild2Feature);
        childTree.mutate().addFeatureBelow(childchild3Feature);

        IConstraint constraint1 = newFeatureModel.mutate().addConstraint(Expressions.True);
        IConstraint constraint2 = newFeatureModel.mutate().addConstraint(Expressions.True);
        IConstraint constraint3 = newFeatureModel.mutate().addConstraint(Expressions.False);
        
        FeatJAR.log().message("\n" + Trees.traverse(rootTree, new TreePrinter()).get());
        
        IFeatureModel loadedFeatureModel = IO.load(
                        Paths.get("../formula/src/testFixtures/resources/formats/uvl/ABC-nAnBnC_01.uvl"),
                        new UVLFeatureModelFormat())
                .orElseThrow();
        
	    IFeatureModel loadedFeatureModel2 = IO.load(
	    		  		Paths.get("../formula/src/testFixtures/resources/formats/uvl/nA_01.uvl"),
	    		  		new UVLFeatureModelFormat())
	    		.orElseThrow();
	    
        FeatJAR.log()
                .message("\n"
                        + Trees.traverse(loadedFeatureModel.getRoots().get(0), new TreePrinter())
                                .get());
        
        TranslatorToEMF.EMFTranslate(newFeatureModel);
        TranslatorToEMF.EMFTranslate(loadedFeatureModel);
        TranslatorToEMF.EMFTranslate(loadedFeatureModel2);
	    
        IFeatureModel myChild = TranslatorFromEMF.FMTranslation("./src/main/java/de/featjar/gui/EMFxmls/My Model.featuremodel");
        
        if (myChild == null) {
        	System.err.println("Failed to create FeatJAT-Model from EMF");
        	return;
        }
        
        IFeatureTree newRoot = myChild.getRoots().get(0);
        FeatJAR.log().message("\n" + Trees.traverse(newRoot, new TreePrinter()).get());
    }
}
