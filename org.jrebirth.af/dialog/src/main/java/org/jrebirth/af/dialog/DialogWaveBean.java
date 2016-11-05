package org.jrebirth.af.dialog;

import org.jrebirth.af.api.annotation.bean.Bean;
import org.jrebirth.af.api.wave.WaveBean;

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
