package test;

import com.lj.kernel.gate.AxGate;
import com.lj.kernel.gate.command.Commands;
import org.ogcs.app.AppContext;
import org.ogcs.ax.component.AxCoInfo;
import org.ogcs.ax.component.Modules;
import org.ogcs.ax.component.SpringContext;
import org.ogcs.ax.component.inner.AxInnerServer;
import org.ogcs.ax.component.manager.AxInnerCoManager;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 网关服务器1
 */
public class Gate1 {

    public static void main(String[] args) {
        ClassPathXmlApplicationContext cpxac = new ClassPathXmlApplicationContext("classpath:spring/spring.xml");
        cpxac.registerShutdownHook();

        String gateId = "Gate1";
        long local = 101L;

        AxInnerCoManager axCoManager = (AxInnerCoManager) AppContext.getBean(SpringContext.MANAGER_AX_COMPONENT);
        // 注册远程节点  - module: 1[chat]
        AxDatas.map.forEach((module, list) -> {
            for (AxCoInfo axCoInfo : list) {
                axCoManager.add(module, local, axCoInfo);
            }
        });

        // 启动内部服务器
        AxInnerServer inner = new AxInnerServer(String.valueOf(local), 8001);
        inner.start();
        // 启动外部服务器
        AxGate gate = new AxGate(gateId, 10001);
        gate.start();
    }
}
