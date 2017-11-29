package test;

import static org.assertj.core.api.Assertions.*;

import java.io.File;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;

import org.apache.bcel.classfile.JavaClass;
import org.junit.Before;
import org.junit.Test;

import staticAnalyzer.ClassAnalyzer;
import tfvis.FileManager;

public class ClassAnalyzerTest {
	FileManager fileManager;
	File targetFile;

	@Before
	public void ファイルマネージャの作成() {
		fileManager = new FileManager();
		targetFile = new File("target/bin");
		fileManager.clearDir(targetFile);
		fileManager.transferTo(Paths.get("../Master/bin"), targetFile.toPath());
	}

	@Test
	public void ターゲットクラスのクラス名を取得する() {
		// Arrange
		boolean expected = true;
		java.util.List<String> testClassName = Arrays.asList("Main", "Ary");
		ArrayList<File> classFiles = fileManager.getClassFile(targetFile);

		// Act
		ClassAnalyzer analyzer = new ClassAnalyzer(classFiles);
		ArrayList<JavaClass> classDatas = analyzer.getClassData();

		// Assert
		for (JavaClass classData : classDatas) {
			System.out.println(classData.getClassName());
			boolean actual = testClassName.contains(classData.getClassName());
			assertThat(actual).isEqualTo(expected);
		}
	}

}
