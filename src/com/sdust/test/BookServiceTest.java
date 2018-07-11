package com.sdust.test;

import com.sdust.dao.IBookDao;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

public class BookServiceTest extends BaseTest{

    @Autowired
    private IBookDao bookDao;

    @Test
    @Transactional
    @Rollback(false)
    public void test(){

    }
}
