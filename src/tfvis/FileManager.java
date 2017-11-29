package tfvis;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.stream.Collectors;

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
	 * 対象フォルダの中身のコピー
	 * 
	 * @param sourcePath
	 *            コピー元ファイルパス
	 * @param targetPath
	 *            コピー先ファイルパス
	 */
	public void transferTo(Path sourcePath, Path targetPath) {
		try {
			recursiveTransferTo(sourcePath, targetPath);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 再帰的なファイルのコピー
	 *
	 * @param inputPath
	 *            コピー元ファイルパス
	 * @param outputPath
	 *            コピー先ファイルパス
	 * @throws IOException
	 */
	public void recursiveTransferTo(Path sourcePath, Path targetPath) throws IOException {
		// 対象パスがフォルダかどうか判定
		if (Files.isDirectory(sourcePath)) {
			// フォルダのコピー
			if (!Files.exists(targetPath)) {
				Files.copy(sourcePath, targetPath);
			}

			// 再帰的にフォルダの中身をコピー
			for (Path child : Files.list(sourcePath).collect(Collectors.toList())) {
				// ターゲットパスの指定
				Path newTargetPath = targetPath.resolve(child.toFile().getName());
				recursiveTransferTo(child, newTargetPath);
			}

		} else {
			// ファイルのコピー
			Files.copy(sourcePath, targetPath);
		}
	}
}
