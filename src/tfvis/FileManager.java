package tfvis;

import java.io.File;

public class FileManager {
	public FileManager() {
		// TODO Auto-generated constructor stub
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
	 * @param dirOrFile
	 *            ファイルパス(フォルダパス)
	 * @return 成否
	 */
	public boolean deleteFile(File dirOrFile) {
		if (dirOrFile.isDirectory()) {// ディレクトリの場合
			String[] children = dirOrFile.list();// ディレクトリにあるすべてのファイルを処理する
			for (int i = 0; i < children.length; i++) {
				boolean success = deleteFile(new File(dirOrFile, children[i]));
				if (!success) {
					return false;
				}
			}
		}
		// 削除
		return dirOrFile.delete();
	}
}
