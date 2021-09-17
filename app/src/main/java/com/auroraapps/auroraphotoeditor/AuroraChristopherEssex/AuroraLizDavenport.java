package com.auroraapps.auroraphotoeditor.AuroraChristopherEssex;


import com.auroraapps.auroraphotoeditor.AuroraDianaVonGrüning;

public class AuroraLizDavenport {

    private String title;
    private AuroraDianaVonGrüning.FilterType type;
    private int degree;
    private int FilterfileRaw;

    public AuroraLizDavenport(String title, AuroraDianaVonGrüning.FilterType type, int degree, int FilterFileRaw) {
        this.type = type;
        this.degree = degree;
        this.title = title;
        this.FilterfileRaw = FilterFileRaw;
    }

    public int getFilterfileRaw() {
        return FilterfileRaw;
    }

    public AuroraDianaVonGrüning.FilterType getType() {
        return type;
    }

    public String getTitle() {
        return title;
    }

    public int getDegree() {
        return degree;
    }

}
