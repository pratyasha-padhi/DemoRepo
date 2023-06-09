package Analyzer;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

import org.testng.IAnnotationTransformer;
import org.testng.annotations.ITestAnnotation;

public class AnnotationTransformer implements IAnnotationTransformer {
	public void transform(ITestAnnotation annotation, Class testClass, Constructor testConstructor, Method testMethod) 
	{
		// Modify test annotations dynamically here
		if (testMethod.getName().equals("myTest")) {
			annotation.setRetryAnalyzer(RetryAnalyzer.class);
		}
	}

}
