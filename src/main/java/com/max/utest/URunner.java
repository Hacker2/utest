package com.max.utest;

import org.junit.Test;
import org.junit.runner.Description;
import org.junit.runner.Runner;
import org.junit.runner.notification.RunNotifier;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import static org.mockito.Mockito.mock;

public class URunner extends Runner {

    private Class testClass;
    public URunner(Class testClass) {
        super();
        this.testClass = testClass;
    }

    public Description getDescription() {
        return Description
                .createTestDescription(testClass, "com.max.utest.URunner automatically inject all mocks");
    }

    public void run(RunNotifier notifier) {
        try {
            Object testObject = testClass.newInstance();
            for(Field mainServiceField : testClass.getDeclaredFields()) {
//                if (mainServiceField.isAnnotationPresent(InjectMocks.class)) {
                    mainServiceField.setAccessible(true);
                    Class mainServiceClass = mainServiceField.getType();
                    Object mainServiceInstance = mainServiceClass.newInstance();
                    mainServiceField.set(testObject, mainServiceInstance);

                    for (Field innerField : mainServiceInstance.getClass().getDeclaredFields()) {
                        innerField.setAccessible(true);
                        Class innerType = innerField.getType();
                        innerField.set(mainServiceInstance, mock(innerType));

                        for(Method method : innerType.getDeclaredMethods()) {
//                            doReturn("").when(method);
//                            when(mock(innerType).method).thenReturn(method);
//                            when(method).thenReturn(method);
//                            when(method).thenReturn(mock(method.getReturnType()));
                        }
                    }
//                }
            }
            for (Method method : testClass.getMethods()) {
                if (method.isAnnotationPresent(Test.class)) {
                    notifier.fireTestStarted(Description
                            .createTestDescription(testClass, method.getName()));
                    method.invoke(testObject);
                    notifier.fireTestFinished(Description
                            .createTestDescription(testClass, method.getName()));
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
