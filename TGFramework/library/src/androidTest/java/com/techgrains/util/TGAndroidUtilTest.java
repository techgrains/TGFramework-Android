package com.techgrains.util;

import com.techgrains.util.TGAndroidUtil;

import junit.framework.Assert;
import junit.framework.TestCase;

/**
 * Created by CNS on 28/04/15.
 */
public class TGAndroidUtilTest extends TestCase {

    @Override
    protected void setUp() throws Exception {
        super.setUp();
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }

    public void testIsNetworkAvailable() throws Exception {

        try {
            boolean check = TGAndroidUtil.isNetworkAvailable();
            Assert.assertTrue(true);
        } catch (Exception e) {
            Assert.assertTrue("Exception in isNetworkAvailable()", false);
        }
    }
}
