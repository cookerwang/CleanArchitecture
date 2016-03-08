package com.kutec.data.user;

import com.kutec.data.ApplicationCaseTest;
import com.kutec.data.user.cache.UserCache;
import com.kutec.data.user.datasource.DiskUserDataStore;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

/**
 * Author: wangrenxing(wangrenxing87@gmail.com)
 * Date: 2016/3/4.
 */
public class DiskUserDataStoreTest extends ApplicationCaseTest {
    private static final int FAKE_USER_ID = 588;
    private DiskUserDataStore diskUserDataStore;

    @Mock private UserCache mockUserCache;
    @Rule public ExpectedException expectedException = ExpectedException.none();

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        diskUserDataStore = new DiskUserDataStore(mockUserCache);
    }

    @Test
    public void testUserEntityDetailsById() {
        diskUserDataStore.userEntityDetailsById(FAKE_USER_ID);
        Mockito.verify(mockUserCache).get(FAKE_USER_ID);
    }

    @Test
    public void testUserEntityList() {
        expectedException.expect(UnsupportedOperationException.class);
        diskUserDataStore.userEntityList();
    }
}
