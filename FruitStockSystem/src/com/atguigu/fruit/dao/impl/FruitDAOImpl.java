package com.atguigu.fruit.dao.impl;

import com.atguigu.fruit.dao.FruitDAO;
import com.atguigu.fruit.pojo.Fruit;
import com.atguigu.myssm.basedao.BaseDAO;

import java.util.List;

public class FruitDAOImpl extends BaseDAO<Fruit> implements FruitDAO {
    @Override
    public List<Fruit> getFruitList(String keyword , Integer pageNo) {
        // % 是通配符，用于模糊查询
        return super.executeQuery("select * from fruit_data where fname like ? or remark like ? limit ? , 5" ,"%"+keyword+"%","%"+keyword+"%", (pageNo-1)*5);
    }

    @Override
    public List<Fruit> getFruitList(Integer pageNo) {
        return super.executeQuery("select * from fruit_data limit ? , 5" , (pageNo-1)*5);
    }

    @Override
    public List<Fruit> getFruitList(){
        return super.executeQuery("select * from fruit_data");
    }

    @Override
    public Fruit getFruitByFid(Integer fid) {
        return super.load("select * from fruit_data where fid = ? " , fid);
    }

    @Override
    public void updateFruit(Fruit fruit) {
        String sql = "update fruit_data set fname = ? , price = ? , fcount = ? , remark = ? where fid = ? " ;
        super.executeUpdate(sql,fruit.getFname(),fruit.getPrice(),fruit.getFcount(),fruit.getRemark(),fruit.getFid());
    }

    @Override
    public void delFruit(Integer fid) {
        super.executeUpdate("delete from fruit_data where fid = ? " , fid) ;
    }

    @Override
    public void addFruit(Fruit fruit) {
        String sql = "insert into fruit_data values(0,?,?,?,?)";
        super.executeUpdate(sql,fruit.getFname(),fruit.getPrice(),fruit.getFcount(),fruit.getRemark());
    }

    @Override
    public int getFruitCount(String keyword) {
        return ((Long)super.executeComplexQuery("select count(*) from fruit_data where fname like ? or remark like ?" , "%"+keyword+"%","%"+keyword+"%")[0]).intValue();
    }

    @Override
    public int getFruitCount() {
        return ((Long)super.executeComplexQuery("select count(*) from fruit_data")[0]).intValue();
    }
}