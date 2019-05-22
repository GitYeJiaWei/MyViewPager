package com.example.yjw.GreenDAO;

import android.os.Bundle;
import android.util.Log;

import com.example.yjw.GreenDAO.DAO.UserDao;
import com.example.yjw.common.BaseActivity;
import com.example.yjw.myviewpager.R;

import org.greenrobot.greendao.query.Query;

import java.util.List;

/**
 * GreenDAO 数据库
 *
 * @Transient，该注解表示这个属性将不会作为数据表中的一个字段
 * @NotNull表示该字段不可以为空，@Unique表示该字段唯一
 */
public class GreenDaoActivity extends BaseActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_green_dao);
        updatadata();//更改数据
        insertdata();//插入数据
        querydata();//查询数据
        getUserDao().deleteByKey(2l);//long类型,删除数据第二条
        querydata();
        querydataBy();//查询条件

        getuserById();
    }

    private UserDao getUserDao() {
        return GreenDaoManager.getInstance().getSession().getUserDao();
    }

    private void getuserById() {
        User user =getUserDao().load(1l);
        Log.i("tagGreen", "结果：" + user.getId() + "," + user.getName() + "," + user.getAge() + "," + user.getSex() + ";");
    }

    private void insertdata() {
        //插入数据
        User insertData = new User(null, "插入数据", 25, "man");
        getUserDao().insert(insertData);
    }

    private void updatadata() {
        //更改数据
        List<User> userss = getUserDao().loadAll();
        User user = new User(userss.get(0).getId(), "更改后的数据用户", 22, "man");
        getUserDao().update(user);

    }

    private void querydata() {
        //查询数据详细
        List<User> users = getUserDao().loadAll();
        Log.i("tagGreen", "当前数量：" + users.size());
        for (int i = 0; i < users.size(); i++) {
            Log.i("tagGreen", "结果：" + users.get(i).getId() + "," + users.get(i).getName() + "," + users.get(i).getAge() + "," + users.get(i).getSex() + ";");
        }

    }

    private void querydataBy() {////查询条件
        Query<User> nQuery = getUserDao().queryBuilder()
//                .where(UserDao.Properties.Name.eq("user1"))//.where(UserDao.Properties.Id.notEq(999))
                .orderAsc(UserDao.Properties.Age)//.limit(5)//orderDesc
                .build();
        List<User> users = nQuery.list();
        Log.i("tagGreen", "当前数量：" + users.size());
        for (int i = 0; i < users.size(); i++) {
            Log.i("tagGreen", "结果：" + users.get(i).getId() + "," + users.get(i).getName() + "," + users.get(i).getAge() + "," + users.get(i).getSex() + ";");
        }

//        QueryBuilder qb = userDao.queryBuilder();
//        qb.where(Properties.FirstName.eq("Joe"),
//                qb.or(Properties.YearOfBirth.gt(1970),
//                        qb.and(Properties.YearOfBirth.eq(1970), Properties.MonthOfBirth.ge(10))));
//        List youngJoes = qb.list();
    }


    /**
     * 根据查询条件,返回数据列表
     * @param where        条件
     * @param params       参数
     * @return             数据列表
     */
    public List<User> queryN(String where, String... params){
        return getUserDao().queryRaw(where, params);
    }

    /**
     * 根据用户信息,插件或修改信息
     * @param user              用户信息
     * @return 插件或修改的用户id
     */
    public long saveN(User user){
        return getUserDao().insertOrReplace(user);
    }

    /**
     * 批量插入或修改用户信息
     * @param list      用户信息列表
     */
    public void saveNLists(final List<User> list){
        if(list == null || list.isEmpty()){
            return;
        }
        getUserDao().getSession().runInTx(new Runnable() {
            @Override
            public void run() {
                for(int i=0; i<list.size(); i++){
                    User user = list.get(i);
                    getUserDao().insertOrReplace(user);
                }
            }
        });

    }

    /**
     * 删除所有数据
     */
    public void deleteAllNote(){
        getUserDao().deleteAll();
    }

    /**
     * 根据用户类,删除信息
     * @param user    用户信息类
     */
    public void deleteNote(User user){
        getUserDao().delete(user);
    }
}
