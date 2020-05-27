package com.athen.system.web.controller.system;

import com.athen.core.Constant;
import com.athen.core.vo.DataTable;
import com.athen.dubbo.annotation.FReference;
import com.athen.system.annotation.NotNeedPermission;
import com.athen.system.api.model.SysUser;
import com.athen.system.api.service.SystemService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * 用户管理
 */
@Controller
@RequestMapping(value = "/system/user")
public class UserController {

    @FReference(version = Constant.DUBBO_VERSION,group = "athenSystem")
    private SystemService systemService;

    @RequestMapping(method = RequestMethod.GET)
    public String list() {
        return "system/userList";
    }

    @RequestMapping(method = RequestMethod.POST)
    public DataTable<SysUser> list(DataTable<SysUser> dt, SysUser user) {
        return systemService.findPageUserByCondition(dt, user);
    }

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String createForm(Model model) {
        model.addAttribute("action", "create");
        model.addAttribute("u", new SysUser());
        model.addAttribute("roles", systemService.findAll());
        return "system/userForm";
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String create(@Validated SysUser user, Long[] ids, RedirectAttributes rs) {
        systemService.saveUser(user, ids);
        rs.addFlashAttribute("message", "新建用户成功!");
        return "redirect:/system/user";
    }

    @RequestMapping(value = "/update/{id}", method = RequestMethod.GET)
    public String updateForm(@PathVariable Long id, Model model) {
        model.addAttribute("action", "update");
        model.addAttribute("u", systemService.findUserById(id));
        model.addAttribute("roles", systemService.findAll());
        return "system/userForm";
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String update(@Validated SysUser user, Long[] ids, RedirectAttributes rs) {
        systemService.saveUser(user, ids);
        rs.addFlashAttribute("message", "更新用户信息成功!");
        return "redirect:/system/user";
    }

    @RequestMapping(value = "/check", method = RequestMethod.GET)
    @ResponseBody
    @NotNeedPermission
    public Boolean checkUser(String loginName) {
        return systemService.checkUserByLoginName(loginName);
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String deleteUser(@PathVariable Long id, RedirectAttributes rs) {
        systemService.deleteUserById(id);
        rs.addFlashAttribute("message", "用户删除成功");
        return "redirect:/system/user";
    }
}
