package io.nacol.rpc.api;

import java.util.List;

public interface Router {

    List<String> route(List<String> urls);
}
