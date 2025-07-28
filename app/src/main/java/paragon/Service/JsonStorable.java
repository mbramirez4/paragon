package paragon.Service;

public interface JsonStorable {
    String getRootDirectory();
    String getFilePath();
    void save();
}
