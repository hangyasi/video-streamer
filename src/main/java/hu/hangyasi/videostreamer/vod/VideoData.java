package hu.hangyasi.videostreamer.vod;

public class VideoData {

    private String name;
    private String summary;
    private String path;
    private byte[] image;

    public VideoData(String name, String summary, String path, byte[] image) {
        this.name = name;
        this.summary = summary;
        this.path = path;
        this.image = image;
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
