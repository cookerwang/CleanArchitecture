package com.kutec.data.common.exception;

import com.kutec.data.ApplicationCaseTest;
import com.kutec.data.common.repository.RepositoryErrorBundle;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

/**
 * Author: wangrenxing(wangrenxing87@gmail.com)
 * Date: 2016/3/3.
 */
public class RepositoryErrorBundleTest extends ApplicationCaseTest {

    private RepositoryErrorBundle repositoryErrorBundle;
    @Mock
    private Exception mockException;
    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        repositoryErrorBundle = new RepositoryErrorBundle(mockException);
    }

    @Test
    public void testGetErrorMessageInteraction() {
        repositoryErrorBundle.getErrorMessage();

        Mockito.verify(mockException).getMessage();
    }


}
