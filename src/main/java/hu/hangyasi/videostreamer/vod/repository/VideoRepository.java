package hu.hangyasi.videostreamer.vod.repository;

import hu.hangyasi.videostreamer.vod.dto.Video;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VideoRepository extends JpaRepository<Video, Integer> {


}
