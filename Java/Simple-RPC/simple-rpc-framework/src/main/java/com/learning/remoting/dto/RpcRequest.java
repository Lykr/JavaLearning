package com.learning.remoting.dto;

import com.learning.properties.RpcServiceProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RpcRequest implements Serializable {
    private static final long serialVersionUID = -5777091485149000633L;
    private String requestId;
    private RpcServiceProperties rpcServiceProperties;
    private String methodName;
    private Object[] parameters;
    private Class<?>[] paramTypes;
}
