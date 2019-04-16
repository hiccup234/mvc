package top.hiccup.db.mysql;

/**
 * MySQL数据库锁分类：
 *
 * 非事务性存储引擎：MyISAM、MEMORY、CSV  		采用表级锁定
 * 事务性存储引擎：InnoDB 						采用行级锁定
 *
 * （MyISAM与InnoDB最大的区别就是：MyISAM不支持事务）
 *
 * 【表级锁定】
 * 1、加锁开销小，速度快
 * 2、不会出现死锁
 * 3、锁定粒度大，发生锁冲突的概率最高
 * 4、并发度最低
 *
 * 直接由MySQL实现，分为： 表共享读锁（Table Read Lock） 和 表独占写锁（Table Write Lock）
 *
 * 【页级锁定】
 * MySQL特有的锁定级别，BerkeleyDB采用了页级锁定
 *
 * 【行级锁定】
 * 1、加锁开销大，速度慢
 * 2、会出现死锁的情况
 * 3、锁定粒度最小，发生锁冲突的概率最低
 * 4、并发度最高
 *
 * 由存储引擎实现，分为：共享锁 和 排他锁，InnoDB同时也使用了意向锁（表级锁定），分为意向共享锁和意向排它锁。
 * 意向锁是InnoDB自动加的，不需用户干预。对于UPDATE、DELETE和INSERT语句，InnoDB会自动给涉及数据集加排他锁（X)；对于普通SELECT语句，InnoDB不会加任何锁。
 *
 * 事务可以通过以下语句显示给记录集加共享锁或排他锁：
 * 共享锁（S）：SELECT * FROM table_name WHERE ... LOCK IN SHARE MODE
 * 排他锁（X）：SELECT * FROM table_name WHERE ... FOR UPDATE
 *
 * ##注意：InnoDB的行锁是通过给索引上的索引项加锁来实现的，所以只有通过索引条件检索数据，InnoDB才使用行级锁，否则InnoDB将使用表锁。
 *
 * ##间隙锁（GAP）
 * 当我们用范围条件而不是相等条件检索数据，并请求共享或排他锁时，InnoDB会给符合条件的已有数据记录的索引项加锁；
 * 对于键值在条件范围内但并不存在的记录，叫做“间隙（GAP)”InnoDB也会对这个“间隙”加锁，可以防止幻读，以满足相关隔离级别的要求。
 *
 * ##通过索引实现锁定
 * （1）当Query无法利用索引的时候，InnoDB会放弃使用行级别锁定而改用表级别的锁定，造成并发性能的降低。
 * （2）当Query使用的索引并不包含所有过滤条件的时候，数据检索使用到的索引键所对应的数据可能有部分并不属于该Query的结果集，
 *      但是也会被锁定，因为间隙锁锁定的是一个范围，而不是具体的索引键；
 *
 * =====================================================================================================================
 *
 * @author wenhy
 * @date 2019/4/16
 */
public class LockType {
}
