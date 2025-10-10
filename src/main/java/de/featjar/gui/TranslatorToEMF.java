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

import de.featjar.feature.model.IConstraint;
import de.featjar.feature.model.IFeatureModel;
import de.featjar.feature.model.IFeatureTree;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Collection;
import java.util.List;

public class TranslatorToEMF {
	public static boolean EMFTranslate(IFeatureModel source) {
    	String name = source.getName().get();
    	String id = source.getIdentifier().toString();
    	
    	//Creating new File for the GLSP-Client/Server to read
    	
    	String filename = "./src/main/java/de/featjar/gui/EMFxmls";
    	Path path = Paths.get(filename);
    	EMFFileDir(path);
    	
    	filename = "./src/main/java/de/featjar/gui/EMFxmls/" + name + ".tasklist";
        File file = new File(filename);
        path = Paths.get(filename);
        
        if (file != null) {
            try {
            	file.createNewFile();
            } catch (IOException e) {
            	System.err.println("Error creating" + file.toString());
            }
            if (!(file.isFile() && file.canWrite() && file.canRead())) {
            	return false;
            }
            System.out.println(file + " created");
        }
        
        //clearing the file in case somethings in there
        try (FileWriter fileWriter = new FileWriter(file, false)) {
            fileWriter.write("");
        } catch (IOException e) {
        	System.err.println("Error creating" + file.toString());
        }

        //writing preamble
        String input = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n\n"
        					+ "<featJAR:FeatureModel id=\">" + id + "\" name=\"" + name + "\" "
        					+ "xmi:version=\"2.0\"\nxmlns:xmi=\"http://www.omg.org/XMI\"\n"
        					+ "xmlns:featJAR=\"http://www.example.org/featJAR\">\n\n";
        try {
        	Files.writeString(path, input, StandardOpenOption.WRITE);
        } catch (IOException e) {
            System.err.println("Error creating" + file.toString());
        }

        //getting the roots (should only be one) and writing them down
    	List<IFeatureTree> rootNodes = source.getRoots();
    	for (IFeatureTree root : rootNodes) {
    		EMFaddRoot(root, path, file);
    	}

    	//getting the constraints
    	EMFaddConstraints(source, path, file);

    	//closing element
    	input = "</featJAR:FeatureModel>";
    	try {
        	Files.writeString(path, input, StandardOpenOption.APPEND);
        } catch (IOException e) {
            System.err.println("Error creating" + file.toString());
        }

    	return true;
	}

	public static void EMFaddRoot(IFeatureTree root, Path path, File file) {
		//fetching info about root
		String rootName = root.getFeature().getName().get();
		String rootId = root.getFeature().getIdentifier().toString();

		//writing info down
		String input = "<root\nid = \"" + rootId + "\"\noptional=\"false\"\nname=\"" + rootName + "\">\n\n";
		try {
        	Files.writeString(path, input, StandardOpenOption.APPEND);
        } catch (IOException e) {
            System.err.println("Error creating" + file.toString());
        }

		//getting the children
		List<? extends IFeatureTree> childTree = root.getChildren();
		for (IFeatureTree feature : childTree) {
			EMFaddFeatures(feature, path, file);
		}

		//closing element
		input = "</root>\n\n";
		try {
	    	Files.writeString(path, input, StandardOpenOption.APPEND);
	    } catch (IOException e) {
	        System.err.println("Error creating" + file.toString());
	    }
	}

	public static void EMFaddFeatures(IFeatureTree feature, Path path, File file) {
		//fetching info about node 
		String featureName = feature.getFeature().getName().get();
		String featureId = feature.getFeature().getIdentifier().toString();
		boolean optional = feature.isOptional();
		int amountChildren = feature.getChildrenCount();

		//writing info down
		String input = "<features\nid = \"" + featureId + "\"\noptional=\"" + optional + "\"\nname=\"" + featureName + "\"";
		try {
        	Files.writeString(path, input, StandardOpenOption.APPEND);
        } catch (IOException e) {
            System.err.println("Error creating" + file.toString());
        }

		//determine if we can already close the element
    	if(amountChildren == 0) {
    		input = "/>\n\n";
    	} else {
	    	input = ">\n\n";
    	}

		try {
        	Files.writeString(path, input, StandardOpenOption.APPEND);
        } catch (IOException e) {
            System.err.println("Error creating" + file.toString());
        }

		//getting the children
		List<? extends IFeatureTree> childTree = feature.getChildren();
    	for (IFeatureTree child : childTree) {
    		EMFaddFeatures(child, path, file);
    	}

    	//determine if we should already close the element
    	if(amountChildren != 0) {
    		input = "</features>\n\n";
    		try {
            	Files.writeString(path, input, StandardOpenOption.APPEND);
            } catch (IOException e) {
                System.err.println("Error creating" + file.toString());
            }
    	}
    }

	public static void EMFaddConstraints(IFeatureModel source, Path path, File file) {
		//fetching constraints
		Collection<IConstraint> cons = source.getConstraints();
		for (IConstraint con : cons) {
			String conName = con.getName().get();
			String conId = con.getIdentifier().toString();
			String input = "<constraints\nid = \"" + conId + "\"\nname=\"" + conName + "\"/>\n\n";
			try {
	        	Files.writeString(path, input, StandardOpenOption.APPEND);
	        } catch (IOException e) {
	            System.err.println("Error creating" + file.toString());
	        }
		}
    }
	
	public static void EMFFileDir(Path path) {
		try {
			Files.createDirectories(path);
		} catch (IOException e) {
			System.err.println("Error creating Directory");
		}
	}
}
