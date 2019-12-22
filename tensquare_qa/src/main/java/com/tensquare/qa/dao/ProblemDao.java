package com.tensquare.qa.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.tensquare.qa.pojo.Problem;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * 数据访问接口
 * @author Administrator
 *
 */
public interface ProblemDao extends JpaRepository<Problem,String>,JpaSpecificationExecutor<Problem>{

    /**
     * 最新回复
     * @param labelid
     * @param pageable
     * @return
     */
    @Query(value = "SELECT * from tb_problem INNER JOIN tb_pl ON tb_problem.id = tb_pl.problemid where labelid=? ORDER BY replytime DESC",nativeQuery = true)
    public Page<Problem> newlist(String labelid, Pageable pageable);
    //最热回复
    @Query(value = "SELECT * from tb_problem INNER JOIN tb_pl ON tb_problem.id = tb_pl.problemid where labelid=? ORDER BY reply DESC",nativeQuery = true)
    public Page<Problem> hotlist(String labelid, Pageable pageable);
    //等待回复
    @Query(value = "SELECT * from tb_problem INNER JOIN tb_pl ON tb_problem.id = tb_pl.problemid where labelid=? and reply=0",nativeQuery = true)
    public Page<Problem> waitlist(String labelid, Pageable pageable);


}
