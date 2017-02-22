/**
 * Get more info at : www.jrebirth.org .
 * Copyright JRebirth.org © 2011-2016
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
package org.jrebirth.af.tooling.codegen.generator;

import org.jboss.forge.roaster.model.source.JavaInterfaceSource;
import org.jrebirth.af.tooling.codegen.bean.Class;

public class InterfaceGenerator extends AbstractGenerator<Class, JavaInterfaceSource> {

    /**
     * Package constructor.
     */
    InterfaceGenerator() {

    }

    @Override
    public String generate(final Class beanDef, JavaInterfaceSource javaInterface) {

        //final JavaInterfaceSource javaInterface = Roaster.create(JavaInterfaceSource.class);

        javaInterface.setPackage(beanDef._package().qualifiedName()).setName(beanDef.name());

        if (beanDef.getSuperType() != null) {
            // javaInterface.setSuperType(beanDef.getSuperType().qualifiedName());
        }
        
        beanDef.properties().stream().forEach(propDef -> {
            writeField(javaInterface, propDef);
        });

        beanDef.operations().stream().forEach(o -> {
            writeOperation(javaInterface, o);
        });

        // return formatClass(javaInterface);
        return javaInterface.toString();

    }

}
