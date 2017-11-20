package tfvis;

import java.io.File;

public class Tfvis implements TfvisConstants {

	public static void main(String[] args) {

		// 古いファイルの削除
		FileManager fileManager = new FileManager();
		fileManager.clearDir(new File(outDir));

	}
}
