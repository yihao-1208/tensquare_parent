package com.tensquare.base.service;

import com.tensquare.base.dao.LabelDao;
import com.tensquare.base.pojo.Label;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import util.IdWorker;
import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class LabelService {
    @Autowired
    private LabelDao labelDao;
    @Autowired
    private IdWorker idWorker;

    /**
     * 查询全部标题
     * @return
     */
    public List<Label> findAll(){
        return labelDao.findAll();
    }

    /**
     * 通过id查询标题
     * @param id
     * @return
     */
    public Label findById(String id){
        return labelDao.findById(id).get();
    }

    /**
     * 添加标题
     * @param label
     */
    public void save(Label label){
        label.setId(idWorker.nextId()+"");
        labelDao.save(label);
    }

    /**
     * 更改
     * @param label
     */
    public void update(Label label){
        labelDao.save(label);
    }

    /**
     * 删除id
     * @param id
     */
    public void deleteById(String id){
        labelDao.deleteById(id);
    }


}
