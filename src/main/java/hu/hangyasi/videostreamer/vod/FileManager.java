package hu.hangyasi.videostreamer.vod;

import org.springframework.core.io.ByteArrayResource;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Service
public class FileManager {

    public ByteArrayResource getFileFullPath(String fullPath) {
        return getByteArrayResource(Path.of(fullPath));
    }

    private static ByteArrayResource getByteArrayResource(Path path) {
        try {
            return new ByteArrayResource(Files.readAllBytes(path));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
