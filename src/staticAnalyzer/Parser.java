package staticAnalyzer;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.bcel.classfile.ClassFormatException;
import org.apache.bcel.classfile.ClassParser;
import org.apache.bcel.classfile.JavaClass;

public class Parser {

	ArrayList<JavaClass> analysisDatas;

	public Parser(ArrayList<File> classFiles) {
		analysisDatas = new ArrayList<JavaClass>(classFiles.size());
		for(File file: classFiles){
			try {
				analysisDatas.add(new ClassParser(file.getAbsolutePath()).parse());
			} catch (ClassFormatException | IOException e) {
				e.printStackTrace();
			}
		}
	}

	public ArrayList<JavaClass> getAnalysisDatas() {
		return analysisDatas;
	}
}
