package org.jrebirth.core.resource.font;

public class CustomFontName implements FontName {

    private final String name;

    public CustomFontName(final String name) {
        this.name = name;
    }

    @Override
    public String name() {
        return this.name;
    }

}
