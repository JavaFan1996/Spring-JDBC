package spring.jdbc;

public interface BookShopDao {


    //根据书号获取书的单价
    public int findBookPrcieByIsbn(Integer isbn);

    //更新书的库存，是书号对应的库存减一
    public void updateBookStock(Integer isbn);


    //更新账户余额
    public void updateUserAccount(String username, int price);
}
