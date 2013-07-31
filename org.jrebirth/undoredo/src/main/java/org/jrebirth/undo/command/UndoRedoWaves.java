package org.jrebirth.undo.command;

import org.jrebirth.core.wave.WaveItem;

public interface UndoRedoWaves {

    WaveItem<String> stackName = new WaveItem<String>() {
    };

    WaveItem<Boolean> undoRedo = new WaveItem<Boolean>() {
    };

    WaveItem<Undoable> undoableCommand = new WaveItem<Undoable>() {
    };

}
