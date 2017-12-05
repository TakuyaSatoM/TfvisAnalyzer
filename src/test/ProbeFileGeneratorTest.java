package test;

import java.io.File;
import java.nio.file.Paths;
import java.util.ArrayList;

import org.apache.bcel.classfile.JavaClass;
import org.junit.Before;
import org.junit.Test;

import staticAnalyzer.ClassAnalyzer;
import tfvis.FileManager;

public class ProbeFileGeneratorTest {

	private FileManager fileManager;
	private File targetFile;

	@Before
	public void ファイルマネージャの作成() {
		fileManager = new FileManager();
		targetFile = new File("target/bin");
		fileManager.clearDir(targetFile);
		fileManager.transferTo(Paths.get("sample/ByteCodeSample/bin"), targetFile.toPath());
	}

	@Test
	public void 変数の値を変更するバイトコードを挿入する() {
		// クラスファイルの取得
		ArrayList<File> classFiles = fileManager.getClassFile(new File("target/bin"));

		// 対象クラスファイルの解析
		ClassAnalyzer analyzer = new ClassAnalyzer(classFiles);
		ArrayList<JavaClass> classDatas = analyzer.getClassData();

		analyzer.printClassData();

	}

}
