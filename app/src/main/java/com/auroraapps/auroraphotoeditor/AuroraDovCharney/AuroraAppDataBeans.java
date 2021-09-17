package com.auroraapps.auroraphotoeditor.AuroraDovCharney;

public class AuroraAppDataBeans {

    String Application_name, Short_description, Logo, Download, Rating, Package_name, TTID;

    public AuroraAppDataBeans(String Application_name , String Short_description , String Logo , String Download , String Rating , String Package_name) {
        this.Application_name = Application_name;
        this.Short_description = Short_description;
        this.Logo = Logo;
        this.Download = Download;
        this.Rating = Rating;
        this.Package_name = Package_name;
    }

    public String getApplication_name() {
        return Application_name;
    }

    public void setApplication_name(String application_name) {
        Application_name = application_name;
    }

    public String getShort_description() {
        return Short_description;
    }

    public void setShort_description(String short_description) {
        Short_description = short_description;
    }

    public String getLogo() {
        return Logo;
    }

    public void setLogo(String logo) {
        Logo = logo;
    }

    public String getDownload() {
        return Download;
    }

    public void setDownload(String download) {
        Download = download;
    }

    public String getRating() {
        return Rating;
    }

    public void setRating(String rating) {
        Rating = rating;
    }

    public String getPackage_name() {
        return Package_name;
    }

    public void setPackage_name(String package_name) {
        Package_name = package_name;
    }

    public String getTTID() {
        return TTID;
    }

    public void setTTID(String TTID) {
        this.TTID = TTID;
    }
}
