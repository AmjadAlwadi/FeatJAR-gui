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

import de.featjar.base.data.identifier.Identifiers;
import de.featjar.feature.model.FeatureModel;
import de.featjar.feature.model.IConstraint;
import de.featjar.feature.model.IFeature;
import de.featjar.feature.model.IFeatureModel;
import de.featjar.feature.model.IFeatureTree;
import de.featjar.formula.io.textual.Symbols;
import de.featjar.formula.structure.Expressions;
import de.featjar.formula.structure.IExpression;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class TranslatorFromEMF {
	public static IFeatureModel FMTranslation(String file) {
		//making a new Model
		IFeatureModel newFeatureModel = new FeatureModel(Identifiers.newCounterIdentifier());
		File emf = new File(file);
		
		//creating a reader
		try {
			BufferedReader reader = new BufferedReader(new FileReader(emf));
			//skipping the preamble
			String stop = readEMFFileAfterChar(reader, '<');
			stop = readEMFFileAfterChar(reader, '<');
			String name = readEMFFileCapsulatedWord(reader, '"');
			name = readEMFFileCapsulatedWord(reader, '"');

			//adding to the model
			if (stop.compareTo("feat") == 0) {
				newFeatureModel.mutate().setName(name);
		        newFeatureModel.mutate().setDescription("awesome description");
		        //System.out.println("created model");
			} else {
				throw new IOException();
			}
			
			//skipping lines
			readEMFFileCapsulatedWord(reader, '"');
			readEMFFileCapsulatedWord(reader, '"');
			readEMFFileCapsulatedWord(reader, '"');
			
			//check for an early end
			if (readEMFFileCheckingClosingTag(reader)) {
				return newFeatureModel;
			}

			//getting the roots (which should only be one)
			while (!((stop = readEMFFileAfterChar(reader, '<')).isBlank())) {
				if (stop.compareTo("root") == 0) {
					FMaddRoot(newFeatureModel, reader);
				} else if (stop.compareTo("/fea") == 0) {
					return newFeatureModel;
				} else if (stop.compareTo("cons") == 0) {
					//TODO: constraints
					//FMaddConstraint(newFeatureModel, reader);
				} else {
					throw new IOException();
				}
			}
		} catch (IOException e) {
			System.err.println("Error reading " + file.toString());
		}
		return null;
	}

	public static void FMaddRoot(IFeatureModel goal, BufferedReader reader) throws IOException {
		String name = readEMFFileCapsulatedWord(reader, '"');
		name = readEMFFileCapsulatedWord(reader, '"');
		IFeature rootFeature = goal.mutate().addFeature(name);
		IFeatureTree rootTree = goal.mutate().addFeatureTreeRoot(rootFeature);
		//System.out.println("added root");
		
		//check for early end
		if (readEMFFileCheckingClosingTag(reader)) {
			return;
		}

		//getting the kids 
		String stop;
		while (!((stop = readEMFFileAfterChar(reader, '<')).isBlank())) {
			if (stop.compareTo("feat") == 0) {
				FMaddFeature(goal, rootTree, reader);
			} else if (stop.compareTo("/roo") == 0) {
				return;
			} else {
				throw new IOException();
			}
		}
		throw new IOException();
	}

	public static void FMaddFeature(IFeatureModel goal, IFeatureTree hook, BufferedReader reader) throws IOException {
		String name = readEMFFileCapsulatedWord(reader, '"');
		name = readEMFFileCapsulatedWord(reader, '"');
		String optional = readEMFFileCapsulatedWord(reader, '"');
		IFeature feature = goal.mutate().addFeature(name);
		IFeatureTree tree = hook.mutate().addFeatureBelow(feature);
		
		if (optional.compareTo("true") == 0) {
			tree.mutate().makeOptional();
		}
		//System.out.println("added feature");
		
		if (readEMFFileCheckingClosingTag(reader)) {
			return;
		}
		
		try {			
			String stop;
		    while (!((stop = readEMFFileAfterChar(reader, '<')).isBlank())) {
		    	//continue adding children...
		    	if (stop.compareTo("feat") == 0) {
					FMaddFeature(goal, tree, reader);
				//unless closing element is read
				} else if (stop.compareTo("/fea") == 0) {
					return;
				} else {
					throw new IOException();
				}
		    	
		    	if (readEMFFileCheckingClosingTag(reader)) {
					return;
				}
		    }
		} catch (IOException e) {
			System.err.println("Error reading the file: Read something unexpected");
		}
		
	}
	/*
	public static void FMaddConstraint(IFeatureModel goal, BufferedReader reader) {
		IConstraint constraint1 = goal.mutate().addConstraint(Expressions.True);
		ExpressionParser parser = new ExpressionParser();
		serialiser
		parser.setSymbols();
		Result<IExpression> parse = parser.parse("");
	}
	*/
	//getting the word thats between the next instance of walls (like ", as in "word")
	public static String readEMFFileCapsulatedWord(BufferedReader reader, char walls) {
		StringBuilder result = new StringBuilder();
		int character;
		try {
			while((character = reader.read()) != -1) {
			    char ch = (char) character;
			    if(ch != walls) {
			        continue;
			    }
			    while((character = reader.read()) != -1) {
			    	ch = (char) character;
				    if(ch == walls) {
				        break;
				    }
				    result.append(ch);
			    }
			    break;
			}
		} catch (IOException e) {

		}
		return result.toString();
	}

	//getting the 4 chars after the next sign is read (used '<' as sign so far)
	// 4 because "root" is shortest word in the file
	public static String readEMFFileAfterChar(BufferedReader reader, char sign) {
		StringBuilder result = new StringBuilder();
		int character;
		try {
			while((character = reader.read()) != -1) {
			    char ch = (char) character;
			    if(ch != sign) {
			        continue;
			    }

			    for (int i = 0; i < 4; i++) {
			    	if ((character = reader.read()) == -1) {
			    		break;
			    	}
			    	ch = (char) character;
			    	result.append(ch);
			    }
			    return result.toString();
			}
		} catch (IOException e) {
			System.err.println("Error reading");
		}
		return "";
	}
	
	//checks if either / or > comes next, to determine if the element continues or not
	public static boolean readEMFFileCheckingClosingTag(BufferedReader reader) throws IOException {
		int character;
		try {
			while((character = reader.read()) != -1) {
			    char ch = (char) character;
			    if(ch == '/') {
			        return true;
			    } else if (ch == '>') {
			    	return false;
			    }
			}
		} catch (IOException e) {
			System.err.println("Error reading");
		}
		throw new IOException();
	}
}
