package de.rusticprism.vessentials.commands.util;

import com.google.common.base.Preconditions;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;
import java.util.Collections;

public class StringUtil {
    @NotNull
    public static <T extends Collection<? super String>> T copyPartialMatches(final String token, final Iterable<String> originals, final T collection) throws UnsupportedOperationException, IllegalArgumentException {
        Preconditions.checkArgument(token != null, "Search token cannot be null");
        Preconditions.checkArgument(collection != null, "Collection cannot be null");
        Preconditions.checkArgument(originals != null, "Originals cannot be null");
        if(token.equalsIgnoreCase(" ")) {
            for (String string : originals) {
                collection.add(string);
            }
        }else {
            for (String string : originals) {
                if (startsWithIgnoreCase(string, token)) {
                    collection.add(string);
                }
            }
        }
        return collection;
    }

    public static boolean startsWithIgnoreCase(@NotNull final String string, @NotNull final String prefix) throws IllegalArgumentException, NullPointerException {
        Preconditions.checkArgument(string != null, "Cannot check a null string for a match");
        if (string.length() < prefix.length()) {
            return false;
        }
        return string.regionMatches(true, 0, prefix, 0, prefix.length());
    }
}
