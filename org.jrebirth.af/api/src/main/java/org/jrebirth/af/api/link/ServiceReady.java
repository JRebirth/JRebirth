/**
 * Get more info at : www.jrebirth.org .
 * Copyright JRebirth.org © 2011-2016
 * Contact : sebastien.bordes@jrebirth.org
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.jrebirth.af.api.link;

import java.util.List;

import org.jrebirth.af.api.key.UniqueKey;
import org.jrebirth.af.api.service.Service;
import org.jrebirth.af.api.wave.Wave;
import org.jrebirth.af.api.wave.WaveBean;
import org.jrebirth.af.api.wave.contract.WaveData;
import org.jrebirth.af.api.wave.contract.WaveType;

/**
 *
 * The interface <strong>ServiceReady</strong>.
 *
 * @author Sébastien Bordes
 */
public interface ServiceReady extends CommandReady {

    /**
     * Return the service singleton or part of multiton.
     *
     * @param clazz the service class to find
     * @param keyPart the unique key (in option)
     *
     * @param <S> a sub class of service
     *
     * @return a service instance
     */
    <S extends Service> S getService(final Class<S> clazz, final Object... keyPart);

    /**
     * Return the service singleton or part of multiton according to {@link UniqueKey}.
     *
     * @param serviceKey the key that describe the searched {@link Service} component
     *
     * @param <S> a sub class of {@link Service}
     *
     * @return a service instance
     */
    <S extends Service> S getService(final UniqueKey<S> serviceKey);

    /**
     * Return the list of service singleton or part of multiton.
     *
     * @param clazz the service class to find
     * @param keyPart the unique key (in option)
     *
     * @param <S> a sub class of service
     *
     * @return a list of service instance
     */
    <S extends Service> List<S> getServices(final Class<S> clazz, final Object... keyPart);

    /**
     * Return the list of service singleton or part of multiton according to {@link UniqueKey}.
     *
     * @param serviceKey the key that describe the searched {@link Service} component
     *
     * @param <S> a sub class of {@link Service}
     *
     * @return a list of service instance
     */
    <S extends Service> List<S> getServices(final UniqueKey<S> serviceKey);

    /**
     * Send a wave used to return data from a service.
     *
     * The service will be called from JRebirthThread and could execute itself from another thread.
     *
     * @param serviceClass the service called
     * @param waveType the type of the wave
     * @param waveBean the WaveBean that holds all required wave data
     *
     * @param <WB> the type of the wave bean to used
     *
     * @return the wave created and sent to JIT, be careful when you use a strong reference it can hold a lot of objects
     */
    <WB extends WaveBean> Wave returnData(final Class<? extends Service> serviceClass, final WaveType waveType, final WB waveBean);

    /**
     * Send a wave used to return data from a service.
     *
     * The service will be called from JRebirthThread and could execute itself from another thread.
     *
     * @param serviceClass the service called
     * @param waveType the type of the wave
     * @param data the data to transport
     *
     * @return the wave created and sent to JIT, be careful when you use a strong reference it can hold a lot of objects
     */
    Wave returnData(final Class<? extends Service> serviceClass, final WaveType waveType, final WaveData<?>... data);

}
