package com.athen.system.web.controller.system;

import com.athen.core.Constant;
import com.athen.core.json.JsonResult;
import com.athen.core.json.JsonUtil;
import com.athen.dubbo.annotation.FReference;
import com.athen.system.api.model.SysResource;
import com.athen.system.api.service.SystemService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import static com.athen.core.json.JsonResult.success;

/**
 * 菜单资源管理
 */
@Controller
@RequestMapping(value = "/system/resource")
public class ResourceController {

    @FReference(version = Constant.DUBBO_VERSION,group = "athenSystem")
    private SystemService systemService;

    /**
     * 返回资源列表的内容到页面
     */
    @RequestMapping(method = RequestMethod.GET)
    public String list(Model model) {
        model.addAttribute("resourceTree", JsonUtil.toJson(systemService.getResourceTree()));
        model.addAttribute("resourceTypes", SysResource.ResourceType.values());
        model.addAttribute("methodTypes", SysResource.MethodType.values());
        return "system/resourceTree";
    }

    /**
     * 读取资源
     */
    @RequestMapping(value = "/read/{id}", method = RequestMethod.GET)
    @ResponseBody
    public SysResource read(@PathVariable Long id) {
        return systemService.findResById(id);
    }

    /**
     * 保存资源信息
     *
     * @param res
     * @param rs
     * @return
     */
    @RequestMapping(value = "save", method = RequestMethod.POST)
    public String create(@Validated SysResource res, RedirectAttributes rs) {
        systemService.saveResource(res);
        rs.addFlashAttribute("message", "保存资源成功");
        return "redirect:/system/resource";
    }

    /**
     * 删除资源
     *
     * @param id 资源ID
     */
    @RequestMapping(value = "delete/{id}", method = RequestMethod.DELETE)
    public JsonResult delete(@PathVariable("id") Long id) {
        systemService.deleteResource(id);
        return success("删除资源成功");
    }
}
