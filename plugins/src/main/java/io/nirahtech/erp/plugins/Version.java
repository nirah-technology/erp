package io.nirahtech.erp.plugins;

import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Version
 */
public record Version(int major, int minor, Optional<Integer> security, Optional<String> tag) {

    private static final String MAJOUR_FORMAT = "(\\d+)";
    private static final String MINOR_FORMAT = "(\\.(\\d+))";
    private static final String SECURITY_FORMAT = "(\\.(\\d+))?";
    private static final String TAG_FORMAT = "(-(\\w+))?";

    private static final String VERSION_FORMAT = "^(" + MAJOUR_FORMAT + MINOR_FORMAT + SECURITY_FORMAT + TAG_FORMAT
            + ")$";

    public static final Version parse(final String version) {
        final Pattern pattern = Pattern.compile(VERSION_FORMAT);
        final Matcher matcher = pattern.matcher(version);
        matcher.find();
        int major = 0;
        int minor = 0;
        Optional<Integer> security = Optional.empty();
        Optional<String> tag = Optional.empty();
        if (matcher.groupCount() >= 4) {
            major = Integer.parseInt(matcher.group(2));
            minor = Integer.parseInt(matcher.group(4));
            if (matcher.group(6) != null) {
                security = Optional.of(Integer.parseInt(matcher.group(6)));
            }
            tag = Optional.ofNullable(matcher.group(8));
        }
        return new Version(major, minor, security, tag);
    }
}