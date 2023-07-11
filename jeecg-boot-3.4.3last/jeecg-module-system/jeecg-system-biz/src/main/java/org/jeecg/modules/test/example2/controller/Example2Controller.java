package org.jeecg.modules.test.example2.controller;

import java.util.Arrays;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.jeecg.common.api.vo.Result;
import org.jeecg.common.system.query.QueryGenerator;
import org.jeecg.modules.test.example2.entity.Example2;
import org.jeecg.modules.test.example2.service.IExample2Service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;

import org.jeecg.common.system.base.controller.JeecgController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.jeecg.common.aspect.annotation.AutoLog;

 /**
 * @Description: 我的案例
 * @Author: jeecg-boot
 * @Date:   2023-07-09
 * @Version: V1.0
 */
@Api(tags="我的案例")
@RestController
@RequestMapping("/example2/example2")
@Slf4j
public class Example2Controller extends JeecgController<Example2, IExample2Service> {
	@Autowired
	private IExample2Service example2Service;

	/**
	 * 分页列表查询
	 *
	 * @param example2
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	//@AutoLog(value = "我的案例-分页列表查询")
	@ApiOperation(value="我的案例-分页列表查询", notes="我的案例-分页列表查询")
	@GetMapping(value = "/list")
	public Result<IPage<Example2>> queryPageList(Example2 example2,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<Example2> queryWrapper = QueryGenerator.initQueryWrapper(example2, req.getParameterMap());
		Page<Example2> page = new Page<Example2>(pageNo, pageSize);
		IPage<Example2> pageList = example2Service.page(page, queryWrapper);
		return Result.OK(pageList);
	}

	/**
	 *   添加
	 *
	 * @param example2
	 * @return
	 */
	@AutoLog(value = "我的案例-添加")
	@ApiOperation(value="我的案例-添加", notes="我的案例-添加")
	//@RequiresPermissions("org.jeecg.modules.test:example:add")
	@PostMapping(value = "/add")
	public Result<String> add(@RequestBody Example2 example2) {
		example2Service.save(example2);
		return Result.OK("添加成功！");
	}

	/**
	 *  编辑
	 *
	 * @param example2
	 * @return
	 */
	@AutoLog(value = "我的案例-编辑")
	@ApiOperation(value="我的案例-编辑", notes="我的案例-编辑")
	//@RequiresPermissions("org.jeecg.modules.test:example:edit")
	@RequestMapping(value = "/edit", method = {RequestMethod.PUT,RequestMethod.POST})
	public Result<String> edit(@RequestBody Example2 example2) {
		example2Service.updateById(example2);
		return Result.OK("编辑成功!");
	}

	/**
	 *   通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "我的案例-通过id删除")
	@ApiOperation(value="我的案例-通过id删除", notes="我的案例-通过id删除")
	//@RequiresPermissions("org.jeecg.modules.test:example:delete")
	@DeleteMapping(value = "/delete")
	public Result<String> delete(@RequestParam(name="id",required=true) String id) {
		example2Service.removeById(id);
		return Result.OK("删除成功!");
	}

	/**
	 *  批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "我的案例-批量删除")
	@ApiOperation(value="我的案例-批量删除", notes="我的案例-批量删除")
	//@RequiresPermissions("org.jeecg.modules.test:example:deleteBatch")
	@DeleteMapping(value = "/deleteBatch")
	public Result<String> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.example2Service.removeByIds(Arrays.asList(ids.split(",")));
		return Result.OK("批量删除成功!");
	}

	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	//@AutoLog(value = "我的案例-通过id查询")
	@ApiOperation(value="我的案例-通过id查询", notes="我的案例-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<Example2> queryById(@RequestParam(name="id",required=true) String id) {
		Example2 example2 = example2Service.getById(id);
		if(example2==null) {
			return Result.error("未找到对应数据");
		}
		return Result.OK(example2);
	}

    /**
    * 导出excel
    *
    * @param request
    * @param example2
    */
    //@RequiresPermissions("org.jeecg.modules.test:example:exportXls")
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, Example2 example2) {
        return super.exportXls(request, example2, Example2.class, "我的案例");
    }

    /**
      * 通过excel导入数据
    *
    * @param request
    * @param response
    * @return
    */
    //@RequiresPermissions("example:importExcel")
    @RequestMapping(value = "/importExcel", method = RequestMethod.POST)
    public Result<?> importExcel(HttpServletRequest request, HttpServletResponse response) {
        return super.importExcel(request, response, Example2.class);
    }

}
