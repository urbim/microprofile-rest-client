/*
 * Copyright 2018 Contributors to the Eclipse Foundation
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or
 * implied.
 *
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.eclipse.microprofile.rest.client.tck.providers;

import javax.ws.rs.client.ClientRequestContext;
import javax.ws.rs.client.ClientResponseContext;
import javax.ws.rs.client.ClientResponseFilter;
import java.io.IOException;

public class TLClientResponseFilter implements ClientResponseFilter{

    private int threadLocalIntDuringResponse;

    @Override
    public void filter(ClientRequestContext reqCtx, ClientResponseContext resCtx) throws IOException {
        threadLocalIntDuringResponse = TLAsyncInvocationInterceptorFactory.getTlInt();
        resCtx.getHeaders().putSingle("Sent-URI", reqCtx.getUri().toString());
    }

    public int getThreadLocalIntDuringResponse() {
        return threadLocalIntDuringResponse;
    }
}
