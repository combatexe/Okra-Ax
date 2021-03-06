package org.okraAx.common;

import org.ogcs.app.Procedure;

/**
 * @author TinyZ
 * @version 2017.03.12
 */
public interface LogicPublicService {

    @Procedure
    void onLogin(String openId);

    @Procedure
    void onSyncTime();

    @Procedure
    void onShowChannelList();

    @Procedure
    void onEnterChannel();
}
