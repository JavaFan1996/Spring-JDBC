package spring.jdbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository("bookShopService")
public class BookShopServiceImpl implements BookShopService {


    @Autowired
    private BookShopDao bookShopDao;

    /**
     * 有可能库存减一了，但是钱不够，所以导致balance没变，stock减少了
     * @param username
     * @param isbn
     */
    //添加事务注解
    @Transactional
    @Override
    public void purcahse(String username, Integer isbn) {
        //获取书的单价
        int prcie = bookShopDao.findBookPrcieByIsbn(isbn);


        //更新书的库存
        bookShopDao.updateBookStock(isbn);

        //更新用户余额
        bookShopDao.updateUserAccount(username, prcie);


    }
}
