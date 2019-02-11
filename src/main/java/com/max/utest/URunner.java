package com.max.utest;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestName;
import org.junit.runner.Description;
import org.junit.runner.Runner;
import org.junit.runner.notification.RunNotifier;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

public class URunner extends Runner {

//    private boolean booleanValue;
//    private int intValue;
//    private short shortValue;
//    private byte byteValue;
//    private long longValue;
//    private char charValue;
//    private float floatValue;
//    private double doubleValue;
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
            Object testObject = testClass.getDeclaredConstructor().newInstance();
//            Object testObject = testClass.newInstance();
            for(Field mainServiceField : testClass.getDeclaredFields()) {
                    mainServiceField.setAccessible(true);
                    Class mainServiceClass = mainServiceField.getType();
                    Object mainServiceInstance = mainServiceClass.getDeclaredConstructor().newInstance();
                    mainServiceField.set(testObject, mainServiceInstance);

                    for (Field innerField : mainServiceInstance.getClass().getDeclaredFields()) {
                        innerField.setAccessible(true);
                        Class innerType = innerField.getType();
                        Object mockInstance = mock(innerType);
                        innerField.set(mainServiceInstance, mockInstance);

                        for(Method method : innerType.getDeclaredMethods()) {
//                            System.out.println("name " + method.getName());
                            int length = method.getParameterTypes().length;
                            Object[] objects = new Object[length];
                            for (int i =0; i < length; i++ ) {
                                objects[i] = any(method.getParameterTypes()[i]);
                            }
                            Class returnType = method.getReturnType();
                            if(!returnType.equals(Void.TYPE)) {
                                if(!returnType.isPrimitive()) {
                                    Object returnObject = returnType.getDeclaredConstructor().newInstance();
                                    when(method.invoke(mockInstance, objects)).thenReturn(returnObject);
                                } //else {
//                                    if(returnType.equals(Boolean.TYPE)) {
//                                        when(method.invoke(mockInstance, objects)).thenReturn(booleanValue);
//                                    } else if(returnType.equals(Integer.TYPE)) {
//                                        when(method.invoke(mockInstance, objects)).thenReturn(intValue);
//                                    } else if(returnType.equals(Short.TYPE)) {
//                                        when(method.invoke(mockInstance, objects)).thenReturn(shortValue);
//                                    } else if(returnType.equals(Byte.TYPE)) {
//                                        when(method.invoke(mockInstance, objects)).thenReturn(byteValue);
//                                    } else if(returnType.equals(Long.TYPE)) {
//                                        when(method.invoke(mockInstance, objects)).thenReturn(longValue);
//                                    } else if(returnType.equals(Character.TYPE)) {
//                                        when(method.invoke(mockInstance, objects)).thenReturn(charValue);
//                                    } else if(returnType.equals(Float.TYPE)) {
//                                        when(method.invoke(mockInstance, objects)).thenReturn(floatValue);
//                                    } else if(returnType.equals(Double.TYPE)) {
//                                        when(method.invoke(mockInstance, objects)).thenReturn(doubleValue);
//                                    }
                                //}
                            }
                        }
                    }
            }
            for (Method method : testClass.getMethods()) {
                if (method.isAnnotationPresent(Test.class)) {
                    notifier.fireTestStarted(Description
                            .createTestDescription(testClass, method.getName()));
                    try {
                        method.invoke(testObject);
                    } catch (Exception e) {
//                        if(!method.getAnnotation(Test.class).expected().equals(((InvocationTargetException) e).getTargetException().getClass())) {
//                            System.out.println("Exceptions equals");
//                        } else {
//                            throw new RuntimeException("Expected " + method.getAnnotation(Test.class).expected().getName() + " but throws " + e.getClass().getName());
//                        }
                    }
                    notifier.fireTestFinished(Description
                            .createTestDescription(testClass, method.getName()));
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
