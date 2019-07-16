package spring.jdbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("cashier")
public class CashierImpl implements Cashier {

    @Autowired
    private BookShopService bookShopService;

    /**
     * 默认情况下事务传播行为，
     * propagation = Propagation.REQUIRED  有就行，不用新开事务 买第一本书钱够，但第二本不够，两本都买不了
     * propagation = Propagation.REQUIRED_NEW 重开新事务 买第一本书钱够就买了，第二本失败
     * isolation = Isolation.READ_COMMITTED 隔离等级
     * noRollbackFor = {UserAccountException.class} 不回滚这个异常 一般不用操作
     * readOnly 指定事务是否为只读
     * timeout=100 在强制回滚之前最多能占用多久时间
     * @param username
     * @param isbns
     */
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED,
            noRollbackFor = {UserAccountException.class})
    @Override
    public void checkout(String username, List<Integer> isbns) {

        for (Integer isbn : isbns) {

            bookShopService.purcahse(username, isbn);

        }


    }
}

