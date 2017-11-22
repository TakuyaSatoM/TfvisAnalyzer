package tfvis;

import java.io.File;
import java.util.ArrayList;

public class FileManager {
	ArrayList<File> classFiles;

	public FileManager() {
		classFiles = new ArrayList<File>();
	}

	/**
	 * フォルダ初期化(中身を全部消す)
	 * 
	 * @param dirOrFile
	 *            ファイルパス(フォルダパス)
	 * @return none
	 */
	public void clearDir(File dirOrFile) {
		if (dirOrFile.isDirectory()) {// ディレクトリの場合
			String[] children = dirOrFile.list();// ディレクトリにあるすべてのファイルを処理する
			for (int i = 0; i < children.length; i++) {
				deleteFile(new File(dirOrFile, children[i]));
			}
		}
	}

	/**
	 * ファイル総削除用(指定したフォルダも消える)
	 * 
	 * @param path
	 *            ファイルパスorフォルダパス
	 * @return 成否
	 */
	public boolean deleteFile(File path) {
		if (path.isDirectory()) {// ディレクトリの場合
			String[] children = path.list();// ディレクトリにあるすべてのファイルを処理する
			for (int i = 0; i < children.length; i++) {
				boolean success = deleteFile(new File(path, children[i]));
				if (!success) {
					return false;
				}
			}
		}
		// 削除
		return path.delete();
	}

	/**
	 * 可視化対象クラスファイルの取得
	 * 
	 * @param targetPath
	 *            可視化対象フォルダのパス
	 * 
	 * @return クラスファイル群
	 */
	public ArrayList<File> getClassFile(File targetPath) {
		classFileWalk(targetPath);
		return classFiles;
	}

	/**
	 * 再帰用のクラスファイル探索
	 * 
	 * @param path
	 *            ファイルパスorフォルダパス
	 * 
	 * @return none
	 */
	private void classFileWalk(File path) {
		if (path.isDirectory()) {
			for (File child : path.listFiles()) {
				classFileWalk(child);
			}
		} else {
			classFiles.add(path);
		}
	}
}
