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

import java.io.IOException;
import java.util.Properties;

import org.jboss.forge.roaster.model.JavaClass;
import org.jboss.forge.roaster.model.JavaInterface;
import org.jboss.forge.roaster.model.source.FieldHolderSource;
import org.jboss.forge.roaster.model.source.FieldSource;
import org.jboss.forge.roaster.model.source.JavaClassSource;
import org.jboss.forge.roaster.model.source.MethodHolderSource;
import org.jboss.forge.roaster.model.source.MethodSource;
import org.jboss.forge.roaster.model.util.Formatter;
import org.jrebirth.af.tooling.codegen.bean.Operation;
import org.jrebirth.af.tooling.codegen.bean.Property;
import org.jrebirth.af.tooling.codegen.template.TemplateName;
import org.jrebirth.af.tooling.codegen.template.Templates;

public abstract class AbstractGenerator<B, J> implements CodeGenerator<B, J> {

    /**
     * @param javaClass
     *
     * @return
     */
    protected String formatClass(final JavaClassSource javaClass) {
        final Properties prefs = new Properties();
        try {
            prefs.load(BeanGenerator.class.getResourceAsStream(FORMATTER_PROPERTIES_FILE));
        } catch (final IOException e) {
            e.printStackTrace();
        }
        final String formattedSource = prefs != null ? Formatter.format(prefs, javaClass) : Formatter.format(javaClass);

        // System.out.println(formattedSource);

        return formattedSource;
    }

	protected void writeOperation(MethodHolderSource<?> methodHolder, Operation o) {
	    if (!methodHolder.hasMethodSignature(o.name())) {
	
	        final StringBuilder javadoc = new StringBuilder();
	        javadoc
	               .append(".");
	
	        final String body = Templates.use(TemplateName.Operation, o);
	
	        final MethodSource<?> method = methodHolder.addMethod()
	                                                .setName(o.name())
	                                                .setAbstract(o.isAbstract())
	                                                .setStatic(o.isStatic())
	                                                .setDefault(o.isDefault());
	
	        if(o.returnType()!= null){
	        	method.setReturnType(o.returnType().qualifiedName());
	        }else{
	        	method.setReturnTypeVoid();
	        }
	        
	        if((methodHolder instanceof JavaInterface && o.isDefault()) || (methodHolder instanceof JavaClass && !o.isAbstract()) ){
	        	method.setBody(body);
	        }else{
	        	method.setBody(null);
	        }
	        
	        switch(o.visibility()){
			case _package:
				method.setPackagePrivate();
				break;
			case _private:
				method.setPrivate();
				break;
			case _protected:
				method.setProtected();
				break;
			case _public:
				method.setPublic();
				break;
			default:
				break;
	        }
	        
	        o.parameters().stream().forEach(td -> method.addParameter(td.type().qualifiedName(), td.name()));
	
	        /*
	         * method.getJavaDoc().setFullText(javadoc.toString()) .addTagValue("@return", "a fresh instance");
	         */
	    } else {
	        // javaInterface.getMethod(propDef.getName()).setBody(javaInterface.getMethod(propDef.getName()).getBody() + body.toString());
	    }
	}

	protected void writeField(final FieldHolderSource<?> fieldHolder, final Property propDef) {
	
	    if (propDef.requireField()) {
	        if (!fieldHolder.hasField(propDef.name())) {
	
	            final StringBuilder javadoc = new StringBuilder();
	            javadoc
	                   .append("The field ")
	                   .append(propDef.name());
	
	            final FieldSource<?> method = fieldHolder.addField()
	                                                   .setType(propDef.type().qualifiedName())
	                                                   .setName(propDef.name())
	                                                   .setPrivate();
	            method.getJavaDoc().setFullText(javadoc.toString());
	
	        } else {
	            // javaClass.getMethod(propDef.getName()).setBody(javaClass.getMethod(propDef.getName()).getBody() + body.toString());
	        }
	    }
	}

}
