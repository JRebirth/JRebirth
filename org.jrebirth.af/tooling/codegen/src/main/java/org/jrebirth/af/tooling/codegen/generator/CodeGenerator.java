package org.jrebirth.af.tooling.codegen.generator;

public interface CodeGenerator<B> {

    String FORMATTER_PROPERTIES_FILE = "/org.eclipse.jdt.core.prefs";

    String generate(B beanDescription);
}
