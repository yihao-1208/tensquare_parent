package com.tensquare.recruit.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.tensquare.recruit.pojo.Recruit;

import java.util.List;

/**
 * 数据访问接口
 * @author Administrator
 *
 */
public interface RecruitDao extends JpaRepository<Recruit,String>,JpaSpecificationExecutor<Recruit>{

    /**
     * 按状态==查最新的六个岗位信息
     * @param state
     * @return
     */
    List<Recruit> findTop6ByStateOrderByCreatetimeDesc(String state);

    /**
     * 按状态!=查最新的六个岗位信息
     * @param state
     * @return
     */
    List<Recruit> findTop6ByStateNotOrderByCreatetimeDesc(String state);


}
