package spring.jdbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository("bookShopDao")
public class BookShopDaoImpl implements BookShopDao {


    @Autowired
    private JdbcTemplate jdbcTemplate;


    @Override
    public int findBookPrcieByIsbn(Integer isbn) {
        String sql = "SELECT price from book where isbn=?";
        return jdbcTemplate.queryForObject(sql, Integer.class, isbn);
    }

    @Override
    public void updateBookStock(Integer isbn) {
        String sql2 = "SELECT stock FROM book_stock where isbn=?";
        int stock = jdbcTemplate.queryForObject(sql2, Integer.class, isbn);
        if (stock == 0) {
            throw new BookStockException("库存不足");
        }

        String sql = "UPDATE book_stock SET stock = stock-1 where isbn=?";
        jdbcTemplate.update(sql, isbn);
    }

    @Override
    public void updateUserAccount(String username, int price) {

        //验证余额是否不足
        String sql2 = "SELECT balance from account where username=?";
        int balance = jdbcTemplate.queryForObject(sql2, Integer.class, username);
        if (balance < price) {
            throw new UserAccountException("余额不足");
        }

        String sql = "UPDATE account SET balance = balance-? where username=?";
        jdbcTemplate.update(sql, price, username);
    }
}
