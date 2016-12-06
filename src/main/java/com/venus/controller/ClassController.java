package com.venus.controller;

import java.util.UUID;

import javax.annotation.Resource;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.venus.frame.utils.ResultBean;
import com.venus.service.ClassService;

@RestController
@EnableAutoConfiguration
@RequestMapping("api/class")
public class ClassController {
	private ResultBean result;

	@Resource
	private ClassService classService;

	@RequestMapping(value = "", method = RequestMethod.GET)
	@ResponseBody
	public Object get(@RequestParam String start, @RequestParam String end, @RequestParam String id) {
		try {
			result = ResultBean.getSuccess("", classService.get(start, end, id));
		} catch (Exception e) {
			result = ResultBean.getFail(e.getMessage(), null);
		}
		return result;
	}

	@RequestMapping(value = "all", method = RequestMethod.GET)
	@ResponseBody
	public Object getAll(@RequestParam String start, @RequestParam String end) {
		try {
			result = ResultBean.getSuccess("", classService.getAll(start, end));
		} catch (Exception e) {
			result = ResultBean.getFail(e.getMessage(), null);
		}
		return result;
	}

	@RequestMapping(value = "one", method = RequestMethod.GET)
	@ResponseBody
	public Object getById(@RequestParam String id) {
		try {
			result = ResultBean.getSuccess("", classService.getById(id));
		} catch (Exception e) {
			result = ResultBean.getFail(e.getMessage(), null);
		}
		return result;
	}

	@RequestMapping(value = "", method = RequestMethod.POST)
	@ResponseBody
	public Object add(@RequestBody JSONObject addInfo) {
		try {
			String id = UUID.randomUUID().toString();
			if (classService.add(id, addInfo)) {
				result = ResultBean.getSuccess("", id);
			} else {
				result = ResultBean.getFail("", null);
			}
		} catch (Exception e) {
			result = ResultBean.getFail(e.getMessage(), null);
		}
		return result;
	}

	@RequestMapping(value = "{id}", method = RequestMethod.DELETE)
	@ResponseBody
	public Object del(@PathVariable String id) {
		try {
			classService.del(id);
			result = ResultBean.getSuccess("", null);
		} catch (Exception e) {
			result = ResultBean.getFail(e.getMessage(), null);
		}
		return result;
	}
}
