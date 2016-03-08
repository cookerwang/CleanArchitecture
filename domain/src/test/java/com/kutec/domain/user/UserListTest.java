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
 * Date: 2016/3/3.
 */
public class UserListTest {

    private GetUserList getUserList;
    @Mock
    private UserRepository mockUserRepository;
    @Mock
    private PostExecutionThread mockPostExecutionThread;
    @Mock
    private ThreadExecutor mockThreadExecutor;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        getUserList = new GetUserList(mockUserRepository, mockThreadExecutor, mockPostExecutionThread);
    }

    @Test
    public void testGetUserListUseCaseObservableHappyCase() {
        getUserList.buildUseCaseObservable();

        Mockito.verify(mockUserRepository).users();
        Mockito.verifyNoMoreInteractions(mockUserRepository);
        Mockito.verifyZeroInteractions(mockPostExecutionThread);
        Mockito.verifyZeroInteractions(mockThreadExecutor);
    }




}
