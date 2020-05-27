package com.athen.system.web;

import com.athen.core.Constant;
import com.athen.core.json.JsonResult;
import com.athen.core.util.RenderViewResolver;
import com.athen.dubbo.annotation.FReference;
import com.athen.exception.AccountException;
import com.athen.system.annotation.NotNeedLogin;
import com.athen.system.annotation.NotNeedPermission;
import com.athen.system.api.model.Resource;
import com.athen.system.api.model.SysUser;
import com.athen.system.api.service.SystemService;
import com.athen.system.util.SessionUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.HandlerMapping;

import java.util.List;

import static com.athen.core.json.JsonResult.success;

@Slf4j
@Controller
public class IndexController {

    @FReference(version = Constant.DUBBO_VERSION, group = "athenSystem")
    private SystemService systemService;

    private static final String INDEX = "redirect:" + "/main";

    @NotNeedLogin
    @RequestMapping("/ee")
    public String ee() {
        return "example";
    }

    @RequestMapping(value = "/")
    public String index() {
        return INDEX;
    }

    @RequestMapping(value = "/main")
    public String main() {
        return "main";
    }

    @NotNeedLogin
    @RequestMapping(value = "/login")
    public String login() {
        return SessionUtil.isLogin() ? INDEX : "login";
    }

    @NotNeedLogin
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(String username, String password, Model model) {
        try {
            SysUser user = systemService.login(username, password);
            List<Resource> resources = systemService.caseResource(user.getId());
            SessionUtil.signIn(user, resources);
            // 验证成功, 跳到首页
            return INDEX;
        } catch (AccountException e) {
            model.addAttribute("username", username);
            model.addAttribute("error", e.getMessage());
            return "login";
        } catch (RuntimeException e) {
            model.addAttribute("username", username);
            model.addAttribute("error", "登陆异常,请稍后再尝试!");
            return "login";
        }
    }

    @RequestMapping(value = "/logout")
    @NotNeedPermission
    public String logout() {
        SessionUtil.signOut();
        return "login";
    }

    @RequestMapping(value = "/update-info")
    public String updateInfo(Model model) {
        model.addAttribute("u", SessionUtil.getUser());
        return "updateForm";
    }

    @RequestMapping(value = "/update-info", method = RequestMethod.POST)
    public JsonResult update(@Validated SysUser user) {
        systemService.updateUser(user);
        return success("修改成功!");
    }

    @NotNeedLogin
    @RequestMapping("/403")
    public String error() {
        return "403";
    }

    @NotNeedLogin
    @RequestMapping("/500")
    public String errorPage500() {
        return "500";
    }


    @NotNeedLogin
    @RequestMapping(value = "/version", method = {RequestMethod.HEAD, RequestMethod.GET})
    public JsonResult changeVersion() {
        return success("版本号更改为: " + RenderViewResolver.changeVersion());
    }

//    @NotNeedLogin
//    @RequestMapping(value = "/code", method = {RequestMethod.HEAD, RequestMethod.GET})
//    public void code(HttpServletResponse response, String width, String height,
//                     String count, String style) throws IOException {
//        String code = SecurityCodeUtil.generateCode(count, style);
//        BufferedImage image = SecurityCodeUtil.generateCode(width, height, code);
//
//        SessionUtil.putImageCode(code);
//        // 向页面渲染图像
//        response.setDateHeader("Expires", 0);
//        response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");
//        response.addHeader("Cache-Control", "post-check=0, pre-check=0");
//        response.setHeader("Pragma", "no-cache");
//        response.setContentType("image/jpeg");
//        ImageIO.write(image, "jpeg", response.getOutputStream());
//    }

}
