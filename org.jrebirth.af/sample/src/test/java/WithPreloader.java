import org.jrebirth.af.preloader.JRebirthPreloader;
import org.jrebirth.af.sample.SampleApplication;

import org.junit.Test;

import com.sun.javafx.application.LauncherImpl;

public class WithPreloader {

    @Test
    public void test() {
        LauncherImpl.launchApplication(SampleApplication.class, JRebirthPreloader.class, null);
    }

}