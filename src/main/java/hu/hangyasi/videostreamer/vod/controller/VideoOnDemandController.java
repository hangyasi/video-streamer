package hu.hangyasi.videostreamer.vod.controller;

import hu.hangyasi.videostreamer.vod.FileManager;
import hu.hangyasi.videostreamer.vod.VideoData;
import hu.hangyasi.videostreamer.vod.repository.VideoRepository;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/dash")
public class VideoOnDemandController {

    private FileManager fileManager;
    private VideoRepository repository;

    public VideoOnDemandController(FileManager fileManager, VideoRepository videoRepository) {
        this.fileManager = fileManager;
        this.repository = videoRepository;
    }

    @GetMapping(value = "/{name}/manifest", produces = "application/dash+xml")
    public ResponseEntity<Resource> index(@PathVariable String name) {
        return ResponseEntity.ok().body(fileManager.getFile(name + "/dash.mpd"));
    }

    @GetMapping(value = "/{name}/{filename}", produces = "video/mp4")
    public ResponseEntity<Resource> getFile(@PathVariable String name, @PathVariable String filename) {
        return ResponseEntity.ok().body(fileManager.getFile(name + "/" + filename));
    }

    @GetMapping(value = "/list-all")
    public List<VideoData> getAllVideo() {
        var list = repository.findAll();
        List<VideoData> result = new ArrayList<>();
        list.forEach(entity -> {
            var fullPath = entity.getLocation() + "/image.png";
            result.add(new VideoData(entity.getName(), entity.getSummary(), Paths.get(entity.getLocation()).getFileName().toString(), fileManager.getFileFullPath(fullPath).getByteArray()));
        });
        return result;
    }

}
