package com.auroraapps.auroraphotoeditor.AuroraKayCohen;

public class AuroraSusienChong {

    String DirName,FileName;

    public AuroraSusienChong(String dirName) {
        DirName = dirName;
    }

    public AuroraSusienChong(String dirName, String fileName) {
        DirName = dirName;
        FileName = fileName;
    }

    public String getDirName() {
        return DirName;
    }

    public void setDirName(String dirName) {
        DirName = dirName;
    }

    public String getFileName() {
        return FileName;
    }

    public void setFileName(String fileName) {
        FileName = fileName;
    }
}
