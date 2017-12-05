package staticAnalyzer;

import java.io.File;
import java.util.ArrayList;

import org.apache.bcel.classfile.Code;
import org.apache.bcel.classfile.JavaClass;
import org.apache.bcel.classfile.Method;

public class ClassAnalyzer {
	private ArrayList<JavaClass> analysisDatas;

	public ClassAnalyzer(ArrayList<File> classFiles) {
		analysisDatas = new Parser(classFiles).getAnalysisDatas();
	}

	// 以下、テスト用メソッド

	/**
	 * クラスファイルの解析データを取得
	 * 
	 * @return analysisDatas 解析したクラスファイルのデータ
	 */
	public ArrayList<JavaClass> getClassData() {
		return analysisDatas;
	}

	/**
	 * 確認用バイトコード出力
	 */
	public void printClassData() {
		for (JavaClass classData : analysisDatas) {
			System.out.println("class:" + classData.getClassName());
			Method[] methods = classData.getMethods();
			printMethodData(methods);
		}
	}

	private void printMethodData(Method[] methods) {
		for (Method methodData : methods) {
			System.out.println("method:" + methodData.getName());
			Code codeData = methodData.getCode();
			printCodeData(codeData);
		}
	}

	private void printCodeData(Code codeData) {
		for (String line : codeData.toString().split("\n")) {
			System.out.println(line);
		}
	}
}
