package test;

import static org.assertj.core.api.Assertions.*;

import java.io.File;
import java.util.ArrayList;

import org.junit.Test;

import tfvis.FileManager;

public class FileManagerTest {

	@Test
	public void outputフォルダのファイルを削除する() {
		// Arrange
		int expected = 0;

		// Act
		FileManager fileManager = new FileManager();
		File targetFolder = new File("output");
		fileManager.clearDir(targetFolder);

		ArrayList<File> classFiles = new ArrayList<File>();
		classFiles = fileManager.getClassFile(targetFolder);

		int actual = classFiles.size();

		// Assert
		assertThat(actual).isEqualTo(expected);

	}

}
