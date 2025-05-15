package br.com.tp.lanchescaieiras.commons.domain;

import org.springframework.beans.factory.annotation.Value;

public class Image {
    public Integer id;
    public String _data;
    public String location;
    public String fileName;
    public String fileExtension;

    public Image() {
    }

    public Image(Integer id, String _data, String location, String fileName, String fileExtension) {
        this.id = id;
        this._data = _data;
        this.location = location;
        this.fileName = fileName;
        this.fileExtension = fileExtension;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String get_data() {
        return _data;
    }

    public void set_data(String _data) {
        this._data = _data;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileExtension() {
        return fileExtension;
    }
    public void setFileExtension(String fileExtension) {
        this.fileExtension = fileExtension;
    }
}
