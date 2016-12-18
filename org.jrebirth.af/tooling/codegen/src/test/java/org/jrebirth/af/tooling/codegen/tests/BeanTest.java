/**
 * Get more info at : www.jrebirth.org .
 * Copyright JRebirth.org © 2011-2015
 * Contact : sebastien.bordes@jrebirth.org
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.jrebirth.af.tooling.codegen.tests;


import org.jrebirth.af.tooling.codegen.bean.FXBeanDefinition;
import org.jrebirth.af.tooling.codegen.bean.FXPropertyDefinition;
import org.jrebirth.af.tooling.codegen.generator.Generators;
import org.junit.Test;

public class BeanTest {

    @Test
    public void testTheReturnItemOfReturnWaveType() {
       
        FXBeanDefinition bean  =new FXBeanDefinition();
        bean.setClassName("MyBean");
        bean.setPackageName("org.jrebirth.af.tooling.codegen");
       
        FXPropertyDefinition p1 = new FXPropertyDefinition();
        p1.setName("property1");
        p1.setType("java.lang.String");
       
        FXPropertyDefinition p2 = new FXPropertyDefinition();
        p2.setName("property2");
        p2.setType("java.lang.String");
       
       
        FXPropertyDefinition p3 = new FXPropertyDefinition();
        p3.setName("property1");
        p3.setType("java.lang.String");
       
       
        FXPropertyDefinition p4 = new FXPropertyDefinition();
        p4.setName("property4");
        p4.setType("java.lang.String");
       
        bean.getProperties().add(p1);
        bean.getProperties().add(p2);
        bean.getProperties().add(p3);
        bean.getProperties().add(p4);

        String output = Generators.beanGenerator.generate(bean);
       
        System.out.println(output);
    }

}