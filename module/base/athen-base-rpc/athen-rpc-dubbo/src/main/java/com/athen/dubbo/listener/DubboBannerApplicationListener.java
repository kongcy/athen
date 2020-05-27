package com.athen.dubbo.listener;

import com.alibaba.dubbo.common.Version;
import com.alibaba.dubbo.qos.server.DubboLogo;
import com.athen.dubbo.model.Constants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.Banner;
import org.springframework.boot.context.event.ApplicationEnvironmentPreparedEvent;
import org.springframework.boot.context.logging.LoggingApplicationListener;
import org.springframework.context.ApplicationListener;
import org.springframework.core.annotation.Order;

/**
 * User: chenying
 * Date: 2019-07-30
 * Time: 16:04
 * since: 1.0.0
 */
@Slf4j
@Order(LoggingApplicationListener.DEFAULT_ORDER)
public class DubboBannerApplicationListener implements ApplicationListener<ApplicationEnvironmentPreparedEvent> {

    private static Banner.Mode BANNER_MODE = Banner.Mode.CONSOLE;

    public static void setBANNER_MODE(Banner.Mode bANNER_MODE) {
        BANNER_MODE = bANNER_MODE;
    }

    @Override
    public void onApplicationEvent(ApplicationEnvironmentPreparedEvent event) {
        if (BANNER_MODE == Banner.Mode.OFF) {
            return;
        }
        String bannerText = this.buildBannerText();
        if (BANNER_MODE == Banner.Mode.CONSOLE) {
            System.out.print(bannerText);
        } else if (BANNER_MODE == Banner.Mode.LOG) {
            log.info(bannerText);
        }
    }

    private String buildBannerText() {
        StringBuilder bannerTextBuilder = new StringBuilder();
        bannerTextBuilder.append(Constants.LINE_SEPARATOR).append(DubboLogo.dubbo)
                .append(" :: Dubbo ::        (v").append(Version.getVersion()).append(")")
                .append(Constants.LINE_SEPARATOR);
        return bannerTextBuilder.toString();
    }
}
