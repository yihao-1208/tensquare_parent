package com.tensquare.base.controller;


import com.sun.org.apache.bcel.internal.generic.LADD;
import com.tensquare.base.pojo.Label;
import com.tensquare.base.service.LabelService;
import entity.Result;
import entity.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin //跨域
@RequestMapping("/label")
public class LabelController {

    @Autowired
    private LabelService labelService;
    /**
     * 查询全部
     * @return
     */
    @GetMapping()
    public Result findAll(){
        return new Result(true, StatusCode.OK,"成功",labelService.findAll());
    }

    /**
     * 根据id查询
     * @param labelId
     * @return
     */
    @GetMapping("/{labelId}")
    public Result findAllById(@PathVariable("labelId") String labelId){

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


}
