package org.jrebirth.af.tooling.ecore_maven_plugin;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;
import org.jrebirth.af.tooling.codegen.bean.FXBeanDefinition;
import org.jrebirth.af.tooling.codegen.bean.FXPropertyDefinition;
import org.jrebirth.af.tooling.codegen.generator.Generators;

import com.google.common.base.Charsets;
import com.google.common.io.Files;

/**
 * Goal which
 *
 */
@Mojo(name = "fromEcore", defaultPhase = LifecyclePhase.GENERATE_SOURCES)
public class FromEcoreMojo extends AbstractMojo {

	/**
	 * Location of the model file.
	 */
	@Parameter(defaultValue = "Model.ecore", property = "jrebirth.ecore", required = true)
	private File ecore;

	/**
	 * Specify output directory where the Java files are generated.
	 */
	@Parameter(defaultValue = "${project.build.directory}/generated-sources")
	private File outputDirectory;

	public void execute() throws MojoExecutionException {

		generate(this.outputDirectory, this.ecore);
	}

	/**
	 * 
	 */
	public void generate(File output, File ecoreFile) {
		ResourceSet rs = new ResourceSetImpl();
		rs.getResourceFactoryRegistry().getExtensionToFactoryMap().put("ecore", new XMIResourceFactoryImpl());

		if (ecoreFile != null && ecoreFile.exists()) {
			URI uri = URI.createFileURI(ecoreFile.getAbsolutePath());
			Resource r = rs.getResource(uri, true);
			List<EObject> objList = r.getContents();

			for (EObject obj : objList) {

				try {
					manageObject(output, obj);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

	private List<FXBeanDefinition> manageObject(File output, EObject obj) throws IOException {

		List<FXBeanDefinition> beans = new ArrayList<FXBeanDefinition>();
		if (obj instanceof EPackage) {
			managePackage(output, (EPackage) obj);
		} else if (obj instanceof EClass) {
			beans.add(manageClass((EClass) obj));
		}

		for (FXBeanDefinition bean : beans) {
			String formattedSource = Generators.beanGenerator.generate(bean);

			String[] parts = bean.getPackageName().split("\\.");

			File temp = output;
			for (String n : parts) {

				temp = new File(temp, n);

			}

			temp = new File(temp, bean.getClassName() + ".java");

			Files.createParentDirs(temp);

			Files.write(formattedSource, temp, Charsets.UTF_8);

		}
		return beans;
	}

	private FXBeanDefinition manageClass(EClass cls) {
		FXBeanDefinition bean = new FXBeanDefinition();

		bean.setClassName(cls.getName());
		bean.setPackageName(cls.getEPackage().getName());

		if(!cls.getESuperTypes().isEmpty()){
			if(!getFullName(cls.getESuperTypes().get(0)).contains("ecore")){
				bean.setSuperType(getFullName(cls.getESuperTypes().get(0)));
			}
		}
		
		for (EAttribute attr : cls.getEAttributes()) {

			FXPropertyDefinition field = new FXPropertyDefinition();

			field.requireField(true);
			field.setName(cleanName(attr.getName()));
			field.setType(convertEcoreType(attr));
			bean.getProperties().add(field);

		}
		
		for (EReference ref: cls.getEReferences()) {

			FXPropertyDefinition field = new FXPropertyDefinition();

			field.requireField(true);
			field.setName(cleanName(ref.getName()));
			
			field.setType(getFullName(ref.getEReferenceType()));
			
			if(!field.getType().contains("ecore")){
				bean.getProperties().add(field);
			}

		}

		return bean;
	}

	private String getFullName(EClass eClass) {
		String name = eClass.getName();
		EPackage pkg = eClass.getEPackage();
		while(pkg != null){
			name = pkg.getName() + "." + name;
			pkg = pkg.getESuperPackage();
		}
		return name;
		
	}

	private String cleanName(String name) {

		String[] KEY_WORD = new String[] {
				"abstract", "continue", "for", "new", "switch",
				"assert", "default", "goto", "package", "synchronized",
				"boolean", "do", "if", "private", "this",
				"break", "double", "implements", "protected", "throw",
				"byte", "else", "import", "public", "throws",
				"case", "enum", "instanceof", "return", "transient",
				"catch", "extends", "int", "short", "try",
				"char", "final", "interface", "static", "void",
				"class", "finally", "long", "strictfp", "volatile",
				"const", "float", "native", "super", "while" };

		List<String> all = Arrays.asList(KEY_WORD);

		if (all.contains(name)) {
			return "_" + name;
		}

		return name;
	}

	/**
	 * @param attr
	 * @return
	 */
	protected String convertEcoreType(EAttribute attr) {
		Object result;

		int typeID = attr.getEType().getClassifierID();
		if (typeID == EcorePackage.EINTEGER_OBJECT || typeID == EcorePackage.EINT) {
			return "int";
		} else if (typeID == EcorePackage.ELONG_OBJECT || typeID == EcorePackage.ELONG) {
			return "long";
		} else if (typeID == EcorePackage.ESHORT_OBJECT || typeID == EcorePackage.ESHORT) {
			return "int";
		} else if (typeID == EcorePackage.EFLOAT_OBJECT || typeID == EcorePackage.EFLOAT) {
			return "float";
		} else if (typeID == EcorePackage.EDOUBLE_OBJECT || typeID == EcorePackage.EDOUBLE) {
			return "double";
		} else if (typeID == EcorePackage.EBYTE_OBJECT || typeID == EcorePackage.EBYTE) {
			return "byte";
		} else if (typeID == EcorePackage.EBIG_INTEGER) {
			return BigInteger.class.getCanonicalName();
		} else if (typeID == EcorePackage.EBIG_DECIMAL) {
			return BigDecimal.class.getCanonicalName();
		}
		return "java.lang.String";
	}

	private void managePackage(File output, EPackage obj) throws IOException {
		for (EClassifier cls : obj.getEClassifiers()) {
			manageObject(output, cls);
		}

	}
}
