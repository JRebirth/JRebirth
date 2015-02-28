package org.jrebirth.af.dialog;

import org.jrebirth.af.api.wave.WaveBean;
import org.jrebirth.af.processor.annotation.bean.Bean;

@Bean
public interface DialogWaveBean extends WaveBean {

    enum DialogType {

        Info,
        Warning,
        Error

    }

    DialogType dialogType();

    String title();

    String header();

    String message();

    Throwable exception();

}
