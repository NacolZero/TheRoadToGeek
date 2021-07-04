package io.nacol.rpc.api;

public interface Filter {

    boolean filter(RpcRequest request);

    // Filter next();

}
