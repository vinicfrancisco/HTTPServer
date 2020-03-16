package utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.activation.MimetypesFileTypeMap;

import Server.Path;

public class FileManipulator {

	public byte[] fileReader(File file) throws IOException {
		FileInputStream newFile = null;
		byte[] byteArray = new byte[(int) file.length()];

		try {
			newFile = new FileInputStream(file);
			newFile.read(byteArray);
		} finally {
			if (newFile != null) {
				newFile.close();
			}

		}

		return byteArray;
	}

}
