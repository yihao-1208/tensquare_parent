package com.tensquare.base.service;

import com.tensquare.base.dao.LabelDao;
import com.tensquare.base.pojo.Label;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import util.IdWorker;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


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


    /**
     * 条件查询
     * @param label
     * @return
     */
    public List<Label> findSearch(Label label) {
        return labelDao.findAll(new Specification<Label>(){
            /**
             *
             * @param root  根对象 也就是把条件封装到那个对象中，where 类名=label.getid？
             * @param query   分装的都是关键字 比如 order by
             * @param cb   用来封装条件对象的 如果直接返回null 表示不需要任何条件
             * @return
             * like(root.get("labelname").as(String.class),"%"+label.getLabelname()+"%");
             * 模糊查询       ↑属性名        ↑属性类型          ↑条件 like必须自己添加%
             * cb.and(labelname);
             *      ↑ 数组类型 返回多个Predicate对象
             */
            @Override
            public Predicate toPredicate(Root<Label> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                //new一个集合 来存放所有的条件
                List<Predicate> list=new ArrayList<>();
                if(label.getLabelname()!=null && !"".equals(label.getLabelname())){
                    Predicate labelname = cb.like(root.get("labelname").as(String.class), "%" + label.getLabelname() + "%");
                    list.add(labelname);
                }
                if(label.getState()!=null && !"".equals(label.getState())){
                    Predicate labelname = cb.equal(root.get("state").as(String.class), label.getState());
                    list.add(labelname);
                }
                //new一个数组作为最终返回值的条件
                Predicate[] predicate=new Predicate[list.size()];
                //把List转成数组
                predicate=list.toArray(predicate);
                return cb.and(predicate);
            }
        });
    }
    public Page<Label> pageQuery(Label label, int page, int size) {
        //封装分页对象
        Pageable pageable = PageRequest.of(page-1,size);
        return labelDao.findAll(new Specification<Label>(){
            @Override
            public Predicate toPredicate(Root<Label> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> list=new ArrayList<>();
                if(label.getLabelname()!=null && !"".equals(label.getLabelname())){
                    Predicate labelname = cb.like(root.get("labelname").as(String.class), "%" + label.getLabelname() + "%");
                    list.add(labelname);
                }
                if(label.getState()!=null && !"".equals(label.getState())){
                    Predicate labelname = cb.equal(root.get("state").as(String.class), label.getState());
                    list.add(labelname);
                }
                Predicate[] predicate=new Predicate[list.size()];
                predicate=list.toArray(predicate);
                return cb.and(predicate);
            }
        },pageable);

    }


}
