package tfvis;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

/**
 * @author Muco
 *
 */

/**
 * @author Muco
 *
 */
/**
 * @author Muco
 *
 */
public class FileManager {
	ArrayList<File> classFiles;

	public FileManager() {
		classFiles = new ArrayList<File>();
	}

	/**
	 * フォルダの中身を全て削除する
	 *
	 * @param dirOrFile
	 *            ファイルパス(フォルダパス)
	 * @return none
	 */
	public void clearDir(File path) {
		// ディレクトリ下の各ファイルを削除
		for (File child : path.listFiles()) {
			recursiveDeleteFile(child);
		}
	}

	/**
	 * 再帰的なファイル削除
	 *
	 * @param path
	 *            ファイルパスorフォルダパス
	 * @return 成否
	 */
	public void recursiveDeleteFile(File path) {
		// 対象パスがディレクトリかどうか判定
		if (path.isDirectory()) {
			// ディレクトリ下の各ファイルについて再帰的に削除
			for (File child : path.listFiles()) {
				recursiveDeleteFile(child);
			}
		}
		// ファイル(空フォルダ)の削除
		path.delete();
	}

	/**
	 * 可視化対象クラスファイルの取得
	 *
	 * @param targetPath
	 *            可視化対象フォルダのパス
	 *
	 * @return クラスファイル群
	 */
	public ArrayList<File> getClassFile(File path) {
		recursiveFileWalk(path);
		return classFiles;
	}

	/**
	 * 再帰的なクラスファイル探索
	 *
	 * @param path
	 *            ファイルパスorフォルダパス
	 *
	 * @return none
	 */
	private void recursiveFileWalk(File path) {
		// 対象パスがディレクトリかどうか判定
		if (path.isDirectory()) {
			// ディレクトリ下の各ファイルについて再帰的に削除
			for (File child : path.listFiles()) {
				recursiveFileWalk(child);
			}
		} else if (path.getName().endsWith("class")) {
			// クラスファイルの取得
			classFiles.add(path);
		}
	}

	/**
	 * ファイルのコピー
	 *
	 * @param inputPath
	 *            コピー元ファイルパス
	 * @param outputPath
	 *            コピー先ファイルパス
	 */
	public void transferTo(Path sourcePath, Path targetPath) {
		if(Files.isDirectory(sourcePath)){
			for(Files child : Files.list(sourcePath);
		}
			Files.copy(sourcePath, targetPath);

	}
}
