package hu.hangyasi.videostreamer.vod.controller;

import hu.hangyasi.videostreamer.vod.dto.Video;
import hu.hangyasi.videostreamer.vod.repository.VideoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private VideoRepository repository;

    @PostMapping("/create")
    public void saveVideoDetails(@RequestBody Video video) {
        repository.save(video);
    }

    @DeleteMapping("{id}")
    public void deleteVideoDetails(@PathVariable("id") int id) {
        repository.deleteById(id);
    }
}
