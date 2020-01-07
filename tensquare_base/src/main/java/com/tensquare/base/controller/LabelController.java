package com.tensquare.base.controller;

import com.tensquare.base.pojo.Label;
import com.tensquare.base.service.LabelService;
import entity.PageResult;
import entity.Result;
import entity.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;

import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin //跨域
@RequestMapping("/label")
public class LabelController {

    @Autowired
    private LabelService labelService;

    @Autowired
    private HttpServletRequest request;
    /**
     * 查询全部
     * @return
     */
    @GetMapping()
    public Result findAll(){
        //获取头信息
        String authorization = request.getHeader("Authorization");
        System.out.println(authorization);
        return new Result(true, StatusCode.OK,"成功",labelService.findAll());
    }

    /**
     * 根据id查询
     * @param labelId
     * @return
     */
    @GetMapping("/{labelId}")
    public Result findAllById(@PathVariable("labelId") String labelId){
        System.out.println("9001");
        return new Result(true, StatusCode.OK,"成功",labelService.findById(labelId));
    }

    /**
     * 添加
     * @param label
     * @return
     */
    @PostMapping()
    public Result save(@RequestBody  Label label){
        labelService.save(label);
        return new Result(true, StatusCode.OK,"添加成功");
    }


    @PutMapping("/{labelId}")
    public Result update(@PathVariable("labelId") String labelId ,Label label){
        System.out.println("进入controller");
        label.setId(labelId);
        labelService.save(label);
        return new  Result(true, StatusCode.OK,"修改成功");
    }

    @DeleteMapping("/{labelId}")
    public Result delete(@PathVariable("labelId")String labelId){
        labelService.deleteById(labelId);
        return new Result(true,StatusCode.OK,"删除成功");
    }

    /**
     * 模糊查询name
     * @param label
     * @return
     */
    @PostMapping("/search")
    public Result findSearch(@RequestBody Label label){
        List<Label> list=labelService.findSearch(label);
        return new Result(true,StatusCode.OK,"查询成功",list);
    }

    /**
     * 分页查询
     * @param label
     * @param page
     * @param size
     * @return
     */
    @PostMapping("/search/{page}/{size}")
    public Result pageQuery(@RequestBody Label label, @PathVariable int page, @PathVariable int size){
        Page<Label> pageData=labelService.pageQuery(label,page,size);
        return new Result(true,StatusCode.OK,"查询成功",new PageResult<Label>(pageData.getTotalElements(),pageData.getContent()));
    }







}
