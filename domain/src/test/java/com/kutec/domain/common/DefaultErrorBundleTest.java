package com.kutec.domain.common;

import com.kutec.domain.common.exception.DefaultErrorBundle;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

/**
 * Author: wangrenxing(wangrenxing87@gmail.com)
 * Date: 2016/3/2.
 */
public class DefaultErrorBundleTest {
    private DefaultErrorBundle defaultErrorBundle;

    @Mock
    private Exception mockException;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        defaultErrorBundle = new DefaultErrorBundle(mockException);
    }

    @Test
    public void testGetErrorMessageInteraction() {
        defaultErrorBundle.getErrorMessage();
        Mockito.verify(mockException).getMessage();
    }
}
