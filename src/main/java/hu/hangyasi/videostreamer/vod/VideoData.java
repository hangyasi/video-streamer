package hu.hangyasi.videostreamer.vod;

public class VideoData {

    private Integer id;
    private String name;
    private String summary;
    private String path;
    private byte[] image;

    public VideoData(Integer id, String name, String summary, String path, byte[] image) {
        this.id = id;
        this.name = name;
        this.summary = summary;
        this.path = path;
        this.image = image;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
