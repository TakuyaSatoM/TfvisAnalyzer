package tfvis;

import java.io.File;
import java.util.ArrayList;

public class Tfvis implements TfvisConstants {

	public static void main(String[] args) {

		// 古いファイルの削除
		FileManager fileManager = new FileManager();
		fileManager.clearDir(new File(outDir));

		// クラスファイルの取得
		ArrayList<File> classFiles = fileManager.getClassFile(new File("target/bin"));

	}
}
