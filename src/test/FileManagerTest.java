package test;

import static org.assertj.core.api.Assertions.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import tfvis.FileManager;

public class FileManagerTest {
	FileManager fileManager;

	@Before
	public void ファイルマネージャの作成() {
		fileManager = new FileManager();
	}

	@Test
	public void outputフォルダのファイルを削除する() throws IOException {
		// Arrange
		int expected = 0;
		File targetFolder = new File("output");
		ArrayList<File> classFiles = new ArrayList<File>();

		// Act
		fileManager.clearDir(targetFolder);
		classFiles = fileManager.getClassFile(targetFolder);

		int actual = classFiles.size();

		// Assert
		assertThat(actual).isEqualTo(expected);
	}

	@Test
	public void デバッグターゲットクラスファイルを取得する() {
		// Arrange
		String expected = "Main.class";
		File targetFolder = new File("target/bin");
		fileManager.clearDir(targetFolder);
		File testFile = new File("target/bin/Main.class");
		try {
			testFile.createNewFile();
		} catch (IOException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
		ArrayList<File> classFiles = new ArrayList<File>();

		// Act
		classFiles = fileManager.getClassFile(targetFolder);

		// Assert
		for (File file : classFiles) {
			String actual = file.getName();
			assertThat(actual).isEqualTo(expected);
		}
	}

	@Test
	public void ファイルを移動する() {
		// Arrange
		boolean expected = true;
		Path sourcePath = Paths.get("../Master/bin");
		Path targetPath = Paths.get("target/bin");
		fileManager.clearDir(targetPath.toFile());
		ArrayList<File> sourceFiles = new ArrayList<File>();
		sourceFiles = fileManager.getClassFile(sourcePath.toFile());
		ArrayList<File> targetFiles = new ArrayList<File>();

		// Act
		fileManager.transferTo(sourcePath, targetPath);
		targetFiles = fileManager.getClassFile(targetPath.toFile());

		// Assert
		for(File file:sourceFiles){
			boolean actual = targetFiles.contains(sourceFiles);
			assertThat(actual).isEqualTo(expected);
		}
	}

}
