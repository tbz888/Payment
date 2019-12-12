package com.tbz.payment.base.db;

import com.tbz.payment.TestEnvironment;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class DataSourceConfigTest extends TestEnvironment {

    @Autowired
    private DataSource d;

    @Test
    public void testBase() {
        Assert.assertNotNull(d);
    }

    @Test
    public void testConnection() throws SQLException {
        Connection c = d.getConnection();
        Assert.assertNotNull(c);
        c.close();
    }
}
