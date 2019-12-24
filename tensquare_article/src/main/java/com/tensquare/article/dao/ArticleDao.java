package com.tensquare.article.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.tensquare.article.pojo.Article;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

/**
 * 数据访问接口
 * @author Administrator
 *
 */
public interface ArticleDao extends JpaRepository<Article,String>,JpaSpecificationExecutor<Article>{

    /**
     * 点赞数
     * @param id
     */
    @Modifying   //使用springDataJPA 增删改 必须加上Modifying
    @Query(value = "update tb_article set thumbup=thumbup+1 where id=?",nativeQuery=true)
    public void updateThumbup(String id);


    /***
     * 状态审核
     * @param id
     */
    @Modifying
    @Query(value = "update tb_article set state=1 where id=?",nativeQuery = true)
    public void updateState(String id );

}
