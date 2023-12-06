package hu.hangyasi.videostreamer.vod.controller;

import hu.hangyasi.videostreamer.vod.FileManager;
import hu.hangyasi.videostreamer.vod.VideoData;
import hu.hangyasi.videostreamer.vod.dto.Video;
import hu.hangyasi.videostreamer.vod.repository.VideoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.nio.file.Paths;
import java.time.Instant;
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

    @GetMapping(value = "/{id}/manifest", produces = "application/dash+xml")
    public ResponseEntity<Resource> index(@PathVariable Integer id) {
        Video metadata = repository.getReferenceById(id);
        return ResponseEntity.ok().body(fileManager.getFileFullPath(metadata.getLocation() + "/dash.mpd"));
    }

    @GetMapping(value = "/{id}/{filename}", produces = "video/mp4")
    public ResponseEntity<Resource> getFile(@PathVariable Integer id, @PathVariable String filename) {
        System.out.println(Instant.now().toString() + "    " + filename + " szegmens kérése");
        Video metadata = repository.getReferenceById(id);
        return ResponseEntity.ok().body(fileManager.getFileFullPath(metadata.getLocation() + "/" + filename));
    }

    @GetMapping(value = "/list-all")
    public List<VideoData> getAllVideo() {
        var list = repository.findAll();
        List<VideoData> result = new ArrayList<>();
        list.forEach(entity -> {
            var fullPath = entity.getLocation() + "/image.png";
            result.add(new VideoData(entity.getId(), entity.getName(), entity.getSummary(), entity.getLocation(), fileManager.getFileFullPath(fullPath).getByteArray()));
        });
        return result;
    }

}
