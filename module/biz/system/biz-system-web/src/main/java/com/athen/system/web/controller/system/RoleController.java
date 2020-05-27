package com.athen.system.web.controller.system;

import com.athen.core.Constant;
import com.athen.core.json.JsonResult;
import com.athen.core.json.JsonUtil;
import com.athen.core.util.A;
import com.athen.core.util.U;
import com.athen.core.vo.DataTable;
import com.athen.dubbo.annotation.FReference;
import com.athen.system.annotation.NotNeedPermission;
import com.athen.system.api.model.SysResource;
import com.athen.system.api.model.SysRole;
import com.athen.system.api.model.SysUser;
import com.athen.system.api.service.SystemService;
import com.google.common.collect.Lists;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

import static com.athen.core.json.JsonResult.success;

/**
 * 角色管理
 */
@Controller
@RequestMapping(value = "/system/role")
public class RoleController {
    @FReference(version = Constant.DUBBO_VERSION,group = "athenSystem")
    private SystemService systemService;

    @RequestMapping(method = RequestMethod.GET)
    public String list() {
        return "system/roleList";
    }

    @RequestMapping(method = RequestMethod.POST)
    public DataTable<SysRole> list(DataTable<SysRole> dt, SysRole role) {
        return systemService.finsPageRoleByCondition(dt, role);
    }

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String createForm(Model model) {
        model.addAttribute("action", "create");
        model.addAttribute("role", new SysRole());
        model.addAttribute("resourceTree", JsonUtil.toJson(systemService.getResourceTree()));
        return "system/roleForm";
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String create(@Validated SysRole role, String ids, RedirectAttributes rs) {
        List<Long> list = Lists.newArrayList();
        if (U.isNotBlank(ids)) {
            for (String str : ids.split(",")) {
                list.add(Long.valueOf(str));
            }
            list = (List<Long>) A.removeDuplicate(list);
        }
        systemService.saveRole(role, list);
        rs.addFlashAttribute("message", "新建角色成功!");
        return "redirect:/system/role";
    }

    @RequestMapping(value = "/update/{id}", method = RequestMethod.GET)
    public String updateForm(@PathVariable Long id, Model model) {
        model.addAttribute("action", "update");
        SysRole role = systemService.findRoleById(id);
        model.addAttribute("role", role);
        model.addAttribute("resourceTree", JsonUtil.toJson(systemService.GetResourceTreeHasSelect(role)));
        return "system/roleForm";
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String update(@Validated SysRole role, String ids, RedirectAttributes rs) {
        List<Long> list = Lists.newArrayList();
        if (U.isNotBlank(ids)) {
            for (String str : ids.split(",")) {
                list.add(Long.valueOf(str));
            }
            list = (List<Long>) A.removeDuplicate(list);
        }
        systemService.saveRole(role, list);
        rs.addFlashAttribute("message", "更新角色成功!");
        return "redirect:/system/role";
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public JsonResult delete(@PathVariable Long id) {
        systemService.deleteRoleById(id);
        return success("角色删除成功!");
    }


    @RequestMapping(value = "/query-user-list")
    @NotNeedPermission
    public DataTable<SysUser> findUserByRole(DataTable<SysUser> dt, Long roleId) {
        return systemService.findPageUserByRoleId(dt, roleId);
    }

    @RequestMapping(value = "/res", method = RequestMethod.GET)
    public String PageRes(Long id, Model model) {
        model.addAttribute("role", id);
        return "system/resList";
    }

    @RequestMapping(value = "/res", method = RequestMethod.POST)
    public DataTable<SysResource> PageRes(DataTable<SysResource> dt, SysResource res) {
        return systemService.findPageResByCondition(dt, res);
    }
}
