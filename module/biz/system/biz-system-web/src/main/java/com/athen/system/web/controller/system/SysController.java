package com.athen.system.web.controller.system;

import com.athen.core.Constant;
import com.athen.dubbo.annotation.FReference;
import com.athen.system.api.model.SystemConfig;
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
 * Created on 2016/8/26.
 */
@Controller
@RequestMapping(value = "/system")
public class SysController {


    //  @Autowired
    //  private SystemConfigService scs;

    @FReference(version = Constant.DUBBO_VERSION,group = "athenSystem")
    private SystemService systemService;

    @RequestMapping(value = "/city")
    public String list() {
        return "system/cityList";
    }


    @RequestMapping(value = "/config")
    public String pageConfig() {
        return "system/configList";
    }

//    @RequestMapping(value = "/config",method = RequestMethod.POST)
//    public DataTable<SystemConfig> PageConfig(DataTable<SystemConfig> dt,SystemConfig config){
//      return  scs.pageConfig(dt,config);
//    }

    @RequestMapping(value = "/config/create")
    public String createForm(Model model) {
        model.addAttribute("action", "create");
        model.addAttribute("config", new SystemConfig());
        return "system/configForm";
    }

    @RequestMapping(value = "/config/create", method = RequestMethod.POST)
    public String create(@Validated SystemConfig config, RedirectAttributes rs) {
        // scs.saveConfig(config);
        rs.addFlashAttribute("message", "系统参数添加成功");
        return "redirect:/system/config";
    }

    @RequestMapping(value = "/config/update/{id}")
    public String updateForm(@PathVariable Long id, Model model) {
        model.addAttribute("action", "update");
        // model.addAttribute("config",scs.findOne(id));
        return "system/configForm";
    }

    @RequestMapping(value = "/config/update", method = RequestMethod.POST)
    public String update(@Validated SystemConfig config, RedirectAttributes rs) {
        //  scs.saveConfig(config);
        rs.addFlashAttribute("message", "系统参数更新成功");
        return "redirect:/system/config";
    }

    @RequestMapping(value = "/config/check")
    @ResponseBody
    public Boolean checkKey(String conKey) {
        // return scs.checkKey(conKey);
        return false;
    }

    @RequestMapping(value = "/config/delete/{id}")
    public String deleteConfig(@PathVariable Long id, RedirectAttributes rs) {
        // scs.delete(id);
        rs.addFlashAttribute("message", "成功删除");
        return "redirect:/system/config";
    }
//    /**
//    *获取用户 角色数量
//    **/
//    @RequestMapping(value = "/list/get-num-list")
//    public JsonResult findUserAndRole(){
//        return JsonResult.success(null,systemService.findUserAndRole());
//    }
//    /**
//     * 获取app下载量，需要做二维码,url统计报表
//     **/
//    @RequestMapping(value = "list/download-total")
//    public JsonResult findDownCount(){
//        return JsonResult.success(null,systemService.findDownCount());
//    }
}
