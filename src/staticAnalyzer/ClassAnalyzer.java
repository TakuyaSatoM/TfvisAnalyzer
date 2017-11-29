package staticAnalyzer;

import java.io.File;
import java.util.ArrayList;

import org.apache.bcel.classfile.JavaClass;

public class ClassAnalyzer {
	ArrayList<JavaClass> analysisData;

	public ClassAnalyzer(ArrayList<File> classFiles) {
		analysisData = new Parser(classFiles).getAnalysisDatas();
	}

	// 以下、テスト用アクセサ
	public ArrayList<JavaClass> getClassData() {
		return analysisData;
	}
}
