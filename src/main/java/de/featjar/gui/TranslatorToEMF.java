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
	private static int idCounter = 0;
	private static String dir = "./src/main/java/de/featjar/gui/EMFxmls";
	
	private static String giveID() {
		idCounter++;
		return String.valueOf(idCounter);
	}
	
	public static void EMFTranslate(IFeatureModel source) {
    	String name = source.getName().get();
    	//String id = source.getIdentifier().toString();
    	List<IFeatureTree> rootNodes = source.getRoots();
    	
    	//Creating new File for the GLSP-Client/Server to read
    	Path path = Paths.get(dir);
    	EMFFileDir(path);
    	
    	String filename = dir + "/" + name + ".featuremodel";
        path = Paths.get(filename);
        
        EMFFiles(filename, name);

        //writing preamble
        String input = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n\n"
        					+ "<featJAR:FeatureModel id=\">" + giveID() + "\" name=\"" + name + "\" "
        					+ "xmi:version=\"2.0\"\nxmlns:xmi=\"http://www.omg.org/XMI\"\n"
        					+ "xmlns:featJAR=\"http://www.example.org/featJAR\"";
        
        if (rootNodes.isEmpty()) {
        	input += " />\n\n";
        } else {
        	input += " >\n\n";
        }
        
        try {
        	Files.writeString(path, input, StandardOpenOption.WRITE);
        } catch (IOException e) {
            System.err.println("Error creating" + path.toString());
        }
        
        if (rootNodes.isEmpty()) {
        	return;
        }

        //going over the roots (should only be one) and writing them down
    	for (IFeatureTree root : rootNodes) {
    		EMFaddRoot(root, path);
    	}

    	//getting the constraints
    	EMFaddConstraints(source, path);

    	//closing element
    	input = "</featJAR:FeatureModel>";
    	try {
        	Files.writeString(path, input, StandardOpenOption.APPEND);
        } catch (IOException e) {
            System.err.println("Error creating" + path.toString());
        }
	}

	public static void EMFaddRoot(IFeatureTree root, Path path) {
		//fetching info about root
		String rootName = root.getFeature().getName().get();
		//String rootId = root.getFeature().getIdentifier().toString();
		List<? extends IFeatureTree> childTree = root.getChildren();
		//writing info down
		String input = "<root id = \"" + giveID() + "\" name = \"" + rootName + "\" optional = \"false\"";
		
		if (childTree.isEmpty()) {
			input += " />\n\n";
		} else {
			input += " >\n\n";
		}
		
		try {
        	Files.writeString(path, input, StandardOpenOption.APPEND);
        } catch (IOException e) {
            System.err.println("Error creating" + path.toString());
        }
		
		//check for early return
		if(childTree.isEmpty()) {
    		return;
    	}

		//getting the children
		for (IFeatureTree feature : childTree) {
			EMFaddFeatures(feature, path);
		}

		//closing element
		input = "</root>\n\n";
		try {
	    	Files.writeString(path, input, StandardOpenOption.APPEND);
	    } catch (IOException e) {
	        System.err.println("Error creating" + path.toString());
	    }
	}

	public static void EMFaddFeatures(IFeatureTree feature, Path path) {
		//fetching info about node 
		String featureName = feature.getFeature().getName().get();
		//String featureId = feature.getFeature().getIdentifier().toString();
		boolean optional = feature.isOptional();
		List<? extends IFeatureTree> childTree = feature.getChildren();
		
		//writing info down
		String input = "<features id = \"" + giveID() + "\" name = \"" + featureName + "\" optional = \"" + optional + "\"";

		//determine if we can already close the element
    	if(childTree.isEmpty()) {
    		input += " />\n\n";
    	} else {
	    	input += " >\n\n";
    	}

		try {
        	Files.writeString(path, input, StandardOpenOption.APPEND);
        } catch (IOException e) {
            System.err.println("Error creating" + path.toString());
        }
		
		if(childTree.isEmpty()) {
    		return;
    	}

		//getting the children
    	for (IFeatureTree child : childTree) {
    		EMFaddFeatures(child, path);
    	}

    	//determine if we should already close the element
		input = "</features>\n\n";
		try {
        	Files.writeString(path, input, StandardOpenOption.APPEND);
        } catch (IOException e) {
            System.err.println("Error creating" + path.toString());
        }
    }

	public static void EMFaddConstraints(IFeatureModel source, Path path) {
		//fetching constraints
		Collection<IConstraint> cons = source.getConstraints();
		for (IConstraint con : cons) {
			String conName = con.getName().get();
			//String conId = con.getIdentifier().toString();
			String input = "<constraints id = \"" + giveID() + "\" name = \"" + conName + "\" />\n\n";
			try {
	        	Files.writeString(path, input, StandardOpenOption.APPEND);
	        } catch (IOException e) {
	            System.err.println("Error creating" + path.toString());
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
	
	//writing the necessary files 
	public static void EMFFiles(String filename, String name) {
		 File file = new File(filename);
		
		if (file != null) {
           try {
           	file.createNewFile();
           } catch (IOException e) {
           	System.err.println("Error creating" + file.toString());
           }
           if (!(file.isFile() && file.canWrite() && file.canRead())) {
           	return;
           }
           System.out.println(file + " created");
       }
		
		//clearing the file in case somethings in there
       try (FileWriter fileWriter = new FileWriter(file, false)) {
           fileWriter.write("");
       } catch (IOException e) {
       	System.err.println("Error creating" + file.toString());
       }
		
       String filename2 = dir + "/" + name + ".notation";
       File file2 = new File(filename2);
       
       if (file2 != null) {
           try {
           	file2.createNewFile();
           } catch (IOException e) {
           	System.err.println("Error creating" + file2.toString());
           }
           if (!(file2.isFile() && file2.canWrite() && file2.canRead())) {
           	return;
           }
           System.out.println(file2 + " created");
       }
       
       try (FileWriter fileWriter = new FileWriter(file2, false)) {
           fileWriter.write("<?xml version=\"1.0\" encoding=\"ASCII\"?>\n"
           		+ "<notation:Diagram xmi:version=\"2.0\" xmlns:xmi=\"http://www.omg.org/XMI\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:notation=\"http://www.eclipse.org/glsp/notation\">\n"
           		+ "<semanticElement elementId=\"root\"/>\n"
           		+ "<elements xsi:type=\"notation:Shape\">\n"
           		+ "<semanticElement elementId=\"2f4667d1-97ac-4d40-92de-581ebfa12cbb\"/>\n"
           		+ "<position x=\"333.0\" y=\"201.0\"/>\n"
           		+ "<size width=\"79.34375\" height=\"25.0\"/>\n"
           		+ "</elements>\n"
           		+ "<elements xsi:type=\"notation:Shape\">\n"
           		+ "<semanticElement elementId=\"0be70207-1f24-4213-98fb-433506118b52\"/>\n"
           		+ "<position x=\"405.0\" y=\"121.0\"/>\n"
           		+ "<size width=\"135.1875\" height=\"25.0\"/>\n"
           		+ "</elements>\n"
           		+ "<elements xsi:type=\"notation:Shape\">\n"
           		+ "<semanticElement elementId=\"2525269f-c7c6-48cd-9c0e-552c6228f13d\"/>\n"
           		+ "<position x=\"180.0\" y=\"128.0\"/>\n"
           		+ "<size width=\"125.375\" height=\"25.0\"/>\n"
           		+ "</elements>\n"
           		+ "</notation:Diagram>");
       } catch (IOException e) {
       	System.err.println("Error creating" + file2.toString());
       }
	}
}
