package spring.jdbc;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.ArrayList;
import java.util.Arrays;

public class BookShopDaoImplTest {
    private ApplicationContext context = null;
    private BookShopDao bookShopDao = null;
    private BookShopService bookShopService=null;
    private Cashier cashier = null;


    {

        context = new ClassPathXmlApplicationContext("spring-config.xml");
        bookShopDao = context.getBean(BookShopDao.class);
        bookShopService = context.getBean(BookShopService.class);
        cashier = context.getBean(Cashier.class);

    }
    @Test
    public void testCashier(){


        cashier.checkout("AA", Arrays.asList(1001, 1002));
    }


    @org.junit.Test
    public void findBookPrcieByIsbn() {

        System.out.println(bookShopDao.findBookPrcieByIsbn(1001));

    }

    @org.junit.Test
    public void updateBookStock() {

        bookShopDao.updateBookStock(1001);
    }

    @org.junit.Test
    public void updateUserAccount() {
        bookShopDao.updateUserAccount("AA",100 );
    }


    @Test
    public void testBookShopService() {

        bookShopService.purcahse("AA", 1001);
    }
}