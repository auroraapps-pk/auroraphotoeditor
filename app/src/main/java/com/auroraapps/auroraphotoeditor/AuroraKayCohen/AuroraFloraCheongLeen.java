package com.auroraapps.auroraphotoeditor.AuroraKayCohen;

public class AuroraFloraCheongLeen {

    String DirName,ImageName;

    public AuroraFloraCheongLeen(String imageName) {
        ImageName = imageName;
    }

    public AuroraFloraCheongLeen(String dirName, String imageName) {
        DirName = dirName;
        ImageName = imageName;
    }

    public String getDirName() {
        return DirName;
    }

    public void setDirName(String dirName) {
        DirName = dirName;
    }

    public String getImageName() {
        return ImageName;
    }

    public void setImageName(String imageName) {
        ImageName = imageName;
    }
}
