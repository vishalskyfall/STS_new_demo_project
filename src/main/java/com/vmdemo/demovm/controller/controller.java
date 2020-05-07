package com.vmdemo.demovm.controller;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.vmdemo.demovm.model.uservo;
import com.vmdemo.demovm.model.validaterVO;
import com.vmdemo.demovm.service.UserService;

@RestController
public class controller {

	String regex ="^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
	String number = "^[789]\\d{9}$";
	@Autowired
	UserService u;

	@RequestMapping(value = "/")
	public ModelAndView start(Model m) {
		m.addAttribute("val", new validaterVO());
		return new ModelAndView("main.html", "uservo", new uservo());

	}
	@RequestMapping(value = "/index")
	public ModelAndView start2(Model m) {
		m.addAttribute("val", new validaterVO());
		return new ModelAndView("index.html", "uservo", new uservo());

	}

	@RequestMapping(value = "/insertData")
	public ModelAndView insert(@ModelAttribute uservo v, Model m) {
		validaterVO v1 = new validaterVO();
		if (!v.getEmail().matches(regex)) {
			v1.setEmailerror(true);
		}
		if (!v.getNumber().matches(number)) {
			v1.setNumbererror(true);
		}
		if (v1.isEmailerror() || v1.isNumbererror()) {
			m.addAttribute("val", v1);
			return new ModelAndView("index.html", "uservo", v);
		} else {
			String msg="Updated SuccessFully";
			if(v.getId()==0)
			{
				msg=" Inserted SuccessFully";
			}
			u.createUser(v);
			/*
			 * List l = u.getUser(); m.addAttribute("msg", "Inserted Successfully");
			 */
			return new ModelAndView("redirect:/showList", "msg", msg);
		}

	}
	@RequestMapping(value="/showList")
	public ModelAndView show( Model m,HttpServletRequest r)
	{
		List l = u.getUser();
		String msg=r.getParameter("msg");
		m.addAttribute("msg", msg);
		return new ModelAndView("searchall.html", "list", l);
	}

	@RequestMapping(value = "/edit/{id}")
	public ModelAndView edit(@ModelAttribute uservo v, @PathVariable("id") int id,Model m) {
		Optional<uservo> o = u.findById(id);
		uservo v1 = o.get();
		m.addAttribute("val",new validaterVO());
		return new ModelAndView("edit.html", "uservo", v1);
	}

	/*
	 * @RequestMapping(value = "/updateData/{id}") public ModelAndView
	 * update(@ModelAttribute uservo v, @PathVariable("id") int id,Model m) {
	 * validaterVO v1 = new validaterVO(); if (!v.getEmail().matches(regex)) {
	 * v1.setEmailerror(true); } if (!v.getNumber().matches(number)) {
	 * v1.setNumbererror(true); } if (v1.isEmailerror() || v1.isNumbererror()) {
	 * m.addAttribute("val", v1); return new ModelAndView("index.html", "uservo",
	 * v); } else { v.setId(id); u.createUser(v); List l = u.getUser();
	 * m.addAttribute("msg", "Updated Successfully"); return new
	 * ModelAndView("searchall.html", "list", l); } }
	 */

	@RequestMapping(value = "/delete/{id}")
	public ModelAndView delete(@ModelAttribute uservo v, @PathVariable("id") int id,Model m) {
		v.setId(id);
		u.deleteUserById(v);
		/*
		 * List l = u.getUser(); m.addAttribute("msg", "Deleted Successfully");
		 */
		return new ModelAndView("redirect:/showList", "msg", "Deleted SuccessFully");	}

	@RequestMapping(value = "/addnew")
	public ModelAndView addnew(Model m ) {
		m.addAttribute("m", new uservo());
		return new ModelAndView("index.html", "uservo", new uservo());
	}

}
