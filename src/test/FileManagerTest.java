package test;

import static org.assertj.core.api.Assertions.*;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import org.junit.Test;

import tfvis.FileManager;

public class FileManagerTest {

	@Test
	public void outputフォルダのファイルを削除する() throws IOException {
		// Arrange
		int expected = 0;
		File targetFolder = new File("output");
		FileManager fileManager = new FileManager();
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
		FileManager fileManager = new FileManager();
		fileManager.clearDir(targetFolder);
		try {
			targetFolder.createTempFile("Main", "class");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ArrayList<File> classFiles = new ArrayList<File>();

		// Act
		classFiles = fileManager.getClassFile(targetFolder);

		// Assert
		for (File file : classFiles) {
			assertThat(file.getName()).isEqualTo(expected);
		}
	}

}
