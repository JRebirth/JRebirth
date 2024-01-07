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


import org.jboss.forge.roaster.Roaster;
import org.jboss.forge.roaster.model.source.JavaClassSource;
import org.jboss.forge.roaster.model.source.JavaEnumSource;
import org.jboss.forge.roaster.model.source.JavaInterfaceSource;
import org.jrebirth.af.tooling.codegen.bean.Class;
import org.jrebirth.af.tooling.codegen.bean.Operation;
import org.jrebirth.af.tooling.codegen.bean.Parameter;
import org.jrebirth.af.tooling.codegen.bean.Property;
import org.jrebirth.af.tooling.codegen.generator.Generators;
import org.junit.jupiter.api.Test;

public class BeanTest {

    @Test
    public void testGenerateClass() {
       
        Class bean  = Class.of("org.jrebirth.af.tooling.codegen.MyBean");
       
        Property p1 = Property.of("property1")
        		.type(Class.of("java.lang.String"));
       
        Property p2 = Property.of("property2")
        .type(Class.of("java.lang.String"));
       
       
        Property p3 = Property.of("property1")
        .type(Class.of("java.util.List<java.lang.String>"));
       
       
        Property p4 = Property.of("property4")
        .type(Class.of("java.util.Map<java.lang.String>"));
       
        bean.addProperty(p1, p2 ,p3, p4);

        String output = Generators.beanGenerator.generate(bean, Roaster.create(JavaClassSource.class));
       
        System.out.println(output);
    }
    
    @Test
    public void testGenerateInterface() {
       
        Class bean  = Class.of("org.jrebirth.af.tooling.codegen.MyInterface");
       
        Operation op = Operation.of("operation1").returnType(Class.of("java.lang.String")).addParameter(Parameter.of("param1").type(Class.of("java.lang.String")));
        
        bean.operations().add(op);
        
        String output = Generators.interfaceGenerator.generate(bean, Roaster.create(JavaInterfaceSource.class));
       
        System.out.println(output);
    }
    
    @Test
    public void testGenerateEnum() {
       
        org.jrebirth.af.tooling.codegen.bean.Enum bean  = org.jrebirth.af.tooling.codegen.bean.Enum.of("org.jrebirth.af.tooling.codegen.MyEnum");
       
        String output = Generators.enumGenerator.generate(bean, Roaster.create(JavaEnumSource.class));
       
        System.out.println(output);
    }

}