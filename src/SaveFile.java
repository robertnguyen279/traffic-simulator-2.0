import java.io.BufferedOutputStream;
import java.io.OutputStream;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static java.nio.file.StandardOpenOption.CREATE;

public class SaveFile {
    public static void main(String[] args) {
        Path file =
                Paths.get("C:\\Users\\matth\\OneDrive - James Cook University\\Semester 4\\CP2406 - Programming 3\\Assignments\\Traffic-Simulator-2.0\\src\\SaveFile.txt");
        String s = "ABCDFG";
        byte[] data = s.getBytes();
        OutputStream output = null;
        try {
            output = new BufferedOutputStream(Files.newOutputStream(file, CREATE));
            output.write(data);
            output.flush();
            output.close();
        } catch (FileAlreadyExistsException e) {
            System.out.println("File already exists");
        } catch (Exception e) {
            System.out.println("Message: " + e);
        }
    }
}
