package org.jrebirth.af.core.command.ref;

import org.jrebirth.af.core.wave.Wave;

@FunctionalInterface
public interface CommandWaveRunner {

	void perform(Wave wave);
}