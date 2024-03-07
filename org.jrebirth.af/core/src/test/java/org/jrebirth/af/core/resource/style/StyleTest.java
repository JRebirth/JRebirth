package org.jrebirth.af.core.resource.style;

import org.junit.Assert;
import org.junit.Test;

public class StyleTest {

    @Test
    public void loading() throws Exception {
        Assert.assertNotNull(TestStyles.DEFAULT_CSS.get());
    }
}
