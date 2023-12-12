package io.nirahtech.erp.edm.document;

public class Version {
    private final int major;
    private final int minor;
    private final int bugfix;
    private final String tag;

    public Version(int major, int minor, int bugfix, String tag) {
        this.major = major;
        this.minor = minor;
        this.bugfix = bugfix;
        this.tag = tag;
    }

    public int getMajor() {
        return major;
    }

    public int getMinor() {
        return minor;
    }

    public int getBugfix() {
        return bugfix;
    }

    public String getTag() {
        return tag;
    }
}
