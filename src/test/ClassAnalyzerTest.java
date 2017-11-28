package test;

import java.io.File;

import org.junit.Test;

import tfvis.FileManager;

public class ClassAnalyzerTest {

	@Test
	public void ターゲットクラスファイルを解析する() {
		// Arrange
		FileManager fileManager = new FileManager();
		fileManager.clearDir(new File("target/bin"));


	}

}
