package org.okraAx.internal.inner;

import com.google.protobuf.Message;
import io.netty.channel.Channel;
import org.ogcs.app.NetSession;
import org.ogcs.app.ProxySingleCallback;
import org.okraAx.utilities.AxAnyUtil;
import org.okraAx.v3.Rpc;
import org.okraAx.v3.RpcError;

/**
 * @author TinyZ
 * @date 2017-01-26.
 */
public class IrSession extends NetSession implements ProxySingleCallback<String> {

    public IrSession(Channel channel) {
        super(channel);
    }

    public void push(int id, int api) {
        writeAndFlush(Rpc.newBuilder()
                .setId(id)
                .setApi(api)
                .build());
    }

    public void push(int id, int api, Message message) {
        writeAndFlush(Rpc.newBuilder()
                .setId(id)
                .setApi(api)
                .setData(AxAnyUtil.pack(message))
                .build());
    }

    public void error(int id, int api, int state, String msg) {
        push(id, api,
                RpcError
                        .newBuilder()
                        .setState(state)
                        .setMsg(msg)
                        .build()
        );
    }

    public void error(int id, int api, int state) {
        push(id, api,
                RpcError
                        .newBuilder()
                        .setState(state)
                        .build()
        );
    }

    @Override
    public String invoker() {
        return null;
    }
}
