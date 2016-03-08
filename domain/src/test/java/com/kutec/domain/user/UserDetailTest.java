package com.kutec.domain.user;

import com.kutec.domain.common.excutor.PostExecutionThread;
import com.kutec.domain.common.excutor.ThreadExecutor;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

/**
 * Author: wangrenxing(wangrenxing87@gmail.com)
 * Date: 2016/3/2.
 */
public class UserDetailTest {
    private final int FAKE_USER_ID = 123;

    private GetUserDetails getUserDetails;

    @Mock
    private UserRepository mockUserRepository;
    @Mock
    private PostExecutionThread mockPostExecutionThread;
    @Mock
    private ThreadExecutor mockThreadExecutor;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        getUserDetails = new GetUserDetails(FAKE_USER_ID, mockUserRepository, mockThreadExecutor, mockPostExecutionThread);
    }

    @Test
    public void testGetUserDetailsUseCaseObservableHappyCase() {
        getUserDetails.buildUseCaseObservable();

        Mockito.verify(mockUserRepository).user(FAKE_USER_ID);
        Mockito.verifyNoMoreInteractions(mockUserRepository);
        Mockito.verifyZeroInteractions(mockPostExecutionThread);
        Mockito.verifyZeroInteractions(mockThreadExecutor);
    }

}
